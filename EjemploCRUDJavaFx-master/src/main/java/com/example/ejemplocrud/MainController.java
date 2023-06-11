package com.example.ejemplocrud;


import Asignaciones.Clases;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;





public class MainController implements Initializable {

    @FXML
    private TableView<Clases> TableView;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Clases, Integer> capClm;

    @FXML
    private TextField capField;

    @FXML
    private TextField classField;

    @FXML
    private TextField commentField;

    @FXML
    private TableColumn<Clases, String> commentsClm;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Clases, Integer> idColumn;

    @FXML
    private TextField idField;

    @FXML
    private Button modifyButton;

    @FXML
    private TableColumn<Clases, String> timeColumn;

    @FXML
    private TextField subjField;

    @FXML
    private TableColumn<Clases, String> subjectClm;

    @FXML
    private TableColumn<Clases, String> teacherClm;

    @FXML
    private TextField teacherField;

    @FXML
    private TextField timeField;

    @FXML
    private TableColumn<Clases, String> aulaColumn;


    @FXML
    private void addButton() {
        String query = "insert into horarios values("+idField.getText()+",'"+teacherField.getText()+"','"+subjField.getText()+"','"+classField.getText()+"','"+timeField.getText()+"',"+capField.getText()+",'"+commentField.getText()+"')";
        executeQuery(query);
        showHorarios();
    }


    @FXML
    private void modifyButton() {
        String query = "UPDATE horarios SET profesor='"+teacherField.getText()+"',asignatura='"+subjField.getText()+"',aula='"+classField.getText()+"',hora='"+timeField.getText()+"',capacidad="+capField.getText()+",comentarios='"+commentField.getText()+"' WHERE Id="+idField.getText()+"";
        executeQuery(query);
        showHorarios();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM horarios WHERE Id="+idField.getText()+"";
        executeQuery(query);
        showHorarios();
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showHorarios();
    }

    public Connection getConnection() {
        Connection conn;
        try {
           // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","2703");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/asignacionaulas";
            String username = "root";
            String password = "";

            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Clases> getClasesList(){
        ObservableList<Clases> clasesList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM horarios ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Clases clases;
            while(rs.next()) {
                clases = new Clases(rs.getInt("Id"),rs.getString("profesor"),rs.getString("asignatura"),rs.getString("aula"),rs.getString("hora"),rs.getInt("capacidad"),rs.getString("comentarios"));
                clasesList.add(clases);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clasesList;
    }

    public void showHorarios() {
        ObservableList<Clases> list = getClasesList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Clases,Integer>("Id"));
        teacherClm.setCellValueFactory(new PropertyValueFactory<Clases,String>("profesor"));
        subjectClm.setCellValueFactory(new PropertyValueFactory<Clases,String>("asignatura"));
        aulaColumn.setCellValueFactory(new PropertyValueFactory<Clases,String>("aula"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Clases,String>("hora"));
        capClm.setCellValueFactory(new PropertyValueFactory<Clases,Integer>("capacidad"));
        commentsClm.setCellValueFactory(new PropertyValueFactory<Clases,String>("comentarios"));

        TableView.setItems(list);
    }

}
