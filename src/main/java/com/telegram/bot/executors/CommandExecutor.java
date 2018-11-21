package com.telegram.bot.executors;

import com.telegram.bot.models.TelegramBot;
import com.telegram.bot.models.ExecutionInstruction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor implements Executor {

    Logger logger = Logger.getLogger(CommandExecutor.class.getName());
    private static Timer timer;
    private static CommandExecutor commandExecutor;

    private CommandExecutor() {
    }

    public static CommandExecutor getInstance() {
        if (commandExecutor == null) {
            commandExecutor = new CommandExecutor();
            timer = new Timer();
        }
        return commandExecutor;
    }

    public void execute(ExecutionInstruction executionInstruction) {
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

    @Override
    public void stopExecuting() {
        timer.cancel();
    }

    @Override
    public void setupSettings() {
        // todo
    }
}
