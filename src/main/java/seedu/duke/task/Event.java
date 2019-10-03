package seedu.duke.task;

import seedu.duke.Duke;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Event class is a typ of task with a date/time when the event is going to happen.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Instantiates the Event class with name and time. Time must be passed in during the instantiation as it
     * cannot be changed later.
     *
     * @param name name of the Event
     * @param time time of the Event that is going to happen
     */
    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
        this.taskType = TaskType.Event;
    }

    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Instantiates the Event class with name and time. Time must be passed in during the instantiation as it
     * cannot be changed later. Supports adding a task to be done after the first main task.
     *
     * @param name    name of the Event
     * @param time    time of the Event that is going to happen
     * @param doAfter task to be done after the main task
     */
    public Event(String name, LocalDateTime time, String doAfter, String tag) {
        super(name);
        this.time = time;
        setDoAfterDescription(doAfter);
        this.taskType = TaskType.Event;
        getTag(tag);
    }

    /**
     * Converts the Event to a human readable string containing important information about the Event,
     * including the type and time of this Event.
     *
     * @return a human readable string containing the important information
     */
    @Override
    public String toString() {
        if (this.doAfterDescription == null && this.hasTag == null) {
            return "[E]" + this.getStatus() + " (by: " + formatDate() + ")";
        } else if (this.hasTag == null) {
            return "[E]" + this.getStatus() + " (by: " + formatDate() + ")"
                    + "\n   After which: " + doAfterDescription;
        } else {
            return "[E]" + this.getStatus() + " (by: " + formatDate() + ")"
                    + " #" + hasTag;
        }
    }

    /**
     * Outputs a string with all the information of this Event to be stored in a file for future usage.
     *
     * @return a string containing all information of this Event
     */
    @Override
    public String toFileString() {
        if (this.doAfterDescription == null && this.hasTag == null) {
            return (this.isDone ? "1" : "0") + " event " + this.name + " /at "
                    + formatDate();
        } else if (this.hasTag == null) {
            return (this.isDone ? "1" : "0") + " event " + this.name + " /at "
                    + formatDate() + " /doafter " + doAfterDescription;
        } else {
            return (this.isDone ? "1" : "0") + " event " + this.name + " /at "
                    + formatDate() + " #" + hasTag;
        }
    }

    /**
     * Outputs a formatted string of the time of this Event. The format is the same as input format and is
     * shared by all tasks.
     *
     * @return a formatted string of the time of this Event
     */
    protected String formatDate() {
        return format.format(this.time);
    }

    /**
     * Calculates whether the time set for the event is near enough.
     *
     * @param dayLimit maximum number of days from now for the event to be considered as near
     * @return the flag whether the event is near enough
     */
    @Override
    public boolean isNear(int dayLimit) {
        LocalDateTime now = LocalDateTime.now();
        if (this.time.compareTo(now) >= 0) {
            if (now.compareTo(this.time.minusDays(dayLimit)) >= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void snooze() {
        time = time.plusDays(3);
    }

    /**
     * Check if this task clashes with the new task being added.
     *
     * @param task the new task being added into the list.
     * @return true if this task clashes with the new task being added, false if not.
     */
    @Override
    public boolean isClash(Task task) {
        try {
            if (task.taskType.equals(TaskType.Deadline)) {
                Deadline deadlineTask = (Deadline) task;  // downcasting task to Deadline in order to use
                // getTime().
                if (this.time.compareTo(deadlineTask.getTime()) == 0) {
                    return true;
                }
            }
            if (task.taskType.equals(TaskType.Event)) {
                Event eventTask = (Event) task;  // downcasting task to Event in order to use getTime().
                if (this.time.compareTo(eventTask.getTime()) == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            Duke.getUI().showError("Error when finding clashes of tasks.");
        }
        return false;
    }
}
