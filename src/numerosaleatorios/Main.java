/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosaleatorios;

import LoaderFiles.LoadConf;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author pedro
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        System.out.println("Gerador de Número Aleatórios a começar ....");
        List<String> configuracao = new LoadConf("conf.txt").loadConfig();
        
        for(String s:configuracao){
            System.out.println(s);
        }
    }
    
}
