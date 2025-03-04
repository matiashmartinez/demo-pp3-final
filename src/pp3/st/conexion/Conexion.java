package pp3.st.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pp3.st.utils.ConfigReader;

/**
 *
 * @author Mati
 */
public class Conexion {

    Properties config = ConfigReader.getProperties();

    String url = config.getProperty("db.url");
    String username = config.getProperty("db.username");
    String password = config.getProperty("db.password");

    public static Connection connection = null;

    public Connection conectar() {

        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR 0 al conectar: " + ex
                    + "\nAsegúrese de que el servidor esté encendido.", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            try {
                connection.close();
            } catch (SQLException ex1) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return null;
        }
        return connection;
    }

    public static void cierraConexion() {
        try {
            connection.close();
            System.out.println("Conexion cerrada");
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error al cerrar conexion", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

//    public String getDatabase() {
//        return database;
//    }
//
//    public void setDatabase(String database) {
//        this.database = database;
//    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Connection getConnection() {
        return connection;
    }

    public Conexion() {
    }

}
