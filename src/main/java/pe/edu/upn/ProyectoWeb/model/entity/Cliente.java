package pe.edu.upn.ProyectoWeb.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "dni", length = 8)
	private Integer dni;
	
	@Column(name = "nombre", length = 60)
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_Nacimiento")	
	private Date fechaNacimiento;
	
	@Column(name = "lugar_Nacimiento", length = 50)
	private String lugarNacimiento;
	
	@Column(name = "sexo", length = 1)
	private char sexo;
	
	@OneToMany(mappedBy="cliente")
	private List<Venta>lista_venta;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tipocliente_codigo")
	private TipoCliente tipocliente;
	
	public Cliente() {
		lista_venta=new ArrayList<>();
	}
	public void addVenta(Venta venta) {
		venta.setCliente(this);
		this.lista_venta.add(venta);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getLugarNacimiento() {
		return lugarNacimiento;
	}
	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public List<Venta> getLista_venta() {
		return lista_venta;
	}
	public void setLista_venta(List<Venta> lista_venta) {
		this.lista_venta = lista_venta;
	}
	public TipoCliente getTipocliente() {
		return tipocliente;
	}
	public void setTipocliente(TipoCliente tipocliente) {
		this.tipocliente = tipocliente;
	}
	
	
	
}
