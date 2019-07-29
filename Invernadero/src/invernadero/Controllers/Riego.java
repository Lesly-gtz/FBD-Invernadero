package invernadero.Controllers;

/**
 *
 * @author Lesly
 */
public class Riego {
   String codigoRiego;
   String nombreProducto;
   String codigoProducto;
   String fecha;
   
   public Riego(){}
   
   public Riego(String codigoRiego, String nombreProducto, String codigoProducto, String fecha){
       this.codigoRiego = codigoRiego;
       this.nombreProducto = nombreProducto;
       this.codigoProducto = codigoProducto;
       this.fecha = fecha;
   }

    public String getCodigoRiego() {
        return codigoRiego;
    }

    public void setCodigoRiego(String codigoRiego) {
        this.codigoRiego = codigoRiego;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
