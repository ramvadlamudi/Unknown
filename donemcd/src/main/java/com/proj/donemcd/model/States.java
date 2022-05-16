package com.proj.donemcd.model;

import java.util.HashMap;
import java.util.Map;

public class States {

     static Map<String, String> myMap = new HashMap<>();
    static {
        myMap.put("mi", "Michigan");
        myMap.put("Oh", "ohaio");
    }
    public static void main(String[] args) {
          String state = statement("mi");
          System.out.println(state);
    }

    public static String statement(String key){
        String name  = myMap.get(key);
        return name;
    }
}
