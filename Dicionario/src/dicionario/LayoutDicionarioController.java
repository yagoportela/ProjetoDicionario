package dicionario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Nads
 */
public class LayoutDicionarioController implements Initializable {

    private Idioma idioma;
    @FXML
    private Label lIdioma;
    @FXML
    private Label lIdioma2;
    @FXML
    private WebView area;
    @FXML
    private TextField tfCampoPalavra;
    @FXML
    private TableView<ObservableList<String>> tabelaView;
    @FXML
    private TableColumn<ObservableList<String>, String> tabelaColuna;
    @FXML
    private Label lEsperantoAtivado;
    @FXML
    private int idiomaPrincipal;
    private WebEngine webEngine;
    private boolean esperanto = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregarInformacao();
        Inicial();
        Style();
    }

    private void Inicial() {
        tfCampoPalavra.setPromptText("Digite sua palavra...");
        tabelaColuna.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
        idiomaPrincipal = 1;
        webEngine = area.getEngine();
    }

    private void Style() {
        lIdioma.setId("label");
        lIdioma2.setId("label1");
        lEsperantoAtivado.setId("desativado");
    }

    @FXML
    private void EventoIdioma(Event evento) {
        if (idiomaPrincipal != 1) {
            lIdioma.setId("label");
            lIdioma2.setId("label1");
            idiomaPrincipal = 1;
            tfCampoPalavra.setText("");
            webEngine.loadContent("");
            Tabela(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void EventoIdioma1(Event evento) {
        if (idiomaPrincipal != 2) {
            lIdioma.setId("label1");
            lIdioma2.setId("label");
            idiomaPrincipal = 2;
            tfCampoPalavra.setText("");
            webEngine.loadContent("");
            Tabela(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void EventoTextFieldSolto(KeyEvent evento) {

        TrocandoLetras(evento);

        //Pegando as palavras
        String letras = tfCampoPalavra.getText();

        ObservableList<ObservableList<String>> palavras = FXCollections.observableArrayList();

        //se o usuario usar o backSpace para nao da erro
        if (letras.trim().equals("") || letras.trim() == null) {

            Tabela(palavras);
            return;

        }

        if (idiomaPrincipal == 1) {
            for (int i = 0; i < idioma.palavraDicionario1.size(); i++) {

                if (idioma.palavraDicionario1.get(i).getPalavra().trim().toUpperCase().startsWith(letras.trim().toUpperCase())) {
                    ObservableList<String> palavra = FXCollections.observableArrayList();
                    palavra.add(idioma.palavraDicionario1.get(i).getPalavra().trim());
                    palavras.add(palavra);
                }
            }
        } else {
            for (int i = 0; i < idioma.palavraDicionario2.size(); i++) {

                if (idioma.palavraDicionario2.get(i).getPalavra().trim().toUpperCase().startsWith(letras.trim().toUpperCase())) {
                    ObservableList<String> palavra = FXCollections.observableArrayList();
                    palavra.add(idioma.palavraDicionario2.get(i).getPalavra().trim());
                    palavras.add(palavra);
                }
            }
        }

        Tabela(palavras);
    }

    private void TrocandoLetras(KeyEvent evento) {

        if (esperanto) {
            if (!evento.isControlDown()) {
                
                if (evento.getCode() == KeyCode.C) {
                    tfCampoPalavra.deletePreviousChar();
                    if (evento.getText().equals("c")) {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "ĉ");
                    } else {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "Ĉ");
                    }

                }
                if (evento.getCode() == KeyCode.G) {
                    tfCampoPalavra.deletePreviousChar();
                    if (evento.getText().equals("g")) {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "ĝ");
                    } else {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "Ĝ");
                    }
                }
                if (evento.getCode() == KeyCode.H) {
                    tfCampoPalavra.deletePreviousChar();
                    if (evento.getText().equals("h")) {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "ĥ");
                    } else {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "Ĥ");
                    }
                }
                if (evento.getCode() == KeyCode.J) {
                    tfCampoPalavra.deletePreviousChar();
                    if (evento.getText().equals("j")) {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "ĵ");
                    } else {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "Ĵ");
                    }
                }
                if (evento.getCode() == KeyCode.S) {
                    tfCampoPalavra.deletePreviousChar();
                    if (evento.getText().equals("s")) {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "ŝ");
                    } else {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "Ŝ");
                    }
                }
                if (evento.getCode() == KeyCode.U) {
                    tfCampoPalavra.deletePreviousChar();
                    if (evento.getText().equals("u")) {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "ŭ");
                    } else {
                        tfCampoPalavra.insertText(tfCampoPalavra.getAnchor(), "Ŭ");
                    }
                }
            }
        }
    }

    @FXML
    private void EventoEsperanto(KeyEvent evento) {

        if (KeyCombination.keyCombination("ctrl+c").match(evento)) {

            if (esperanto == false) {
                esperanto = true;
                lEsperantoAtivado.setText("Ativado");
                lEsperantoAtivado.setId("ativado");
            } else {
                esperanto = false;
                lEsperantoAtivado.setText("Desativado");
                lEsperantoAtivado.setId("desativado");

            }
        }

//        if (combinacao.match(evento)) {
//        }
    }

    public void Tabela(ObservableList<ObservableList<String>> lista) {

        tabelaView.setItems(lista);
    }

    public void EventoTabela() {

        String valor = (tabelaView.getSelectionModel().getSelectedItem() + "").replace("[", "");
        valor = valor.replace("]", "");
        ObservableList<Palavra> palavra = FXCollections.observableArrayList();

        if (idiomaPrincipal == 1) {
            for (int i = 0; i < idioma.palavraDicionario1.size(); i++) {
                if (idioma.palavraDicionario1.get(i).getPalavra().trim().equals(valor.trim())) {
                    palavra.add(idioma.palavraDicionario1.get(i));
                    LayoutArea(palavra);
                    break;
                }
            }
        } else if (idiomaPrincipal == 2) {

            for (int i = 0; i < idioma.palavraDicionario2.size(); i++) {
                if (idioma.palavraDicionario2.get(i).getPalavra().trim().equals(valor.trim())) {
                    palavra.add(idioma.palavraDicionario2.get(i));
                    LayoutArea(palavra);
                    break;
                }
            }
        }

    }

    public void LayoutArea(ObservableList<Palavra> palavra) {

        webEngine.loadContent("");
        LayoutArea layoutArea = new LayoutArea();
        webEngine.loadContent(layoutArea.LayoutArea(palavra), "text/html");
    }

    public void CarregarInformacao() {

        Banco banco = new Banco();

        if (banco.Temp().equals("")) {
            String[] idiomas = banco.Idiomas();
            int valor = 0;

            if (idiomas != null && idiomas.length != 0) {

                for (int i = 0; i < idiomas.length; i++) {

                    if (idiomas[i].trim().equalsIgnoreCase("portugues")) {
                        valor = i;
                    }
                }

                idioma = banco.Abrir(idiomas[valor]);
                if (idioma != null) {

                    lIdioma.setText(idioma.getDicionario1());
                    lIdioma2.setText(idioma.getDicionario2() + "    ");

                } else {
                    JOptionPane.showMessageDialog(null, "Erro no Idioma");
                    System.exit(0);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Atualize seu banco de dados");
                System.exit(0);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Idioma não encontrado \nAtualiza seu banco de dados");
            System.exit(0);
        }

    }
}
