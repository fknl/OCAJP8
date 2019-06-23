package com.tuygun.sandbox.other.ocajp8.paths;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Map;
import java.util.stream.Stream;

public class PathTests {
    public static void main(String[] args) throws Exception {
        //testPathBasicMethods();
        //testFilesBasicMethods();
        //testFilesAndPathsViaStreams_nio2();
    }

    private static void testFilesAndPathsViaStreams_nio2() throws IOException {
        System.out.println("------------Read File Content----------");
        Path path = Paths.get("/home/tuygun/source/sandbox/others/src/main/java",
                "com/tuygun/sandbox/other/ocajp8/paths",
                "PathTests.java");
        Stream<String> lines = Files.lines(path);
        lines.forEach(System.out::println);//prints the file content

        System.out.println("----------Print all the paths which are starting with c letter------------");
        Path path2 = Paths.get("/home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/");
        Stream<Path> list = Files.list(path2);//visits the first level children only
        list.filter(i->i.getFileName().toString().startsWith("c")).forEach(System.out::println);
        //----------Print all the paths which are starting with c letter------------
        ///home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/compare
        ///home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/collectors
        ///home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/concurency
        ///home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/collections

        System.out.println("----------File Tree Walker------------");
        Files.walk(path2, FileVisitOption.FOLLOW_LINKS).forEach(System.out::println);//no predicate only FileVisitOption

        System.out.println("----------Find A File------------");
        Files.find(path2, 10,//we have to give max-depth
                (p, attr)-> p.getFileName().toString().equalsIgnoreCase("Employee.java") && attr.isRegularFile()).
                forEach(System.out::println);
        //above statement will print "/home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/compare/Employee.java"
    }

    private static void testFilesBasicMethods() {
        System.out.println("------------Read File Content----------");
        Path path = Paths.get("/home/tuygun/source/sandbox/others/src/main/java",
                "com/tuygun/sandbox/other/ocajp8/paths",
                "PathTests.java");
        System.out.println(path.toAbsolutePath());
        ///home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/paths/PathTests.java

        if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)){//whether symbolic links in the path will be followed or not
            System.out.println("PathTests.java exists!");//prints

            try {
                Files.readAllLines(path, Charset.forName("UTF-8")).forEach(System.out::println);//prints the content of the file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("------------Delete non-existing File----------");
        Path nonExistingFile = Paths.get("/home/tuygun/serhantuygun.txt");
        try {
            boolean deleted = Files.deleteIfExists(nonExistingFile);
            System.out.println("serhantuygun.txt file isDeleted=" + deleted);//serhantuygun.txt file isDeleted=false
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------Copy File to System.out----------");
        try {
            Files.copy(path, System.out);//prints the file content
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------File Attributes----------");
        try {
            //------------File Attributes----------
            //Last Modified Time: 2019-06-17T08:48:52Z
            //Owner: tuygun
            //Is Directory: false
            //Is Regular Files: true
            //Is Symbolic Link:false
            //Is Readable: true
            //Is Writable: true
            //Is Executable: false
            //Is Hidden: false
            //Files.getAttribute-> basic:isDirectory false
            System.out.println("Last Modified Time: " + Files.getLastModifiedTime(path));
            System.out.println("Owner: " + Files.getOwner(path));
            System.out.println("Is Directory: " + Files.isDirectory(path));
            System.out.println("Is Regular Files: " + Files.isRegularFile(path));
            System.out.println("Is Symbolic Link:" + Files.isSymbolicLink(path));
            System.out.println("Is Readable: " + Files.isReadable(path));
            System.out.println("Is Writable: " + Files.isWritable(path));
            System.out.println("Is Executable: " + Files.isExecutable(path));
            System.out.println("Is Hidden: " + Files.isHidden(path));

            System.out.println("Files.getAttribute-> basic:isDirectory " + Files.getAttribute(path, "basic:isDirectory"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("------------File Bulk Attributes----------");
        try {
            //------------File Bulk Attributes----------
            //name:lastAccessTime, value:2019-06-17T08:48:54Z
            //name:lastModifiedTime, value:2019-06-17T08:48:52Z
            //name:size, value:7695
            //name:creationTime, value:2019-06-17T08:48:52Z
            //name:isSymbolicLink, value:false
            //name:isRegularFile, value:true
            //name:fileKey, value:(dev=801,ino=1245515)
            //name:isOther, value:false
            //name:isDirectory, value:false
            Map<String, Object> stringObjectMap = Files.readAttributes(path, "basic:*");//attention to *
            stringObjectMap.forEach((i, j) -> {
                System.out.println("name:" + i + ", value:" + j);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testPathBasicMethods() {
        Path path = Paths.get("/home/tuygun/source/sandbox/others/src/main/java",
                "com/tuygun/sandbox/other/ocajp8/io",
                "IOTests.java");
        System.out.println("Path: " + path);
        //Path: /home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/io/IOTests.java
        Path fileName = path.getFileName();
        System.out.println("File name: " + fileName);
        //File name: IOTests.java

        Path parent = path.getParent();
        System.out.println("Parent: " + parent);
        //Parent: /home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/io

        Path root = path.getRoot();
        System.out.println("Root: " + root);
        //Root: /

        System.out.println("Name Count: " + path.getNameCount());
        //Name Count: 14

        Path subpath = path.subpath(1, 4);
        System.out.println("Subpath[1,4): " + subpath);
        //Subpath[1,4): tuygun/source/sandbox

        if(path.isAbsolute()){
            System.out.println("Path is absolute!");//Path is absolute!
        } else {
            System.out.println("Path is not absolute!");//no output
        }
        System.out.println("Absolute Path:" + path.toAbsolutePath());
        //Absolute Path:/home/tuygun/source/sandbox/others/src/main/java/com/tuygun/sandbox/other/io/IOTests.java

        System.out.println("----------Second Path-----------");

        Path otherPath = Paths.get("com/tuygun/sandbox/other/ocajp8/io/IOTests.java");
        System.out.println("Path: " + otherPath);
        //Path: ./com/tuygun/../../com/tuygun/sandbox/other/io/IOTests.java

        Path otherPathFileName = otherPath.getFileName();
        System.out.println("File Name: " + otherPathFileName);
        //File Name: IOTests.java

        Path otherPathParent = otherPath.getParent();
        System.out.println("Parent: " + otherPathParent);
        //Parent: ./com/tuygun/../../com/tuygun/sandbox/other/io

        if(otherPathParent.isAbsolute()){
            System.out.println("Path is absolute!");//no output
        } else {
            System.out.println("Path is not absolute!");//Path is not absolute!
        }
        System.out.println("Absolute Path:" + otherPathParent.toAbsolutePath());
        //Absolute Path:/home/tuygun/source/sandbox/others/./com/tuygun/../../com/tuygun/sandbox/other/io

        System.out.println("Normalized Path: " + otherPath.normalize());
        //Normalized Path: com/tuygun/sandbox/other/io/IOTests.java

        System.out.println("Relative Path: " + otherPath.normalize().toAbsolutePath().relativize(path));
        //Relative Path: ../../../../../../src/main/java/com/tuygun/sandbox/other/io/IOTests.java

        System.out.println("----------Third Path-----------");
        Path path1 = Paths.get("/src/main/java/com/tuygun/sandbox/other/io/IOTests.java");
        Path path2 = Paths.get("sandbox/other/nested/AbstractClass.java");

        System.out.println(path1.resolve(path2));
        ///src/main/java/com/tuygun/sandbox/other/io/IOTests.java/sandbox/other/nested/AbstractClass.java

    }
}
