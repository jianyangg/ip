package duke;

/**
 * Represents a Task.
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Constructs a Task object.
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task name.
     * @return String representing the task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the status of the task.
     * @return Boolean representing the status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task object.
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.taskName;
    }

    /**
     * Returns a string representation of the Task object to be saved in the file.
     * @return String representation of the Task object to be saved in the file.
     */
    public String saveString() {
        return "";
    }

    /**
     * Parses the data string and returns a Task object.
     * @param data String representation of the Task object to be saved in the file.
     * @return Task object representing the data string.
     */
    public static Task parseTask(String data) {
        // T | 1 | read book
        // D | 0 | return book | 1920-01-01
        // E | 0 | project meeting | Aug 6th | Aug 8th
        String[] dataArr = data.split(" \\| ");
        String taskType = dataArr[0];
        boolean isDone = dataArr[1].equals("1");
        // each task has at least 3 elements
        String taskName = dataArr[2];
        Task newTask;
        // todo
        if (taskType.equals("T")) {
            newTask = new ToDo(taskName);
            // deadline
        } else if (taskType.equals("D")) {
            newTask = new Deadline(taskName, dataArr[3]);
            // event
        } else {
            newTask = new Event(taskName, dataArr[3], dataArr[4]);
        }
        if (isDone) {
            newTask.markAsDone();
        }
        return newTask;
    }
}