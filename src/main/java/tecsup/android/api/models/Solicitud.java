package tecsup.android.api.models;

public class Solicitud {
	
	private Integer id;
	private Integer idUsuario;
	private String correo;
	private String tipoSolicitud;
	private String motivo;
	private String imagen;
	private String estado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}	
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", idUsuario=" + idUsuario + ", correo=" + correo + ", tipoSolicitud="
				+ tipoSolicitud + ", motivo=" + motivo + ", imagen=" + imagen + ", estado=" + estado + "]";
	}
	
	
}
