/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pp3.st.vistas;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import pp3.st.Main;
import pp3.st.conexion.ClienteDB;
import pp3.st.conexion.ServicioDB;
import pp3.st.conexion.TipoServicioDB;
import pp3.st.modelo.Cliente;
import pp3.st.modelo.Estado;
import pp3.st.modelo.Servicio;
import pp3.st.modelo.TipoServicio;

/**
 * FXML Controller class
 *
 * @author Matyas
 */
public class GestionController1 implements Initializable {

    private ObservableList<Cliente> lista = FXCollections.observableArrayList();
    private ObservableList<TipoServicio> listaTS = FXCollections.observableArrayList();
    private ObservableList<String> listaProductos = FXCollections.observableArrayList();
    private ObservableList<Servicio> listaServicio = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Cliente, Integer> colId;
    @FXML
    private TableColumn<Cliente, String> colDni;
    @FXML
    private TableColumn<Cliente, String> colApellido;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TextField tfBuscador;
    @FXML
    private TextField tfDni;
    @FXML
    private TextField tfApellido;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btNuevoCliente;
    @FXML
    private Button btEditarCliente;
    @FXML
    private Button btLimpiarCamposC;
    @FXML
    private TableView<Cliente> tablaClientes;
    private Main main;
    @FXML
    private ComboBox<TipoServicio> cmbTipo;
    @FXML
    private DatePicker dateEstimada;
    @FXML
    private TextArea txtDetalle;
    @FXML
    private TextField tfNumRma;
    @FXML
    private TextField tfCosto;
    @FXML
    private ComboBox<String> cmbTipoProd;
    @FXML
    private TableView<Servicio> tablaServicios;
    @FXML
    private TableColumn<Servicio, Integer> colServicioId;
    @FXML
    private TableColumn<Servicio, LocalDate> colServicioFechaEst;
//    @FXML
//    private TableColumn<Servicio, LocalDate> colServicioFechaRec;
    @FXML
    private TableColumn<Servicio, String> colServicioDetalle;
    @FXML
    private TableColumn<Servicio, String> colServicioEstado;
    @FXML
    private Button btExplorarServicio;
    @FXML
    private Button btEliminarServicio;
    @FXML
    private Button btEliminarCliente;
    @FXML
    private Label labelIdCliente;

    ClienteDB dao = new ClienteDB();
    TipoServicioDB daoTS = new TipoServicioDB();
    ServicioDB daoServicio = new ServicioDB();

    private Integer idSumado;
    private Integer idServicioSumado;
    private Integer estadoServicio;

    @FXML
    private TextField tfIdServicio;
    @FXML
    private TableColumn<Servicio, LocalDate> colServicioFechaRec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        idClienteSumado();
        setLabelIdCliente();
        configurarTablas();

        idServicioSumado();
        setTextFieldIdTipoServicio();

        listaTS.setAll(daoTS.buscarTodos());
        cmbTipo.setItems(listaTS);
        llenarListaTipoProductos();
        cmbTipoProd.setItems(listaProductos);

