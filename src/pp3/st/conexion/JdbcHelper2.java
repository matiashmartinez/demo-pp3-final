package pp3.st.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
import pp3.st.utils.ConfigReader;

public class JdbcHelper2 {

     private Connection connection;
     Properties config = ConfigReader.getProperties();
     
     
     String url = config.getProperty("db.url");
            String username =  config.getProperty("db.username");
            String password = config.getProperty("db.password");
    
    //para leer 
    public ResultSet realizarConsulta(String query) {
        Conexion conexionMySql = new Conexion();

        Connection conn = conexionMySql.conectar();
        ResultSet rs = null;
        Statement stQuery;
        try {
            stQuery = conn.createStatement();
            rs = stQuery.executeQuery(query);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar consulta a la base de datos, no hay conexión " + ": " + ex);
            return null;

        }
        return rs;
    }

    //para insert, update y delete
    public boolean ejecutarQuery(String query) {
        Conexion conexionMySql = new Conexion();
        Connection conn = conexionMySql.conectar();
        boolean exito = false;
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 0) {
                exito = true;
                Conexion.cierraConexion();
            } else {
                exito = false;
                Conexion.cierraConexion();
            }
            System.out.println("Affected rows: " + affectedRows);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar " + query + ": " + ex);
            ex.getMessage();
            exito = false;
        }
        return exito;
    }
    
    
     public Connection getConnection() throws SQLException {
         

        if (connection == null || connection.isClosed()) {
            // Establece los parámetros de conexión a tu base de datos
            
            
            // Establece la conexión
            connection = DriverManager.getConnection(url, username, password);
        }
        
        return connection;
    }
     
     
      public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el ResultSet: " + ex.getMessage());
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }

}
