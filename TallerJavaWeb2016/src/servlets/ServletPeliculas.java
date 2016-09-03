package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capa_negocio.Manager_Pelicula;

/**
 * Servlet implementation class ServletPeliculas
 */
@WebServlet("/ServletPeliculas")
public class ServletPeliculas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPeliculas() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String titulo = request.getParameter("titulo");
		String actores = request.getParameter("actores");
		String genero = request.getParameter("genero");
		String tipo = request.getParameter("tipo");

		Manager_Pelicula mp = Manager_Pelicula.getManagerPelicula();
		if (tipo != null && tipo.equals("orignal"))
			mp.alta_Pelicula(titulo, actores, genero);
		else {

			String estado = request.getParameter("estado");
			if (estado != null && estado.equals("bueno")) {
				mp.alta_Copia(titulo, actores, genero, "Bueno");
			} else if (estado != null && estado.equals("muyBueno")) {
				mp.alta_Copia(titulo, actores, genero, "Muy Bueno");
			} else if (estado != null && estado.equals("excelente")) {
				mp.alta_Copia(titulo, actores, genero, "Excelente");
			}
			

		}

		request.getRequestDispatcher("/Peliculas/ListarPeliculas.jsp").forward(request, response);
	}

}
