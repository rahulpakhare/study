package com.study.java;

public class StringReplace {

    /**
     * Simple logic to replace char. Get char array of string and match each char with replace char. after match replace it
     * @param string
     * @param replace
     * @param replaceWith
     * @return
     */
    public String replaceChar(String string, char replace, char replaceWith) {
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == replace) {
                charArray[i] = replaceWith;
            }
        }

        return String.copyValueOf(charArray);
    }

    /**
     * Recursion method to replace char. Here string's char array sending every time and the increased index to get next char for comparision
     * Recursion break - at the end index counter will be equal to chars array length or string length
     * @param chars
     * @param replace
     * @param replaceWith
     * @param index
     * @return
     */
    public String recursionChar(char[] chars, char replace, char replaceWith, int index) {
        if (index == chars.length) {
            return String.valueOf(chars);
        }
        if (chars[index] == replace) {
            chars[index] = replaceWith;
        }

        return recursionChar(chars, replace, replaceWith, ++index);
    }

    public String recursionReplace(String string, char replace, char replaceWith) {
        char[] charArray = string.toCharArray();
        return recursionChar(charArray, replace, replaceWith, 0);
    }

    /**
     * Pass the substring excluding first char of the string. Compare first char of substring to replace if it's matched return that char or char converted string
     * Recursion break - at the end substring will be empty. Check length and return empty string
     * @param str
     * @param replace
     * @param to
     * @return
     */
    public String recursionReplaceString (String str, char replace, char to) {
        String append = "";
        if (str.length() == 0) {
            return append;
        } else {
             append = (str.charAt(0) == replace ? to : str.charAt(0)) + "";
        }

        return append + recursionReplaceString(str.substring(1), replace, to);
    }

    public static void main(String args[]) {
        StringReplace stringReplace = new StringReplace();
        //String replacedString = stringReplace.replaceChar(args[0], args[1].toCharArray()[0], args[2].toCharArray()[0]);
        //String replacedString = stringReplace.recursionReplace(args[0], args[1].toCharArray()[0], args[2].toCharArray()[0]);
        String replacedString = stringReplace.recursionReplaceString(args[0], args[1].toCharArray()[0], args[2].toCharArray()[0]);
        System.out.println(replacedString);
    }
}
