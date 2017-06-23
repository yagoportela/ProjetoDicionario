package dicionario;

import javafx.collections.ObservableList;

public class LayoutArea {

    private String CSS() {

        String valor = ".tooltip {\n"
                + "    position: relative;"
                + "}"
                + ".tooltip .tooltiptext {"
                + "    visibility: hidden;"
                + "    width: auto;"
                + "    background-color: #555;"
                + "    color: #fff;"
                + "    text-align: center;"
                + "    padding: 5px 0;"
                + "    border-radius: 6px;"
                + ""
                + "    position: absolute;"
                + ""
                + "    opacity: 0;"
                + "    transition: opacity 1s;"
                + "}"
                + ""
                + ".tooltip:hover .tooltiptext {"
                + "    visibility: visible;"
                + "     top:20px;"
                + "    opacity: 1;"
                + "}";

        return valor;
    }

    public String LayoutArea(ObservableList<Palavra> palavra) {

        //Adiconando Classe gramatical
        String valor = "";

        //Fonte
        valor += "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Area principal</title>";
        valor += "<style type=\"text/css\">";
        valor += CSS() + "</style>";
        valor += "</head>";
        valor += "<body border-width: 6px;>";
        valor += "<font face=\"times\">";

        valor += "<font size = \"8\">&emsp;" + Esperanto(palavra.get(0).getPalavra().trim()) + "</font>";

        if (palavra.get(0).getClasseGramatical().length() != 0) {
            palavra.get(0).setClasseGramatical(palavra.get(0).getClasseGramatical().replaceAll("\n", "</br>"));
            palavra.get(0).setClasseGramatical(palavra.get(0).getClasseGramatical().replaceAll(palavra.get(0).getPalavra(), "<b>" + palavra.get(0).getPalavra().trim() + "</b>"));
            valor += "</br><b><font size = \"3\" color=#FFB266>&emsp;" + palavra.get(0).getClasseGramatical() + "<br/></font></b>";
        }
        if (palavra.get(0).getTraducao().length() != 0) {
            palavra.get(0).setTraducao(palavra.get(0).getTraducao().replaceAll("\n", "</br>"));
            palavra.get(0).setTraducao(palavra.get(0).getTraducao().replaceAll(palavra.get(0).getPalavra(), "<b>" + palavra.get(0).getPalavra().trim() + "</b>"));
            valor += "<hr color=3399ff><font size = \"5\" color=#00FF00>&emsp; Tradução: " + "<br/></font></b>" + "<font size = \"5\" color= black><p align=\"justify\">" + palavra.get(0).getTraducao() + "</p></font>";
        }
        if (palavra.get(0).getSignificados().length() != 0) {
            palavra.get(0).setSignificados(palavra.get(0).getSignificados().replaceAll("\n", "</br>"));
            palavra.get(0).setSignificados(palavra.get(0).getSignificados().replaceAll(palavra.get(0).getPalavra(), "<b>" + palavra.get(0).getPalavra().trim() + "</b>"));
            valor += "<hr color=3399ff><font size = \"5\" color=#00FF00>&emsp; Significado: " + "<br/></font></b>" + "<font size = \"5\" color= black><p align=\"justify\">" + palavra.get(0).getSignificados() + "</p></font>";
        }
        if (palavra.get(0).getDerivacao().length() != 0) {
            palavra.get(0).setDerivacao(palavra.get(0).getDerivacao().replaceAll("\n", "</br>"));
            palavra.get(0).setDerivacao(palavra.get(0).getDerivacao().replaceAll(palavra.get(0).getPalavra(), "<b>" + palavra.get(0).getPalavra().trim() + "</b>"));
            valor += "<hr color=3399ff><font size = \"5\" color=#00FF00>&emsp; Derivação: " + "<br/></font></b>" + "<font size = \"5\" color= black><p align=\"justify\">" + palavra.get(0).getDerivacao() + "</p></font>";
        }
        if (palavra.get(0).getLocucao().length() != 0) {
            palavra.get(0).setLocucao(palavra.get(0).getLocucao().replaceAll("\n", "</br>"));
            palavra.get(0).setLocucao(palavra.get(0).getLocucao().replaceAll(palavra.get(0).getPalavra(), "<b>" + palavra.get(0).getPalavra().trim() + "</b>"));
            valor += "<hr color=3399ff><font size = \"5\" color=#00FF00>&emsp; Locução: " + "<br/></font></b>" + "<font size = \"5\" color= black><p align=\"justify\">" + palavra.get(0).getLocucao() + "</p></font>";
        }

        //Fechando a fonte
        valor += "</font>";
        valor += "</body>";
        valor += "</html>";

        return valor;
    }

    private String Esperanto(String palavra) {

        //Separando as palavras pela parte em negrito e a normal
        int m = 0;
        String valor = palavra;
        while (palavra.length() != (m)) {

            char caracteres = palavra.charAt(m);

            if (caracteres == '/') {
                String[] palavraEsperanto = palavra.split("/");
                valor = palavraEsperanto[0];
                valor += "<b>" + palavraEsperanto[1] + "</b>";
                return valor;
            }
            m++;
        }
        return valor;
    }

}
