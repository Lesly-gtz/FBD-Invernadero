package invernadero.Controllers;

/**
 *
 * @author Lesly
 */
public class Historial {
    String codigoProducto;
    String fecha;
    String imagen;
    
    public Historial(){}
    
    public Historial(String codigoProducto, String fecha, String imagen){
        this.codigoProducto = codigoProducto;
        this.fecha = fecha;
        this.imagen = imagen;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
