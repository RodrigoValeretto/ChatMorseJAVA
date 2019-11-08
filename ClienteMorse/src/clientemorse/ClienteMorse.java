/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientemorse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author rodrigo
 */
public class ClienteMorse extends JFrame{
    Codificador code;
    Comunicador com;
    private JTextField text = new JTextField();
    private JTextArea visor = new JTextArea();
    private JScrollPane scroll = new JScrollPane(text);
    private JScrollPane scroll2 = new JScrollPane(visor);
    private JPanel painel = new JPanel();
    private JButton connect = new JButton("Conectar");
    private JButton disconnect = new JButton("Desconectar");
    private JButton send = new JButton("Enviar");
    
    public ClienteMorse()
    {
        super("Cliente Morse");
        code = new Codificador("codigo_morse.txt");
        com = new Comunicador(text, visor, code);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        text.setEditable(true);
        scroll.setBorder(new TitledBorder(new EtchedBorder(), "Texto"));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(400,50));
        
        visor.setEditable(false);
        visor.setLineWrap(true);
        scroll2.setBorder(new TitledBorder(new EtchedBorder(), "Visor"));
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll2.setPreferredSize(new Dimension(400,150));
        
        painel.setLayout(new GridLayout(1,3));
        painel.add(connect);
        painel.add(disconnect);
        painel.add(send);
        
        this.add(scroll, BorderLayout.NORTH);
        this.add(scroll2, BorderLayout.CENTER);
        this.add(painel, BorderLayout.SOUTH);
        
        this.pack();
        
        connect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    com.conectar();
                }catch(IOException ex){}
            }      
        });
        
        disconnect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    com.desconectar();
            }      
        });
        
        send.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                com.SendMsg(code.linetomorse(text.getText()));
                text.setText("");
            }              
        });

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClienteMorse client = new ClienteMorse();
    }

    public JTextField getText() {
        return text;
    }

    public void setText(JTextField text) {
        this.text = text;
    }

    public JTextArea getVisor() {
        return visor;
    }

    public void setVisor(JTextArea visor) {
        this.visor = visor;
    }
    
}
