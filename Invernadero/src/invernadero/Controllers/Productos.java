package invernadero.Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Lesly
 */
public class Productos {
   private IntegerProperty id;
   private StringProperty codigoProducto;
   private StringProperty nombreProducto;
   private StringProperty codigotipo;
   
   public Productos(){}
   
   public Productos(Integer id, String codigoProducto, String nombreProducto, String codigoTipo){
       this.id = new SimpleIntegerProperty(id);
       this.codigoProducto = new SimpleStringProperty(codigoProducto);
       this.nombreProducto = new SimpleStringProperty(nombreProducto);
       this.codigotipo = new SimpleStringProperty(codigoTipo);
       
   }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

   
    public String getCodigoProducto() {
        return codigoProducto.get();
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = new SimpleStringProperty(codigoProducto);
    }

    public String getNombreProducto() {
        return nombreProducto.get();
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = new SimpleStringProperty(nombreProducto);
    }

    public String getCodigotipo() {
        return codigotipo.get();
    }

    public void setCodigotipo(String codigotipo) {
        this.codigotipo = new SimpleStringProperty(codigotipo);
    }
    
    
}
