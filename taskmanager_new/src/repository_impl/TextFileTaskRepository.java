package repository_impl;


import model.Task;
import repository.TaskRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * File-based implementation of TaskRepository, persisting tasks in a text file (tasks.txt).
 * Each line in the file has the format: id|completed|description
 */
public class TextFileTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private final Path filePath = Paths.get("tasks.txt");

    /**
     * Constructs the repository and loads existing tasks from file.
     */
    public TextFileTaskRepository() {
        loadFromFile();
    }

    private void loadFromFile() {
        tasks.clear();
        if (Files.exists(filePath)) {
            try {
                for (String line : Files.readAllLines(filePath)) {
                    String[] parts = line.split("\\|", 3);
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0]);
                        boolean completed = Boolean.parseBoolean(parts[1]);
                        String desc = parts[2];
                        Task t = new Task(id, desc);
                        t.setCompleted(completed);
                        tasks.add(t);
                        if (id >= nextId) {
                            nextId = id + 1;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to load tasks from file: " + e.getMessage());
            }
        }
    }

    private void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Task t : tasks) {
            lines.add(t.getId() + "|" + t.isCompleted() + "|" + t.getDescription());
        }
        try {
            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Failed to save tasks to file: " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> findAll() {
        loadFromFile();
        return new ArrayList<>(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Task task) {
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == task.getId()) {
                tasks.set(i, task);
                found = true;
                break;
            }
        }
        if (!found) {
            tasks.add(task);
        }
        saveToFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Task task) {
        tasks.removeIf(t -> t.getId() == task.getId());
        saveToFile();
    }

    /**
     * Creates and persists a new Task with an auto-incremented ID.
     *
     * @param description the description for the new task
     * @return the created Task
     */
    public Task create(String description) {
        Task task = new Task(nextId++, description);
        tasks.add(task);
        saveToFile();
        return task;
    }
}

