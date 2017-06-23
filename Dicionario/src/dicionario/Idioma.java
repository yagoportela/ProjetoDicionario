package dicionario;

import java.io.Serializable;
import java.util.ArrayList;

public class Idioma implements Serializable {

    private String idioma;
    private String dicionario1;
    private String dicionario2;
    private double versao;
    public ArrayList<Palavra> palavraDicionario1;
    public ArrayList<Palavra> palavraDicionario2;

    public Idioma(String idioma) {
        this.idioma = idioma;
        dicionario1 = "";
        dicionario2 = "";
        versao = 0.00;
        palavraDicionario1 = new ArrayList();
        palavraDicionario2 = new ArrayList();

    }

    public String getDicionario1() {
        return dicionario1;
    }

    public void setDicionario1(String dicionario1) {
        this.dicionario1 = dicionario1;
    }

    public String getDicionario2() {
        return dicionario2;
    }

    public void setDicionario2(String dicionario2) {
        this.dicionario2 = dicionario2;
    }

    public double getVersao() {
        return versao;
    }

    public void setVersao(int versao) {
        this.versao = versao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ArrayList<Palavra> getPalavraDicionario1() {
        return palavraDicionario1;
    }

    public void setPalavraDicionario1(ArrayList<Palavra> palavraDicionario1) {
        this.palavraDicionario1 = palavraDicionario1;
    }

    public ArrayList<Palavra> getPalavraDicionario2() {
        return palavraDicionario2;
    }

    public void setPalavraDicionario2(ArrayList<Palavra> palavraDicionario2) {
        this.palavraDicionario2 = palavraDicionario2;
    }

}
