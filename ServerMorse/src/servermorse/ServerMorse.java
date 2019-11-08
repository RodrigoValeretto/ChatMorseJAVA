/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermorse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author rodrigo
 */
public class ServerMorse extends JFrame{
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);        
        Vector<RepassaMsg> clients = new Vector();
        
        while(true)
        {
            System.out.println("Aguardando Conexões...\n");

            Socket socket = server.accept();
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            RepassaMsg client = new RepassaMsg(clients, in, out);
            clients.add(client);

            System.out.println("Cliente conectado!\n");
            Thread t = new Thread(client);
            t.start();
        }
    }
    
}
