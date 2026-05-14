package com.study.java.karat;


import java.util.Arrays;
import java.util.HashSet;

public class LogFileFindExample {
    public static void main(String[] args) {
        String[] str = {"a1", "a3", "a4", "a2", "a3"};
       //write code to find the duplicate string in the array without using any collection framework with less time complexity
        for (int i = 0; i < str.length; i++) {
            String find = str[i];
            for (int j = i+1; j < str.length; j++) {
                if(find.equalsIgnoreCase(str[j])) {
                    System.out.println(find);
                    break;
                }
            }
        }

    }
   /* public static void main(String[] args) {
            String[] str = {"a1", "a3", "a4", "a2", "a3"};
            for (int i = 0; i < str.length; i++) {
                String find = str[i];
                for (int j = i+1; j < str.length; j++) {
                    if(find.equalsIgnoreCase(str[j])) {
                        System.out.println(find);
                        break;
                    }
                }
            }
    }*/

   /* public static void main(String[] args){
        HashSet<String> strHash = new HashSet<>();
        String[] str = {"a1", "a3", "a4", "a2", "a3"};
        for(String s : str) {
            if(!strHash.add(s))
                System.out.println(s);
        }
    }*/
    // Partially completed
/*    public static void main(String[] args) {
        String[] str = {"a1", "a3", "a4", "a2", "a3"};
        int index = str.length/2;
        String find =
                compare(Arrays.copyOfRange(str, 0, index));
        if (find != null)
            find = compare(Arrays.copyOfRange(str, index, str.length -1));

        System.out.println(find);
    }

    public static String compare(String[] str) {
        String find = null;
        for (int i = 0; i < str.length; i++) {
            find = str[i];
            for (int j = i+1; j < str.length; j++) {
                if(find.equalsIgnoreCase(str[j])) {
                    break;
                }
            }
        }

        return find;
    }*/
}
