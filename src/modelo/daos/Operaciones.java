package modelo.daos;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.beans.Libro;
import modelo.beans.Pedido;
import modelo.beans.Tema;
import modelo.beans.Usuario;



public class Operaciones {
	
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;
	protected String sql;
	protected Query query;
	public Operaciones() {
		super();
		emf = Persistence.createEntityManagerFactory("20_Libreria_simple");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		
	}
	
	public Usuario validarCuenta(String usuarioid, String password) {
		
		Usuario usuario = new Usuario();
		try {
		usuario = em.find(Usuario.class, usuarioid);
		if(usuario.getPassword().equals(password))
		{
			return usuario;
		}
		else {
			return null;	
		}
		}catch(Exception e) {
			return null;
			}
	}
	public Usuario obtenerUsuarioById(String usuarioid) {
		Usuario usuario = new Usuario();
		try {
		usuario = em.find(Usuario.class, usuarioid);
		return usuario;
		}catch(Exception e) {
			return null;
			}
		
	}
	public boolean registrar(String usuarioid, String password, String nombre, String apellido, String direccion) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(usuarioid);
		usuario.setPassword(password);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setDireccion(direccion);
		usuario.setTipoUsuario("normal");
		usuario.setFechaAlta(new Date());
		try {
			tx.begin();
			em.persist(usuario);
			tx.commit();
			return true;
			}catch(Exception e) {
			return false;
			}
		
		
		
		
	}
	public List<Tema> verTemas(){
		
		sql  = "select e from Tema e";
		List<Tema> listaADevolver = null;
		query = em.createQuery(sql);
		
		try {
			listaADevolver= (List<Tema>)query.getResultList();
			
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaADevolver;
	}
public List<Libro> librosTemas(int idTema){
		Tema tema = new Tema();
		tema.setIdTema(idTema);
		sql  = "select e from Libro e where e.tema = :tema";
		List<Libro> listaADevolver = null;
		query = em.createQuery(sql);
		query.setParameter("tema", tema);
		try {
			listaADevolver= (List<Libro>)query.getResultList();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaADevolver;
	}
public Libro encontrarLibro(long ISBN) {
	Libro libro;
	try {
		libro = em.find(Libro.class, ISBN);
	}
	catch(Exception e) {
		return null;
	}
	return libro;
}
	public boolean ejecutarPedido(Pedido pedido) {
		
		try {
			tx.begin();
			em.persist(pedido);
			tx.commit();
			return true;
			}catch(Exception e) {
			return false;
			}
		
	}
	public boolean registrarNuevoTema(String nombre, String abreviatura) {
		Tema tema = new Tema();
		tema.setDescTema(nombre);
		tema.setAbreviatura(abreviatura);
		try {
			tx.begin();
			em.persist(tema);
			tx.commit();
			return true;
			}catch(Exception e) {
			return false;
			}
	}
	public boolean registrarNuevoLibro(long ISBN, String titulo, String autor, BigDecimal precio, int stock, int temaid) {
		Tema tema;
		tema = em.find(Tema.class, temaid);
		Libro libro = new Libro();
		libro.setIsbn(ISBN);
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setPrecioUnitario(precio);
		libro.setStock(stock);
		libro.setTema(tema);
		try {
			tx.begin();
			em.persist(libro);
			tx.commit();
			return true;
			}catch(Exception e) {
			return false;
			}
	}
	public List<Pedido> pedidoDelDia(){
		sql  = "select e from Pedido e where e.fechaAlta = :fecha";
		List<Pedido> listaADevolver = null;
		query = em.createQuery(sql);
		query.setParameter("fecha", new Date());
		try {
			listaADevolver= (List<Pedido>)query.getResultList();
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaADevolver;
		
	}
	
	public Object[] estadisticasCliente(String idUsuario) {
		sql="select p.usuario.idUsuario, count(lp), sum(lp.precioVenta), count(distinct(lp.libro.tema)) from LineaPedido lp join lp.pedido p where p.usuario.idUsuario = :usu";
				try {
					query = em.createQuery(sql);
					query.setParameter("usu", idUsuario);
					return (Object[]) query.getSingleResult();
				}catch(Exception e) {
					e.printStackTrace();
					return null;
				}
				
	}
public List<Usuario> verUsuariosNormales(){
		
		sql  = "select e from Usuario e where e.tipoUsuario='normal' ";
		List<Usuario> listaADevolver = null;
		query = em.createQuery(sql);
		
		try {
			listaADevolver= (List<Usuario>)query.getResultList();
			
			
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaADevolver;
	}
}

