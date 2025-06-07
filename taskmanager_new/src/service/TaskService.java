package service;

import model.Task;
import java.util.List;

/**
 * Provides business operations for managing tasks.
 */
public interface TaskService {

    /**
     * Retrieves all tasks available.
     *
     * @return list of tasks
     */
    List<Task> getAllTasks();

    /**
     * Adds a new task with the given description.
     *
     * @param description the description of the new task
     * @return the created Task
     */
    Task addTask(String description);

    /**
     * Removes the specified task.
     *
     * @param task the task to remove
     */
    void removeTask(Task task);

    /**
     * Toggles the completion status of a task.
     *
     * @param task the task whose status to toggle
     */
    void toggleTaskCompletion(Task task);
}