package dicionario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Banco {

    public void Salvar(Idioma idioma) {

        try {
            //Escrita do XML
            DocumentBuilderFactory documento = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentoBuilder = documento.newDocumentBuilder();
            Document documentoXML = documentoBuilder.newDocument();

            //Raiz
            Element root = documentoXML.createElement("root");
            documentoXML.appendChild(root);

            //Idioma
            Element idiomas = documentoXML.createElement("lingua");
            idiomas.appendChild(documentoXML.createTextNode(idioma.getIdioma()));
            root.appendChild(idiomas);

            //dicionario 1
            Element dicionario1 = documentoXML.createElement("raizDicionario");
            Attr idDicionario1 = documentoXML.createAttribute("id");
            idDicionario1.setValue("" + 1);
            dicionario1.setAttributeNode(idDicionario1);
            root.appendChild(dicionario1);

            //Tradução
            Element traducao = documentoXML.createElement("traducao");
            traducao.appendChild(documentoXML.createTextNode(idioma.getDicionario1()));
            dicionario1.appendChild(traducao);

            for (int i = 0; i < idioma.palavraDicionario1.size(); i++) {

                //Palavra
                Element raizPalavra = documentoXML.createElement("raizPalavra");
                Attr idPalavra = documentoXML.createAttribute("id");
                idPalavra.setValue("" + i);
                raizPalavra.setAttributeNode(idPalavra);
                dicionario1.appendChild(raizPalavra);

                Element palavra = documentoXML.createElement("palavra");
                palavra.appendChild(documentoXML.createTextNode(idioma.palavraDicionario1.get(i).getPalavra()));
                raizPalavra.appendChild(palavra);

                Element palavraTraducao = documentoXML.createElement("palavraTraducao");
                palavraTraducao.appendChild(documentoXML.createTextNode(idioma.palavraDicionario1.get(i).getTraducao()));
                raizPalavra.appendChild(palavraTraducao);

                Element significado = documentoXML.createElement("significado");
                significado.appendChild(documentoXML.createTextNode(idioma.palavraDicionario1.get(i).getSignificados()));
                raizPalavra.appendChild(significado);

                Element derivacao = documentoXML.createElement("derivacao");
                derivacao.appendChild(documentoXML.createTextNode(idioma.palavraDicionario1.get(i).getDerivacao()));
                raizPalavra.appendChild(derivacao);

                Element locucao = documentoXML.createElement("locucao");
                locucao.appendChild(documentoXML.createTextNode(idioma.palavraDicionario1.get(i).getLocucao()));
                raizPalavra.appendChild(locucao);

            }

            //dicionario 1
            Element dicionario2 = documentoXML.createElement("raizDicionario");
            Attr idDicionario2 = documentoXML.createAttribute("id");
            idDicionario2.setValue("" + 2);
            dicionario2.setAttributeNode(idDicionario2);
            root.appendChild(dicionario2);

            //Tradução
            Element traducao2 = documentoXML.createElement("traducao");
            traducao2.appendChild(documentoXML.createTextNode(idioma.getDicionario2()));
            dicionario2.appendChild(traducao2);

            for (int i = 0; i < idioma.palavraDicionario2.size(); i++) {

                //Palavra
                Element raizPalavra = documentoXML.createElement("raizPalavra");
                Attr idPalavra = documentoXML.createAttribute("id");
                idPalavra.setValue("" + i);
                raizPalavra.setAttributeNode(idPalavra);
                dicionario2.appendChild(raizPalavra);

                Element palavra = documentoXML.createElement("palavra");
                palavra.appendChild(documentoXML.createTextNode(idioma.palavraDicionario2.get(i).getPalavra()));
                raizPalavra.appendChild(palavra);

                Element significado = documentoXML.createElement("significado");
                significado.appendChild(documentoXML.createTextNode(idioma.palavraDicionario2.get(i).getSignificados()));
                raizPalavra.appendChild(significado);

                Element derivacao = documentoXML.createElement("derivacao");
                derivacao.appendChild(documentoXML.createTextNode(idioma.palavraDicionario2.get(i).getDerivacao()));
                raizPalavra.appendChild(derivacao);

                Element locucao = documentoXML.createElement("locucao");
                locucao.appendChild(documentoXML.createTextNode(idioma.palavraDicionario2.get(i).getLocucao()));
                raizPalavra.appendChild(locucao);
                
                Element classe = documentoXML.createElement("classeGrmatical");
                classe.appendChild(documentoXML.createTextNode(idioma.palavraDicionario2.get(i).getClasseGramatical()));
                raizPalavra.appendChild(classe);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource documentFont = new DOMSource(documentoXML);

            StreamResult documentoFinal = new StreamResult(new File("banco/" + idioma.getIdioma() + ".xml"));

            transformer.transform(documentFont, documentoFinal);

        } catch (ParserConfigurationException | TransformerException | DOMException ex) {
            JOptionPane.showMessageDialog(null, "Disco protegido erro ao salvar");
        }

    }

    public Idioma Abrir(String bancoIdioma) {

        //Pegando as palavras salvas no arquivo XML
        Idioma idioma = null;
        try {
            
            //Loalização do arqivo XML
            DocumentBuilderFactory documento = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentoBuilder = documento.newDocumentBuilder();
            Document documentoXML = documentoBuilder.parse("banco/Portugues.xml");

            //Pegando os idiomas
            Node noDicionario = documentoXML.getElementsByTagName("root").item(0);

            if (noDicionario.getNodeType() == Node.ELEMENT_NODE) {

                Element raizLingua = (Element) noDicionario;
                Node noLingua = raizLingua.getChildNodes().item(0);

                if (noLingua.getNodeType() == Node.ELEMENT_NODE) {
                    Element lingua = (Element) noLingua;

                    switch (lingua.getTagName()) {

                        case "lingua":
                            //Pegando os idiomas da tradução
                            idioma = new Idioma(lingua.getTextContent());
                            
                            //Pegando as linguas que foram traduzidas
                            for (int i = 0; i < 2; i++) {
                                Node noraizDicionario = documentoXML.getElementsByTagName("raizDicionario").item(i);

                                if (noraizDicionario.getNodeType() == Node.ELEMENT_NODE) {

                                    Element raizDicionario = (Element) noraizDicionario;
                                    Node noTraducao = raizDicionario.getChildNodes().item(0);

                                    if (noTraducao.getNodeType() == Node.ELEMENT_NODE) {
                                        Element traducao = (Element) noTraducao;

                                        switch (traducao.getTagName()) {

                                            case "traducao":
                                                if (i == 0) {
                                                    idioma.setDicionario1(traducao.getTextContent());
                                                } else {
                                                    idioma.setDicionario2(traducao.getTextContent());
                                                }

                                                //Pegando as palavras
                                                NodeList listaPalavra = raizDicionario.getElementsByTagName("raizPalavra");
                                                for (int j = 0; j < listaPalavra.getLength(); j++) {

                                                    Node noRaizPalavra = listaPalavra.item(j);

                                                    if (noRaizPalavra.getNodeType() == Node.ELEMENT_NODE) {

                                                        Element raizPalavra = (Element) noRaizPalavra;
                                                        NodeList listRaizPalavra = raizPalavra.getChildNodes();

                                                        for (int k = 0; k < listRaizPalavra.getLength(); k++) {
                                                            Node noPalavra = raizPalavra.getChildNodes().item(k);

                                                            if (noPalavra.getNodeType() == Node.ELEMENT_NODE) {
                                                                Element palavras = (Element) noPalavra;

                                                                switch (palavras.getTagName()) {

                                                                    case "palavra":

                                                                        if (i == 0) {
                                                                            Palavra palavra = new Palavra(palavras.getTextContent());
                                                                            idioma.palavraDicionario1.add(palavra);
                                                                        } else {
                                                                            Palavra palavra = new Palavra(palavras.getTextContent());
                                                                            idioma.palavraDicionario2.add(palavra);
                                                                        }
                                                                        break;

                                                                    case "traducao":
                                                                        if (i == 0) {
                                                                            idioma.palavraDicionario1.get(j).setTraducao(palavras.getTextContent());
                                                                        } else {
                                                                            idioma.palavraDicionario2.get(j).setTraducao(palavras.getTextContent());
                                                                        }
                                                                        break;

                                                                    case "significado":
                                                                        if (i == 0) {
                                                                            idioma.palavraDicionario1.get(j).setSignificados(palavras.getTextContent());
                                                                        } else {
                                                                            idioma.palavraDicionario2.get(j).setSignificados(palavras.getTextContent());
                                                                        }
                                                                        break;

                                                                    case "derivacao":
                                                                        if (i == 0) {
                                                                            idioma.palavraDicionario1.get(j).setDerivacao(palavras.getTextContent());
                                                                        } else {
                                                                            idioma.palavraDicionario2.get(j).setDerivacao(palavras.getTextContent());
                                                                        }
                                                                        break;

                                                                    case "locucao":
                                                                        if (i == 0) {
                                                                            idioma.palavraDicionario1.get(j).setLocucao(palavras.getTextContent());
                                                                        } else {
                                                                            idioma.palavraDicionario2.get(j).setLocucao(palavras.getTextContent());
                                                                        }
                                                                        break;

                                                                    case "classeGramatical":
                                                                        if (i == 0) {
                                                                            idioma.palavraDicionario1.get(j).setLocucao(palavras.getTextContent());
                                                                        } else {
                                                                            idioma.palavraDicionario2.get(j).setLocucao(palavras.getTextContent());
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                        }

                                    }
                                }
                            }
                            break;
                    }

                }

            }

            //Se acontecer algum tipo de erro no XML
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            JOptionPane.showMessageDialog(null, "Erro no banco");
        }
        return idioma;
    }

    public String Temp() {
        return "";
    }

    public String[] Idiomas() {

        File file = new File("banco/");
        String[] idiomas = file.list();
        return idiomas;

    }

    //Todas letras existentes de um idioma
    public String[] Letra(String idioma) {

        String[] letra = null;

        //Verificando se arquivo existe
        File caminho = new File("banco\\idioma\\" + idioma.trim());
        if (!caminho.exists()) {
            return letra;
        }

        //pegando todas as letras existentes
        File caminhoLetra[] = caminho.listFiles();
        letra = new String[caminhoLetra.length];

        for (int i = 0; i < caminhoLetra.length; i++) {
            String[] quebrandoCaminho = String.valueOf(caminhoLetra[i]).split("\\\\");

            letra[i] = quebrandoCaminho[3].substring(0, 1);

        }

        return letra;
    }

    //Adiconando idioma
    public boolean AdicionarIdioma(String idioma) {

        //Verificando se as palavras estão branco 
        if (idioma == null || idioma.length() == 0 || idioma.equals("")) {
            JOptionPane.showMessageDialog(null, "Não e aceito palavra em branco");
            return false;
        }

        //Criando caminho
        File existenciaCaminho = new File("banco\\idioma\\");
        String[] idiomasSalvos = Idioma();
        if (!existenciaCaminho.exists() || idiomasSalvos.length == 0) {
            try {
                File caminho = new File("banco\\idioma\\" + idioma);
                caminho.mkdirs();
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar idioma");
                return false;
            }
        }

        //Verificando existencia do idioma        
        if (idiomasSalvos.length != 0) {
            for (String idiomasSalvo : idiomasSalvos) {
                if (idioma.trim().equalsIgnoreCase(idiomasSalvo)) {
                    JOptionPane.showMessageDialog(null, "Esse idioma ja existi");
                    return false;
                }
            }
            try {
                File caminho = new File("banco\\idioma\\" + idioma);
                caminho.mkdirs();
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar idioma");
                return false;
            }
        }
        return false;
    }

    //Pegando ultimo idioma salvo
    public String PegUltimoIdiomaUSado() {

        String valor = "";
        try {
            File caminho = new File("banco\\dados\\dados.dat");

            if (caminho.exists() && Idioma().length > 0) {
                DataInputStream procurar = new DataInputStream(new FileInputStream(caminho));
                valor = String.valueOf(procurar.readUTF());
                procurar.close();
                return valor;

            } else {

                if (Idioma().length > 0) {
                    valor = Idioma()[0];
                    return valor;
                } else {

                    return "";
                }
            }
        } catch (IOException e) {

        }

        return "";
    }

    //Salvando ultimo idioma usado
    public boolean DadosIdiomas(String idioma) {

        if (idioma == null || idioma.length() == 0 || idioma.equals("")) {

            return false;
        }
        try {
            File caminho = new File("banco\\dados");

            if (!caminho.exists()) {
                caminho.mkdirs();
            }
            DataOutputStream salvar = new DataOutputStream(new FileOutputStream(caminho + "\\" + "dados.dat"));
            salvar.writeUTF(idioma);
            salvar.flush();
            salvar.close();
            return true;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados");
        }
        return false;
    }

    //Idiomas existentes
    public String[] Idioma() {

        //Instaciado
        String[] idiomas = null;

        //Procurando as pastas existentes na pasta banco
        try {
            File caminho = new File("banco\\idioma\\");
            File listaPastas[] = caminho.listFiles();

            if (caminho.exists()) {
                //Se existir instaciando novamente com a quantidade de pasta existente
                idiomas = new String[listaPastas.length];

                //Passando para uma string
                for (int i = 0; i < listaPastas.length; i++) {
                    //Passar apenas o nome das pastas e não o caminho                
                    String[] lingua = String.valueOf(listaPastas[i]).split("\\\\");
                    idiomas[i] = lingua[2];

                }
                return idiomas;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 001 " + e);
        }

        return idiomas;
    }

    public boolean salvandoPalavras(String valor, String idioma) {

        //Salvando
        try {
            //Se não existir nada salvo
            File pasta = new File("banco\\idioma\\" + idioma);
            if (!pasta.exists()) {
                //Criando diretorio
                pasta.mkdirs();
            }

            //Local do arquivo para armazenar a palavra
            String local = String.valueOf((char) valor.charAt(0));
            DataOutputStream salvar = new DataOutputStream(new FileOutputStream(pasta + "\\" + local.toUpperCase() + ".dat"));

            //Salvando os valores
            salvar.writeUTF(valor);
            salvar.flush();
            salvar.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e + " ERRO");
            return false;
        }
        return true;
    }

    public String PegarTodasPalavras(String palavra, String idioma) {

        String todasPalavras = "";
        String letra = String.valueOf((char) palavra.trim().charAt(0));
        String caminho = "banco\\idioma\\" + idioma + "\\" + letra.toUpperCase() + ".dat";

        //Verificando existencia do arquivo
        File existencia = new File(caminho);
        if (!existencia.exists()) {
            return "";
        }

        //pegando palavra
        try {
            DataInputStream pegar = new DataInputStream(new FileInputStream(caminho));

            todasPalavras += pegar.readUTF();

            pegar.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Palavras não encontrada");
        }

        return todasPalavras;
    }

    public boolean ApagandoArquivos(String palavra, String lingua) {

        String todasPalavras = "";
        String letra = String.valueOf((char) palavra.trim().charAt(0));
        String caminho = "banco\\idioma\\" + lingua + "\\" + letra.toUpperCase() + ".dat";

        //Verificando existencia do arquivo
        File existencia = new File(caminho);
        if (existencia.exists()) {
            existencia.delete();
            return true;
        }

        System.out.println("erro");
        return false;

    }

    public void Importando() {

    }

}
