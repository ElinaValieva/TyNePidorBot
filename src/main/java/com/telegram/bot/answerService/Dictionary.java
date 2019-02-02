package com.telegram.bot.answerService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author: ElinaValieva on 02.02.2019
 */
class Dictionary {

    private static Map<String, String> dictionary;
    private static List<String> chars;

    static {
        chars = Arrays.asList("а", "у", "е", "ы", "а", "о", "э", "я", "и", "ю");
        dictionary = new HashMap<>();
        dictionary.put(null, "Ой, ты пидор!");
        dictionary.put("хочу", "Хотеть невредно!");
        dictionary.put("?", "А тебе не все равно?! Ну ты тупой");
        dictionary.put("нет", "Пидора ответ!");
        dictionary.put("короче", "У кого короче, тот дома сидит!");
    }

    static String getAnswer(String question) {
        if (question.contains("нет"))
            return dictionary.get("нет");
        if (question.contains("короч") || question.contains("кароч"))
            return dictionary.get("короче");
        if (question.contains("хочу") || question.contains("хачу"))
            return dictionary.get("хочу");
        else return null;
    }

    static String getAnswerForQuestion() {
        return dictionary.get("?");
    }

    static String getAnswerNegative(String question) {
        String[] words = question.split(" ");
        String mainWord = words[words.length - 1];
        String firstSyllable = getFirstSyllable(mainWord);
        return mainWord
                .replace(firstSyllable, "ху")
                .replace("а", "я")
                .replace("о", "ё");

    }

    static String getDefault() {
        return dictionary.get(null);
    }

    private static String getFirstSyllable(String word) {
        String character = word.substring(0, 1);
        String finalCharacter = character;
        if (!chars.stream().filter(characterChars -> characterChars.equals(finalCharacter)).findAny().isPresent()) {
            for (int characterWord = 1; characterWord < word.length(); characterWord++) {
                String wordChar = word.substring(characterWord, characterWord + 1);
                List<String> strings = chars.stream().filter(characterChars -> characterChars.equals(wordChar))
                        .collect(Collectors.toList());
                if (strings == null || strings.isEmpty())
                    character = character + wordChar;
                else
                    break;
            }
        }
        return character;
    }
}
