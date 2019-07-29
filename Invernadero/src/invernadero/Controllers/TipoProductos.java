package invernadero.Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Lesly
 */
public class TipoProductos {
    private IntegerProperty id;
    private StringProperty codigoTipo;
    private StringProperty tipoProducto;
    
    public TipoProductos(Integer id, String codigoTipo, String tipoProducto){
        this.id = new SimpleIntegerProperty (id);
        this.codigoTipo = new SimpleStringProperty(codigoTipo);
        this.tipoProducto = new SimpleStringProperty(tipoProducto);

    }
    
    public TipoProductos(){}

    TipoProductos(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId(){
        return id.get();
    }
    
    public void setId(int id){
        this.id = new SimpleIntegerProperty (id);
    }
    public String getCodigoTipo() {
        return codigoTipo.get();
    }

    public void setCodigoTipo(String codigoTipo) {
        this.codigoTipo = new SimpleStringProperty(codigoTipo);
    }

    public String getTipoProducto() {
        return tipoProducto.get();
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = new SimpleStringProperty(tipoProducto);
    }
    
    public int agregarTipo(Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO tipo(codigo_tipo, tipo_producto) VALUES(?, ?)");
            ps.setString(1, codigoTipo.get());
            ps.setString(2, tipoProducto.get());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int actualizarTipo(Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE tipo SET codigo_tipo = ?, tipo_producto = ? WHERE id = ?");
            ps.setString(1, codigoTipo.get());
            ps.setString(2, tipoProducto.get());
            ps.setInt(3, id.get());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarTipo(Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM tipo WHERE id=?");
            ps.setInt(1, id.get());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoProductos.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    public static void llenarTabla(Connection connection, ObservableList<TipoProductos>tipoP){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tipo");
            while(rs.next()){
                tipoP.add(new TipoProductos(rs.getInt("id"), rs.getString("codigo_tipo"), rs.getString("tipo_producto")));
            }
        }
        catch(SQLException e){
            
        }
    }
    
    /*public static void llenarCombo(Connection connection, ObservableList<TipoProductos>productos){
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tipo");
            while(rs.next()){
                productos.add(new TipoProductos(rs.getString("tipo_producto")));
            }
        }
        catch(SQLException e){
            
        }
    }*/
}
