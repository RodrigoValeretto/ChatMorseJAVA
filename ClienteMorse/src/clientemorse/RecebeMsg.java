/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientemorse;

import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JTextArea;

/**
 *
 * @author rodrigo
 */
public class RecebeMsg implements Runnable{
    private Codificador cod;
    private JTextArea v;
    private ObjectInputStream in;
    
    public RecebeMsg(Codificador cod, JTextArea v, ObjectInputStream in)
    {
        this.cod = cod;
        this.v = v;
        this.in = in;
    }
    
    @Override
    public void run() {
        while(true)
        {
            try {
                String str = in.readUTF();
                v.append(cod.morsetoline(str));
                v.append("\n");
            } catch (IOException ex) {
                return;
            }
        }
    }
}
