package com.telegram.bot.executors;

import com.telegram.bot.models.ExecutionInstruction;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandHandler {

    private static Logger logger = Logger.getLogger(CommandHandler.class.getName());

    public static void setExecutor(String command) {
        logger.log(Level.FINE, () -> String.format("Handle command %s", command));

        if (command.equals(Commands.COMMANDS_START.command)) {
            CommandExecutor.getInstance().execute(new ExecutionInstruction());
        }
        if (command.equals(Commands.COMMANDS_STOP.command)) {
            CommandExecutor.getInstance().stopExecuting();
        }
        if (command.equals(Commands.COMMANDS_SET_REMIND_TIME.command)) {
            CommandExecutor.getInstance().setupSettings();
        }
        if (command.equals(Commands.COMMANDS_SET_RECIPIENT.command)) {
            CommandExecutor.getInstance().setupSettings();
        }
        if (command.equals(Commands.COMMANDS_SET_IMAGES.command)) {
            CommandExecutor.getInstance().setupSettings();
        }
        if (command.equals(Commands.COMMANDS_SET_MESSAGES.command)) {
            CommandExecutor.getInstance().setupSettings();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Commands {

        COMMANDS_START("/start", "Hello, this is TyPidorBot. Let's start!"),
        COMMANDS_STOP("/stop", "Ok."),
        COMMANDS_SET_REMIND_TIME("/time", "Set time reminding for pidors."),
        COMMANDS_SET_RECIPIENT("/pidors", "Set all reminds pidors."),
        COMMANDS_SET_MESSAGES("/message", "Set all message for pidors."),
        COMMANDS_SET_IMAGES("/image", "Set all images for pidors."),
        COMMANDS_ERROR("", "Wrong command. Use /help to start bot.");

        private String command;
        private String commandDescription;
    }
}
