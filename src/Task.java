import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate dueDate;

    //getters for a task
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    public String getDueDate() {
        String dueDateString = dueDate.toString();
        return dueDateString;
    }
    

    //setters for the attributes separately
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    //Convert task to CSV string
    public String toFileString() {
        String dueDateString = dueDate.toString();
        return title + "," + description + "," + dueDateString;
    }

    //Convert CSV string to task object
    public static Task fromString(String taskString) {
        String[] parts = taskString.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid task format");
        }

        String title = parts[0];
        String description = parts[1];
        LocalDate dueDate = LocalDate.parse(parts[2]);

        return new Task(title, description, dueDate);
    }
    

    //constructor for the Task class
    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}

