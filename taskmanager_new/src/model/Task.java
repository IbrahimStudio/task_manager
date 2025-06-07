package model;

/**
 * Represents a task with a unique identifier, description, and completion status.
 */
public class Task {
    private final int id;
    private String description;
    private boolean completed;

    /**
     * Constructs a new Task with the specified ID and description.
     *
     * @param id          the unique identifier of the task
     * @param description the textual description of the task
     */
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.completed = false;
    }

    /**
     * Returns the unique identifier of this task.
     *
     * @return the task ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of this task.
     *
     * @param description the new description text
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Checks whether this task has been completed.
     *
     * @return true if completed; false otherwise
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of this task.
     *
     * @param completed the new completion status
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns a string representation of this task, including its completion status and description.
     *
     * @return formatted string of the task
     */
    @Override
    public String toString() {
        return (completed ? "[x] " : "[ ] ") + description;
    }
}