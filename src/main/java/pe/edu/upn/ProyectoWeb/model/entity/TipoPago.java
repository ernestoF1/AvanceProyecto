package pe.edu.upn.ProyectoWeb.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipopagos")
public class TipoPago {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "nombre",length = 25,nullable = false)
	private String nombre;

	@OneToMany(mappedBy="tipoPago",fetch=FetchType.LAZY)
	private List<Venta>listaVentas;
	
	public TipoPago() {
		listaVentas=new ArrayList<>();
	}
	public void addVentas(Venta venta) {
		venta.setTipoPago(this);
		this.listaVentas.add(venta);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Venta> getListaVentas() {
		return listaVentas;
	}
	public void setListaVentas(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}
	
	
	
	
}
