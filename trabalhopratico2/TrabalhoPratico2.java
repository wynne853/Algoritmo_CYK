package trabalhopratico2;

import Controler.Controler;
import java.util.Scanner;

/**
 *
 * @author Wynne
 */
public class TrabalhoPratico2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Controler controler = Controler.getInstance();
        
        Scanner scan = new Scanner(System.in);
        boolean init = true;
        while(init){
            System.out.println("Informe o nome do arquivo que está dentro da pasta D do projeto");
            if(!controler.readFile(scan.nextLine()+ ".txt")){
                continue;
            }
            
            controler.setWord(askWord(scan));
            
            init = menu(scan,controler);
        }
    }
    
    private static boolean menu(Scanner scan,Controler controler){
        
        int selected = 1;
        
        while(selected != 0){
            System.out.println("0 - encerrar o programa | 1 - Testar palavra | 2 - Alterar a palavra | 3 - resetar configuração | 4 - imprimir Gramática");
            selected = scan.nextInt();
            
            switch (selected){
                case 1: System.out.println(controler.buildTable()?"Aceita":"Não aceita");
                    break;
                case 2:controler.setWord(askWord(scan));
                    break;
                case 3: return true;
                case 4: controler.printGrammar();
            }
        }
        
        return false;
    }
    
    private static String askWord(Scanner scan){
        System.out.println("Informe a palavra de teste");
        String answer = scan.nextLine();
        return answer;
    }
}
