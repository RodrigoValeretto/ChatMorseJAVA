/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientemorse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rodrigo
 */
public class Comunicador{
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;
    private JTextField t;
    private JTextArea v;
    private Codificador c;
    private Thread th;
    
    public Comunicador(JTextField text, JTextArea visor, Codificador code)
    {
        t = text;
        v = visor;
        c = code;
    }
    
    public void conectar() throws IOException
    {
        if(socket != null && !socket.isClosed())
            return;
        v.append("Conectando...\n");

        socket = new Socket("127.0.0.1", 1234);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        
        th = new Thread(new RecebeMsg(c, v, in));
        th.start();
        
        v.append("Conectado\n");
    }
    
    public void desconectar()
    {
        if(socket == null || socket.isClosed())
            return;
        v.append("Desconectando...\n");
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) { 
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        v.append("Desconectado\n");
    }
    
    public void SendMsg(String str)
    {  
        if(socket != null && !socket.isClosed())
        {
            try {
                out.writeUTF(str);
                out.flush();
            } catch (IOException ex) {
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }   
}
