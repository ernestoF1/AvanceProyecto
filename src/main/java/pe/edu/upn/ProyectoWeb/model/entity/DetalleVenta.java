package pe.edu.upn.ProyectoWeb.model.entity;

import java.util.ArrayList;
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



@Entity
@Table(name="detalleventas")
public class DetalleVenta {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@OneToMany(mappedBy = "detalleventa",fetch = FetchType.LAZY)
	private List<Venta> listaventa;
	
	public DetalleVenta() {
		listaventa=new ArrayList<>();
	}
	
	public void addVenta(Venta venta) {
		venta.setDetalleventa(this);
		this.listaventa.add(venta);
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoPago_id")
	private TipoPago tipoPago;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tipocliente_codigo")
	private TipoCliente tipoclientes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public List<Venta> getListaventa() {
		return listaventa;
	}

	public void setListaventa(List<Venta> listaventa) {
		this.listaventa = listaventa;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public TipoCliente getTipoclientes() {
		return tipoclientes;
	}

	public void setTipoclientes(TipoCliente tipoclientes) {
		this.tipoclientes = tipoclientes;
	}
	
	
	
}
