package com.telegram.bot.executors;

import com.telegram.bot.models.ExecutionInstruction;

public interface Executor {

    void execute(ExecutionInstruction executionInstruction);

    void stopExecuting();

    void setupSettings(String timeCode, Integer cntRemains, ExecutionInstruction executionInstruction);
}
