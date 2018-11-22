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
                        .setChatId(executionInstruction.getChatId())
                        .setText(executionInstruction.getMessage());
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
    public void setupSettings(String timeCode, Integer cntRemains, ExecutionInstruction executionInstruction) {
        logger.log(Level.FINE, () -> String.format("Setup executing command: %s", CommandHandler.Commands.COMMANDS_SET_REMIND_TIME));
        TelegramBot telegramBot = new TelegramBot();
        telegramBot.sendMessage(new SendMessage()
                .setChatId(executionInstruction.getChatId())
                .setText(CommandHandler.Commands.COMMANDS_SET_REMIND_TIME.getCommandDescription()));
        switch (timeCode) {
            case "Min":
                executionInstruction.setTimeReminding(60 * 1000 / cntRemains);
                break;
            case "Hour":
                executionInstruction.setTimeReminding(60 * 60 * 1000 / cntRemains);
                break;
            case "Day":
                executionInstruction.setTimeReminding(24 * 60 * 60 * 1000 / cntRemains);
                break;
            case "Week":
                executionInstruction.setTimeReminding(7 * 24 * 60 * 60 * 1000 / cntRemains);
                break;
            case "Month":
                executionInstruction.setTimeReminding(30 * 7 * 60 * 60 * 1000 / cntRemains);
                break;
        }
    }
}
