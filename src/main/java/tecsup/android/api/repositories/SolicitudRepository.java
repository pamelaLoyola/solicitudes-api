package tecsup.android.api.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tecsup.android.api.models.Solicitud;

@Repository
public class SolicitudRepository{
	
private static final Logger logger = LoggerFactory.getLogger(SolicitudRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Solicitud> listar(){
		
		String sql = "select * from solicitudes";
		
		List<Solicitud> solicitudes = jdbcTemplate.query(sql, new RowMapper<Solicitud>() {
			
			@Override
			public Solicitud  mapRow(ResultSet rs, int rowNum) throws SQLException {
				Solicitud solicitud = new Solicitud();
				solicitud.setId(rs.getInt("id"));
				solicitud.setIdUsuario(rs.getInt("idUsuario"));
				solicitud.setCorreo(rs.getString("correo"));
				solicitud.setTipoSolicitud(rs.getString("tipoSolicitud"));
				solicitud.setMotivo(rs.getString("motivo"));
				solicitud.setImagen(rs.getString("imagen"));
				solicitud.setEstado(rs.getString("estado"));

				return solicitud;

			}
		});
		
		logger.info("solicitudes" + solicitudes);
		
		return solicitudes;
	}
	
	public List<Solicitud> findByIdUsuario(String idUsuario) {
		
		logger.info("obtener: " + idUsuario);
		
		String sql = "select * from solicitudes where idUsuario = ?";
		
			List<Solicitud> solicitudes = jdbcTemplate.query(sql, new RowMapper<Solicitud>() {
			
			@Override
			public Solicitud  mapRow(ResultSet rs, int rowNum) throws SQLException {
				Solicitud solicitud = new Solicitud();
				solicitud.setId(rs.getInt("id"));
				solicitud.setIdUsuario(rs.getInt("idUsuario"));
				solicitud.setCorreo(rs.getString("correo"));
				solicitud.setTipoSolicitud(rs.getString("tipoSolicitud"));
				solicitud.setMotivo(rs.getString("motivo"));
				solicitud.setImagen(rs.getString("imagen"));
				solicitud.setEstado(rs.getString("estado"));

				return solicitud;
				
			}
			
		}, idUsuario);
		
		logger.info("solicitud buscada" + solicitudes);
		
		return solicitudes;
	}
	
	public void crear(Solicitud solicitud) {
		logger.info("crear " + solicitud);
		
		String sql = "insert into solicitudes (idUsuario, correo, tipoSolicitud, motivo, imagen, estado) values (?, ?, ?,?,?, 1)";
		
		jdbcTemplate.update(sql,solicitud.getIdUsuario(), solicitud.getCorreo(), solicitud.getTipoSolicitud(), solicitud.getMotivo(), solicitud.getImagen());
	}


}
