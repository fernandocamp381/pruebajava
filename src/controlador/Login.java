package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Usuario;
import modelo.daos.Operaciones;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Operaciones operacion = new Operaciones();
		
		switch(request.getParameter("login")) {
		case "validar":
			//le manda al metodo login donde comprueba que introdujo
			//datos correctos y si es usuario o admin
			login(operacion, request, response);
			break;
		case "registrar":
			//permite registrar una cuenta usando el metodo de registro
			registro(operacion, request, response);
			break;
		default:
			System.out.println("algo salido mal");
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

	protected void login(Operaciones operacion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//este metodo comprueba si la contraseña y usuario son correctos, en caso
		//de que lo sean, enviara un objeto de clase usuario de vuelta
		Usuario usuario = operacion.validarCuenta(request.getParameter("usuarioid"), request.getParameter("password"));
		//si devolvio uno, entrara a este if, donde habra otro if que comprobara
		//si es admin o usuario, y le enviara a cada uno a su pagina respectiva
		if(usuario!=null)
		{
			request.getSession().invalidate();
			HttpSession miSesion = request.getSession();
				miSesion.setAttribute("usuario", usuario.getIdUsuario());
			if(usuario.getTipoUsuario().equals("admon"))
			{
				request.getRequestDispatcher("MenuAdmin.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("MenuNormal.jsp").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
	}
	protected void registro(Operaciones operacion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean exito = false;
		//aqui usamos el metodo para registrar. Este metodo devuelve true si 
		//funciono correctamente el registro. Si se registro correctamente, devuelve
		//el usuario al login, si no, le pide que vuelva a intentar registrarse
		exito = operacion.registrar(request.getParameter("usuarioid"), request.getParameter("password"),request.getParameter("nombre"),request.getParameter("apellido"),request.getParameter("direccion"));
	if(exito == true) {
		request.getRequestDispatcher("Index.jsp").forward(request, response);
	}else {
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}
	}
	
}
