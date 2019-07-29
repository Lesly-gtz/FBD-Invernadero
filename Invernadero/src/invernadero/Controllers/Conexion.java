package invernadero.Controllers;

//import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Lesly
 */
public class Conexion {
    private String username;
    private String password;
    private String driver;
    private String urlBD;
    private String codigoTipo;
    private String tipo;
    
    Connection conn = null;
    Statement st;
    
    public Conexion (){}
    
    public Conexion(String username, String password, String driver, String urlBD){
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.urlBD = urlBD;
    }

    public Connection agregarConexion(){
        //boolean respuesta;
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(urlBD, username, password);
            st = conn.createStatement();
            System.out.println("Conexion exitosa");
            //respuesta = true;
        }
        catch(Exception e){
            //respuesta = false;
            System.out.println("ERROR");  
        }
        return conn;
    }
    
    /*INSERTAR*/
    /*public void insertarTipo(){
        try{
            String query = "INSERT INTO `tipo`(`codigo_tipo`, `tipo_producto`) VALUES ('"+codigoTipo+"', '"+tipo+"')";
            st.executeUpdate(query);
            System.out.println("Se agrego");
        }
        catch(SQLException e){
            System.out.println("No se agrego");
        }
    }*/
    
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrlBD() {
        return urlBD;
    }

    public void setUrlBD(String urlBD) {
        this.urlBD = urlBD;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     
    
}
