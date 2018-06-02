package com.study.java;

public class StringReplace {

    public String replaceChar(String string, char replace, char replaceWith) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == replace) {
                charArray[i] = replaceWith;
            }
        }

        return String.copyValueOf(charArray);
    }

    public String recursion(char[] chars, char replace, char replaceWith, int index) {
        if (index == chars.length) {
            return String.valueOf(chars);
        }
        if (chars[index] == replace) {
            chars[index] = replaceWith;
        }

        return recursion(chars, replace, replaceWith, ++index);
    }

    public String recursionReplace(String string, char replace, char replaceWith) {
        char[] charArray = string.toCharArray();
        return recursion(charArray, replace, replaceWith, 0);
    }

    public static void main(String args[]) {
        StringReplace stringReplace = new StringReplace();
        //String replacedString = stringReplace.replaceChar(args[0], args[1].toCharArray()[0], args[2].toCharArray()[0]);
        String replacedString = stringReplace.recursionReplace(args[0], args[1].toCharArray()[0], args[2].toCharArray()[0]);
        System.out.println(replacedString);
    }
}
