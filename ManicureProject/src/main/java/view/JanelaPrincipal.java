/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author thiag
 */
public class JanelaPrincipal extends JFrame {

    private JPanel pnInicial;
    private JLabel lbHello;

    public JanelaPrincipal() {

        new BorderLayout();

        pnInicial = new JPanel();

        //mensagem de ola
        lbHello = new JLabel("Hello");
        lbHello.setFont(new Font("Arial", Font.BOLD, 24));
        pnInicial.add(lbHello);

        add(pnInicial, BorderLayout.CENTER);
        iniciarJanela();
    }

    private void iniciarJanela() {
        setTitle("Manicure System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        JanelaPrincipal janela = new JanelaPrincipal();
        janela.iniciarJanela();
    }

}
