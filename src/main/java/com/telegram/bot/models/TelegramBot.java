package com.telegram.bot.models;

import com.telegram.bot.executors.CommandHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramBot extends TelegramLongPollingBot {

    Logger log = Logger.getLogger(TelegramBot.class.getName());

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            CommandHandler.setExecutor(update.getMessage().getText(), update);
            SendMessage message = new SendMessage()
                    .setText(CommandHandler.Commands.COMMANDS_START.getCommandDescription())
                    .setChatId(update.getMessage().getChatId());
            sendMessage(message);
        }
    }

    public void sendMessage(SendMessage message)  {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public String getBotUsername() {
        return "TyNePidorBot";
    }

    public String getBotToken() {
        return "798633763:AAG5MJIA75_gtQe5_3km6b5PdktkHnfSpbc";
    }
}
