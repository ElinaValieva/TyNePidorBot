package com.telegram.bot.executors;

import com.telegram.bot.models.TelegramBot;
import com.telegram.bot.models.ExecutionInstruction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandExecutor implements Executor {

    private static Logger logger = Logger.getLogger(CommandExecutor.class.getName());
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
        logger.log(Level.FINE, () -> String.format("Start executing command: %s with parameters %d", CommandHandler.Commands.COMMANDS_START, executionInstruction.getTimeReminding()));
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
        logger.log(Level.FINE, () -> String.format("Stop executing command: %s", CommandHandler.Commands.COMMANDS_STOP));
        timer.cancel();
    }

    @Override
    public void setupSettings() {
        logger.log(Level.FINE, () -> String.format("Setup executing command: %s", CommandHandler.Commands.COMMANDS_SET_REMIND_TIME));
        // todo
    }
}
