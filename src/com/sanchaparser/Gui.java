/*
**     _   _ _           _    _
**    /_\ | | |_____ __ | |  (_)_ __  __ _
**   / _ \| | / -_) \ / | |__| | '  \/ _` |
**  /_/ \_\_|_\___/_\_\ |____|_|_|_|_\__,_|
**
**  By Allex Lima <allexlima@unn.edu.br> | www.allexlima.com
**
**
**  Interface Gráfica com Java Swing para uma mini IDE Cpp
**  a fim de ser utilizada no projeto SanchaCppParser, um
**  analisador sintático para a linguagem de programação C++
**  escrito em Java por meio do framework JavaCC
**
**  ~ Conheça o projeto: https://github.com/pauloigormoraes/SanchaCppParser
**
**  --
**  Meu GiHub: https://github.com/allexlima
*/

package com.sanchaparser;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

import sintatico.*;
import lexico.*;

interface GerenciamentoArquivos{
    void actionAbrirArquivo();
    void actionSalvarArquivo();
}

abstract class Interface extends JFrame implements GerenciamentoArquivos{
    abstract void setFavicon();
    abstract void setMenuToolBar();
}

class Gui extends Interface{

    private RSyntaxTextArea entrada;
    private Container janela;
    private String arquivo = null;

    Gui(String tituloDaJanela){
        setTitle(tituloDaJanela);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela = getContentPane();

        setFavicon();
        setMenuToolBar();
        setTextarea();

        setSize(500, 600);
        setMinimumSize(new Dimension(500, 600));
        setVisible(true);
    }

    public void setTextarea(){
        entrada = new RSyntaxTextArea("/*\n** Escreva seu programa aqui...\n*/\n");
        entrada.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        entrada.setCodeFoldingEnabled(true);
        entrada.setTabSize(2);
        entrada.setLineWrap(true);
        RTextScrollPane entradaIde = new RTextScrollPane(entrada);

        janela.add(entradaIde);
    }

    public void setFavicon(){
        java.net.URL url = ClassLoader.getSystemResource("com/sanchaparser/favicon.png");

        try {
            ImageIcon icon = new ImageIcon(url);
            setIconImage(icon.getImage());
        }catch (Exception e) {
            System.out.println("Erro ao definir ícone: " + e);
        }finally {
            System.out.println("Ícone: " + url);
        }
    }

    public void setMenuToolBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        JMenu ferramentas = new JMenu("Ferramentas");
        JMenu sobre = new JMenu("Sobre");

        menuBar.add(arquivo);
        menuBar.add(ferramentas);
        menuBar.add(sobre);

        JMenuItem aAbrir = new JMenuItem("Abrir");
        JMenuItem aSalvar = new JMenuItem("Salvar");
        JMenuItem fLexemas = new JMenuItem("Analisar lexemas");
        JMenuItem fAnalisar = new JMenuItem("Analisar sintaxe");
        JMenuItem fCompilar = new JMenuItem("Compilar");
        JMenuItem fConfiguracoes = new JMenuItem("Configurações");
        JMenuItem sGithub = new JMenuItem("GitHub");
        JMenuItem sSobre = new JMenuItem("Sobre");

        fCompilar.setEnabled(false);
        fConfiguracoes.setEnabled(false);

        arquivo.add(aAbrir);
        arquivo.add(aSalvar);
        ferramentas.add(fLexemas);
        ferramentas.add(fAnalisar);
        ferramentas.add(fCompilar);
        ferramentas.addSeparator();
        ferramentas.add(fConfiguracoes);
        sobre.add(sGithub);
        sobre.add(sSobre);

        aAbrir.addActionListener(actionEvent -> this.actionAbrirArquivo());
        aSalvar.addActionListener(actionEvent -> this.actionSalvarArquivo());
        fAnalisar.addActionListener(actionEvent -> this.actionAnaliseSintatica());
        sGithub.addActionListener(actionEvent -> this.actionGithub());
        sSobre.addActionListener(actionEvent -> this.actionSobre());

        setJMenuBar(menuBar);
    }

    public void actionAbrirArquivo(){
        JFileChooser fileChooser = new JFileChooser("./models");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (.txt) ", "txt", "text");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            this.arquivo = file.toString();
            this.entrada.setText("");
            updateTitle(file.toString());
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                try {
                    String line = in.readLine();
                    while (line != null) {
                        this.entrada.append(line + "\n");
                        line = in.readLine();
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Erro: " + e);
                }
            }catch (FileNotFoundException e){
                JOptionPane.showMessageDialog(this, "Erro: " + e);
            }
        }
    }

    public void actionSalvarArquivo(){
        JFileChooser fileChooser = new JFileChooser("./models");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (.txt) ", "txt", "text");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            this.arquivo = file.toString() + ".txt";
            try(FileWriter fw = new FileWriter(file + ".txt")) {
                fw.write(getEntrada());
                fw.close();
                updateTitle(file.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTitle(String address){
        String[] parts = address.split("/");
        String file = parts[parts.length - 1];
        setTitle("SanchaCppParser IDE - " + file + ".txt");
    }

    private void saveFile(){
        try(FileWriter fw = new FileWriter(arquivo)) {
            fw.write(getEntrada());
            fw.close();
            updateTitle(arquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actionAnaliseLexica(){
        if(this.arquivo == null){
            JOptionPane.showMessageDialog(this, "Você deve sempre salvar o arquivo antes realizar análises!");
        }else{
            try {
                saveFile();
                setSaida(AnalisadorLexico.LexicoStart(this.arquivo));
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Erro: " + e);
            }
        }
    }

    private void actionAnaliseSintatica(){
        if(this.arquivo == null){
            JOptionPane.showMessageDialog(this, "Você deve sempre salvar o arquivo antes realizar análises!");
        }else{
            try {
                saveFile();
                setSaida(AnalisadorSintatico.SintaticoStart(this.arquivo));
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, "Erro: " + e);
            }
        }
    }

    private void actionGithub(){
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/pauloigormoraes/SanchaAnalyzer").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actionSobre(){
        JOptionPane.showMessageDialog(this,
                "\nTrabalho desenvolvido para compor a nota parcial da 3ª ARE da disciplina de \n" +
                        "Computação Teórica/Compiladores, ministrada pelo Prof.º M.Sc. Camilo Souza" +
                        "\n\nDesenvolvido por: Allex Lima, Daniel Bispo, Paulo Moraes e Renan Barroncas"
        );
    }

    private void setSaida(String mensagem){
        JFrame output = new JFrame("Output");
        output.setSize(300, 400);
        output.setMinimumSize(new Dimension(300, 400));
        output.setVisible(true);

        JTextArea saida = new JTextArea(mensagem);
        saida.setLineWrap(true);
        saida.setTabSize(2);
        saida.setEnabled(false);
        saida.setBackground(Color.decode("#34495e"));
        saida.setFont(new Font(null, 0, 14));

        JScrollPane scrollSaida = new JScrollPane(saida);
        scrollSaida.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        output.getContentPane().add(saida);
    }

    private String getEntrada(){
        return this.entrada.getText();
    }

    public static void main(String args[]){
        new Gui("SanchaCppParser IDE");
    }

}
