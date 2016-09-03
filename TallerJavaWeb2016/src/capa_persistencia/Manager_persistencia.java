package capa_persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import capa_negocio.Copia;
import capa_negocio.DatatypeAlquileres;
import capa_negocio.DatatypeReservas;
import capa_negocio.Manager_Pelicula;
import capa_negocio.Pelicula;
import capa_negocio.Socio;

public class Manager_persistencia {

	// Singleton Manager_Persistencia
	private static Manager_persistencia manager_persistencia;

	private Manager_persistencia() {
	}

	public static Manager_persistencia getManagerPersistencia() {
		if (manager_persistencia == null) {
			manager_persistencia = new Manager_persistencia();
		}
		return manager_persistencia;
	}

	public String crearBaseDatos() {

		Connection con = null;
		ResultSet rs = null;
		;
		Statement s = null;
		Statement stmt = null;

		// CREATE SCHEMA `taller1hector` ;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/MySqlDS");
			con = ds.getConnection();

			// con = DriverManager.getConnection
			// ("jdbc:mysql://localhost/?user=root&password=admin&useSSL=false");

			rs = con.getMetaData().getCatalogs();

			while (rs.next()) {

				String databaseName = rs.getString(1);
				// System.out.println("databaseName: " + databaseName);
				if (databaseName.equals("tallerj2ee")) {
					rs.close();
					return "Base de datos ya existe!";
				}
			}

			// creo la base de datos
			s = con.createStatement();
			int Result = s.executeUpdate("CREATE DATABASE tallerj2ee");

			// creo las tablas
			stmt = con.createStatement();

			stmt.executeUpdate("CREATE TABLE tallerj2ee.peliculas (" + "codigo VARCHAR(8) NOT NULL,"
					+ " titulo VARCHAR(45) NOT NULL," + " actores VARCHAR(60) NULL," + " genero VARCHAR(20) NULL,"
					+ " estado VARCHAR(10) NULL," + " PRIMARY KEY (codigo))" + " ENGINE = InnoDB"
					+ " DEFAULT CHARACTER SET = utf8");

			stmt.executeUpdate("CREATE TABLE tallerj2ee.socios (" + "nroDeSocio INT(11) NOT NULL,"
					+ " nombre VARCHAR(45) NOT NULL," + " direccion VARCHAR(45) NULL," + " PRIMARY KEY (nroDeSocio))"
					+ " ENGINE = InnoDB" + " DEFAULT CHARACTER SET = utf8");

			stmt.executeUpdate("CREATE TABLE tallerj2ee.alquilerpeliculas (" + "socio INT(11) NOT NULL,"
					+ " pelicula VARCHAR(8) NOT NULL," + " PRIMARY KEY (pelicula),"
					+ " INDEX socio_alquiler_idx (socio ASC), " + " CONSTRAINT pelicula_alquiler"
					+ " FOREIGN KEY (pelicula)" + " REFERENCES tallerj2ee.peliculas (codigo)" + " ON DELETE NO ACTION"
					+ " ON UPDATE NO ACTION," + " CONSTRAINT socio_alquiler " + " FOREIGN KEY (socio)"
					+ " REFERENCES tallerj2ee.socios (nroDeSocio)" + " ON DELETE NO ACTION" + " ON UPDATE NO ACTION)"
					+ " ENGINE = InnoDB" + " DEFAULT CHARACTER SET = utf8");

			stmt.executeUpdate("CREATE TABLE tallerj2ee.reservapeliculas (" + "socio INT(11) NOT NULL,"
					+ " pelicula VARCHAR(8) NOT NULL," + " PRIMARY KEY (pelicula),"
					+ " INDEX socio_reserva_idx (socio ASC)," + " CONSTRAINT pelicula_reserva"
					+ " FOREIGN KEY (pelicula)" + " REFERENCES tallerj2ee.peliculas (codigo)" + " ON DELETE NO ACTION"
					+ " ON UPDATE NO ACTION," + " CONSTRAINT socio_reserva " + " FOREIGN KEY (socio)"
					+ " REFERENCES tallerj2ee.socios (nroDeSocio)" + " ON DELETE NO ACTION" + " ON UPDATE NO ACTION)"
					+ " ENGINE = InnoDB" + " DEFAULT CHARACTER SET = utf8");

			return "Ok";
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			return e.getMessage();
			// throw e;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		}

		finally {
			try {
				if (stmt != null)
					stmt.close();
				if (s != null)
					s.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos");
				e.printStackTrace();
			}

		}
		return "Error";

	}

