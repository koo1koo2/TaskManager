import java.time.LocalDate;

public class Task {
    String title;
    String description;
    LocalDate dueDate;

    //getter for a whole task TODO
    
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

