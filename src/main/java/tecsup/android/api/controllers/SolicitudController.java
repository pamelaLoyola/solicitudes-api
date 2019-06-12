package tecsup.android.api.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tecsup.android.api.models.ResponseMessage;
import tecsup.android.api.models.Solicitud;
import tecsup.android.api.services.SolicitudService;

@RestController
public class SolicitudController {
	
	private static final Logger logger = LoggerFactory.getLogger(SolicitudController.class);
		
		private static final String FILEPATH = "/var/data/solicitudes/images";
		
		@Autowired
		private SolicitudService solicitudService;
		
		@GetMapping("/solicitudes")
		public List<Solicitud> solicitudes(){
			
			 logger.info("callSolicitud");
			 
			 List<Solicitud> solicitudes = solicitudService.listar();
			 
			 return solicitudes;
		}
		
		@GetMapping("/solicitudes/{idUsuario}")
		public List<Solicitud> obtener(@PathVariable String idUsuario) {
			logger.info("call obtener: " + idUsuario);
			
			List<Solicitud> solicitud = solicitudService.findByIdUsuario(idUsuario);
			
			return solicitud;
		}

	
		@GetMapping("solicitudes/images/{filename:.+}")
		public ResponseEntity<Resource> file(@PathVariable String filename) throws Exception{
			
			logger.info("call images: " + filename);
			
			Resource resource = new UrlResource(Paths.get(FILEPATH).resolve(filename).toUri());
			logger.info("Resource: " + resource);
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
					.header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(FILEPATH).resolve(filename)))
					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
					.body(resource);
	
		}
		
		@PostMapping("/solicitudes")	// https://spring.io/guides/gs/uploading-files/
		public ResponseMessage crear(@RequestParam(name="imagen", required=false) MultipartFile imagen,
				@RequestParam(name = "idUsuario", required = false) Integer idUsuario, 
				@RequestParam("correo") String correo, @RequestParam("tipoSolicitud") String tipoSolicitud,
				@RequestParam("motivo") String motivo) {
			logger.info("call crear("+idUsuario + ", " + correo + ", " + tipoSolicitud + "." + motivo + "." + imagen + ")");
			Solicitud solicitud = new Solicitud();
			solicitud.setIdUsuario(idUsuario);
			solicitud.setCorreo(correo);
			solicitud.setTipoSolicitud(tipoSolicitud);
			solicitud.setMotivo(motivo);
			
			if (imagen != null && !imagen.isEmpty()) {
				try {
					
					solicitud.setImagen(imagen.getOriginalFilename());
					
					Files.copy(imagen.getInputStream(), Paths.get(FILEPATH).resolve(imagen.getOriginalFilename()));
					
				}catch(IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			
			solicitudService.crear(solicitud);
			
			return ResponseMessage.success("Registro completo");
		}


}
