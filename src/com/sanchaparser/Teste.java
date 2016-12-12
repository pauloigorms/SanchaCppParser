package com.sanchaparser;


import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;


import javax.swing.*;
import java.awt.*;

public class Teste extends JFrame {

    private static final long serialVersionUID = 1L;

    public Teste() {

        JPanel cp = new JPanel(new BorderLayout());

        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        cp.add(sp);

        setContentPane(cp);
        setTitle("Text Editor Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(() -> new Teste().setVisible(true));
    }


}
