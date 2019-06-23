package com.tuygun.sandbox.other.ocajp8.misc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MiscTests {
    public static void main(String[] args) throws Exception {
        testLocalBuilder();
        testResourceBundle();
        testPropertyResourceBundle();
        testListResourceBundle();
    }

    private static void testListResourceBundle() {
    }

    private static void testPropertyResourceBundle() throws IOException {
        //3-ways of creating property resource bundle
        PropertyResourceBundle prb1 = (PropertyResourceBundle) ResourceBundle.getBundle("Exam");
        PropertyResourceBundle prb2 = new PropertyResourceBundle(new FileInputStream("Exam.properties"));
        PropertyResourceBundle prb3 = new PropertyResourceBundle(new FileReader("Exam.properties"));

        //same methods like getKeys, getKeySet
    }

    private static void testResourceBundle() {
        //Consider there is properties file called Exam.properties in the class path, containing
        //key1  =Value1
        //key2=  Value2
        //Not that the whitespaces around = sign will be ignored. So, both the entries above are valid.
        ResourceBundle rb = ResourceBundle.getBundle("Exam");
        //The above statement searches the bundle like below order (assume that local is fr_CA)
        //Exam_fr_CA -> Exam_fr -> Exam_en_US -> Exam_en -> Exam

        Enumeration<String> keys = rb.getKeys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            System.out.println(key);
        }
        //or
        Set<String> keySet = rb.keySet();
        keySet.stream().forEach(System.out::println);

        String value = rb.getString("key");
        //if there is no key, then throws NoPropertyFound Exception
    }

    private static void testLocalBuilder() {
        Locale enLocale1 = new Locale.Builder().setLanguage("en").build();
        Locale enLocale2 = new Locale("en");

        Locale frLocale1 = new Locale.Builder().setLanguage("fr").setRegion("CA").build();
        Locale frLocale2 = new Locale("fr", "CA");
        Locale frLocale3 = Locale.forLanguageTag("fr-CA");
        System.out.println(frLocale3); //->fr_CA: it always prints the language in lower and country with upper case.
        Locale frLocale4 = Locale.CANADA_FRENCH; //->fr_CA

        Locale itLocale1 = Locale.ITALY; // -> it_it
        Locale itLocale2 = Locale.ITALIAN; //-> it

        Locale local = Locale.getDefault(); // -> returns the locale underlying platform of jvm runs.
    }
}
