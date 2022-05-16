package com.proj.donemcd.model;

public class employ {
    public static void main(String args[]){

        String input = "aerodfjdhf E788w 23";
        String volues = "aeiou02468";
        for (int i=0; i<input.length(); i++){
            char chr = input.charAt(i);
            if(!volues.contains(String.valueOf(chr).toLowerCase())){
                System.out.print(chr);
            }
        }


    }
}
