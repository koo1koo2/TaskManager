import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

     // Validation method for user integer input
    
    public static boolean validateIntegerInput(String input, int max) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
    
        if(number > 0 && number <= max) {
            return true;
        } else {
            return false;
        }
    }
    
    //MAIN
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
            

            // Read and validate user input
            String input = "";
     
            boolean isValid = false;
            while(!isValid) {
                System.out.print("Enter your choice: ");
                input = scanner.nextLine();
                isValid = validateIntegerInput(input, 5);
                if(!isValid) {
                    System.out.println("Invalid number. Please enter a valid number.");
                }
            }

            //Convert user input string to integer
            int choice = Integer.parseInt(input);

            //Handle the different user choises
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
                   
                    break;
                case 2:    
                    // List the task titles with their index to the user
                    for (int i = 0; i < taskList.size(); i++) {
                        Task taskListItem = taskList.get(i);
                        String taskTitle = taskListItem.getTitle();
                        System.out.println(i + 1 + ". " + taskTitle);
                    }

                    //Prompt user for the task to update and validate user input
                    validInput = false;
                    String index = "";
                    while (!validInput) {
                        System.out.println("Enter the index of the task you want to update: ");
                        index = scanner.nextLine();
                        validInput = validateIntegerInput(index, taskList.size());
                        if(!validInput) {
                            System.out.println("Invalid input.");
                        }
                    }

                    // Convert user input to integer, substract 1 to corrigate to 0 index.
                    int taskIndex = Integer.parseInt(index) - 1;

                    // Get the selected task
                    Task selectedTask = taskList.get(taskIndex);

                    //Prompt user for updated task details
                    //TODO validate user input
                    System.out.println("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    System.out.println(" Enter new due date (yyyy-MM-dd): ");
                    String newDueDateString = scanner.nextLine();
                    LocalDate newDueDate = LocalDate.parse(newDueDateString);

                    //Update the selected task in the task list
                    selectedTask.setDescription(newDescription);
                    selectedTask.setDueDate(newDueDate);

                    //Notify the user 
                    System.out.println("Task updated successfully.");
                    break;

                case 3:
                    //TODO Delete a Task
                    //TODO Prompt user for the task to delete (e.g., by index)
                    //TODO Remove the selected task from the task list
                    break;
                case 4:
                    // TODO List Tasks
                    // TODO Display a list of all tasks in the task list
                    break;
                
                case 5:
                // TODO Ask if user really wants to quit, if yes quit, if no return
                    System.out.println("quit");
                    scanner.close();
                    isRunning = false;
                default:
                //TODO 
                    System.out.println("default");
                    break;

            }
        }
    }
}
