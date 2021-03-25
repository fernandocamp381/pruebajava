package modelo.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the LIBROS database table.
 * 
 */
@Entity
@Table(name="LIBROS")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ISBN")
	private long isbn;

	@Column(name="AUTOR")
	private String autor;

	@Column(name="PRECIO_UNITARIO")
	private BigDecimal precioUnitario;

	@Column(name="STOCK")
	private int stock;

	@Column(name="TITULO")
	private String titulo;

	//bi-directional many-to-one association to Tema
	@ManyToOne
	@JoinColumn(name="ID_TEMA")
	private Tema tema;

	//bi-directional many-to-one association to LineaPedido
	@OneToMany(mappedBy="libro")
	private List<LineaPedido> lineaPedidos;

	public Libro() {
	}

	public long getIsbn() {
		return this.isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Tema getTema() {
		return this.tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<LineaPedido> getLineaPedidos() {
		return this.lineaPedidos;
	}

	public void setLineaPedidos(List<LineaPedido> lineaPedidos) {
		this.lineaPedidos = lineaPedidos;
	}

	public LineaPedido addLineaPedido(LineaPedido lineaPedido) {
		getLineaPedidos().add(lineaPedido);
		lineaPedido.setLibro(this);

		return lineaPedido;
	}

	public LineaPedido removeLineaPedido(LineaPedido lineaPedido) {
		getLineaPedidos().remove(lineaPedido);
		lineaPedido.setLibro(null);

		return lineaPedido;
	}

}