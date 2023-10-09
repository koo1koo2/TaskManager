import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate dueDate;

    //getter for a whole task TODO
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

    //constructor for the Task class
    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}

