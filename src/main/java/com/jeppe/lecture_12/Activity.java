package com.jeppe.lecture_12;

public class Activity {
    private int event;
    private String task;
    private int duration;

    public Activity(int event, String task, int duration) {
        this.event = event;
        this.task = task;
        this.duration = duration;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "event=" + event +
                ", task=" + task +
                ", duration=" + duration +
                '}';
    }
}
