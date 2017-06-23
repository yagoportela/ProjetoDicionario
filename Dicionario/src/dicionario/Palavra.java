package dicionario;

public class Palavra{

    private String palavra;
    private String idioma;
    private String traducao;
    private String significados;
    private String derivacao;
    private String locucao;
    public String classeGramatical;

    public Palavra(String palavra) {

        this.palavra = palavra;
        this.idioma = "";
        this.traducao = "";
        this.significados = "";
        this.derivacao = "";
        this.locucao = "";
        this.classeGramatical = "";

    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getPalavra() {
        return palavra;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTraducao() {
        return traducao;
    }

    public void setTraducao(String traducao) {
        this.traducao = traducao;
    }

    public String getSignificados() {
        return significados;
    }

    public void setSignificados(String significados) {
        this.significados = significados;
    }

    public String getDerivacao() {
        return derivacao;
    }

    public void setDerivacao(String derivacao) {
        this.derivacao = derivacao;
    }

    public String getLocucao() {
        return locucao;
    }

    public void setLocucao(String locucao) {
        this.locucao = locucao;
    }
    
    public String getClasseGramatical() {
        return classeGramatical;
    }

    public void setClasseGramatical(String classeGramatical) {
        this.classeGramatical = classeGramatical;
    }
    
}
