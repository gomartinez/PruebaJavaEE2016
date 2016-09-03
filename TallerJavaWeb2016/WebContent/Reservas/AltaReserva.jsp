<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="capa_negocio.*"
	import="java.util.Enumeration" import="java.util.Iterator"
	import="java.util.ArrayList" import="java.util.Hashtable"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"	href="/TallerJavaWeb2016/Estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALTA PAIS</title>
</head>
<body>
	<ul>
		<li><a href="/TallerJavaWeb2016/Peliculas/ListarPeliculas.jsp">Peliculas</a></li>
		<li><a href="/TallerJavaWeb2016/Socios/ListarSocios.jsp">Socios</a></li>
		<li><a href="/TallerJavaWeb2016/Reservas/ListarReservas.jsp">Reservas</a></li>
		<li><a href="/TallerJavaWeb2016/Alquileres/ListarAlquileres.jsp">Alquileres</a></li>
	</ul>
	<br>

	<%!Hashtable socios;
	Hashtable peliculas;%>
	<%
		Manager_Socio ms = Manager_Socio.getManagerSocio();
		socios = ms.getColeccionSocios();

		Manager_Pelicula mp = Manager_Pelicula.getManagerPelicula();
		peliculas = mp.getColeccionPeliculas();
	%>

	<form action="/TallerJavaWeb2016/ServletReserva" method="post">
		<table style="">
			<tr>
				<td>Socio:</td>
				<td><select name="socio">
						<%
							if (socios != null) {
								for (Enumeration e = socios.elements(); e.hasMoreElements();) {
									Socio elem = (Socio) e.nextElement();
						%>
						<option value="<%=elem.getNroSocio()%>"><%=elem.getNombre()%></option>

						<%
							}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Pelicula:</td>
				<td><select name="pelicula">
						<%
							if (peliculas != null) {
								for (Enumeration e = peliculas.elements(); e.hasMoreElements();) {
									Pelicula elem = (Pelicula) e.nextElement();
						%>
						<option value="<%= elem.getCodigo() %>"><%=elem.getTitulo() %></option>

						<%
							}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Confirmar"></td>
			</tr>
			<tr></tr>
		</table>
	</form>
</body>
</html>