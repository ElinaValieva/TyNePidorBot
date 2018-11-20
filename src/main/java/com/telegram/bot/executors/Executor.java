package com.telegram.bot.executors;

import com.telegram.bot.models.ExecutionInstruction;

public interface Executor {

    void execute(ExecutionInstruction executionInstruction);
}
