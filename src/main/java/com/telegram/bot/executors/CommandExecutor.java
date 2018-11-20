package com.telegram.bot.executors;

import com.telegram.bot.models.ExecutionInstruction;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommandExecutor {

    private static Executor executor;

    public static void setCommandForExecutor(String command) {
        if (command.equals(Commands.COMMANDS_START.command)) {
            executor = new StartExecutor();
            executor.execute(new ExecutionInstruction());
        }
        if (command.equals(Commands.COMMANDS_STOP.command)) {
            executor = new StopExecutor();
        }
        if (command.equals(Commands.COMMANDS_SET_REMIND_TIME.command)) {
            executor = new SettingsExecutor();
        }
        if (command.equals(Commands.COMMANDS_SET_RECIPIENT.command)) {
            executor = new SettingsExecutor();
        }
        if (command.equals(Commands.COMMANDS_SET_IMAGES.command)) {
            executor = new SettingsExecutor();
        }
        if (command.equals(Commands.COMMANDS_SET_MESSAGES.command)) {
            executor = new SettingsExecutor();
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
