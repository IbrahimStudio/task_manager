package ui;


import model.Task;
import service.TaskService;
import service_impl.TaskServiceImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * JavaFX application providing a graphical interface for the Task Manager.
 */
public class Main_EP extends Application {
    private final TaskService taskService = new TaskServiceImpl();
    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    /**
     * Starts the JavaFX application and sets up the main stage.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Manager");

        // Load persisted tasks on startup
        taskList.addAll(taskService.getAllTasks());

        // List view showing all tasks
        ListView<Task> listView = new ListView<>(taskList);
        listView.setPrefHeight(200);

        // Text field for new task descriptions
        TextField input = new TextField();
        input.setPromptText("New task description");
        input.setPrefWidth(200);

        // Button to add a task
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String desc = input.getText().trim();
            if (!desc.isEmpty()) {
                Task newTask = taskService.addTask(desc);
                taskList.add(newTask);
                input.clear();
            }
        });

        // Button to remove selected task
        Button removeButton = new Button("Remove");
        removeButton.setOnAction(e -> {
            Task selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                taskService.removeTask(selected);
                taskList.remove(selected);
            }
        });

        // Button to toggle completion status
        Button toggleButton = new Button("Toggle Complete");
        toggleButton.setOnAction(e -> {
            Task selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                taskService.toggleTaskCompletion(selected);
                listView.refresh();
            }
        });

        // Layout for controls
        HBox controls = new HBox(10, input, addButton, removeButton, toggleButton);
        controls.setPadding(new Insets(10));

        VBox root = new VBox(10, listView, controls);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    /**
     * Main entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
