import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args)  {
        // Create a task list to store tasks
        ArrayList<Task> taskList = new ArrayList<>();

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Display a welcome message
        System.out.println("Welcome to the Task Manager Application!");

        // Main application loop
        boolean isRunning = true;
        while (isRunning) {
            // Display menu options
            System.out.println("\nMenu:");
            System.out.println("1. Add a Task");
            System.out.println("2. Update a Task");
            System.out.println("3. Delete a Task");
            System.out.println("4. List Tasks");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            // Read user input
            // TODO validate user input
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch(choice) {
                case 1:
                    //prompt user for task title, make sure it`s not an empty string 
                    String title = "";
                    while (title.isEmpty()) {
                        System.out.println("Enter task title: ");
                        title = scanner.nextLine();
                        if (title.isEmpty()) {
                            System.out.println("Title is required.");
                        }
                    }
                    //prompt user for task description
                    System.out.println("Enter task description (optional): ");
                    String description = scanner.nextLine();
                    
                    //prompt user for task due date, validate user input
                    boolean validInput = false;
                    LocalDate dueDate = null;

                    while (!validInput) {
                        System.out.println("Enter due date (yyyy-MM-dd): ");

                        try {
                            String dueDateString = scanner.nextLine();
                            dueDate = LocalDate.parse(dueDateString);
                            validInput = true; // Valid input; exit the loop
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                        }
                    }
                
                    //create the new task object 
                    Task newTask = new Task(title, description, dueDate);
                
                    //add new task to the tasklist
                    taskList.add(newTask);
                    System.out.println("The new task is added to the list.");

                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 5:
                // TODO Ask if user really wants to quit, if yes quit, if no return
                    System.out.println("quit");
                    scanner.close();
                    isRunning = false;
                default:
                    System.out.println("default");
                    break;

            }
        }
    }
}
