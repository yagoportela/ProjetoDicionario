package dicionario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class LayoutAdicionarController implements Initializable {

    private Idioma idioma;
    @FXML
    private TextField tfPalavra;
    @FXML
    private TextArea taClasseGramatical;
    @FXML
    private TextArea taTextoClasseGramatical;
    @FXML
    private TextArea taTraducao;
    @FXML
    private TextArea taSignificado;
    @FXML
    private TextArea taDerivacao;
    @FXML
    private TextArea taLocucoes;
    @FXML
    private TextField tfIdioma;
    @FXML
    private ComboBox cbDicionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idioma = new Idioma(JOptionPane.showInputDialog(null, "Idioma para adicionar as palavras"));
        idioma.setDicionario1(JOptionPane.showInputDialog(null, "Dicionario 1"));
        idioma.setDicionario2(JOptionPane.showInputDialog(null, "Dicionario 2"));

        tfIdioma.setText(idioma.getIdioma());
        ObservableList<String> valores = FXCollections.observableArrayList();
        valores.add(idioma.getDicionario1());
        valores.add(idioma.getDicionario2());
        cbDicionario.setItems(valores);
        cbDicionario.setValue(idioma.getDicionario1());
    }

    @FXML
    public void AdicionarPalavras(Event evento) {

        if (!tfPalavra.getText().trim().equals("") && !taClasseGramatical.getText().trim().equals("")) {

                Palavra palavra = new Palavra(tfPalavra.getText());
            
                    palavra.setSignificados(taSignificado.getText());
                    palavra.setDerivacao(taDerivacao.getText());
                    palavra.setLocucao(taLocucoes.getText());
                

            if (cbDicionario.getSelectionModel().getSelectedItem().toString().equals(idioma.getDicionario1())) {                   
                idioma.palavraDicionario1.add(palavra);
            }

            if (cbDicionario.getSelectionModel().getSelectedItem().toString().equals(idioma.getDicionario2())) {
                idioma.palavraDicionario2.add(palavra);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Palavra e classe gramatical não pode esta em branco");
        }

    }

    public void Salvar() {

        Banco banco = new Banco();
        banco.Salvar(idioma);

    }
    
}
