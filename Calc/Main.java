package com.Protok.idea.Calc;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/** Создай консольное приложение “Калькулятор”.
Приложение должно читать из консоли введенные пользователем строки, числа,
арифметические операции проводимые между ними и выводить в консоль результат их выполнения.

Реализуй класс Main с методом public static String calc(String input).
Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения.
Ты можешь добавлять свои импорты, классы и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие) */

public class Main {
    public static void main(String[] args) {
        String welcomeMSG = new String("Привет Дорогой Друг! Это программа простого консольного калькулятора. Пожалуйста введи выражение в соответствии с форматом (X +-/* Y):");
        Scanner scanInput = new Scanner(System.in);

        System.out.println(welcomeMSG);
        String userInput = scanInput.nextLine();
        IExpressionCheckAndSet Expression1 = new IExpressionCheckAndSet(userInput);
        scanInput.close();
    }
}
class IExpressionCheckAndSet{
    private String [] elements;
//    private final String [] acceptableArab = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", };
//    private final String [] acceptableRoman = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",  };
    private final String [] acceptableNumbers = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
    };
    private int v1 = -1;
    private boolean v1IsRome = false;
    private int v2 = -1;
    private boolean v2IsRome = false;
    private int result1;
    private Roman100[] x100 = Roman100.values();
    public static boolean _pdo = false; // print debug out
    //  functions
    public static String getVersion(){ return "1.01"; }
    public IExpressionCheckAndSet(String userInput){

        // check empty string
        if (userInput.isEmpty()) {
            try { throw new IOException(); }
            catch (IOException e) { System.err.println("Получена пустая строка. Пожалуйста введи подходящее выражение. (X +-/* Y)."); return; }
        }

        // check minimum string length
        else if (userInput.length() < 5) {
            try { throw new IOException(); }
            catch (IOException e) { System.err.println("В выражении верного формата должно быть не менее 5 символов с учётом пробелов. Пожалуйста введи заново. (X +-/* Y)."); return; } }
        else {
            // gather input
            if (_pdo) { System.out.println("start to check and gather info from input: "+userInput); }
            // vars
            elements = userInput.split(" ");
            if (_pdo) {
                short i = 0;
                for (String element: elements){
                    System.out.println(i+". "+element);
                    ++i;
                }
            }

            // check number of elements of expression
            if (elements.length > 3 ) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Получено больше эелементов выражения, чем требуется по заданному формату - два операнда и один оператор. Пожалуйста введи ещё раз (X +-/* Y)."); return; }
            }
            if (elements.length < 3) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Получено меньше эелементов выражения, чем требуется по заданному формату операции. Пожалуйста введи ещё раз (X +-/* Y)."); return; }
            }

            // set acceptable numbers and lang
            for (short i = 0; i < acceptableNumbers.length; i++) {
                if (_pdo) { System.out.println("elements[0]: "+elements[0]+", acceptableNumbers[i]: "+acceptableNumbers[i]); }
                if (Objects.equals(acceptableNumbers[i], elements[0])) {
                    if (i < 10) {
                        v1IsRome = true;
                        v1 = i + 1;
                    }
                    if (i > 9 ) {
                        v1IsRome = false;
                        v1 = i - 9;
                    }
                    break;
                }
            }
            for (short i = 0; i < acceptableNumbers.length; i++) {
                if (Objects.equals(acceptableNumbers[i], elements[2])) {
                    if (i < 10) {
                        v2IsRome = true;
                        v2 = i + 1;
                    }
                    if (i > 9 ) {
                        v2IsRome = false;
                        v2 = i - 9;
                    }
                    break;
                }
            }

            // check is numbers was acceptable
            if (v1 == -1 ) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Недопустимое первое число. К вводу допустимы положительные числа величиной от 1 до 10. Пожалуйста введи ещё раз."); return; }
            }
            if (v2 == -1 ) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Недопустимое второе число. К вводу допустимы положительные числа величиной от 1 до 10. Пожалуйста введи ещё раз."); return; }
            }

            // check numbers language equal to each other
            if (v1IsRome != v2IsRome){
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Оба числа одновременно необходимо ввести или римским или арабским письмом. Пожалуйста введи ещё раз."); return; }
            }

            // set vars, unused
//            v1 = parseInt(elements[0], 10);
//            v2 = parseInt(elements[2], 10);
            if (_pdo) { System.out.println("v1: "+v1+", v2: "+v2); }

            // check numbers value not higher than 10 and no less than 1. obsolete
//            if (v1 < 1 || v1 > 10) {
//                try { throw new IOException(); }
//                catch (IOException e) { System.err.println("К вводу допустимы положительные числа величиной от 1 до 10. Пожалуйста введи ещё раз."); return; }
//            }
//            if (v2 < 1 || v2 > 10) {
//                try { throw new IOException(); }
//                catch (IOException e) { System.err.println("К вводу допустимы положительные числа величиной от 1 до 10. Пожалуйста введи ещё раз."); return; }
//            }

            // check and do operation
            switch (elements[1]){
                case "+" :
                    if (_pdo) { System.out.println("Действие - сумма"); }
                    result1 = (int)(v1 + v2);
                    break;
                case "-" : if (_pdo) { System.out.println("Действие - разность"); }
                    result1 = (int)(v1 - v2);
                    break;
                case "*" : if (_pdo) { System.out.println("Действие - умножение"); }
                    result1 = (int)(v1 * v2);
                    break;
                case "/" : if (_pdo) { System.out.println("Действие - деление"); }
                    result1 = (int)(v1 / v2);
                    break;
                case default :
                    try { throw new IOException(); }
                    catch (IOException e) { System.err.println("Полученое действие не соотвествует заданному формату выражения. Пожалуйста введи ещё раз. (X +-/* Y)."); return; }
            }

            // check result for Roman > 0
            if (v1IsRome && result1 < 1){
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Результат действия с римскими цифрами должен быть больше 0. Попробуй ввести другой пример."); return; }
            }
            // print result
            if (v1IsRome){
                System.out.println(x100[result1-1]);
            } else {
                System.out.println(result1);
            }
        }
    }
}

