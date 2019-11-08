/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermorse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 *
 * @author rodrigo
 */
public class RepassaMsg implements Runnable{
    Vector <RepassaMsg> clients;
    ObjectInputStream input;
    ObjectOutputStream output;

    public RepassaMsg(Vector<RepassaMsg> clients, ObjectInputStream input, ObjectOutputStream output) {
        this.clients = clients;
        this.input = input;
        this.output = output;
    }
    
    @Override
    public void run() {
        while(true)
        {
            try
            {
                String msg = input.readUTF();
                for(RepassaMsg i : clients)
                    {
                        i.output.writeUTF(msg);
                        i.output.flush();
                    }
            }catch(IOException ex){
                try{
                    clients.remove(this);
                    this.input.close();
                    this.output.close();
                }catch(IOException ex1){}
                finally{return;}
            }
        }
    }
    
}
