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
@WebServlet("/ServletReserva")
public class ServletReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReserva() {
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
		int nro_soc = Integer.valueOf(request.getParameter("socio"));
		String codigo = request.getParameter("pelicula");
		
		Manager_Socio ms =  Manager_Socio.getManagerSocio();
		ms.alta_Reserva(nro_soc, codigo);
		
		request.getRequestDispatcher("/Reservas/ListarReservas.jsp").forward(request, response);
	}

}
