package controlador;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Usuario;
import modelo.daos.Operaciones;

/**
 * Servlet implementation class GestionAdmin
 */
@WebServlet("/GestionAdmin")
public class GestionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession miSesion = request.getSession();
		Operaciones operacion = new Operaciones();
		switch(request.getParameter("opcion")) {
		case "altaTema":
			request.getRequestDispatcher("altaTema.jsp").forward(request, response);
			break;
		case "altaLibro":
			request.setAttribute("temas", operacion.verTemas());
			request.getRequestDispatcher("altaLibro.jsp").forward(request, response);
			break;
		case "datosCliente":
			datosCliente(miSesion, operacion, request, response);
			
			break;
		case "pedidosDelDia":
			request.setAttribute("pedidosdia", operacion.pedidoDelDia());
			request.getRequestDispatcher("pedidosDelDia.jsp").forward(request, response);
			break;
		case "procesarAltaTema":
			//guardamos en la base de datos un nuevo tema
			operacion.registrarNuevoTema(request.getParameter("nombreTema"),request.getParameter("abreviaturaTema"));
			request.getRequestDispatcher("MenuAdmin.jsp").forward(request, response);
			break;
		case "procesarAltaLibro":
			//guardamos en la base de datos un nuevo libro
			operacion.registrarNuevoLibro(Long.valueOf(request.getParameter("ISBN")).longValue(), request.getParameter("titulo"), request.getParameter("autor"),
					new BigDecimal(request.getParameter("precio")), Integer.parseInt(request.getParameter("stock")), Integer.parseInt(request.getParameter("temaelegido")));
			request.getRequestDispatcher("MenuAdmin.jsp").forward(request, response);
			break;
		case "volverAlMenu":
			request.getRequestDispatcher("MenuAdmin.jsp").forward(request, response);
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
	protected void datosCliente(HttpSession miSesion, Operaciones operacion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Object[]> listaclientes = new ArrayList<Object[]>();
		List<Usuario> usuariosnormales = operacion.verUsuariosNormales();
		for(Usuario usuario : usuariosnormales) {
			Object[] lista = operacion.estadisticasCliente(usuario.getIdUsuario());
			listaclientes.add(lista);
		}
		request.setAttribute("informacionusuarios", listaclientes);
		
		request.getRequestDispatcher("datosCliente.jsp").forward(request, response);
	}
}
