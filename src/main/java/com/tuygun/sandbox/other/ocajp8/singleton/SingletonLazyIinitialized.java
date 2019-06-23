package com.tuygun.sandbox.other.ocajp8.singleton;

public class SingletonLazyIinitialized {
    private static SingletonLazyIinitialized INSTANCE;

    private SingletonLazyIinitialized(){}

    public synchronized static SingletonLazyIinitialized getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SingletonLazyIinitialized();
        }

        return INSTANCE;
    }
}
