package datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceClass {
	public DataSourceClass() {
		Connection con = null;
		try {
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext.lookup("java:/MySqlDS");
			con = ds.getConnection();
		} catch (SQLException e) {
			System.out.println("Error al obtener la conexión: " + e.getMessage());
		} catch (NamingException e1) {
			System.out.println("Error de : NamingException" + e1.getMessage());
		}
	}
}
