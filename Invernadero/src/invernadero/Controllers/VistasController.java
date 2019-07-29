package invernadero.Controllers;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.StageStyle;

/**
 *
 * @author Lesly
 */
public class VistasController implements Initializable{
    ObservableList<String> manejadores = FXCollections.observableArrayList("MySQL", "SQLServer");
    ObservableList<String> reporte = FXCollections.observableArrayList("Cantidad de Productos por Tipo de Producto", "Cantidad de Riegos por Día",
                                                                        "Cantidad de Productos por Condición Actual", "Cantidad de Fotografías por Tipo de Producto");
    ObservableList<String> condicionA = FXCollections.observableArrayList("Buen Estado", "Falta de Agua", "Exceso de Agua", "Con Plaga");
    ObservableList<TipoProductos> combo;
    String username, password;
    @FXML private TextField txtUsername;
    @FXML private TextField txtTipo;
    @FXML private TextField txtCodigoTipo;
    @FXML private TextField txtId;
    @FXML private TextField txtIdP;
    @FXML private TextField txtCodigoProducto;
    @FXML private TextField txtProducto;
    @FXML private DatePicker dateProducto;
    @FXML private PasswordField txtPassword;
    @FXML private AnchorPane login;
    @FXML private AnchorPane menu;
    @FXML private AnchorPane tipoProductos;
    @FXML private AnchorPane productos;
    @FXML private AnchorPane riego;
    @FXML private AnchorPane historial;
    @FXML private AnchorPane reportes;
    @FXML private ComboBox<String> comboBox;
    @FXML private ComboBox<String> report;
    @FXML private ComboBox<String> condicion;
    @FXML private ComboBox cbTipo;
    //@FXML private ComboBox<TipoProductos> tipoP;
    /*TABLA TIPO*/
    @FXML private TableView<TipoProductos> tablaTipo;
    @FXML private TableColumn<TipoProductos, String> codigoT;
    @FXML private TableColumn<TipoProductos, String> tipo;
    ObservableList<TipoProductos>listaTipo;
    @FXML 
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    
    Conexion conexion;
    /*CONEXION A BASE DE DATOS*/
    @FXML
    private void btnSesion (ActionEvent event){   
        String cb = comboBox.getValue();
        conexion = new Conexion();
        if(cb.equals("MySQL")){
            conexion.setUsername(txtUsername.getText());
            conexion.setPassword(txtPassword.getText());
            conexion.setDriver("com.mysql.jdbc.Driver");
            conexion.setUrlBD("jdbc:mysql://localhost:3306/invernadero");
            if(conexion.agregarConexion() == null){
                Alert dialogo = new Alert(AlertType.ERROR);
                dialogo.setTitle("ERROR");
                dialogo.setHeaderText("Error de conexión");
                dialogo.setContentText("Usuario ó contraseña incorrectos");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
                
            }
            else{
                Alert dialogo = new Alert(AlertType.INFORMATION);
                dialogo.setTitle("CONFIRMACIÓN");
                dialogo.setHeaderText("Conectado");
                dialogo.setContentText("Iniciando Sesión...");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
                this.menu.setVisible(true);
                this.login.setVisible(false);
            }       
        }
        else if(cb.equals("SQLServer")){
            conexion.setUsername(txtUsername.getText());
            conexion.setPassword(txtPassword.getText());
            conexion.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion.setUrlBD("jdbc:sqlserver://localhost:1433;databaseName=invernadero");
            if(conexion.agregarConexion() == null){
                Alert dialogo = new Alert(AlertType.ERROR);
                dialogo.setTitle("ERROR");
                dialogo.setHeaderText("Error de conexión");
                dialogo.setContentText("Usuario ó contraseña incorrectos");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
                
            }
            else{
                Alert dialogo = new Alert(AlertType.INFORMATION);
                dialogo.setTitle("CONFIRMACIÓN");
                dialogo.setHeaderText("Conectado");
                dialogo.setContentText("Iniciando Sesión...");
                dialogo.initStyle(StageStyle.UTILITY);
                dialogo.showAndWait();
                this.menu.setVisible(true);
                this.login.setVisible(false);
            }
        }
        /*VIDEO*/
        String path = new File("src/img/presentacion.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        mv.setFitWidth(530);
        mv.setFitHeight(500);
    }
    
    /*CRUD TIPO PRODUCTO*/
    @FXML
    private void btnTipoProducto(ActionEvent event){
        this.tipoProductos.setVisible(true);
        this.menu.setVisible(false);
        this.riego.setVisible(false);
        this.historial.setVisible(false);
        this.reportes.setVisible(false);
        this.productos.setVisible(false);
        listaTipo = FXCollections.observableArrayList();
        codigoT.setCellValueFactory(new PropertyValueFactory<>("codigoTipo"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipoProducto")); 
        TipoProductos.llenarTabla(conexion.agregarConexion(), listaTipo);
        tablaTipo.setItems(listaTipo);
        gestionarEventos();
    }
    @FXML
    private void btnGuardarT(ActionEvent event){
        TipoProductos tp = new TipoProductos(0, txtCodigoTipo.getText(),txtTipo.getText());
        int resultado = tp.agregarTipo(conexion.agregarConexion());
        if(resultado == 1){
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("CONFIRMACIÓN");
            dialogo.setHeaderText("Verificación de Inserción");
            dialogo.setContentText("Se inserto correctamente");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait(); 
            TipoProductos.llenarTabla(conexion.agregarConexion(), listaTipo);
        }
        else{
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("ERROR");
            dialogo.setHeaderText("Error de Inserción");
            dialogo.setContentText("No se pudo insertar");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        }
    }
    @FXML void btnEditarT(ActionEvent event){
        TipoProductos tp = new TipoProductos(
                Integer.valueOf(txtId.getText()), txtCodigoTipo.getText(),txtTipo.getText());
        int resultado = tp.actualizarTipo(conexion.agregarConexion());
        
        if(resultado == 1){
            Alert dialogo = new Alert(AlertType.INFORMATION);
            dialogo.setTitle("CONFIRMACIÓN");
            dialogo.setHeaderText("Verificación de Inserción");
            dialogo.setContentText("Se inserto correctamente");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait(); 
            TipoProductos.llenarTabla(conexion.agregarConexion(), listaTipo);
        }
        else{
            Alert dialogo = new Alert(AlertType.ERROR);
            dialogo.setTitle("ERROR");
            dialogo.setHeaderText("Error de Inserción");
            dialogo.setContentText("No se pudo insertar");
            dialogo.initStyle(StageStyle.UTILITY);
            dialogo.showAndWait();
        }
    }
    @FXML void btnEliminarT(ActionEvent event){
        //int resultado = 
        tablaTipo.getSelectionModel().getSelectedItem().eliminarTipo(conexion.agregarConexion());
        TipoProductos.llenarTabla(conexion.agregarConexion(), listaTipo);
    }
    /*CRUD PRODUCTOS*/
    @FXML 
    private void btnProductos(ActionEvent event){
        
        this.productos.setVisible(true);
        this.tipoProductos.setVisible(false);
        this.riego.setVisible(false);
        this.historial.setVisible(false);
        this.reportes.setVisible(false);
        this.menu.setVisible(false);
        /*combo = FXCollections.observableArrayList();
        TipoProductos.llenarCombo(conexion.agregarConexion(), combo);
        tipoP.setItems(combo);*/

    }
    @FXML
    private void btnGuardarP(ActionEvent event){
        
    }
    @FXML void btnEditarP(ActionEvent event){
        
    }
    @FXML 
    private void btnEliminarP(ActionEvent event){
        
    }
    
    /*CRUD RIEGO*/
    @FXML
    private void btnRiego(ActionEvent event){
        this.riego.setVisible(true);
        this.menu.setVisible(false);
        this.tipoProductos.setVisible(false);
        this.productos.setVisible(false);
        this.historial.setVisible(false);
        this.reportes.setVisible(false);
    }
    
    @FXML
    private void btnHistorial(ActionEvent event){
        this.historial.setVisible(true);
        this.menu.setVisible(false);
        this.tipoProductos.setVisible(false);
        this.productos.setVisible(false);
        this.riego.setVisible(false);
        this.reportes.setVisible(false);
    }
    
    @FXML
    private void btnReporte(ActionEvent event){
        this.reportes.setVisible(true);
        this.historial.setVisible(false);
        this.menu.setVisible(false);
        this.tipoProductos.setVisible(false);
        this.productos.setVisible(false);
        this.riego.setVisible(false);
    }
    
    @FXML
    private void btnStop(ActionEvent event){
        mp.seek(mp.getTotalDuration());
        mp.stop();
    }
    
    @FXML
    private void btnPlay(ActionEvent event){
        mp.play();
    }
    
    @FXML
    private void btnPause(ActionEvent event){
        mp.pause();
    }
    
    @FXML
    private void btnInicio(ActionEvent event){
        this.menu.setVisible(true);
        this.tipoProductos.setVisible(false);
        this.productos.setVisible(false);
        this.historial.setVisible(false);
        this.reportes.setVisible(false);
        this.riego.setVisible(false);

    }
    
    /*OBTENER DATOS DE LA TABLA*/
    public void gestionarEventos(){
        tablaTipo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TipoProductos>(){
            @Override
            public void changed(ObservableValue<? extends TipoProductos> observable, TipoProductos valorAnterios, TipoProductos valorSeleccionado){
                txtId.setText(String.valueOf(valorSeleccionado.getId()));
                txtCodigoTipo.setText(String.valueOf(valorSeleccionado.getCodigoTipo()));
                txtTipo.setText(String.valueOf(valorSeleccionado.getTipoProducto()));
                
            }
            
        });
    }
    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.setItems(manejadores);
        report.setItems(reporte);
        condicion.setItems(condicionA);
        
    }
}
