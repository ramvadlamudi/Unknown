package com.proj.donemcd.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sample {

    public static void main(String[] args){
        System.out.println("hello world");
        String str= "Madam Arora teaches Mathematics";
        String[] str1=  str.split(" ");
        List<String> stringList = new ArrayList<>();
        int i = 0;
        for (i=0; i< str1.length; i++){
            stringList.add(str1[i]);
        }

        for(String str2:stringList ){

        }

        System.out.println(Arrays.toString(str1));
       /* System.out.println(checkIfSentenceHasPalindrome("Madam Arora teaches Mathematics"));

        System.out.println(checkIfSentenceHasPalindrome("Bob speaks malayalam"));

        System.out.println(checkIfSentenceHasPalindrome("this t
                hat"));

                System.out.println(checkIfSentenceHasPalindrome("xyz pqr DAD"));*/
    }
}
