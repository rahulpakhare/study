package com.study.java.karat;

public class StringOperationsExample {
        public static void main(String[] args) {
            //Reverse string using array or charAt
           /* String str = "Hello Word";
            StringBuilder strBuilder = new StringBuilder();
            for(int i=str.length()-1; i!=-1; i--) {
                strBuilder.append(str.charAt(i));
            }
            System.out.println(strBuilder.toString());

            //Reverse String using StringBuffer
            StringBuilder strBuilder1 = new StringBuilder(str);
            System.out.println(strBuilder1.reverse());*/

            //Palindrome with String Buffer
            String paliStr = "AABCDADCBAAIO";
           /* StringBuilder strBuilder2 = new StringBuilder(paliStr);
            System.out.println(strBuilder2.reverse().toString().equals(paliStr));*/

            //Palindrome with String array
           /* for (int i=0, j=paliStr.length()-1; i<paliStr.length()-1 && j > -1; i++, j--) {
                if(i == j ) {
                    System.out.println("String is  palindrom");
                    break;
                }
                System.out.println(i + " " + j);
                if(paliStr.charAt(i) == paliStr.charAt(j))
                    continue;
                else {
                    System.out.println("String is not palindrom");
                    break;
                }
            }*/

            //Vovels count within the string
            /*String vovels = "AEIOU";
            Integer counter = 0;
            for(int i = 0; i < paliStr.length(); i++) {
                if(vovels.contains(String.valueOf(paliStr.charAt(i)))) counter++;
            }
            System.out.println(counter);*/
        }
    /*public static void main(String[] args) {
        String input = "Hello, World!";

        // 1. Reverse the string
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("Reversed: " + reversed);

        // 2. Check if the string is a palindrome
        String cleanedInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String cleanedReversed = new StringBuilder(cleanedInput).reverse().toString();
        boolean isPalindrome = cleanedInput.equals(cleanedReversed);
        System.out.println("Is Palindrome: " + isPalindrome);

        // 3. Count the number of vowels in the string
        long vowelCount = input.chars()
                .filter(ch -> "AEIOUaeiou".indexOf(ch) != -1)
                .count();
        System.out.println("Number of Vowels: " + vowelCount);
    }*/
}
