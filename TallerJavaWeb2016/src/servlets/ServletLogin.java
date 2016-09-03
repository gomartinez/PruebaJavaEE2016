package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import capa_negocio.Login;
import capa_negocio.Manager_Socio;
import capa_negocio.Manager_Usuarios;

/**
 * Servlet implementation class ServletLogin
 */
//@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String,String> usrPass;
    /**
     * @see HttpServlet#HttpServlet()
     */
  
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		usrPass = new HashMap<String,String>();
		Enumeration<String> paramNames = config.getInitParameterNames();
		
		while(paramNames.hasMoreElements()){
			String name = paramNames.nextElement();
			String value = config.getInitParameter(name);
			usrPass.put(name, value);
		}
		//Iterator itr = config.getInitParameterNames();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("pass");
		String texto;
		Manager_Usuarios mu =  Manager_Usuarios.getManagerUsuarios();
		if (mu.ExisteUsuario(usuario, pass)){	
			HttpSession session = request.getSession(); 
			Login login = new Login();
			login.setUsuario(usuario);
			session.setAttribute("login", login);
			texto = "<html><body><h1>Bienvenid@ " + usuario+"!!</h1></body></html>";
			request.getRequestDispatcher("/Peliculas/ListarPeliculas.jsp").forward(request, response);	
			
		}
		else
		{
			texto = "<html><body><h1>Usuario y/o contraseña incorrecta!!</h1></body></html>";
			response.getWriter().print(texto);	
		}
		
		
		
	}

}
