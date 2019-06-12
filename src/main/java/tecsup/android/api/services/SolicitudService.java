package tecsup.android.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tecsup.android.api.models.Solicitud;
import tecsup.android.api.repositories.SolicitudRepository;

@Service
public class SolicitudService{
	
	@Autowired
	SolicitudRepository sr;
	
	public List<Solicitud> listar(){
		return sr.listar();
	}
	
	public Solicitud findByIdUsuario(String idUsuario) {
		return sr.findByIdUsuario(idUsuario);
	}
	
	public void crear(Solicitud solicitud) {
		sr.crear(solicitud);
	}

}
