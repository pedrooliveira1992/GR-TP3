/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
package src.numerosaleatorios;

import LoaderFiles.LoadConf;
import LoaderFiles.LoadSementes;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pedro
 *
public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Gerador de Número Aleatórios a começar ....");
        List<String> configuracao = new LoadConf("conf.txt").loadConfig();
        
        for(String s:configuracao){
            System.out.println(s);
        }
        
        Map<Integer,String> semente = new LoadSementes("sementes.txt").loadSement();
        
        Set<Integer> chaves = semente.keySet();
        for(Integer chave: chaves){
            if(chave != null){
                System.out.println(chave + " "+ semente.get(chave));
            }
        }
    }
    
}*/
