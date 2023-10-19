import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

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
    
    // Validation method for date input
    public static boolean validateDateInput(String input, String formatString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Method to list tasks
    public static void listTasks(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task taskListItem = taskList.get(i);
            String taskTitle = taskListItem.getTitle();
            String taskDescription = taskListItem.getDescription();
            String taskDueDate = taskListItem.getDueDate();
            System.out.println(i + 1 + ". " + taskTitle + " - due date : " + taskDueDate + "\n description: " + taskDescription);  
            }
    }
//TODO Files
    private static ArrayList<Task> loadTasksFromFile(String fileName) {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File taskFile = new File(fileName);
            Scanner fileReader = new Scanner(taskFile);
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                Task task = Task.fromString(line);
                taskList.add(task);
            }
            fileReader.close();
            System.out.println("Tasks loaded.");
            return taskList;
        
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return taskList;
        }
    }
//TODO delete the existing file and save the new or overwrite it
    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try {

        } catch (IOException e) {
            // TODO Handle exceptions (e.g., file not found, write errors)
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
            //TODO load from file, save to file(overwrite?)

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
                    
                    //TODO prompt user for task due date, validate user input
                    boolean validInput = false;
                    LocalDate dueDate = null;
                    String dateInput = "";
                    String formatString = "dd-MM-yy";
                    while (!validInput) {
                        System.out.println("Enter due date ("+formatString+"): ");
                        dateInput = scanner.nextLine();
                        validInput = validateDateInput(dateInput, formatString);
                        if(!validInput) {
                            System.out.println("Invalid date format. Please use "+formatString+ ".");
                        }
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                    dueDate = LocalDate.parse(dateInput, formatter);
                
                    //create the new task object 
                    Task newTask = new Task(title, description, dueDate);
                
                    //add new task to the tasklist
                    taskList.add(newTask);
                    System.out.println("The new task is added to the list.");
                   
                    break;
                case 2:    
                    // List the task titles with their index to the user
                    listTasks(taskList);

                    //Prompt user for the task to update and validate user input
                    validInput = false;
                    String updateIndex = "";
                    while (!validInput) {
                        System.out.println("Enter the index of the task you want to update: ");
                        updateIndex = scanner.nextLine();
                        validInput = validateIntegerInput(updateIndex, taskList.size());
                        if(!validInput) {
                            System.out.println("Invalid input.");
                        }
                    }

                    // Convert user input to integer, substract 1 to corrigate to 0 index.
                    int taskIndex = Integer.parseInt(updateIndex) - 1;

                    // Get the selected task
                    Task selectedTask = taskList.get(taskIndex);

                    //Prompt user for updated task details
                    //TODO validate user input
                    String newTitle = "";
                    System.out.println("Enter new task title: ");
                    String newTitleInput = scanner.nextLine();
                    if (newTitleInput.isEmpty()) {
                        newTitle = selectedTask.getTitle();
                        System.out.println("Title did not change.");
                    } else {
                        newTitle = newTitleInput;
                    }
                    
                    System.out.println("Enter new description: ");
                    String newDescriptionInput = scanner.nextLine();
                    String newDescription = "";
                    if (newDescriptionInput.isEmpty()){
                        newDescription = selectedTask.getDescription();
                        System.out.println("Description did not change.");
                    } else {
                        newDescription = newDescriptionInput;
                    }
//TODO
                    System.out.println(" Enter new due date (dd-MM-yy): ");
                    String newDueDateString = scanner.nextLine();
                    LocalDate newDueDate = LocalDate.parse(newDueDateString);

                    //Update the selected task in the task list
                    selectedTask.setTitle(newTitle);
                    selectedTask.setDescription(newDescription);
                    selectedTask.setDueDate(newDueDate);

                    //Notify the user 
                    System.out.println("Task updated successfully.");
                    break;

                case 3:
                    //Delete a Task
                    //Prompt user for the task to delete and validate the input
                    validInput = false;
                    String deleteIndex = "";
                    while (!validInput) {
                        System.out.println("Enter the index of the task you want to delete: ");
                        deleteIndex = scanner.nextLine();
                        validInput = validateIntegerInput(deleteIndex, taskList.size());
                        if(!validInput) {
                            System.out.println("Invalid input.");
                        }
                    }
                    //Remove the selected task from the task list
                    taskIndex = Integer.parseInt(deleteIndex) - 1;
                    taskList.remove(taskIndex);
                    System.out.println("Task deleted.");

                    break;
                case 4:
                    //Display a list of all tasks in the task list
                    listTasks(taskList);
                    break;
                
                case 5:
                    //Ask user if they really want to quit, if yes quit, else return
                    System.out.println("Do you want to quit? y/n: ");
                    String answer = scanner.nextLine();
                    
                    if(answer.contains("y")) {
                        scanner.close();
                        isRunning = false;
                        System.out.println("quit");
                    }
                    break;
            }
        }
    }
}
