package com.example.oase;

public class Task {

    public String matkulTask, typeTask, deadlineTask;

    public Task (){};
    public Task(String matkulTask, String typeTask, String deadlineTask) {
        this.matkulTask = matkulTask;
        this.typeTask = typeTask;
        this.deadlineTask = deadlineTask;
    }

    public String getMatkulTask() {
        return matkulTask;
    }

    public void setMatkulTask(String matkulTask) {
        this.matkulTask = matkulTask;
    }

    public String getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(String typeTask) {
        this.typeTask = typeTask;
    }

    public String getDeadlineTask() {
        return deadlineTask;
    }

    public void setDeadlineTask(String deadlineTask) {
        this.deadlineTask = deadlineTask;
    }
}
