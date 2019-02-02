package com.telegram.bot.telegramBot;

import com.telegram.bot.answerService.AnswerService;
import com.telegram.bot.configuration.BotConfiguration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramBot extends TelegramLongPollingBot {

    Logger log = Logger.getLogger(TelegramBot.class.getName());
    private AnswerService answerService = AnswerService.getInstance();

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            SendMessage message = new SendMessage()
                    .setText(answerService.getByWordTrigger(messageText))
                    .setChatId(update.getMessage().getChatId());
            sendMessage(message);
        }
    }

    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public String getBotUsername() {
        return BotConfiguration.BOT_CONFIGURATION_NAME.getConfigs();
    }

    public String getBotToken() {
        return BotConfiguration.BOT_CONFIGURATION_TOKEN.getConfigs();
    }
}
