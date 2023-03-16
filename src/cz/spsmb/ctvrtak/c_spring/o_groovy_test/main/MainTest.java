package cz.spsmb.ctvrtak.c_spring.o_groovy_test.main;


import groovy.lang.Binding;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.chainsaw.Main;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class MainTest {
    public static final long TIMEOUT_IN_MILISECONDS = 1000;
    public static final String FILENAME_CODE_FILE_NAME = "test.groovy";
    public static final String DIRECTORY_CODE_PATH = System.getenv("HOMEDRIVE")+System.getenv("HOMEPATH");
    public static final String FILENAME_CODE_PATH = DIRECTORY_CODE_PATH+"\\"+FILENAME_CODE_FILE_NAME;

    public static void createGroovyTemplateFile(String code) throws IOException {
        FileUtils.writeStringToFile(new File(MainTest.FILENAME_CODE_PATH), code, Charset.forName("UTF-8").toString());
    }

    private String entry;
    private String inp;
    private String out;
    private Testable test;
    private int id;
    private String result;
    GroovyScriptEngine engine;

    public String getInp() {
        return inp;
    }

    public void setInp(String inp) {
        this.inp = inp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }


    public String getOut() {
        return out;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setOut(String out) {
        this.out = out;
    }

    /*public void setTest(Testable test) {
        this.test = test;
    }*/

    public MainTest() {
        try {
            this.engine = new GroovyScriptEngine(ResourceUtils.getFile("file:"+ MainTest.FILENAME_CODE_PATH)
                    .getAbsolutePath(), this.getClass().getClassLoader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String check() {
        Thread t = new Thread() {
            @Override
            public void run() {
                Binding binding = new Binding();
                binding.setVariable("in", MainTest.this.inp);
                Class<GroovyObject> joinerClass = null;
                try {
                    joinerClass = engine.loadScriptByName(MainTest.FILENAME_CODE_FILE_NAME);
                    GroovyObject joiner = joinerClass.newInstance();
                    MainTest.this.result = joiner.invokeMethod("check", new Object[]{MainTest.this.inp}).toString();
                } catch (ResourceException e) {
                    e.printStackTrace();
                } catch (ScriptException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        try {
            t.join(MainTest.TIMEOUT_IN_MILISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.result;
        /*
        try {
            result = engine.run(MainTest.FILENAME_CODE_FILE_NAME, binding).toString();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }*/
        //return test.check(this.in);
        //System.out.println();
        //return result;
    }

    public boolean isValid() {

        //String tmp = test.check(this.in);

        //System.out.println(tmp);
        return out.equals(this.check());
    }
    /*
    Binding binding = new Binding();
binding.setVariable("arg1", "Mr.");
binding.setVariable("arg2", "Bob");
Object result = engine.run("StringJoinerScript.groovy", binding);
assertEquals("Mr.Bob", result.toString());
     */

}
