package com.tenta.puzzles.ioexample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class Main {
    static public void main(final String[] args) {

        System.out.println("Emoji calculator\n Please enter a function:");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            final String wholeFunction = reader.readLine();
            reader.close();
            doTheOperation(wholeFunction);

        } catch (Exception e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    private static String getSecondNumber(String wholeFunction) {
        int i = -1;
        while (i == -1) {
            i = getOperatorPosition(wholeFunction);
        }

        int index = 0;
        String lastChar = wholeFunction.substring(i + 1);
        for (int j = 0; j < lastChar.length(); j++) {
            if (lastChar.charAt(j) == '+' || lastChar.charAt(j) == '-' || lastChar.charAt(j) == '*' || lastChar.charAt(j) == '/') {
                index = j;
                break;
            }
        }
        String returnString = lastChar.substring(0, index);
        if (returnString.isEmpty()) {
            return lastChar;
        }
        return returnString;
    }

    private static char getOperator(String wholeFunction) {
        return wholeFunction.charAt(getOperatorPosition(wholeFunction));
    }

    private static String getFirstNumber(String wholeFunction) {
        int i = -1;
        while (i == -1) {
            i = getOperatorPosition(wholeFunction);
        }
        int index = 0;
        String lastChar = wholeFunction.substring(0, i);
        for (int j = lastChar.length() - 1; j > 0; j--) {
            if (lastChar.charAt(j) == '+' || lastChar.charAt(j) == '-' || lastChar.charAt(j) == '*' || lastChar.charAt(j) == '/') {
                index = j;
                break;
            }
        }
        String returnString = lastChar.substring(index);

        returnString = returnString.replaceAll("[^a-zA-Z0-9_-]", "");

        return returnString;
    }

    private static int getOperatorPosition(String wholeFunction) {
        if (wholeFunction.indexOf('*') != -1) {
            return wholeFunction.indexOf('*');
        } else if (wholeFunction.indexOf('/') != -1) {
            return wholeFunction.indexOf('/');
        }else if (wholeFunction.indexOf('%') != -1) {
            return wholeFunction.indexOf('%');
        } else if (wholeFunction.indexOf('+') != -1) {
            return wholeFunction.indexOf('+');
        } else if (wholeFunction.indexOf('-') != -1) {
            return wholeFunction.indexOf('-');
        }
        return -1;
    }

    private static void doTheOperation(String function) {

        function = replaceAllEmoticonesToAscii(function);
        String finalResult;
        while (!function.matches("[0-9]+") && function.length() > 1) {
            String firstNumber = getFirstNumber(function);
            if (!firstNumber.matches("[0-9]+")){
                styleToEmoticon("\uD83E\uDD37");
                function = null;
                break;
            }

            char operator1 = getOperator(function);
            String operator = String.valueOf(operator1);

            String secondNumber = getSecondNumber(function);
            if (secondNumber.equals("0") || !secondNumber.matches("[0-9]+")){
                styleToEmoticon("\uD83E\uDD37");
                function = null;
                break;
            }
            double num1 = Integer.parseInt(firstNumber);
            double num2 = Integer.parseInt(secondNumber);
            double result = 0;
                switch (operator) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "*" -> result = num1 * num2;
                    case "/" -> result = num1 / num2;
                    case "%" -> result = num1 % num2;

                }
            function = function.replace(firstNumber + operator + secondNumber, String.valueOf((int)result));

        }
        if (function !=null){
            finalResult = function;
            styleToEmoticon(finalResult);
        }
    }

    private static void styleToEmoticon(String finalResult) {
        finalResult = finalResult.replace("100", "\uD83D\uDCAF");
        finalResult = finalResult.replace("10", "\uD83D\uDD1F");
        finalResult = finalResult.replace("0", "0️");
        finalResult = finalResult.replace("1", "1️");
        finalResult = finalResult.replace("2", "2️");
        finalResult = finalResult.replace("3", "3️");
        finalResult = finalResult.replace("4", "4️");
        finalResult = finalResult.replace("5", "5️");
        finalResult = finalResult.replace("6", "6️");
        finalResult = finalResult.replace("7", "7️");
        finalResult = finalResult.replace("8", "\uD83C\uDFB1");
        finalResult = finalResult.replace("9", "9️");
        System.out.println("\n"+finalResult);
    }

    private static String replaceAllEmoticonesToAscii(String function) {
        function = function.replaceAll("\\s", "");
        function = function.replace("minus", "-");
        function = function.replace("➖", "-");

        function = function.replace("plus", "+");
        function = function.replace("➕", "+");

        function = function.replace("divided by", "/");
        function = function.replace("➗", "/");

        function = function.replace("times", "*");
        function = function.replace("✖️", "*");
        function = function.replace("x", "*");
        function = function.replace("✖", "*");

        function = function.replace("0️", "0");
        function = function.replace("1️", "1");
        function = function.replace("2️", "2");
        function = function.replace("3️", "3");
        function = function.replace("4️", "4");
        function = function.replace("5️", "5");
        function = function.replace("6️", "6");
        function = function.replace("7️", "7");
        function = function.replace("8️", "8");
        function = function.replace("\uD83C\uDFB1", "8");
        function = function.replace("9️", "9");
        function = function.replace("\uD83D\uDD1F", "10");
        function = function.replace("\uD83D\uDCAF", "100");

        return function;
    }

}
