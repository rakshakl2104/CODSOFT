import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Student class representing individual students
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

// Student Management System class to manage the collection of students
class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    // Method to add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Method to remove a student
    public void removeStudent(int rollNumber) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNumber() == rollNumber) {
                students.remove(i);
                break;
            }
        }
    }

    // Method to search for a student
    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    // Method to display all students
    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Method to save student data to a file
    /*@SuppressWarnings("unused")
    private static void saveDatatofile(String filename, String data) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(data);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }*/

    // Method to load data from a file
    @SuppressWarnings("unused")
    private static String loadDatafromfile(String filename) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            return null; // File not found or unable to read
        }
    }
}

// User interface for the Student Management System
class UserInterface {
    private StudentManagementSystem system;
    private Scanner scanner;
    //private String filename;
    public UserInterface() {
        system = new StudentManagementSystem();
        scanner = new Scanner(System.in);
    }

    // Method to display menu and handle user input
    public void displayMenu() {
        int choice;
        do {
            System.out.println("\nStudent Management System Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Student Data");
            System.out.println("6. Load Student Data");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    saveData();
                    break;
                case 6:
                    loadData();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    // Method to add a student
    private void addStudent() {
        System.out.println("\nEnter student details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Roll Number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Grade: ");
        String grade = scanner.nextLine();

        // Create student object
        Student student = new Student(name, rollNumber, grade);
        system.addStudent(student);
        System.out.println("Student added successfully.");
    }

    // Method to remove a student
    private void removeStudent() {
        System.out.print("\nEnter roll number of student to remove: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        system.removeStudent(rollNumber);
        System.out.println("Student removed successfully.");
    }

    // Method to search for a student
    private void searchStudent() {
        System.out.print("\nEnter roll number of student to search: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = system.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found:");
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to display all students
    private void displayAllStudents() {
        system.displayAllStudents();
    }

    // Method to save student data to a file
    public void saveData() {
        System.out.println("Enter the filename to save data:");
            String filename = scanner.nextLine();

            // Ask for the data to save
            System.out.println("Enter the data to save:");
            String data = scanner.nextLine();

            // Save the data into the specified file
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.println(data);
            } catch (IOException e) {
                System.err.println("Error saving data to file: " + e.getMessage());
            }
            System.out.println("Data saved successfully to " + filename);
        }

    public void loadData() {
        // Ask for the filename to load data
        System.out.println("Enter the filename to load data:");
        String filename = scanner.nextLine();

        // Load the data from the specified file
        //String loadedData = loadDatafromfile(filename);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) { // File not found or unable to read
        }
        System.out.println("Data loaded successfully from " + filename + ":");
        //System.out.println(loadedData);
    }
}

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.displayMenu();
    }
}

