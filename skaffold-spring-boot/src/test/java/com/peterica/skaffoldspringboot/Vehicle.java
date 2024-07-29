package com.peterica.skaffoldspringboot;
abstract class Vehicle{
    String name;
    public Vehicle(String val){
        this.name = val;
    }

    Vehicle() {
    }

    public String getName(){
        return "vehicle name:" + name;
    }

    public String getName(String val){
        return "Car name:" + val;
    }
}

