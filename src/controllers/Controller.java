package controllers;

import data.DatosEmpresa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.DB.CurrentLogin;
import models.DB.Empresa;
import models.DB.Facade;
import models.POJO.Producto;
import models.POJO.Proveedor;
import models.POJO.Usuario;
import utils.Cleaner;
import utils.CodigosGenerador;
import utils.Fuller;
import utils.Validator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //Atributos para la funcionalidad de arrastrar la ventana
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private AnchorPane usuariosPane;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button venta_limpiarLabelBtn;

    @FXML
    private Button proveedores_limpiarBtn;


    @FXML
    private TableView<?> productosMasVendidosTV;

    @FXML
    private TextField venta_descuentoLabel;

    @FXML
    private TextArea ajustes_direccionTA;

    @FXML
    private Button btnInicio;

    @FXML
    private TextField producto_agregarStockTF;

    @FXML
    private Button proveedores_limpiarBtn1;

    @FXML
    private Button btnUsuarios;

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
    private PasswordField usuarios_confirmarPass;

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
    private ComboBox<String> producto_proveedorCB;

    @FXML
    private Label currentUserLabel;

    @FXML
    private Button producto_eliminarBtn;

    @FXML
    private Button proveedores_agregarBtn;

    @FXML
    private ComboBox<?> estadisticas_elementoCB;

    @FXML
    private TextField proveedores_nombreTF;

    @FXML
    private Button marcas_eliminarBtn;

    @FXML
    private TableView<?> usuarios_tablaResultado;

    @FXML
    private TextField producto_stockTF;

    @FXML
    private Button usuarios_eliminarBtn;

    @FXML
    private Button producto_agregarBtn;

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
    private Button ajustes_guardarDatosEmpresaBtn;

    @FXML
    private Button medida_eliminarBtn;

    @FXML
    private TableView<?> marcas_tablaLista;

    @FXML
    private TableView<?> venta_tablaHistorial;

    @FXML
    private Button producto_reporteProductosBtn;

    @FXML
    private Label productosTotalesLabel;

    @FXML
    private PasswordField usuarios_passTF;

    @FXML
    private PasswordField usuarios_confirmarPassTF;

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
    private Button producto_agregarStockBtn;

    @FXML
    private TextField ajustes_telefonoTF;

    @FXML
    private ComboBox<?> usuarios_rolCB;

    @FXML
    private Button usuarios_agregarBtn;

    @FXML
    private TextField producto_marcaTF;

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
    private Button producto_modificarBtn1;

    @FXML
    private TextArea venta_observacionesLabel;

    @FXML
    private TextField venta_marcaLabel;

    @FXML
    private Button btnAjustes;

    @FXML
    private ComboBox<String> producto_unidadMedidaCB;

    @FXML
    private ComboBox<String> producto_estadoCB;

    @FXML
    private Button ajustes_editarDatosEmpresaBtn;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnProductos;

    @FXML
    private TextField proveedores_telefonoTF;

    @FXML
    private AnchorPane ajustesPane;

    @FXML
    private Button venta_ventaRealizadaBtn;

    @FXML
    private Button venta_agregarVentaBtn;

    @FXML
    private TextField medida_nombreTF;

    @FXML
    private Button estados_eliminarBtn;

    @FXML
    private Button producto_limpiarTF;


    String RIF;

    //Metodo que se ejecuta al cargar el scene
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DatosEmpresa.crearArchivo(new File("empresadata.dat"));
            System.out.println(DatosEmpresa.obtenerDatosEmpresa());
            //ponemos el nombre en el panel superior
            if (DatosEmpresa.obtenerDatosEmpresa().getNombre() == null) {
                tituloEmpresaLabel.setText("Nombre de empresa");
            } else {
                tituloEmpresaLabel.setText(DatosEmpresa.obtenerDatosEmpresa().getNombre());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentUserLabel.setText(CurrentLogin.getCurrentUsuario().getUsuario());
        actualizarComboBoxs();

        //Panel de Inicio
        actualizarDatosGenerales();

        //Panel de Productos


        //Panel de Usuarios

        //Fuller.llenarComboBox(usuarios_rolCB,Facade);

        //Panel de Ajustes
        try {
            Empresa empresa = Facade.obtenerDatosEmpresa();
            ajustes_nombreEmpresaTF.setText(empresa.getNombre());
            ajustes_rifTF.setText(empresa.getRIF());
            ajustes_correoTF.setText(empresa.getCorreo());
            ajustes_telefonoTF.setText(empresa.getTelefono());

        } catch (SQLException throwables) {
            System.out.println("Error al obtener datos de la empresa");
            throwables.printStackTrace();
        }


        //actualizar tablas
        actualizarTablasInicio();


        //Metodo para busqueda productos
        productos_buscarProductoTF.textProperty().addListener((obs, oldText, newText) -> {
            String busqueda = newText;
            try {
                if (busqueda.isEmpty()) {
                    Fuller.llenarTableView(productos_tablaResultadosBusqueda, Facade.obtenerProductos());

                } else {
                    Fuller.llenarTableView(productos_tablaResultadosBusqueda, Facade.filtrarProductos(busqueda));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


    //Metodo para actualizar los datos generales de la ventana de inicio
    public void actualizarDatosGenerales() {
        try {
            //actualizar label de datos generales
            ventasTotalesLabel.setText(Facade.totalVentas() + "");
            productosTotalesLabel.setText(Facade.totalProductos() + "");
            usuariosRegistradosLabel.setText(Facade.totalUsuarios() + "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Actualizar todos los combobox
    public void actualizarComboBoxs() {
        try {
            //Productos
            Fuller.llenarComboBox(producto_proveedorCB, Facade.obtenerProveedoresList());
            Fuller.llenarComboBox(producto_estadoCB, Facade.obtenerEstadosList());

            //Ajustes
            Fuller.llenarComboBox(producto_unidadMedidaCB, Facade.obtenerMedidasList());

        } catch (SQLException throwables) {
            System.out.println("Error al tratar de llenar combobox");
            throwables.printStackTrace();
        }
    }

    //Metodos para rellenar tablas Inicio
    public void actualizarTablasInicio() {
        try {
            Fuller.llenarTableView(ultimosProductosVendidosTV, Facade.topÚltimosProductos());
            Fuller.llenarTableView(productosMasVendidosTV, Facade.topProductosMasVendidos());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Error al rellenar tabla de panel de Inicio");
        }
    }

    //Metodos para rellenar tablas Productos
    public void actualizarTablasProducto() {
        try {
            Fuller.llenarTableView(proveedores_tablaProveedores, Facade.obtenerProveedores());
            Fuller.llenarTableView(productos_tablaResultadosBusqueda, Facade.obtenerProductos());
        } catch (SQLException throwables) {
            System.out.println("Error al actualizar tablas de panel de producto");
            throwables.printStackTrace();
        }
    }

    //Metodos para rellenar tablas Usuarios
    public void actualizarTablasUsuarios() {
        try {
            Fuller.llenarTableView(usuarios_tablaResultado, Facade.obtenerUsuarios(RIF));
        } catch (SQLException throwables) {
            System.out.println("Error al rellenar tabla en el panel de Usuarios");
            throwables.printStackTrace();
        }
    }

    //Metodos para rellenar tablas Ajustes
    public void actualizarTablasAjustes() {
        try {
            Fuller.llenarTableView(medida_tablaLista, Facade.obtenerMedidas());
            Fuller.llenarTableView(estados_tablaLista, Facade.obtenerEstados());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //Eventos de boton del panel de productos
    public void productosHandleButton(ActionEvent event) {
        //Agregar producto
        if (event.getSource() == producto_agregarBtn) {
            TextField[] tfs = {producto_nombreTF, producto_stockTF, producto_marcaTF};
            ComboBox[] cbs = {producto_proveedorCB, producto_unidadMedidaCB};
            if (Validator.validarTextFields(tfs) || Validator.validarComboBoxs(cbs)) {
                //Crear producto
                Producto p = new Producto();
                p.setIdProducto(CodigosGenerador.getCodigo(0));
                p.setNombre(producto_nombreTF.getText());
                p.setMarca(producto_marcaTF.getText());
                p.setMedida(producto_unidadMedidaCB.getValue());
                p.setCantidad(Integer.parseInt(producto_stockTF.getText()));
                p.setEstado(producto_estadoCB.getValue());

                try {
                    System.out.println(CurrentLogin.getCurrentUsuario().getUsuario());
                    Facade.agregarProducto(p, producto_proveedorCB.getValue(), CurrentLogin.getCurrentUsuario().getUsuario());
                    actualizarTablasProducto();
                    Cleaner.vaciarComboBox(cbs);
                    Cleaner.vaciarTextFields(tfs);
                } catch (SQLException throwables) {
                    System.out.println("Error al agregar producto");
                    JOptionPane.showMessageDialog(null, "Ya existe un producto con ese nombre");
                    throwables.printStackTrace();
                }
            }

        }

        //Limpiar formulario de producto
        if (event.getSource() == producto_limpiarTF) {
            //Limpiar formulario para agregar productos
            TextField[] tfs = {producto_nombreTF, producto_stockTF};
            ComboBox[] cbs = {producto_proveedorCB, producto_unidadMedidaCB};

            Cleaner.vaciarComboBox(cbs);
            Cleaner.vaciarTextFields(tfs);
        }

        //Eliminar producto
        if (event.getSource() == producto_eliminarBtn) {
            String id = productos_tablaResultadosBusqueda.getSelectionModel().getSelectedItem().toString();
            id = id.substring(1, 6);
            System.out.println(id);

            //Si desea eliminar
            try {
                Facade.eliminarProducto(id);
                JOptionPane.showMessageDialog(null, "Producto eliminado con exito.");
            } catch (SQLException throwables) {
                System.out.println("Error al eliminar proveedor");
                throwables.printStackTrace();
            } finally {
                productos_tablaResultadosBusqueda.getColumns().clear();
                actualizarTablasProducto();
                actualizarComboBoxs();
                productos_buscarProductoTF.setText("");
            }
        }

        //Agregar proveedor
        if (event.getSource() == proveedores_agregarBtn) {
            TextField[] tfs = {proveedores_nombreTF, proveedores_apellidoTF, proveedores_correoTF, proveedores_telefonoTF};
            if (Validator.validarTextFields(tfs)) {
                Proveedor p = new Proveedor();
                p.setNombres(proveedores_nombreTF.getText());
                p.setApellidos(proveedores_apellidoTF.getText());
                p.setCorreo(proveedores_correoTF.getText());
                p.setTelefono(proveedores_telefonoTF.getText());
                p.setIdProveedor(CodigosGenerador.getCodigo(1));
                try {
                    Facade.agregarProveedor(p, CurrentLogin.getCurrentUsuario().getUsuario());
                    actualizarTablasProducto();
                    actualizarComboBoxs();
                    Cleaner.vaciarTextFields(tfs);
                } catch (SQLException throwables) {
                    System.out.println("Error al ingresar proveedor");
                    JOptionPane.showMessageDialog(null, "Ya existe un proveedor con ese nombre");
                    throwables.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
            }
        }

        //eliminar proveedor
        if (event.getSource() == proveedores_eliminarBtn) {
            String id = proveedores_tablaProveedores.getSelectionModel().getSelectedItem().toString();
            id = id.substring(1, 11);
            System.out.println(id);

            //Si desea eliminar
            try {
                Facade.eliminarProveedor(id);
                JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito.");
            } catch (SQLException throwables) {
                System.out.println("Error al eliminar proveedor");
                throwables.printStackTrace();
            } finally {
                proveedores_tablaProveedores.getColumns().clear();
                actualizarTablasProducto();
                actualizarComboBoxs();
            }

        }

        //Modificar proveedor
        if (event.getSource() == proveedores_modificarBtn) {
            String nombreProveedor = proveedores_tablaProveedores.getSelectionModel().getSelectedItem().toString();
            System.out.println(nombreProveedor);
            String nombreCompleto = "";
            int comas = 0;
            for (int i = 11; i < nombreProveedor.length(); i++) {

                if (nombreProveedor.charAt(i) == ',') {
                    comas++;
                } else {
                    nombreCompleto += nombreProveedor.charAt(i);
                }
                if (comas == 3) {
                    break;
                }
            }
            nombreCompleto = nombreCompleto.trim();

            try {
                Proveedor proveedorAModificar = Facade.obtenerProveedor(nombreCompleto);

                proveedores_nombreTF.setText(proveedorAModificar.getNombres());
                proveedores_apellidoTF.setText(proveedorAModificar.getApellidos());
                proveedores_correoTF.setText(proveedores_correoTF.getText());
                proveedores_telefonoTF.setText(proveedores_telefonoTF.getText());

                proveedores_agregarBtn.setVisible(false);
                proveedores_agregarBtn1.setVisible(true);
                proveedores_limpiarBtn.setVisible(false);
                proveedores_limpiarBtn1.setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        if (event.getSource() == proveedores_agregarBtn1) {
            //TODO
            TextField[] tfs = {proveedores_nombreTF, proveedores_apellidoTF, proveedores_correoTF, proveedores_telefonoTF};

            if (Validator.validarTextFields(tfs)) {
                Proveedor p = new Proveedor();
                p.setNombres(proveedores_nombreTF.getText());
                p.setApellidos(proveedores_apellidoTF.getText());
                p.setCorreo(proveedores_correoTF.getText());
                p.setTelefono(proveedores_telefonoTF.getText());

                try {
                    Facade.actualizarProveedor(p);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    proveedores_agregarBtn.setVisible(true);
                    proveedores_agregarBtn1.setVisible(false);
                    proveedores_limpiarBtn.setVisible(true);
                    proveedores_limpiarBtn1.setVisible(false);

                    Cleaner.vaciarTextFields(tfs);
                    actualizarTablasProducto();
                }
            }

        }

        //Limpiar formulario proveedores
        if (event.getSource() == proveedores_limpiarBtn) {
            TextField[] tfs = {proveedores_nombreTF, proveedores_apellidoTF, proveedores_apellidoTF, proveedores_telefonoTF};
            Cleaner.vaciarTextFields(tfs);
        }
    }

    //Eventos de botones del panel de usuarios
    public void usuariosHandleButton(ActionEvent event) {
        if (event.getSource() == usuarios_agregarBtn) {
            TextField[] tfs = {usuarios_nombreTF, usuarios_passTF, usuarios_confirmarPassTF};

            if (Validator.validarTextFields(tfs) && usuarios_rolCB.getValue() != null) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(usuarios_nombreTF.getText());
                usuario.setContrasena(usuarios_passTF.getText());
                usuario.setRol(usuarios_rolCB.getValue().toString());
                try {
                    Facade.agregarUsuario(usuario);
                    actualizarTablasUsuarios();
                    actualizarDatosGenerales();
                } catch (SQLException throwables) {
                    System.out.println("Ya existe un usuario con ese nombre");
                    throwables.printStackTrace();
                }
            }
        }
    }

    //Eventos de botones del panel de ajustes
    public void ajustesHandleButton(ActionEvent event) {
        //agregar unidad de medida
        if (event.getSource() == medida_agregarBtn) {
            if (!medida_nombreTF.getText().isEmpty()) {
                try {
                    Facade.agregarMedida(medida_nombreTF.getText());
                    actualizarTablasAjustes();
                    medida_nombreTF.setText("");
                } catch (SQLException throwables) {
                    JOptionPane.showMessageDialog(null, "Ya existe medida con ese nombre");
                    System.out.println("Error al agregar medida");
                    throwables.printStackTrace();
                }

            }
        }
        //agregar estado de producto
        if (event.getSource() == estados_agregarBtn) {
            if (!estados_nombreTF.getText().isEmpty()) {
                try {
                    Facade.agregarMedida(estados_nombreTF.getText());
                    estados_nombreTF.setText("");
                } catch (SQLException throwables) {
                    System.out.println("Error al agregar estado");
                    JOptionPane.showMessageDialog(null, "Ya existe estado con ese nombre");
                    throwables.printStackTrace();
                }
            }
        }

        //Editar datos de la empresa
        if (event.getSource() == ajustes_editarDatosEmpresaBtn) {
            TextField[] tfs = {ajustes_nombreEmpresaTF, ajustes_correoTF, ajustes_telefonoTF, ajustes_rifTF};

            //Permitir editar los campos
            for (TextField tf : tfs) {
                tf.setDisable(false);
            }
            ajustes_direccionTA.setDisable(false);

            //Habilitar boton de guardado y desabilitar el de editar
            ajustes_guardarDatosEmpresaBtn.setDisable(false);
            ajustes_editarDatosEmpresaBtn.setDisable(true);

        }

        //Guardar datos empresa
        if(event.getSource()==ajustes_guardarDatosEmpresaBtn){

        }
    }


    //Evento del boton para cerrarSesion
    public void cerrarSesionHandleButton(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        CurrentLogin.setCurrentUsuario(null);

        Scene registroScene = new Scene(FXMLLoader.load(getClass().getResource("../views/Login.fxml")));
        stage.setScene(registroScene);
        stage.setMaxWidth(800);
        stage.setMaxHeight(600);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
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
            actualizarTablasProducto();
            productosPane.toFront();
        }


        if (e.getSource() == btnUsuarios) {
            setActive(btnUsuarios);
            actualizarTablasUsuarios();
            usuariosPane.toFront();
        }

        if (e.getSource() == btnAnalisis) {
            setActive(btnAnalisis);
            analisisPane.toFront();
        }

        if (e.getSource() == btnAjustes) {
            setActive(btnAjustes);
            actualizarTablasAjustes();
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

    //metodo para minimizar la aplicacion
    public void minimizeApp(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

}