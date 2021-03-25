package modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LINEA_PEDIDOS database table.
 * 
 */
@Entity
@Table(name="LINEA_PEDIDOS")
@NamedQuery(name="LineaPedido.findAll", query="SELECT l FROM LineaPedido l")
public class LineaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LineaPedidoPK id;

	@Column(name="CANTIDAD")
	private int cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Column(name="precio_venta")
	private BigDecimal precioVenta;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="ISBN")
	private Libro libro;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="ID_PEDIDO")
	private Pedido pedido;

	public LineaPedido() {
	}

	public LineaPedidoPK getId() {
		return this.id;
	}

	public void setId(LineaPedidoPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public BigDecimal getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}