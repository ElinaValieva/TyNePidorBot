package com.telegram.bot.models;

import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExecutionInstruction {

    Logger logger = Logger.getLogger(ExecutionInstruction.class.getName());

    @Getter
    private Long chatId;

    private List<String> messages;

    @Getter
    private Integer timeReminding = 10000;

    public ExecutionInstruction(Update update) {
        initMessagesList();
        chatId = update.getMessage().getChatId();
    }

    public String getMessage() {
        int message = ThreadLocalRandom.current().nextInt(0, messages.size());
        return messages.get(message);
    }

    public void setMessage(String message) {
        if (messages.isEmpty())
            messages = new ArrayList<>();

        messages.add(message);
    }

    public void setTimeReminding(Integer timeReminding) {
        if (timeReminding > 0)
            this.timeReminding = timeReminding;
    }

    private void initMessagesList() {
        messages = new ArrayList<>();
        String path = ExecutionInstruction.class.getClassLoader().getResource("messages.txt").getPath();
        System.out.println(path);
        try (FileReader fileReader = new FileReader(path)) {
            String value;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((value = bufferedReader.readLine()) != null) {
                messages.add(value);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
