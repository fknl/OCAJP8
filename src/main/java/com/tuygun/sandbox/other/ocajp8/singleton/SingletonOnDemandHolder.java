package com.tuygun.sandbox.other.ocajp8.singleton;

public class SingletonOnDemandHolder {

    private SingletonOnDemandHolder(){}

    private static class SingletonOnDemandHolderInnerClass {
        private static final SingletonOnDemandHolder INSTANCE = new SingletonOnDemandHolder();
    }

    public static SingletonOnDemandHolder getInstance(){
        return SingletonOnDemandHolderInnerClass.INSTANCE;
    }
}
