package com.tenta.puzzles.ioexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static public void main(final String[] args) {

        System.out.println("Emoji calculator\n Please enter a function:");

        final String wholeFunction;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            wholeFunction = reader.readLine();
            reader.close();
            doTheOperation(wholeFunction);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * The main objective here is to find Multiplication and Division operators in the function.
     * When we found first of it, we find numbers on the left and right side of it, and then do the Multiplication/Division.
     * After that we store the result on the place of those two numbers and do the process again until the final result
     * doesn't have just digits in the String.
     * @return
     */
    public static String doTheOperation(String function) {
        // filter all emoji inputs
        System.out.println(function);
        function = replaceAllEmojis(function);
        String finalResult;
        // Check if the result is just digits
        while (!function.matches("[0-9]+") && function.length() > 1) {
            String firstNumber = getFirstNumber(function);
            if (!firstNumber.matches("[0-9]+")) {
                styleToEmoticon("\uD83E\uDD37");
                function = null;
                break;
            }

            char operator1 = getOperator(function);
            String operator = String.valueOf(operator1);

            String secondNumber = getSecondNumber(function);
            if (secondNumber.equals("0") || !secondNumber.matches("[0-9]+")) {
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
            function = function.replace(firstNumber + operator + secondNumber, String.valueOf((int) result));

        }
        if (function != null) {
            finalResult = function;
            styleToEmoticon(finalResult);
            return finalResult;
        } else {
            return null;
        }

    }

    //getting operator from the exact position
    private static char getOperator(String wholeFunction) {
        return wholeFunction.charAt(getOperatorPosition(wholeFunction));
    }


    //get position of the operator to get first number on the left of it and the second of the right of it
    private static int getOperatorPosition(String wholeFunction) {
        for (int i = 0; i < wholeFunction.length(); i++) {
            //priority over the addition and subtraction
            if (wholeFunction.charAt(i) == '*' || wholeFunction.charAt(i) == '/' || wholeFunction.charAt(i) == '%') {
                return i;
            }
        }
        for (int i = 0; i < wholeFunction.length(); i++) {
            if (wholeFunction.charAt(i) == '+' || wholeFunction.charAt(i) == '-') {
                return i;
            }
        }
        return -1;
    }

    //getting first number from function
    private static String getFirstNumber(String wholeFunction) {
        int i = -1;
        int br = 0;
        while (i == -1) {
            i = getOperatorPosition(wholeFunction);
            br++;
            if (br == wholeFunction.length() - 1) {
                return "";
            }
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

    //getting second number from function
    private static String getSecondNumber(String wholeFunction) {
        int i = -1;
        int br = 0;
        while (i == -1) {
            i = getOperatorPosition(wholeFunction);
            br++;
            if (br == wholeFunction.length() - 1) {
                return "";
            }
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

    // output filtering
    public static String styleToEmoticon(String finalResult) {
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
        System.out.println("\n" + finalResult);
        return finalResult;
    }

    // Input filtering
    private static String replaceAllEmojis(String function) {
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
