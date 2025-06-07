package repository;

import model.Task;
import java.util.List;

/**
 * Defines methods for storing, retrieving, and deleting Task entities.
 */
public interface TaskRepository {

    /**
     * Retrieves all tasks from storage.
     *
     * @return list of all tasks
     */
    List<Task> findAll();

    /**
     * Saves or updates the specified task in storage.
     *
     * @param task the task to save or update
     */
    void save(Task task);

    /**
     * Deletes the specified task from storage.
     *
     * @param task the task to delete
     */
    void delete(Task task);
}