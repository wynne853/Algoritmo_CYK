package Algoritmo_CYK.Model;

/**
 *
 * @author Wynne
 */
public class Table {
    private String[][] table;
    private String word;
    private Grammar grammar;

    public Table(String word,Grammar grammar) {
        this.word = word;
        this.table = new String[word.length()][word.length()];
        this.grammar = grammar;
    }

    public boolean buildTable(){
        String word ;
        
        int level = 2;
        
        this.fillFirstLine();
        
        for(int i = this.word.length() - 2; i >= 0; i--){
            for(int j = 0; j < (this.word.length() - level + 1) ; j ++){
                word = this.word.substring(j,j + level); 
                String[] posibilite = this.posibilitesForColumnInLevel(j,word.split(""));
                if(posibilite != null){
                    this.table[i][j] = this.grammar.searchInProductions(posibilite);
                }
                print();
            }
            level++;
        }
        
        return true;
    }
    
    private String[] posibilitesForColumnInLevel(int column,String[] word){
        String combination = "";
        String target1 = word[0];
        String target2 = "";
        
        for(int i = 1; i < word.length; i++){
            target2 += word[i];
        }
        
        for(int k = 0; k < word.length - 1; k++){
            
            String[] Production1 = this.table[this.table.length - target1.length()][column].split(",");
            String[] Production2 = this.table[this.table.length - target2.length()][column + target1.length()].split(",");
            if(Production1.equals("-") || Production2.equals("-")){
                return null;
            }
            for(int i = 0; i < Production1.length; i++){
                for(int j = 0; j < Production2.length; j++){                
                    combination += Production1[i] + Production2[j] + ","; 
                }
            }
            
            target1 += target2.charAt(0);
            target2 = target2.substring(1);
        }
        return combination.split(",");
    }
    
    private boolean fillFirstLine(){
        String[] word = this.word.split("");
        String element;
        
        for(int i = 0; i < word.length ; i++){
            element = this.grammar.searchInProductions(word[i]);
            if(!element.equals("")){
                this.table[word.length - 1][i] = element;
            }else{
                return false;
            }
        }
        
        return true;
    }
    
    public boolean wordIsAccepted(){
        return this.grammar.isInitialVariable(this.table[0][0]);
    }

    public void print(){
        for(int i = 0; i < this.word.length(); i++){
            for(int j = 0; j < (i + 1); j++){
                System.out.print(" " + (this.table[i][j] == null?"{}":this.table[i][j]));
            }
            System.out.print("\n");
        }
    }
}