	public Hashtable getColeccionSocios() {
		Hashtable coleccion_socios = new Hashtable();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = establecer_Conexion();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM socios ORDER BY nroDeSocio ASC");
			ResultSetMetaData rsm = rs.getMetaData();

			int nro_socio;
			String nombre;
			String direccion;

			while (rs.next()) {
				nro_socio = rs.getInt("nroDeSocio");
				nombre = rs.getString("nombre");
				direccion = rs.getString("direccion");
				Socio socio = new Socio(nro_socio, nombre, direccion);
				coleccion_socios.put(nro_socio, socio);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			// throw e;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// obtener la coleccion de socios");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos");
				e.printStackTrace();
			}

		}
		return coleccion_socios;
	}

	public void probarConexion() {
		Connection con = null;
		try {
			con = establecer_Conexion();
			// System.out.println("nivel:"+con.getTransactionIsolation());
			// System.out.println("TRANSACTION_NONE:"+Connection.TRANSACTION_NONE);
			// System.out.println("TRANSACTION_READ_COMMITTED:"+Connection.TRANSACTION_READ_COMMITTED);
			// System.out.println("TRANSACTION_READ_UNCOMMITTED:"+Connection.TRANSACTION_READ_UNCOMMITTED);
			// System.out.println("TRANSACTION_REPEATABLE_READ:"+Connection.TRANSACTION_REPEATABLE_READ);
			// System.out.println("TRANSACTION_SERIALIZABLE:"+Connection.TRANSACTION_SERIALIZABLE);
			System.out.println("***Conectado OK!!!***");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// probar conexion a la base");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos");
				e.printStackTrace();
			}

		}

	}

	public Connection establecer_Conexion() {
		Connection con = null;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/MySqlDS");
			con = ds.getConnection();

			Class.forName("com.mysql.jdbc.Driver");
			// con =
			// DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tallerj2ee?useSSL=false",
			// "root", "admin");
		} catch (SQLException e) {
			System.out.println("Se produjo un error al conectar con la base de datos SQL");
			// System.out.println("SQLException: "+e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return con;
	}

	public void alta_Socio_BD(Socio soc) throws Exception {
		int nro_soc = soc.getNroSocio();
		String nom = soc.getNombre();
		String dir = soc.getDireccion();
		Connection con = null;
		PreparedStatement pstmt = null;
		con = establecer_Conexion();
		try {
			con = establecer_Conexion();
			String insertQuery = "INSERT INTO socios (nroDeSocio, nombre, direccion) VALUES(?, ?, ?)";
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, nro_soc);
			pstmt.setString(2, nom);
			pstmt.setString(3, dir);
			int i = pstmt.executeUpdate();

			System.out.println(i + " registro/s ingresado/s");
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw (new Exception());
		}

		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// dar de alta el socio");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos");
				e.printStackTrace();
			}

		}

	}

	public Hashtable getColeccionPeliculas() {
		Hashtable coleccion_peliculas = new Hashtable();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = establecer_Conexion();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM peliculas ORDER BY titulo");
			ResultSetMetaData rsm = rs.getMetaData();

			String codigo = "";
			String titulo = "";
			String actores = "";
			String genero = "";
			String estado = "";

			while (rs.next()) {

				codigo = rs.getString("codigo");
				// System.out.println(codigo);
				titulo = rs.getString("titulo");
				// System.out.println(titulo);
				actores = rs.getString("actores");
				genero = rs.getString("genero");
				estado = rs.getString("estado");
				Pelicula pelicula;
				if (estado.equals("")) {
					pelicula = new Pelicula(codigo, titulo, actores, genero);
				} else {
					pelicula = new Copia(codigo, titulo, actores, genero, estado);
				}
				coleccion_peliculas.put(codigo, pelicula);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		}

		finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// obtener la coleccion de peliculas");
			} catch (SQLException e) {
				System.out
						.println("Ocurrio un error al liberar los recursos luego de obtener la coleccion de peliculas");
				e.printStackTrace();
			}

		}

		return coleccion_peliculas;
	}

	public void alta_Pelicula_BD(Pelicula nueva_pelicula, String actores) throws Exception {
		String codigo = nueva_pelicula.getCodigo();
		String titulo = nueva_pelicula.getTitulo();
		String genero = nueva_pelicula.getGenero();
		String estado = nueva_pelicula.getEstado();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecer_Conexion();
			String insertQuery = "INSERT INTO peliculas (codigo, titulo, actores, genero, estado) VALUES(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setString(1, codigo);
			pstmt.setString(2, titulo);
			pstmt.setString(3, actores);
			pstmt.setString(4, genero);
			pstmt.setString(5, estado);

			int i = pstmt.executeUpdate();

			System.out.println(i + " registro/s ingresado/s");

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw (new Exception());
		}

		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// dar de alta la pelicula");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos en alta pelicula");
				e.printStackTrace();
			}

		}

	}

	public void alta_Alquiler_BD(int nro_socio, String codigo) throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = establecer_Conexion();
			String insertQuery = "INSERT INTO alquilerpeliculas (socio, pelicula) VALUES(?, ?)";
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, nro_socio);
			pstmt.setString(2, codigo);
			int i = pstmt.executeUpdate();
			System.out.println(i + " registro/s ingresado/s");
			System.out.println("Alquiler realizado");

		} catch (SQLException e) {
			System.out.println("Se produjo un error SQL");
			throw (new SQLException());
		} catch (Exception e) {
			System.out.println("Se produjo un error en la BD");
			throw (new Exception());
		}

		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// dar de alta el alquiler");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos");
				e.printStackTrace();
			}

		}

	}

	public void alta_Reserva_BD(int nro_socio, String codigo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = establecer_Conexion();
			String insertQuery = "INSERT INTO reservapeliculas (socio, pelicula) VALUES(?, ?)";
			pstmt = con.prepareStatement(insertQuery);
			pstmt.setInt(1, nro_socio);
			pstmt.setString(2, codigo);
			int i = pstmt.executeUpdate();
			System.out.println(i + " registro/s ingresado/s");
			System.out.println("Reserva realizada\n\n");

		} catch (SQLException e) {
			System.out.println("Se produjo el siguiente error SQL: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// dar de alta la reserva");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos luego de dar de alta la reserva");
				e.printStackTrace();
			}

		}

	}

	public Vector getColeccionAlquileres() {
		Vector<DatatypeAlquileres> coleccion_alquileres = new Vector<DatatypeAlquileres>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = establecer_Conexion();
			stmt = con.createStatement();
			// consulta JOIN de las tablas para obtener de una sola vez todos
			// los datos de interes
			rs = stmt.executeQuery(
					"SELECT nroDeSocio, nombre, socio, pelicula, codigo, titulo FROM socios, alquilerpeliculas, peliculas WHERE socios.nroDeSocio = alquilerpeliculas.socio AND alquilerpeliculas.pelicula = peliculas.codigo");
			ResultSetMetaData rsm = rs.getMetaData();
			int nro_socio;
			String pelicula;
			String nombre;
			String titulo;

			while (rs.next()) {

				nro_socio = rs.getInt("socio");
				pelicula = rs.getString("pelicula");
				nombre = rs.getString("nombre");
				titulo = rs.getString("titulo");
				DatatypeAlquileres datatype_alquileres = new DatatypeAlquileres(nro_socio, nombre, pelicula, titulo);
				coleccion_alquileres.add(datatype_alquileres);
			}

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// obtener la coleccion de alquileres");
			} catch (SQLException e) {
				System.out.println(
						"Ocurrio un error al liberar los recursos luego de obtener la coleccion de alquileres");
				e.printStackTrace();
			}

		}
		return coleccion_alquileres;
	}

	public Hashtable getColeccionReservas() {

		Hashtable<String, DatatypeReservas> coleccion_reservas = new Hashtable<String, DatatypeReservas>();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = establecer_Conexion();
			stmt = con.createStatement();

			// consulta JOIN de las tablas para obtener de una sola vez todos
			// los datos de interes
			rs = stmt.executeQuery(
					"SELECT nroDeSocio, nombre, socio, pelicula, codigo, titulo FROM socios, reservapeliculas, peliculas WHERE socios.nroDeSocio = reservapeliculas.socio AND reservapeliculas.pelicula = peliculas.codigo");
			ResultSetMetaData rsm = rs.getMetaData();
			int nro_socio;
			String pelicula;
			String nombre;
			String titulo;

			while (rs.next()) {

				nro_socio = rs.getInt("socio");
				pelicula = rs.getString("pelicula");
				nombre = rs.getString("nombre");
				titulo = rs.getString("titulo");
				DatatypeReservas datatype_reservas = new DatatypeReservas(nro_socio, nombre, pelicula, titulo);
				coleccion_reservas.put(pelicula, datatype_reservas);
			}


		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// obtener la coleccion de reservas");
			} catch (SQLException e) {
				System.out
						.println("Ocurrio un error al liberar los recursos luego de obtener la coleccion de reservas");
				e.printStackTrace();
			}

		}
		return coleccion_reservas;
	}

	public void baja_Reserva_BD(int nro_socio, String codigo) {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = establecer_Conexion();
			st = con.prepareStatement("DELETE FROM reservapeliculas WHERE socio = ? and pelicula = ?");
			st.setInt(1, nro_socio);
			st.setString(2, codigo);
			st.executeUpdate();
			System.out.println("Se dio de baja la reserva");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			try {
				if (st != null)
					st.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// dar de baja la reserva");
			} catch (SQLException e) {
				System.out.println("Ocurrio un error al liberar los recursos luego de dar de baja la reserva");
				e.printStackTrace();
			}

		}

	}

	public boolean ExisteUsuario(String nombre, String contrasena) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean resultado = false;
		try {
			con = establecer_Conexion();
			

			// consulta JOIN de las tablas para obtener de una sola vez todos
			// los datos de interes
			
			String insertQuery = "SELECT * FROM usuarios WHERE UsuarioNombre = ? and UsuarioContrasena = ?";
			stmt = con.prepareStatement(insertQuery);

			stmt.setString(1, nombre);
			stmt.setString(2, contrasena);
			rs = stmt.executeQuery();
			

			if (rs.next()) {

				resultado = true;
			}
			else{
				resultado = false;
			}


		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				// System.out.println("Recursos liberados correctamente luego de
				// obtener la coleccion de reservas");
			} catch (SQLException e) {
				System.out
						.println("Ocurrio un error al liberar los recursos luego de obtener la coleccion de reservas");
				e.printStackTrace();
			}

		}
		return resultado;

	}

}
