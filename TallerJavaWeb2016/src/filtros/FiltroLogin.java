package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import capa_negocio.Login;


/**
 * Servlet Filter implementation class Filtro1
 */

public class FiltroLogin implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest htsr = (HttpServletRequest) request;
	
		if (!htsr.getRequestURI().contains("/TallerJavaWeb2016/Login/Login.html") && 
				!htsr.getRequestURI().contains("/TallerJavaWeb2016/ServletLogin")  
			&& !htsr.getRequestURI().contains("/TallerJavaWeb2016/Estilo.css")
			&& !htsr.getRequestURI().contains("/TallerJavaWeb2016/ManagerWS")) 
				{
			HttpSession session = htsr.getSession(); 
			Login login = (Login)session.getAttribute("login");
			if (login != null)
				chain.doFilter(request, response);
			else{

				request.getRequestDispatcher("/Login/Login.html").forward(request, response);	
				//ir a el login
			}
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
