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
<title>Peliculas</title>
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
		Manager_Pelicula mp = Manager_Pelicula.getManagerPelicula();
	%>

	<table style="">
		<tr>
			<td colspan="2"><a href="/TallerJavaWeb2016/Peliculas/RegistroPelicula.html">Agregar</a><br></td>
		</tr>
		<tr>
			<td colspan="2">
				<table style="width: 100%">
					<tr>
						<th>Codigo</th>
						<th>Titulo</th>
						<th>Genero</th>
						<th>Actores</th>
						<th>Estado</th>
					</tr>
					<%
						Vector coleccion_peliculas_vector = mp.devolver_Vector_Peliculas();
						for (Enumeration e = coleccion_peliculas_vector.elements(); e.hasMoreElements();) {
							Pelicula element = (Pelicula) e.nextElement();
							String codigo = element.getCodigo();
							String titulo = element.getTitulo();
							String genero = element.getGenero();
							Vector vector_actores = element.getActores();
							String estado;
							if (element instanceof Copia)
								estado = element.getEstado();
							else
								estado = "";
							String actores = "";
							for (int j = 0; j < vector_actores.size(); j++) {
								String actor = (String) vector_actores.get(j);
								actores = actores + actor;
								if (j < vector_actores.size() - 1) {
									actores = actores + ", ";
								}

							}
					%>
					<tr>
						<td><%=codigo%></td>
						<td><%=titulo%></td>
						<td><%=genero%></td>
						<td><%=actores%></td>
						<td><%=estado%></td>
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