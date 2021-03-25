package modelo.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LINEA_PEDIDOS database table.
 * 
 */
@Embeddable
public class LineaPedidoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_PEDIDO", insertable=false, updatable=false)
	private int idPedido;

	@Column(name="ISBN", insertable=false, updatable=false)
	private long isbn;

	public LineaPedidoPK() {
	}
	public int getIdPedido() {
		return this.idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public long getIsbn() {
		return this.isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LineaPedidoPK)) {
			return false;
		}
		LineaPedidoPK castOther = (LineaPedidoPK)other;
		return 
			(this.idPedido == castOther.idPedido)
			&& (this.isbn == castOther.isbn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPedido;
		hash = hash * prime + ((int) (this.isbn ^ (this.isbn >>> 32)));
		
		return hash;
	}
}