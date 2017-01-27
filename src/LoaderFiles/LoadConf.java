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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pedro
 */
public class LoadConf {
    String filename;
    
    
    public LoadConf(String file){
        this.filename = file;
    }
    
    public List<String> loadConfig() throws IOException{
        List<String> config = new ArrayList<>();
        
        
        
        try{
            FileReader arq = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(arq);
            
            String linha = br.readLine();
            
            while(linha != null){
            String[] arrayElements = linha.split(":");
            config.add(arrayElements[1]);
            //System.out.println(config);
            linha = br.readLine();
        }
            arq.close();
            
        }catch(FileNotFoundException e){
            System.out.println("Unable to Load the Config file");
        }
        
        
        return config;
    }
    
    
    
}
