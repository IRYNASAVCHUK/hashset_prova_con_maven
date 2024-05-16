package com.example.project.logic.log_record.record;

public record Levels(int target, int args, int result) {
    public Levels decreaseTarget() {
        return new Levels(target - 1, args, result);
    }

    public Levels decreaseArgs() {
        return new Levels(target, args - 1, result);
    }

    public Levels decreaseResult() {
        return new Levels(target, args, result - 1);
    }
    public Levels decreaseAll(){
        return new Levels(target-1, args-1, result - 1);
    }
}
