/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoaderFiles;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pedro
 */
public class LoadSementes {
    String filename;
    
    public LoadSementes(String file){
        this.filename = file;
    }
    
    public Map<Integer,String> loadSement() throws IOException{
        Map<Integer,String> sementes = new HashMap<>();
        int i = 0;
        
        try{
            FileReader arq = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(arq);
            
            String linha = br.readLine();
            
            while(linha!=null){
                i = i+1;
                sementes.put(i, linha);
                //System.out.println(sementes);
                
                linha = br.readLine();
                
            }
            
            arq.close();
            
        }catch(FileNotFoundException e){
            System.out.println("Unable to Load the Config file");
        }
        
        return sementes;
        
    }
    
}
