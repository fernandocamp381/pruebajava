package controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Libro;
import modelo.beans.LineaPedido;
import modelo.beans.Pedido;
import modelo.beans.Tema;
import modelo.beans.Usuario;
import modelo.daos.Operaciones;

/**
 * Servlet implementation class GestionNormal
 */
@WebServlet("/GestionNormal")
public class GestionNormal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionNormal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession miSesion = request.getSession();
		Operaciones operacion = new Operaciones();
		List<Libro>prueba =new ArrayList<Libro>();
		//el objetivo de este if es crear la lista que almacenara los libros de la cesta,
		//en caso de que aun no este creado
		if(!(miSesion.getAttribute("LibrosCliente") != null))miSesion.setAttribute("LibrosCliente", prueba);
		switch(request.getParameter("opcion")) {
		case "cerrarSession":
			//esto borra la sesion actual, haciendo que todos los datos de sesion
			//se borren
			miSesion.invalidate();
			request.getRequestDispatcher("Index.jsp").forward(request, response);
			break;
		case "VerTemas":
			//metemos un arraylist con todos los temas y se lo metemos al request
			request.setAttribute("listaTemas", operacion.verTemas());
			request.getRequestDispatcher("VerTemas.jsp").forward(request, response);
			break;
		case "librosTemas":
			//con el tema elejido, los pasamos a un metodo que nos pasara una lista
			//con los libros del tema, y se lo metemos al request
			request.setAttribute("listaLibros", operacion.librosTemas(Integer.parseInt(request.getParameter("valorElejido"))));
			request.getRequestDispatcher("VerLibros.jsp").forward(request, response);
			break;
		case "addLibros":
			//usamos este metodo para añadir libros a la cesta
			addLibros(operacion, miSesion, request, response);
			break;
		case "VerCarrito":
			request.getRequestDispatcher("VerCarrito.jsp").forward(request, response);
			break;
		case "eliminar":
			//usamos este metodo para eliminar un libro de la cesta
			eliminar(operacion, miSesion, request, response);
			break;
		case "vaciar":
			//aqui sobreescribimos la lista que almacena en sesion los libros de la cesta
			//con otra cesta vacia, dando el resultado que al usuario se le vacie
			miSesion.setAttribute("LibrosCliente", prueba);
			request.getRequestDispatcher("VerCarrito.jsp").forward(request, response);
			break;
		case "volver":
			request.getRequestDispatcher("MenuNormal.jsp").forward(request, response);
			break;
		case"comprar":
			//usa el metodo comprar para procesar los registros en la base de dato
			comprar(operacion, miSesion, request, response);
			miSesion.setAttribute("LibrosCliente", prueba);
			request.getRequestDispatcher("MenuNormal.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void addLibros(Operaciones operacion, HttpSession miSesion, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		//Aqui pasamos todos los valores elegidos en los checkbox a un array,
		//y los transformamos en un arraylist
		String[] arraytemporal = request.getParameterValues("valorElejido");
		ArrayList<String> arraylistlibros =new ArrayList<>(Arrays.asList(arraytemporal));
		//obtemenos la cesta
		List<Libro> listatotal = (List<Libro>)miSesion.getAttribute("LibrosCliente");
		//aqui comprobaremos que los libros no estan ya en la cesta, y en caso
		//de que esten, no se añadiran a la cesta
		for(String elemento : arraylistlibros)
		{
			Libro libro = operacion.encontrarLibro(Long.parseLong(elemento));
			Boolean repetido = false;
			for(Libro elemento2 : listatotal) {
				if(libro.getIsbn()==elemento2.getIsbn())repetido=true;
			}
			if(!repetido)listatotal.add(libro);
			
		}
		//guardamos la cesta en sesion
		miSesion.setAttribute("LibrosCliente", listatotal);
		request.getRequestDispatcher("MenuNormal.jsp").forward(request, response);
	}
	protected void eliminar(Operaciones operacion, HttpSession miSesion, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		//obtenemos la lista de la ceste
		List<Libro> listatotal = (List<Libro>)miSesion.getAttribute("LibrosCliente");
		//recorremos un buble for hasta que encontremos un libro con la misma id
		//del que queramos eliminar, y lo eliminamos de la cesta
		for(int i = 0; i<listatotal.size();i++) {
			if(listatotal.get(i).getIsbn()==Long.parseLong(request.getParameter("libroEliminar"))) {
				listatotal.remove(i);
			}
		}
		//guardamos la cesta actualizada en sesion
		miSesion.setAttribute("LibrosCliente", listatotal);
		request.getRequestDispatcher("VerCarrito.jsp").forward(request, response);
	}
	protected void comprar(Operaciones operacion, HttpSession miSesion, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//preparamos el pedido con todos los datos antes de enviarlo a la base de datos
		Usuario usuario = operacion.obtenerUsuarioById((String)miSesion.getAttribute("usuario"));
		Pedido pedido = new Pedido();
		pedido.setDireccionEntrega(usuario.getDireccion());
		pedido.setEstado("Procesando");
		pedido.setUsuario(usuario);
		pedido.setFechaAlta(new Date());
		List<LineaPedido> listapedidosvacia = new ArrayList<LineaPedido>();
		pedido.setLineaPedidos(listapedidosvacia);
		//aqui metemos en la linea de pedido, que es la table donde combina
		//el pedido con los libros, todos los libros usando un bucle for
		List<Libro> listatotal = (List<Libro>)miSesion.getAttribute("LibrosCliente");
		for(Libro libro : listatotal) {
			LineaPedido lineapedido = new LineaPedido();
			lineapedido.setCantidad(1);
			lineapedido.setPrecioVenta(libro.getPrecioUnitario().multiply(BigDecimal.valueOf(lineapedido.getCantidad())));
			lineapedido.setLibro(libro);
			lineapedido.setPedido(pedido);
			lineapedido.setFechaAlta(new Date());
			pedido.addLineaPedido(lineapedido);
		}
		//ahora enviamos a que use el objeto pedido para que lo meta sus datos
		//en la base de datos
		operacion.ejecutarPedido(pedido);
	}
}
