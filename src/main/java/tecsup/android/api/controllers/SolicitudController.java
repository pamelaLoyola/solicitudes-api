package tecsup.android.api.controllers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
		
		@GetMapping("/solicitudes/{id}")
		public Solicitud obtener(@PathVariable Integer id) {
			logger.info("call obtener: " + id);
			
			Solicitud solicitud = solicitudService.findByIdUsuario(id);
			
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

}
