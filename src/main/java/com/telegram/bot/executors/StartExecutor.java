package com.telegram.bot.executors;

import com.telegram.bot.TelegramBot;
import com.telegram.bot.models.ExecutionInstruction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartExecutor implements Executor {

    Logger logger = Logger.getLogger(StartExecutor.class.getName());

    public void execute(ExecutionInstruction executionInstruction) {
        Timer timer = new Timer();
        TelegramBot telegramBot = new TelegramBot();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SendMessage sendMessage = new SendMessage()
                        .setChatId(416062411L)
                        .setText("Ты пидор!");
                logger.log(Level.FINE, String.format("Start send message %s", sendMessage.getText()));
                telegramBot.sendMessage(sendMessage);
            }
        }, 0, executionInstruction.getTimeReminding());
    }
}
