package com.tuygun.sandbox.other.ocajp8.exceptions;

public class AResource implements AutoCloseable {
    private final String name;

    public AResource(String name){
        this.name = name;
        System.out.println("Resource(" + this.name + ") has opened.");
    }

    public void use(){
        System.out.println(this.getName() + " is being used.");
    }

    public String getName(){
        return this.name;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource(" + this.name + ") has been closed.");
        throw new Exception("Exception thrown in resource(" + this.name + ") close method.");
    }
}
