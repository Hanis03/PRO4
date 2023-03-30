package cz.spsmb.ctvrtak.c_spring.o_groovy_test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;

public class SuperMain {
    public static void main(String[] args) {
        //String inp = "Groovy";
        String inp = "15:30 17:22 18:01 22:22 23:23";
        inp = "2023-03-30 2023-03-31 2023-05-01 2023-08-31";
        String[] arr = inp.split(" ");
        for (String date: arr) {
            //LocalTime lt = LocalTime.parse(date);
            LocalDate ld = LocalDate.parse(date);
            //LocalDateTime localDateTime = LocalDateTime.parse(date);
            //localDate = localDate.plus(1, ChronoUnit.MONTHS);
            ;
            LocalDate ch = LocalDate.parse("2023-12-24");

            //System.out.print(ChronoUnit.DAYS.between(ld, ch) +" ");
            //System.out.println(localDateTime);
            System.out.print(ld.format(DateTimeFormatter.ofPattern("dd.MMMM,YYYY")) + " ");
            //System.out.println(LocalDateTime.of(localDate, LocalTime.now()));

        }
        System.out.println();
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
