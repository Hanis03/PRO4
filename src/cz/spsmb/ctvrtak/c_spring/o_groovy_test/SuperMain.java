package cz.spsmb.ctvrtak.c_spring.o_groovy_test;

import java.util.Arrays;
import java.util.List;

public class SuperMain {
    public static void main(String[] args) {
        String inp = "Groovy";
        List<String> souhl = Arrays.asList("a", "e", "i", "o", "u");
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i<inp.length(); i++){
            String ch = inp.substring(i,i+1);
            //System.out.println(ch);
            if(souhl.contains(ch)){
                System.out.println(inp.charAt(i));
            }
        }
        cz.spsmb.ctvrtak.c_spring.o_groovy_test.main.Gui.main(args);
    }
}
