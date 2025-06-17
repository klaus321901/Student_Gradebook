import java.util.*;

class Student {
    String name;
    HashMap<String, Double> subjectScores;

    Student(String name) {
        this.name = name;
        this.subjectScores = new HashMap<>();
    }

    // Add or update a score
    void addScore(String subject, double score) {
        subjectScores.put(subject, score);
    }

    // Calculate the average score
    double getAverageScore() {
        double total = 0;
        for (double score : subjectScores.values()) {
            total += score;
        }
        return subjectScores.isEmpty() ? 0 : total / subjectScores.size();
    }

    public String toString() {
        return "Name: " + name + ", Average Score: " + String.format("%.2f", getAverageScore());
    }
}

public 1class Gradebook {
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Menu loop
        do {
            System.out.println("\n=== Student Gradebook ===");
            System.out.println("1. Add Student");
            System.out.println("2. Enter Grades");
            System.out.println("3. View All Students & Averages");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    enterGrades(scanner);
                    break;
                case 3:
                    viewStudents();
                    break;
                case 4:
                    System.out.println("Exiting Gradebook. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 4);

        scanner.close();
    }

    static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        students.add(new Student(name));
        System.out.println("Student added.");
    }

    static void enterGrades(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        viewStudentsBrief();

        System.out.print("Enter student number to add grades: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (index <= 0 || index > students.size()) {
            System.out.println("Invalid student number.");
            return;
        }

        Student student = students.get(index - 1);

        System.out.print("How many subjects? ");
        int subjectCount = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Enter subject name: ");
            String subject = scanner.nextLine();

            System.out.print("Enter score for " + subject + ": ");
            double score = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            student.addScore(subject, score);
        }

        System.out.println("Grades updated.");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    static void viewStudentsBrief() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).name);
        }
    }
}
