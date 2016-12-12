package com.sanchaparser;

import java.awt.*;
import java.io.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

class Gui extends JFrame{

    private RSyntaxTextArea entrada;
    private Container janela;

    Gui(String tituloDaJanela){
        super(tituloDaJanela);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela = getContentPane();

        setFavicon();
        setMenuToolBar();
        setTextarea();

        setSize(500, 600);
        setVisible(true);
        //setResizable(false);
    }

    private void setTextarea(){
        entrada = new RSyntaxTextArea("//Escreva seu programa aqui...\n\n");
        entrada.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        entrada.setCodeFoldingEnabled(true);
        entrada.setTabSize(2);
        entrada.setLineWrap(true);
        RTextScrollPane entradaIde = new RTextScrollPane(entrada);

        janela.add(entradaIde);
    }

    private void setFavicon(){
        java.net.URL url = ClassLoader.getSystemResource("com/sanchaparser/img/girl.png");

        try {
            ImageIcon icon = new ImageIcon(url);
            setIconImage(icon.getImage());
        }catch (Exception e) {
            System.out.println("Erro ao definir ícone: " + e);
        }finally {
            System.out.println("Ícone: " + url);
        }
    }

    private void setMenuToolBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        JMenu ferramentas = new JMenu("Ferramentas");
        JMenu sobre = new JMenu("Sobre");

        menuBar.add(arquivo);
        menuBar.add(ferramentas);
        menuBar.add(sobre);

        JMenuItem aAbrir = new JMenuItem("Abrir");
        JMenuItem aSalvar = new JMenuItem("Salvar");
        JMenuItem fAnalisar = new JMenuItem("Analisar sintaxe");
        JMenuItem fCompilar = new JMenuItem("Compilar");
        JMenuItem fConfiguracoes = new JMenuItem("Configurações");
        JMenuItem sGithub = new JMenuItem("GitHub");
        JMenuItem sSobre = new JMenuItem("Sobre");

        fCompilar.setEnabled(false);
        fConfiguracoes.setEnabled(false);

        arquivo.add(aAbrir);
        arquivo.add(aSalvar);
        ferramentas.add(fAnalisar);
        ferramentas.add(fCompilar);
        ferramentas.addSeparator();
        ferramentas.add(fConfiguracoes);
        sobre.add(sGithub);
        sobre.add(sSobre);

        aAbrir.addActionListener(actionEvent -> this.actionAbrirArquivo());
        aSalvar.addActionListener(actionEvent -> this.actionSalvar());
        fAnalisar.addActionListener(actionEvent -> this.actionAnalise());
        sGithub.addActionListener(actionEvent -> this.actionGithub());
        sSobre.addActionListener(actionEvent -> this.actionSobre());

        setJMenuBar(menuBar);
    }

    private void actionAbrirArquivo(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (.txt) ", "txt", "text");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            this.entrada.setText("");
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
    private void actionSalvar(){}
    private void actionAnalise(){
        setSaida("Compilado com sucesso  \n");
    }
    private void actionGithub(){
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/pauloigormoraes/SanchaAnalyzer").toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actionSobre(){
        JOptionPane.showMessageDialog(this, "Mensagem bonitinha aquie o/");
    }
    private void setSaida(String mensagem){
        JFrame output = new JFrame("Output");
        output.setSize(300, 400);
        output.setMinimumSize(new Dimension(300, 400));
        output.setVisible(true);

        JTextArea saida = new JTextArea(mensagem);
        saida.setLineWrap(true);
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
