package service_impl;


import model.Task;
import repository_impl.TextFileTaskRepository;
import service.TaskService;
import java.util.List;

/**
 * Default implementation of TaskService using a text-file repository.
 */
public class TaskServiceImpl implements TaskService {
    private final TextFileTaskRepository repository = new TextFileTaskRepository();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task addTask(String description) {
        return repository.create(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeTask(Task task) {
        repository.delete(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleTaskCompletion(Task task) {
        task.setCompleted(!task.isCompleted());
        repository.save(task);
    }
}
