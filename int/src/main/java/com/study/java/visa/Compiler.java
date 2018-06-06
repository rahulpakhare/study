package com.study.java.visa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Compiler {

    private static final String NO = "NO";
    private static final String YES = "YES";
    private static Map<Character, Character> bracesOppsiteMatch;
    static {
         bracesOppsiteMatch = new HashMap<>();
         bracesOppsiteMatch.put('{', '}');
         bracesOppsiteMatch.put('[', ']');
         bracesOppsiteMatch.put('(', ')');
    }

    static String[] braces(String[] values) {
        String [] result = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = YES;
            String value = values[i];
            if (value.length() % 2 != 0) {
                result[i] = NO;
                continue;
            }

            char[] charArray = value.toCharArray();
            int middleIndex = charArray.length / 2;
            int leftCounter = middleIndex - 1;
            int rightCounter = middleIndex - 1;

            for (int j = 1; j <= middleIndex; j++) {
               if (bracesOppsiteMatch.get(charArray[leftCounter--]) != charArray[++rightCounter]) {
                   result[i] = NO;
                   break;
               }
            }

        }

        return result;
    }

    public static void main(String args[]) {
        if (args.length != 0) {
            int inputLength = Integer.parseInt(args[0]);
            if (inputLength + 1 == args.length) {
                String[] inputFunctions = Arrays.copyOfRange(args, 1, args.length);
                String result[] = braces(inputFunctions);
                System.out.println(Arrays.toString(result));
            }
        }
    }
}