        listeners();

    }

    private void llenarListaTipoProductos() {
        listaProductos.add("Periférico");
        listaProductos.add("PC ");
        listaProductos.add("Notebook");
        listaProductos.add("Otro");

    }

    private void idClienteSumado() {
        this.idSumado = dao.traerUltimoIdCliente() + 1;
    }

    private void setLabelIdCliente() {
        labelIdCliente.setText(String.valueOf(idSumado));
    }

    //servicio bd
    private void idServicioSumado() {

        this.idServicioSumado = daoServicio.traerUltimoIdServicio() + 1;
        tfIdServicio.setText(this.idServicioSumado.toString());
    }

    private void setTextFieldIdTipoServicio() {
        tfIdServicio.setText(String.valueOf(idServicioSumado));
    }

    private void configurarTablas() {
        colId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));
        colDni.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));

        colServicioId.setCellValueFactory(new PropertyValueFactory<Servicio, Integer>("idservicio"));
        colServicioDetalle.setCellValueFactory(new PropertyValueFactory<Servicio, String>("detalle"));
        colServicioEstado.setCellValueFactory(new PropertyValueFactory<Servicio, String>("estado"));
        colServicioFechaEst.setCellValueFactory(new PropertyValueFactory<Servicio, LocalDate>("fechaServicio"));
        colServicioFechaRec.setCellValueFactory(new PropertyValueFactory<Servicio, LocalDate>("fechaServicioRec"));
        lista.addAll(dao.buscarTodos());
        tablaClientes.setItems(lista);

    }

    @FXML
    private void tfBuscar(KeyEvent event) {
        lista.clear();
        lista.addAll(dao.buscarTodos());
        tablaClientes.setItems(lista);

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Cliente> filteredData = new FilteredList<Cliente>(lista, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfBuscador.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filteredData.setPredicate((Cliente cliente) -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (cliente.getApellido().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (cliente.getDni().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tablaClientes.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablaClientes.setItems(sortedData);

    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private boolean validarInsertarCliente() {
        if (tfDni.getText() == "" || tfDni.getText().matches("A-Z")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Falta tipo servicio ");
            alert.setContentText("Por favor seleccione el tipo de servicio de la nueva solicitud\n");
            return false;
        }
        return true;

    }

    //Boton Nuevo Cliente
    @FXML
    private void onNuevoCliente(ActionEvent event) {

        try {
            
            String dni = tfDni.getText();
            Integer idCliente = dao.traerUltimoIdCliente();
            System.out.println("Número de ID traido de la BD: " + idCliente);

            if (dni.isEmpty() || dni.length()<=8 || dni.matches("[A-Z,a-z]")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha podido editar el cliente");
                alert.setContentText("Posible error en la conexión con la base de datos o valores incorrectos\n");

                alert.showAndWait();
            }

            if (nuevoClienteOk()) {
                System.out.println("Cliente insertado");
                limpiarCamposNuevoCliente();
                tablaClientes.setItems(dao.buscarTodos());

            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    //INSERTA CLIENTE EN BASE DE DATOS  - LLAMADA EN onNuevoCliente() 
    private boolean nuevoClienteOk() {
        Cliente c = null;

        c = getTextFieldsCliente(false);

        System.out.println("Datos cliente a insertar: \n");
        c.toString();
        dao.insertarCliente(c);
        lista.clear();
        idClienteSumado();

        return true;
    }

    //INSERTA SERVICIO EN BASE DE DATOS  - LLAMADA EN onNuevoServicio() 
    private boolean nuevoServicioOk() {
        Cliente c = tablaClientes.getSelectionModel().getSelectedItem();
        Servicio s = new Servicio();
        Estado e = new Estado(1, "Pendiente");
        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            System.out.println("Datos cliente a insertar:" + c.toString() + "\n");

            s.setIdservicio(Integer.parseInt(tfIdServicio.getText()));
            s.setTipoServicio(cmbTipo.getValue());
            s.setFechaServicio(dateEstimada.getValue());
            s.setDetalle(txtDetalle.getText());
            s.setNumFactura(tfNumRma.getText());
            s.setCosto(tfCosto.getText());
            s.setCliente(c);
            s.setEstado(e);

            if (daoServicio.insertarServicio(s)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se ha registrado una nueva solicitud de servicio técnico");
                alert.setContentText("Cliente: " + c.getApellido() + " " + c.getNombre());
                listaServicio.clear();
                idServicioSumado();
                limpiarCamposNuevoServicio();
                return true;
            }

        }
        return false;

    }

    //BOTON EDITAR CLIENTE
    @FXML
    private void onEditarCliente(ActionEvent event) {
        Cliente c;
        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            c = tablaClientes.getSelectionModel().getSelectedItem();
            if (editarClienteOk()) {
                limpiarCamposNuevoCliente();
                tablaClientes.setItems(dao.buscarTodos());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se han editado los datos del cliente");
                alert.setContentText("");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha podido editar el cliente");
                alert.setContentText("Posible error en la conexión con la base de datos\n");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Ningún cliente seleccionado");
            alert.setContentText("Por favor seleccione un elemento de la lista de clientes\n");

            alert.showAndWait();
        }

    }

    //CAMBIAR ESTADO DE SERVICIO TECNICO DE PENDIENTE A FINALIZADO O ENTREGADO
    private void onEditarServicioEstado(ActionEvent event) {

        if (tablaServicios.getSelectionModel().getSelectedItem() != null) {
            Servicio s;
            s = tablaServicios.getSelectionModel().getSelectedItem();
            if (editarServicioEstadoOk(s, this.estadoServicio)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se ha finalizado el servicio técnico seleccionado");
                alert.setContentText("");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Ningún servicio seleccionado");
            alert.setContentText("Por favor seleccione un elemento de la tabla de servicios\n");

            alert.showAndWait();
        }

    }

    //EDITA CLIENTE EN BASE DE DATOS - LLAMADA EN on EditarCliente()
    private boolean editarClienteOk() {

        if (dao.editarCliente(getTextFieldsCliente(true))) {
            lista.clear();
            idClienteSumado();

            return true;
        } else {
            return false;
        }

    }

    //EDITA SERVICIO EN BASE DE DATOS A FINALIZADO- LLAMADA EN on EditarServicioFinalizado()
    private boolean editarServicioEstadoOk(Servicio s, int estado) {

        if (daoServicio.editarServicioEstado(s, estado)) {
            listaServicio.clear();

            return true;
        } else {
            return false;
        }

    }

    //DAR BAJA A UN CLIENTE
    @FXML
    private void onEliminarCliente(ActionEvent event) {
        Cliente c;
        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            c = tablaClientes.getSelectionModel().getSelectedItem();
            if (eliminarClienteOk()) {
                limpiarCamposNuevoCliente();
                tablaClientes.setItems(dao.buscarTodos());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setHeaderText("Se ha dado de baja un cliente");
                alert.setContentText("");

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha podido dar de baja al cliente");
                alert.setContentText("Posible error en la conexión con la base de datos\n");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Ningún cliente seleccionado");
            alert.setContentText("Por favor seleccione un elemento de la lista de clientes\n");

            alert.showAndWait();
        }
    }

    //ELIMINAR CLIENTE EN BASE DE DATOS - LLAMADA EN onEliminarCliente()
    private boolean eliminarClienteOk() {

        if (dao.bajaCliente(getTextFieldsCliente(true))) {
            lista.clear();
            idClienteSumado();

            return true;
        } else {
            return false;
        }

    }

    @FXML
    public void limpiarCamposNuevoCliente() {
        setLabelIdCliente();
        idClienteSumado();
        tfDni.setText("");
        tfApellido.setText("");
        tfNombre.setText("");
        tfTelefono.setText("");
        tfEmail.setText("");
        tablaClientes.refresh();
        idClienteSumado();

    }

    public void limpiarCamposNuevoServicio() {
        tfNumRma.setText("");
        txtDetalle.setText("");
        tfCosto.setText("");

    }

    public Cliente getTextFieldsCliente(boolean editando) {

        if (editando) {
            Integer idClienteE = tablaClientes.getSelectionModel().getSelectedItem().getIdCliente();
            String dni = tfDni.getText();
            String apellido = tfApellido.getText();
            String nombre = tfNombre.getText();
            String telefono = tfTelefono.getText();
            String email = tfEmail.getText();

            Cliente c = new Cliente(idClienteE, dni, apellido, nombre, telefono, email);

            return c;
        } else {
            Integer idCliente = dao.traerUltimoIdCliente() + 1;
            String dni = tfDni.getText();
            String apellido = tfApellido.getText();
            String nombre = tfNombre.getText();
            String telefono = tfTelefono.getText();
            String email = tfEmail.getText();

            Cliente c = new Cliente(idCliente, dni, apellido, nombre, telefono, email);

            return c;
        }

    }

    private void listeners() {
        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setTextFieldsClientes(newValue));

        tablaClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setTablaServicios(newValue));

    }

    private boolean validarRegistrarServicio() {
        if (dateEstimada.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Campo fecha vacío");
            alert.setContentText("Por favor seleccione una fecha estimada en la que estará finalizado el servicio\n");

            alert.showAndWait();
            return false;
        }
        if (cmbTipo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atención");
            alert.setHeaderText("Falta tipo servicio ");
            alert.setContentText("Por favor seleccione el tipo de servicio de la nueva solicitud\n");
            return false;
        }

        return true;
    }

    @FXML
    private void onRegistrar(ActionEvent event) {

        if (validarRegistrarServicio()) {
            if (tablaClientes.getSelectionModel().getSelectedItem() != null) {

                if (nuevoServicioOk()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atención");
                    alert.setHeaderText("Se ha registrado un nuevo servicio correctamente");
                    alert.setContentText("");

                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atención");
                alert.setHeaderText("No se ha seleccionado cliente");
                alert.setContentText("Por favor seleccione al cliente que desea registrar una nueva solicitud");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atención");
            alert.setHeaderText("No ha sido posible registrar la nueva solicitud");
            alert.setContentText("Por favor revise los campos e intente nuevamente");

            alert.showAndWait();

        }
    }

    @FXML
    private void onImprimir(ActionEvent event) {
    }

    @FXML
    private void onEnviarEmail(ActionEvent event) {
    }

    @FXML
    private void onExplorarItemServicio(ActionEvent event) {
    }

    //SETEA EN LOS CAMPOS SEGUN EL CLIENTE SELECCIONADO DE LA TABLA
    private void setTextFieldsClientes(Cliente c) {

        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            labelIdCliente.setText(String.valueOf(c.getIdCliente()));
            tfDni.setText(c.getDni());
            tfApellido.setText(c.getApellido());
            tfNombre.setText(c.getNombre());
            tfTelefono.setText(c.getTelefono());
            tfEmail.setText(c.getEmail());

        }

    }

    private void setTablaServicios(Cliente c) {
        listaServicio.clear();
        if (tablaClientes.getSelectionModel().getSelectedItem() != null) {
            c = tablaClientes.getSelectionModel().getSelectedItem();
            listaServicio.addAll(daoServicio.buscarTodos(c));
            tablaServicios.setItems(listaServicio);

        }

    }

    private boolean eliminarServicio() {
        Servicio s = tablaServicios.getSelectionModel().getSelectedItem();

        if (daoServicio.eliminarServicioEstado(s)) {
            listaServicio.clear();
            tablaServicios.refresh();
        }

        return true;
    }

    @FXML
    private void onEliminarServicio(ActionEvent event) {

        if (tablaServicios.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Atención");
            alerta.setHeaderText("No hay seleccionado ningún servicio de la tabla");
            alerta.setContentText("Por favor seleccione el servicio que desea eliminar");

            alerta.showAndWait();
        } else if (eliminarServicio()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("Se ha eliminado correctamente el servicio");
            alert.setContentText("");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setHeaderText("No se ha eliminado correctamente el servicio");
            alert.setContentText("");
        }

    }

    public Integer getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(Integer estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    @FXML
    private void onServicioFinalizado(ActionEvent event) {
        this.setEstadoServicio(2);
        onEditarServicioEstado(null);
    }

    @FXML
    private void onServicioEntregado(ActionEvent event) {
        this.setEstadoServicio(3);
        onEditarServicioEstado(null);
    }

}
