package com.telegram.bot.answerService;

/**
 * author: ElinaValieva on 02.02.2019
 */
public class AnswerService {

    private AnswerService() {

    }

    private static AnswerService answerService;

    public static AnswerService getInstance() {
        if (answerService == null)
            answerService = new AnswerService();
        return answerService;
    }

    /**
     * Если в содержится вопрос или слова когда/как/где, то заменять на ответ
     * Если есть однозначный ключ, то вернуть значение
     * Иначе, заменяем в фразе первый слог на ХУ, буквы А - Я, О - Е [У, Е, Ы, А, О, Э, Я, И, Ю]
     * В противном случае, отправляем default-ответ
     */
    public String getByWordTrigger(String word) {
        word = word.toLowerCase();
        if (word.contains("?") || word.contains("как")
                || word.contains("когда") || word.contains("где"))
            return Dictionary.getAnswerForQuestion();
        if (Dictionary.getAnswer(word) != null)
            return Dictionary.getAnswer(word);
        if (word.contains("у") || word.contains("е") || word.contains("ы") ||
                word.contains("а") || word.contains("о") || word.contains("э") ||
                word.contains("я") || word.contains("и") || word.contains("ю"))
            return Dictionary.getAnswerNegative(word);
        else
            return Dictionary.getDefault();
    }
}
