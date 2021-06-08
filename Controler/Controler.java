package Controler;

import Model.Grammar;
import Model.Table;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Wynne
 */
public class Controler {
    
    private Grammar grammar = new Grammar();
    private String word;
    
    private static Controler controler = new Controler();
    
    public static  Controler getInstance(){
        return Controler.controler;
    }
    
    public boolean readFile(String fileName){
        
        String path = System.getProperty("user.dir")+ "\\src\\D\\" + fileName;
        
        try{
            File file = new File(path);
            Scanner in = new Scanner(file);
           
            while (in.hasNextLine()) {
                String line = in.nextLine();
                this.grammar.addElementLine(line);
                
            }
            
        }catch(IOException e){
            return false;
        }
        
        return true;
    }
    
    public boolean buildTable(){
        Table table = new Table(this.word, this.grammar);
        
        table.buildTable();
        
        return table.wordIsAccepted();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void printGrammar(){
        this.grammar.print();
    }
    
}
