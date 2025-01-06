/**
 * This program demonstrates basic file operations in Java.
 * It provides options to:
 * 1. Write to a file
 * 2. Read from a file
 * 3. Modify a file
 */

import java.io.*;
import java.util.Scanner;

public class File_Handling_utility {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "example.txt";

        try {
            System.out.println("Choose an operation:");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify File");
            System.out.print("Enter your choice (1/2/3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    writeToFile(fileName, scanner);
                    break;
                case 2:
                    readFromFile(fileName);
                    break;
                case 3:
                    modifyFile(fileName, scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Writes user-provided content to a file.
     * @param fileName The name of the file to write to.
     * @param scanner Scanner to read user input.
     * @throws IOException If an I/O error occurs.
     */
    private static void writeToFile(String fileName, Scanner scanner) throws IOException {
        System.out.println("Enter text to write to the file:");
        String content = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Content written to " + fileName);
        }
    }

    /**
     * Reads and displays the content of a file.
     * @param fileName The name of the file to read from.
     * @throws IOException If an I/O error occurs.
     */
    private static void readFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Contents of " + fileName + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

    /**
     * Modifies the content of a file by overwriting it with user-provided content.
     * @param fileName The name of the file to modify.
     * @param scanner Scanner to read user input.
     * @throws IOException If an I/O error occurs.
     */
    private static void modifyFile(String fileName, Scanner scanner) throws IOException {
        System.out.println("Enter new content to overwrite the file:");
        String newContent = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(newContent);
            System.out.println("File " + fileName + " modified successfully.");
        }
    }
}
