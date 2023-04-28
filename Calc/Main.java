package com.Protok.idea.Calc;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/** Создай консольное приложение “Калькулятор”.
Приложение должно читать из консоли введенные пользователем строки, числа,
арифметические операции проводимые между ними и выводить в консоль результат их выполнения.

Реализуй класс Main с методом public static String calc(String input).
Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения.
Ты можешь добавлять свои импорты, классы и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие)

Выполнил Protok, версия 1.03 */

public class Main {
    static boolean _pdo = false; // print debug out

    public static void main(String[] args) {
        System.out.println("Привет Дорогой Друг! Это программа простого консольного калькулятора. Пожалуйста введи выражение в соответствии с форматом (X +-/* Y):");
        Scanner scanInput = new Scanner(System.in);
        String userInput = scanInput.nextLine();
        String output = calc(userInput);
        if (output != null){ System.out.println(output); }
        scanInput.close();
    }

    public static String calc(String input){

        // check empty string
        if (input.isEmpty()) {
            try { throw new IOException(); }
            catch (IOException e) { System.err.println("Получена пустая строка. Пожалуйста введи подходящее выражение. (X +-/* Y)."); return null; }
        }

        // check minimum string length
        else if (input.length() < 5) {
            try { throw new IOException(); }
            catch (IOException e) { System.err.println("В выражении верного формата должно быть не менее 5 символов с учётом пробелов. Пожалуйста введи заново. (X +-/* Y)."); return null; } }
        else {
            // gather input
            if (_pdo) { System.out.println("start to check and gather info from input: "+input); }

            // vars
            String[] elements = input.split(" ");
            int v1 = -1;
            boolean v1IsRome = false;
            int v2 = -1;
            boolean v2IsRome = false;
            int result1;
            String result2;
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
                catch (IOException e) { System.err.println("Получено больше эелементов выражения, чем требуется по заданному формату - два операнда и один оператор. Пожалуйста введи ещё раз (X +-/* Y)."); return null; }
            }
            if (elements.length < 3) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Получено меньше эелементов выражения, чем требуется по заданному формату операции. Пожалуйста введи ещё раз (X +-/* Y)."); return null; }
            }

            // set acceptable numbers and lang
            String[] acceptableNumbers = {
                    "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            };
            for (short i = 0; i < acceptableNumbers.length; i++) {
                if (_pdo) { System.out.println("elements[0]: "+ elements[0]+", acceptableNumbers[i]: "+ acceptableNumbers[i]); }
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

            // check is numbers acceptable
            if (v1 == -1 ) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Недопустимое первое число. К вводу допустимы положительные числа величиной от 1 до 10. Пожалуйста введи ещё раз."); return null; }
            }
            if (v2 == -1 ) {
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Недопустимое второе число. К вводу допустимы положительные числа величиной от 1 до 10. Пожалуйста введи ещё раз."); return null; }
            }

            // check numbers language equal to each other
            if (v1IsRome != v2IsRome){
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Оба числа одновременно необходимо ввести или римским или арабским письмом. Пожалуйста введи ещё раз."); return null; }
            }
            if (_pdo) { System.out.println("v1: "+ v1 +", v2: "+ v2); }

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
                    catch (IOException e) { System.err.println("Полученое действие не соотвествует заданному формату выражения. Пожалуйста введи ещё раз. (X +-/* Y)."); return null; }
            }

            // check result for Roman > 0
            if (v1IsRome && result1 < 1){
                try { throw new IOException(); }
                catch (IOException e) { System.err.println("Результат действия с римскими цифрами должен быть больше 0. Попробуй ввести другой пример."); return null; }
            }

            // print result
            if (v1IsRome){
                Roman100[] x100 = Roman100.values();
//                System.out.println(x100[result1 -1]);
                result2 = String.valueOf(x100[result1 -1]);
            } else {
//                System.out.println(result1);
                result2 = String.valueOf(result1);
            }
            return result2;

         }
    }
}


