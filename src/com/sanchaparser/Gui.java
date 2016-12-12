
import com.sanchaparser.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.JFileChooser;

class Gui extends JFrame{

    private JTextArea entrada;

    Gui(){
        super("SanchaCppParser IDE");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("./img/girl.png"));
            setIconImage(icon.getImage());
        }catch (Exception e) {
            System.out.println("Erro ao localizar ícone ./img/girl.png ~ " + e);
        }

        menu();

        Container content = getContentPane();

        JTextArea entrada = new JTextArea("//Type your Cpp Program here...\n\n");
        entrada.setSize(400, 400);
        entrada.setTabSize(2);
        entrada.setPreferredSize(new Dimension(400, 200));
        entrada.setLineWrap(true);

        JScrollPane entradaIde = new JScrollPane(entrada);
        TextLineNumber tln = new TextLineNumber(entrada);
        entradaIde.setRowHeaderView(tln);

        content.add(entradaIde);

        this.entrada = entrada;

        setSize(500, 600);
        setVisible(true);
        setResizable(false);
    }

    private void menu(){
        JMenuBar menuBar = new JMenuBar();

        JMenu arquivo = new JMenu("Arquivo");
        JMenu sobre = new JMenu("Sobre");

        menuBar.add(arquivo);
        menuBar.add(sobre);

        JMenuItem aAbrir = new JMenuItem("Abrir");
        JMenuItem aSalvar = new JMenuItem("Salvar");
        JMenuItem aCompilar = new JMenuItem("Compilar");
        JMenuItem sGithub = new JMenuItem("GitHub");
        JMenuItem sSobre = new JMenuItem("Sobre");

        arquivo.add(aAbrir);
        arquivo.add(aSalvar);
        arquivo.addSeparator();
        arquivo.add(aCompilar);
        sobre.add(sGithub);
        sobre.add(sSobre);

        aAbrir.addActionListener(actionEvent -> this.actionAbrir());
        aSalvar.addActionListener(actionEvent -> this.actionSalvar());
        aCompilar.addActionListener(actionEvent -> this.actionCompilar());
        sGithub.addActionListener(actionEvent -> this.actionGithub());
        sSobre.addActionListener(actionEvent -> this.actionSobre());

        setJMenuBar(menuBar);
    }

    private void actionAbrir(){
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de texto (.txt) ", "txt", "text");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            setEntrada("");
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
    private void actionCompilar(){

        JFrame output = new JFrame("Output");
        output.setSize(300, 400);
        output.setVisible(true);
        output.setResizable(false);

        JTextArea saida = new JTextArea(getEntrada());
        saida.setLineWrap(true);
        saida.setEnabled(false);
        saida.setBackground(Color.decode("#34495e"));
        saida.setFont(new Font(null, 0, 14));

        JScrollPane scrollSaida = new JScrollPane(saida);
        scrollSaida.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        output.getContentPane().add(saida);

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
    private void setEntrada(String entrada){
        this.entrada.setText(entrada);
    }
    private void setSaida(String saida){}
    private String getEntrada(){
        return this.entrada.getText();
    }
    private String getSaida(){
        return null;
    }

    public static void main(String args[]){
        new Gui();
    }

}
