package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import capa_negocio.Manager_Socio;

/**
 * Servlet implementation class ServletSocios
 */
@WebServlet("/ServletSocios")
public class ServletSocios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSocios() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		
		Manager_Socio ms =  Manager_Socio.getManagerSocio();
		ms.alta_Socio(nombre, direccion);
		
		request.getRequestDispatcher("/Socios/ListarSocios.jsp").forward(request, response);
	}

}
