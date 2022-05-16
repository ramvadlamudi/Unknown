package com.proj.donemcd.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array1 {

    private static void addElement(
            Integer[] arr, int element,
            int position)
    {
        // Converting array to ArrayList
        List<Integer> list = new ArrayList<>(
                Arrays.asList(arr));

        // Adding the element at position
        list.add(position - 1, element);

        // Converting the list back to array
        arr = list.toArray(arr);

        // Printing the original array
        System.out.println("Initial Array:\n"
                + Arrays.toString(arr));

        // Printing the updated array
        System.out.println("\nArray with " + element
                + " inserted at position "
                + position + ":\n"
                + Arrays.toString(arr));
    }

    public static void main(String[] args){

        Integer[] arr = {10,20,30,40,60,70};
       // intarray = new int[]{10,20,30,40,60,70};
        System.out.println(Arrays.toString(arr));
    // Element to be inserted
        int element = 50;

        // Position to insert
        int position = 5;

        // Calling the function to insert
        addElement(arr, element, position);

    }
}
