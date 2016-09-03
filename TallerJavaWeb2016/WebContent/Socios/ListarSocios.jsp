<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="capa_negocio.*"
	import="java.util.Enumeration" import="java.util.Iterator"
	import="java.util.ArrayList" import="java.util.ArrayList"
	import="java.util.Hashtable" import="java.util.Vector"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"	href="/TallerJavaWeb2016/Estilo.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Socios</title>
</head>
<body>
	<ul>
		<li><a href="/TallerJavaWeb2016/Peliculas/ListarPeliculas.jsp">Peliculas</a></li>
		<li><a href="/TallerJavaWeb2016/Socios/ListarSocios.jsp">Socios</a></li>
		<li><a href="/TallerJavaWeb2016/Reservas/ListarReservas.jsp">Reservas</a></li>
		<li><a href="/TallerJavaWeb2016/Alquileres/ListarAlquileres.jsp">Alquileres</a></li>
	</ul>
	<br>


	<%
		Manager_Socio ms = Manager_Socio.getManagerSocio();
	%>

	<table style="">
		<tr>
			<td colspan="2"><a href="/TallerJavaWeb2016/Socios/RegistroSocios.html">Agregar</a><br></td>
		</tr>
		<tr>
			<td colspan="2">
				<table style="width: 100%">
					<tr>
						<th>Nro de Socio</th>
						<th>Nombre</th>
						<th>Dirección</th>
					</tr>
					<%
						Vector coleccion_socios_vector = ms.devolver_Vector_Socios();
						for (Enumeration e = coleccion_socios_vector.elements(); e.hasMoreElements();) {
							Socio element = (Socio) e.nextElement();
							int nrosocio = element.getNroSocio();
							String nombre = element.getNombre();
							String direccion = element.getDireccion();
					%>
					<tr>
						<td><%=nrosocio%></td>
						<td><%=nombre%></td>
						<td><%=direccion%></td>
					</tr>
					<%
						}
					%>

				</table>
			</td>
		</tr>
		<tr></tr>
	</table>
</body>
</html>