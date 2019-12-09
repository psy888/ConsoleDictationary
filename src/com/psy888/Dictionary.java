package com.psy888;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    private static final String WORD_DIVIDER = " - ";
    private static final String LINE_DIVIDER = "\n";
    private static final String MENU_TRANSLATE = "1";
    private static final String MENU_ADD_WORD = "2";
    private static final String MENU_REPLACE_WORD = "3";
    private static final String MENU_REMOVE_WORD = "4";
    private static final String MENU_SHOW_WORD_LIST = "0";
    HashMap<String, String> base;
    Scanner scanner;

    {
        scanner = new Scanner(System.in);
        base = FileRW.readBase();
        System.out.println("В словаре " + base.size() + " слов");
    }

    //todo User interaction process via menu

    public void menu() {
        String userInput;
        String translation;
        do {
            System.out.println("\"" + MENU_TRANSLATE + "\" - перевод слова (рус -> eng)");
            System.out.println("\"" + MENU_ADD_WORD + "\" - добавить слово в словарь");
            System.out.println("\"" + MENU_REPLACE_WORD + "\" - заменить перевод слова");
            System.out.println("\"" + MENU_REMOVE_WORD + "\" - удалить слово из словоря");
            System.out.println("\"" + MENU_SHOW_WORD_LIST + "\" - вывести весь список слов");

            userInput = scanner.nextLine().trim().toLowerCase();
            switch (userInput) {

                case MENU_TRANSLATE:
                    System.out.println("Введите слово на русском языке и нажмите ввод");
                    userInput = scanner.nextLine().trim().toLowerCase();
                    translation = getTranslation(userInput);
                    if (translation != null) {
                        System.out.println(userInput + WORD_DIVIDER + translation);
                    } else {
                        System.out.println("Слово " + translation + " не найдено в словаре");
                    }
                    break;
                case MENU_ADD_WORD:
                    System.out.println("Для добавления введите слово на русском языке и нажмите ввод");
                    userInput = scanner.nextLine().trim().toLowerCase();
                    System.out.println("Введите перевод слова на английский и нажмите ввод");
                    translation = scanner.nextLine().trim().toLowerCase();
                    if (addWord(userInput, translation) == null) {
                        System.out.println("Слово " + userInput + WORD_DIVIDER + translation + " добавлено в словарь");
                    } else {
                        System.out.println("Слово " + userInput + " не добавлено в словарь");
                    }
                    break;
                case MENU_REPLACE_WORD:
                    System.out.println("Для замены введите слово на русском языке и нажмите ввод");
                    userInput = scanner.nextLine().trim().toLowerCase();
                    System.out.println("Перевод слова " + getTranslation(userInput) + " введите замену:");
                    translation = scanner.nextLine().trim();
                    if (replaceWord(userInput, translation) != null) {
                        System.out.println("Успешно заменено");
                    } else {
                        System.out.println("Что то пошло не так");
                    }
                    break;
                case MENU_REMOVE_WORD:
                    System.out.println("Для удаления введите слово на русском языке и нажмите ввод");
                    userInput = scanner.nextLine().trim().toLowerCase();
                    if (removeWord(userInput) != null) {
                        System.out.println("Слово " + userInput + " удалено");
                    } else {
                        System.out.println("Слово " + userInput + "не удалено");
                    }
                    break;
                case MENU_SHOW_WORD_LIST:
                    System.out.println(listAllWords());
                    break;
                default:
                    userInput = "";
                    break;
            }


        } while (!userInput.isEmpty());

        FileRW.saveBase(base);


    }

    /**
     * перевести слово
     *
     * @param word -слово на русском
     * @return - перевод
     */
    private String getTranslation(String word) {
        return base.get(word);
    }

    /**
     * Добавить слово в словарь
     *
     * @param key   - слово на русском
     * @param value - перевод
     * @return - null (по идее)
     */
    private String addWord(String key, String value) {
        return base.putIfAbsent(key, value);
    }

    /**
     * заменить перевод слова
     *
     * @param key   - слово которое есть в словаре
     * @param value - новый перевод
     * @return - старое значение перевода
     */
    private String replaceWord(String key, String value) {
        return base.replace(key, value);
    }

    private String listAllWords() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : base.entrySet()) {
            sb.append(entry.getKey());
            sb.append(WORD_DIVIDER);
            sb.append(entry.getValue());
            sb.append(LINE_DIVIDER);
        }
        return sb.toString();
    }

    private String removeWord(String key) {
        return base.remove(key);
    }
}
