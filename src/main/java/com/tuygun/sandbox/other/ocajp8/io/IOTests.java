package com.tuygun.sandbox.other.ocajp8.io;

import java.io.*;
import java.util.Scanner;

public class IOTests {
    public static void main(String[] args) throws Exception{
        //testWritingToConsole();
        //testReadingFromStandardInputViaScanner();
        //testReadingFromStandardInputViaConsole();
        //testCreateFileAndDirectory();
        //testFileWriteAndRead();
        //testBinaryFileWriteAndRead();
        //testBufferedWriterAndReader();
        //testPrintWritter();
        //testObjectOutputStream();
    }

    private static void testObjectOutputStream() {
        Instructor instructor = new Instructor(1, "Serhan");

        try(FileOutputStream instructorOs = new FileOutputStream("instructor.ser");
            ObjectOutputStream oos = new ObjectOutputStream(instructorOs)){
            oos.writeObject(instructor);
        } catch(Exception e){
            e.printStackTrace();
        }

        try(FileInputStream instructorIs = new FileInputStream("instructor.ser");
            ObjectInputStream ois = new ObjectInputStream(instructorIs)){
            Instructor instructorFromFile = (Instructor) ois.readObject();
            System.out.printf("Instructor: %d, %s", instructorFromFile.getId(), instructor.getName());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void testPrintWritter() {
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.write("Hello World!");
        printWriter.flush();
        printWriter.close();
    }

    private static void testBufferedWriterAndReader() throws Exception {
        //to read from standard system input (console)
        BufferedReader brSystemIn = new BufferedReader(new InputStreamReader(System.in));
        //to read from a file
        BufferedReader brFileReader = new BufferedReader(new FileReader("test.txt"));

        //writes buffer to file
        BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));

    }

    private static void testBinaryFileWriteAndRead() {
        //create file
        File file = new File("test.txt");
        if(!file.exists()){
            System.out.println("File test.txt does not exist!");

            try {
                boolean newFile = file.createNewFile();
                if(newFile){
                    System.out.println("File test.txt has been created!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //write something to existing file
        try(FileOutputStream fileOutputStream = new FileOutputStream("test.txt", false)){
            byte[] buffer = "Welcome to tests!".getBytes();
            fileOutputStream.write(buffer);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read the content of the file
        try(FileInputStream fileInputStream = new FileInputStream("test.txt")){
            byte[] buffer = new byte[17];
            fileInputStream.read(buffer);
            System.out.println(new String(buffer));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testFileWriteAndRead() {
        try(FileWriter writer = new FileWriter("test.txt", false)){
            writer.write("Welcome");
            writer.append(" to tests!");
        } catch (IOException e){
            e.printStackTrace();
        }

        try{
            FileReader reader = new FileReader("test.txt");
            char[] buffer = new char[17];
            reader.read(buffer);
            System.out.println(buffer);
            reader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void testCreateFileAndDirectory() {
        File file = new File("test.txt");
        if(!file.exists()){
            System.out.println("File test.txt does not exist!");

            try {
                boolean newFile = file.createNewFile();
                if(newFile){
                    System.out.println("File test.txt has been created!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File dir = new File("test");
        if(!dir.exists()){
            System.out.println("test directory does not exist.");

            boolean mkdir = dir.mkdir();
            if(mkdir){
                System.out.println("test directory has been created!");
            }
        }
    }

    private static void testReadingFromStandardInputViaConsole() {
        Console console = System.console();
        if(console != null){
            String in = "";
            String out = "";
            console.printf("%s", "Please enter your commands");

            while(true){
                in = console.readLine();
                if("EXIT".equalsIgnoreCase(in)){
                    break;
                }

                out += in + " ";
            }
            console.printf("%s%s", "Your output: ", out);
        } else {
            System.out.println("Console is not available!");
        }
    }

    private static void testReadingFromStandardInputViaScanner() {
        Scanner scanner = new Scanner(System.in);
        String in = "";
        String out = "";
        System.out.println("Please enter your commands");
        while(true){
            in = scanner.nextLine();
            if("EXIT".equalsIgnoreCase(in)){
                break;
            }
            out += in + " ";
        }
        System.out.println("Your output: " + out);
        scanner.close();
    }

    private static void testWritingToConsole() {
        //System.in     -> InputStream
        //System.out    -> PrintStream
        //System.err    -> PrintStream
        System.out.println("Serhan Tuygun");
        System.out.printf("%s%d\n", "Test Number: ", 8);
        System.err.format("%s%d\n", "Error number: ", 99);
        System.err.println("Test Error");
    }

    static class Instructor implements Serializable {
        private final int id;
        private final String name;

        public Instructor(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId(){
            return this.id;
        }

        public String getName(){
            return this.name;
        }
    }
}
