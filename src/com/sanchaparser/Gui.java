import com.oracle.webservices.internal.api.message.ContentType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.*;


class Gui extends JFrame{

    Gui(){
        super("SanchaCppParser IDE");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //URL iconURL = getClass().getResource("./img/girl.png");
        //ImageIcon icon = new ImageIcon(iconURL);
        //setIconImage(icon.getImage());

        menu();


        JTextArea textArea = new JTextArea("oi");
        textArea.setSize(400,400);
        textArea.setLineWrap(true);
        textArea.setEditable(true);
        textArea.setVisible(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        getContentPane().add(scroll);
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
        System.out.println("Abrir arquivo");
    }
    private void actionSalvar(){}
    private void actionCompilar(){}
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
    private void setEntrada(String entrada){}
    private void setSaida(String saida){}
    private String getEntrada(){
        return null;
    }
    private String getSaida(){
        return null;
    }

    public static void main(String args[]){
        new Gui();
    }

}
