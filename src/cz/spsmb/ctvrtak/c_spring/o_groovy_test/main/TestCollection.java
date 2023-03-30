package cz.spsmb.ctvrtak.c_spring.o_groovy_test.main;

import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Random;

public class TestCollection {
    private List<MainTest> testCollection;
    private int id;
    private double difficulty;
    private String imports;
    private String initGroovyCode;
    private String grvCode;

    public String getInitGroovyCode() {
        return String.format(
                "import cz.spsmb.ctvrtak.c_spring.o_groovy_test.main.Testable;\n" +
                 "%s" +
                "class GroovyScriptTest implements Testable {\n" +
                "%s" +
                "}", imports == null ? "":imports, grvCode);
    }
    public void setImports(String imports) {
        this.imports = imports;
    }
    public void setGrvCode(String grvCode) {
        this.grvCode = grvCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public void setTestCollection(List<MainTest> testCollection) {
        this.testCollection = testCollection;
    }


    public MainTest getRandomTest(){
        Random rnd = new Random();
        rnd.setSeed(LocalTime.now().toNanoOfDay());
        return testCollection.get(rnd.nextInt(testCollection.size()));
    }
}
