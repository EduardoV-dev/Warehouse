package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.DB.CurrentLogin;
import models.DB.Facade;
import models.POJO.Empresa;
import utils.Fuller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Atributos para la funcionalidad de arrastrar la ventana
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField usuarios_confirmarPassTF;

    @FXML
    private AnchorPane usuariosPane;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button venta_limpiarLabelBtn;

    @FXML
    private Button proveedores_limpiarBtn;

    @FXML
    private ComboBox<?> ajustes_departamentoCB;

    @FXML
    private TableView<?> productosMasVendidosTV;

    @FXML
    private TextField venta_descuentoLabel;

    @FXML
    private TextArea ajustes_direccionTA;

    @FXML
    private Button btnInicio;

    @FXML
    private Button proveedores_limpiarBtn1;

    @FXML
    private Button btnUsuarios;

    @FXML
    private TableColumn<?, ?> ultimosProducto_Producto;

    @FXML
    private Button proveedores_eliminarBtn;

    @FXML
    private Button usuarios_limpiarBtn;

    @FXML
    private TextField ajustes_rifTF;

    @FXML
    private TableView<?> estados_tablaLista;

    @FXML
    private TableView<?> ultimosProductosVendidosTV;

    @FXML
    private Button marcas_agregarBtn;

    @FXML
    private Label ventasTotalesLabel;

    @FXML
    private TextField ventas_cantidadLabel;

    @FXML
    private Button medida_agregarBtn;

    @FXML
    private AnchorPane inicioPane;

    @FXML
    private TextField marcas_nombreTF;

    @FXML
    private Button proveedores_agregarBtn1;

    @FXML
    private TextField usuarios_buscarTF;

    @FXML
    private TextField estados_nombreTF;

    @FXML
    private TableView<?> proveedores_tablaProveedores;

    @FXML
    private TextField venta_subTotalLabel;

    @FXML
    private TextField producto_nombreTF;

    @FXML
    private TableView<?> estadisticas_tablaMejores;

    @FXML
    private TextField venta_TotalLabel;

    @FXML
    private TextField productos_buscarProductoTF;

    @FXML
    private AnchorPane ventaPane;

    @FXML
    private DatePicker venta_fechaHistorialDatePicker;

    @FXML
    private AnchorPane northPane;

    @FXML
    private TextField proveedores_correoTF;

    @FXML
    private Button venta_reporteBtn;

    @FXML
    private Button btnVenta;

    @FXML
    private ComboBox<?> producto_proveedorCB;

    @FXML
    private Label currentUserLabel;

    @FXML
    private Button producto_eliminarBtn;

    @FXML
    private Button proveedores_agregarBtn;

    @FXML
    private TextField proveedores_nombreTF;

    @FXML
    private ComboBox<?> estadisticas_elementoCB;

    @FXML
    private Button marcas_eliminarBtn;

    @FXML
    private TextField producto_stockTF;

    @FXML
    private TableView<?> usuarios_tablaResultado;

    @FXML
    private Button producto_agregarBtn;

    @FXML
    private Button usuarios_eliminarBtn;

    @FXML
    private TableView<?> medida_tablaLista;

    @FXML
    private Button proveedores_modificarBtn;

    @FXML
    private TextField venta_stockRestanteLabel;

    @FXML
    private TextField ajustes_nombreEmpresaTF;

    @FXML
    private Label estadistica_analisisParrafo;

    @FXML
    private Button btnAnalisis;

    @FXML
    private TextField ajustes_correoTF;

    @FXML
    private TableView<?> proveedores_tablaHistorial;

    @FXML
    private Label usuariosRegistradosLabel;

    @FXML
    private TextField venta_productoLabel;

    @FXML
    private TextField usuarios_nombreTF;

    @FXML
    private ComboBox<?> producto_MarcaCB;

    @FXML
    private Button medida_eliminarBtn;

    @FXML
    private TableView<?> marcas_tablaLista;

    @FXML
    private TableView<?> venta_tablaHistorial;

    @FXML
    private Label productosTotalesLabel;

    @FXML
    private TextField usuarios_passTF;

    @FXML
    private TextField proveedores_apellidoTF;

    @FXML
    private AnchorPane productosPane;

    @FXML
    private AnchorPane analisisPane;

    @FXML
    private Button usuarios_modificarBtn;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private TextField ajustes_telefonoTF;

    @FXML
    private ComboBox<?> usuarios_rolCB;

    @FXML
    private Button usuarios_agregarBtn;

    @FXML
    private TableView<?> productos_tablaResultadosBusqueda;

    @FXML
    private Label tituloEmpresaLabel;

    @FXML
    private Button estados_agregarBtn;

    @FXML
    private LineChart<?, ?> actividadesVentaLineChart;

    @FXML
    private TableView<?> venta_tablaVentaDatos;

    @FXML
    private Button producto_modificarBtn;

    @FXML
    private TextArea venta_observacionesLabel;

    @FXML
    private TextField venta_marcaLabel;

    @FXML
    private Button btnAjustes;

    @FXML
    private ComboBox<?> producto_unidadMedidaCB;

    @FXML
    private TableColumn<?, ?> productosMasVendidos_Producto;

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<?, ?> productosMasVendidos_TotalVentas;

    @FXML
    private Button btnProductos;

    @FXML
    private TextField proveedores_telefonoTF;

    @FXML
    private AnchorPane ajustesPane;

    @FXML
    private Button venta_ventaRealizadaBtn;

    @FXML
    private TableColumn<?, ?> ultimosProducto_FechayHora;

    @FXML
    private Button venta_agregarVentaBtn;

    @FXML
    private TextField medida_nombreTF;

    @FXML
    private Button estados_eliminarBtn;

    @FXML
    private Button producto_limpiarTF;

    //Metodo que se ejecuta al cargar el scene
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //ponemos el nombre en el panel superior
        System.out.println(CurrentLogin.getCurrentEmpresa().getNombre());
        tituloEmpresaLabel.setText(CurrentLogin.getCurrentEmpresa().getNombre());
        currentUserLabel.setText(CurrentLogin.getCurrentUsuario().getUsuario());
        //Panel de Inicio
        actualizarDatosGenerales();
    }

    //Metodo para actualizar los datos generales de la ventana de inicio
    public void actualizarDatosGenerales() {
        try {
            //actualizar label de datos generales
            ventasTotalesLabel.setText(Facade.totalVentas("V1252457895") + "");
            productosTotalesLabel.setText(Facade.totalProductos("V1252457895") + "");
            usuariosRegistradosLabel.setText(Facade.totalUsuarios("V1252457895") + "");

            //actualizar tablas de datos
            rellenarTablaUltimosProductos();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void rellenarTablaUltimosProductos() {
        try {
            Fuller.llenarTableView(ultimosProductosVendidosTV, Facade.topÚltimosProductos("V1252457895"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error al rellenar tabla de ultimos productos");
        }
    }

    //Funcionalidad de la navegación
    public void handleMenuButton(ActionEvent e) {
        if (e.getSource() == btnInicio) {
            setActive(btnInicio);
            inicioPane.toFront();
        }

        if (e.getSource() == btnVenta) {
            setActive(btnVenta);
            ventaPane.toFront();
        }

        if (e.getSource() == btnProductos) {
            setActive(btnProductos);
            productosPane.toFront();
        }


        if (e.getSource() == btnUsuarios) {
            setActive(btnUsuarios);
            usuariosPane.toFront();
        }

        if (e.getSource() == btnAnalisis) {
            setActive(btnAnalisis);
            analisisPane.toFront();
        }

        if (e.getSource() == btnAjustes) {
            setActive(btnAjustes);
            ajustesPane.toFront();
        }
    }

    private void setActive(Button b) {
        ArrayList<Button> menuBotones = new ArrayList<>();
        menuBotones.add(btnInicio);
        menuBotones.add(btnVenta);
        menuBotones.add(btnAnalisis);
        menuBotones.add(btnProductos);
        menuBotones.add(btnAjustes);
        menuBotones.add(btnUsuarios);

        for (Button button : menuBotones) {
            if (button == b) {
                button.setStyle("-fx-background-color: rgba(0,0,0,.35)");
            } else {
                button.setStyle("-fx-background-color: transparent");
            }
        }
        menuBotones.clear();
    }

    //Funcionalidad para arrastrar la ventana
    public void onMouseDragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    //Metodo para cerrar la aplicación al presionar el boton
    public void closeApp(ActionEvent e) {
        System.exit(0);
    }

    public void minimizeApp(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    //metodo para minimizar la aplicacion

}