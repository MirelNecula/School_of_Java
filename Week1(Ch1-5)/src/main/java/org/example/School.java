package org.example;
import java.util.*;

public class School {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while(true){
    System.out.print("Enter the student name : ( or 'exit' to quit) ");
    String studentName = sc.nextLine();
    if (studentName.equalsIgnoreCase("exit")) {
        System.out.print("Program exited.");
        break;
    }
    System.out.print("Enter the student notes (3): ");
    int note1 = sc.nextInt();
    int note2 = sc.nextInt();
    int note3 = sc.nextInt();
    sc.nextLine();
    double average = (note1 + note2 + note3) / 3.0;
    System.out.printf("The average of %s is: %.2f%n", studentName, average);

    System.out.println("Enter total number of classes: ");
    String totalClasses = sc.nextLine();
    System.out.println("Enter number of classes attended: ");
    String attendedClasses = sc.nextLine();

    double  attendace = (Double.parseDouble(attendedClasses) / Double.parseDouble(totalClasses)) * 100;
    System.out.printf("The attendance of %s is: %.2f%%%n ", studentName, attendace);

        String grade = switch((int)average/10) {
            case 10,9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };

    if(average >= 70 && attendace >= 75){
        System.out.println("Average: " + average + "Grade: " + grade + " ->PASS");
    } else if(average >= 50 && attendace < 70){
        System.out.println("Average: " + average + "Grade: " + grade + " -> Re-exam");
    } else {
        System.out.println("Average: " + average + "Grade: " + grade + " -)FAIL");
    }
    }

    }
}
