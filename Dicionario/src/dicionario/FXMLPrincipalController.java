package dicionario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label titulo;
    @FXML
    private Label subTitulo;
    @FXML
    private ComboBox menu;
    private String tela;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tela = "Dicionario";
        menu.setVisible(false);
        try {
            borderPane.setCenter((Parent) FXMLLoader.load(getClass().getResource("LayoutDicionario.fxml")));
        } catch (IOException ex) {
            System.out.println("Erro");
        }
        Style();
        LayoutsMenu();
    }

    private void Style() {
        borderPane.setId("dicionario");
        titulo.setId("titulo");
        subTitulo.setId("titulo");
        menu.setId("botao");
    }

    private void LayoutsMenu() {
        menu.setItems(FXCollections.observableArrayList("Dicionario", "Adicionar"));
        menu.getSelectionModel().select(0);
    }

    @FXML
    private void EventoMenu() {

        if (menu.getSelectionModel().getSelectedItem().toString().equals("Adicionar")) {
            if (!tela.equals("Adicionar")) {
                tela = "Adicionar";
                try {
                    borderPane.setCenter((Parent) FXMLLoader.load(getClass().getResource("LayoutAdicionar.fxml")));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro na pagina", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if (menu.getSelectionModel().getSelectedItem().toString().equals("Dicionario")) {
            if (!tela.equals("Dicionario")) {
                tela = "Dicionario";
                try {
                    borderPane.setCenter((Parent) FXMLLoader.load(getClass().getResource("LayoutDicionario.fxml")));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro na pagina", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
