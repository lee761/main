package seedu.duke.task.command;

import seedu.duke.CommandParser;
import seedu.duke.Duke;
import seedu.duke.common.command.Command;
import seedu.duke.task.TaskList;
import seedu.duke.task.entity.Task;
import seedu.duke.task.entity.ToDo;

import java.util.ArrayList;

/**
 * Edit command to change (or add) certain attributes of a task.
 */
public class TaskUpdateCommand extends Command {
    private int index;
    private ArrayList<String> descriptions;
    private ArrayList<TaskUpdateCommand.Attributes> attributes;

    /**
     * Instantiates a find command with all variables necessary.
     * @param index         position of task as specified by input
     */
    public TaskUpdateCommand(int index, ArrayList<String> descriptions,
                             ArrayList<TaskUpdateCommand.Attributes> attributes) {
        this.index = index;
        this.descriptions = descriptions;
        this.attributes = attributes;
    }

    public enum Attributes {
        time, doAfter, priority
    }

    /**
     * Executes the edit command.
     * @return true if successful, false otherwise
     */
    @Override
    public boolean execute() {
        TaskList taskList = Duke.getModel().getTaskList();
        String msg = "";
        try {
            for (int i = 0; i < descriptions.size(); i++) {
                switch (attributes.get(i)) {
                    case time:
                        if (taskList.get(index).getTaskType() == Task.TaskType.ToDo) {
                            throw new CommandParser.UserInputException("Time cannot be added to Todo task.");
                        }
                        msg = taskList.setTime(index, descriptions.get(i));
                        break;
                    case doAfter:
                        msg = taskList.setDoAfter(index, descriptions.get(i));
                        break;
                    case priority:
                        msg = taskList.setPriority(index, descriptions.get(i));
                        break;
                    default:
                        msg = "Invalid attribute";
                        break;
                }
            }
        } catch (CommandParser.UserInputException e) {
            Duke.getUI().showError(e.getMessage());
            return false;
        }
        Duke.getUI().showResponse(msg);
        return true;
    }
}
