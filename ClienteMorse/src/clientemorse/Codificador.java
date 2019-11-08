/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientemorse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class Codificador {
    private BufferedReader in;
    private ArrayList<Character> letras;
    private ArrayList<String> morse;
    
    public Codificador(String nomeArq)
    {
        String Aux;
        String [] tokens = new String[2];
        letras = new ArrayList();
        morse = new ArrayList();
        
        try {
            this.in = new BufferedReader(new FileReader(nomeArq));
            while((Aux = in.readLine()) != null)
            {
                tokens = Aux.split(" ");
                letras.add(tokens[0].charAt(0));
                morse.add(tokens[1]);
            }
            
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    public String chartomorse(char a)
    {
        return morse.get(letras.indexOf(a));
    }
    
    public String wordtomorse(String str)
    {
        str = str.toUpperCase();
        String aux = "";
        for(int i = 0; i < str.length(); i++)
        {
            aux = aux.concat(this.chartomorse(str.charAt(i)));
            if(i != str.length()-1)
                aux = aux.concat(" ");
        }
        
        return aux;
    }
    
    public char morsetochar(String m)
    {
        return letras.get(morse.indexOf(m));
    }
    
    public String morsetoword(String m)
    {
        String str = "";
        String [] tokens = m.split(" ");
        
        for(int i = 0; i < tokens.length; i++)
        {
            str = str.concat(String.valueOf(morsetochar(tokens[i])));
        }

        return str;
    }
    
    public String linetomorse(String str)
    {
        String aux = "";
        String [] tokens = str.split(" ");
        for(int i = 0; i < tokens.length; i++)
        {
            aux = aux.concat(wordtomorse(tokens[i]));
            if(i != tokens.length-1)
                aux = aux.concat("   ");
        }
        
        return aux;
    }
    
    public String morsetoline(String str)
    {
        String aux = "";
        String [] tokens = str.split("   ");
        for(int i = 0; i < tokens.length; i++)
        {
            aux = aux.concat(morsetoword(tokens[i]));
            if(i != tokens.length - 1)
                aux = aux.concat(" ");
        }
        
        return aux;
    }
}
