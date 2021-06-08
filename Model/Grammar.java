package Algoritmo_CYK.Model;

import java.util.ArrayList;

/**
 *
 * @author Wynne
 */
public class Grammar {

    ArrayList<ArrayList<String>> grammar;

    public Grammar() {
        grammar = new ArrayList<>();
    }

    public boolean addElementLine(String elementLine) {
        String[] variable = elementLine.split(" => ");

        ArrayList<String> line = new ArrayList<>();
        line.add(variable[0]);
        String[] production = variable[1].split(" ");
        String elementsNotAccepted = " |";
        for (int i = 0; i < production.length; i++) {
            if (!production[i].equals("|")) {
                line.add(production[i]);
            }
        }

        this.grammar.add(line);

        return true;
    }

    public String searchInProductions(String[] seach) {
        String find = "";
        
        for (int k = 0; k < seach.length; k++) {
            for (int i = 0; i < this.grammar.size(); i++) {
                for (int j = 1; j < this.grammar.get(i).size(); j++) {
                    if (this.grammar.get(i).get(j).equals(seach[k])) {
                        find += (find.length() == 0 ? "" : ",") + this.grammar.get(i).get(0);
                    }
                }
            }
        }
        
        return find.equals("")?"-":find;
    }

    public String searchInProductions(String seach) {
        String find = "";

        for (int i = 0; i < this.grammar.size(); i++) {
            for (int j = 1; j < this.grammar.get(i).size(); j++) {
                if (this.grammar.get(i).get(j).equals(seach)) {
                    find += (find.length() == 0 ? "" : ",") + this.grammar.get(i).get(0);
                }
            }
        }

        return find;
    }

    public boolean isInitialVariable(String variable) {
        String[] elements = variable.split(",");

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(this.grammar.get(0).get(0))) {
                return true;
            }
        }

        return false;
    }

    public void print() {
        for (int i = 0; i < this.grammar.size(); i++) {
            for (int j = 0; j < this.grammar.get(i).size(); j++) {
                System.out.print(this.grammar.get(i).get(j) + " * ");
            }
            System.out.print("\n");
        }
    }

}
