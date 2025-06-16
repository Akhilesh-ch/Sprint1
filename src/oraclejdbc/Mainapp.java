package oraclejdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;

public class Mainapp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StudentDAO sdao = new StudentDAO();
        CourseDAO cdao = new CourseDAO();
        ApplicationDAO adao = new ApplicationDAO();
        SupportTicketDAO tdao = new SupportTicketDAO();

        boolean run = true;
        while (run) {
            System.out.println("\n1. Add Student\n2. Add Course\n3. Add Application\n4. Raise Support Ticket\n5. Exit");
            System.out.print("Choice To Enter: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
            case 1:
                while (true) {
                    System.out.println("Enter Student ID, Name, Email, Phone, DOB (yyyy-MM-dd), Address:");
                    int sid = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    String name = sc.nextLine();
                    String email = sc.nextLine();
                    String phone = sc.nextLine();
                    String dobInput = sc.nextLine();
                    String address = sc.nextLine();

                    try {
                        // Use strict resolver to prevent auto-correction of invalid dates like 2003-02-30
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                                                                       .withResolverStyle(ResolverStyle.STRICT);
                        LocalDate dobParsed = LocalDate.parse(dobInput, formatter);
                        Date dob = Date.valueOf(dobParsed); // Convert to SQL Date

                        sdao.insertStudent(new Student(sid, name, email, phone, dob, address));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid Date! Please enter a real calendar date in yyyy-MM-dd format.");
                    }

                    System.out.print("Add another student? (y/n): ");
                    if (!sc.nextLine().equalsIgnoreCase("y")) break;
                }
                break;



                case 2:
                    while (true) {
                        System.out.println("Enter Course ID, Name, Duration in Months:");
                        int cid = sc.nextInt();
                        sc.nextLine();
                        String cname = sc.nextLine();
                        int dur = sc.nextInt();
                        sc.nextLine();
                        cdao.insertCourse(new Course(cid, cname, dur));
                        System.out.print("Add another course? (y/n): ");
                        if (!sc.nextLine().equalsIgnoreCase("y")) break;
                    }
                    break;

                case 3:
                    while (true) {
                        System.out.println("Enter Application ID, Student ID, Course ID:");
                        int aid = sc.nextInt();
                        int asid = sc.nextInt();
                        int acid = sc.nextInt();
                        sc.nextLine();

                        Date currentDate = new Date(System.currentTimeMillis());
                        Application app = new Application(aid, asid, acid, currentDate); // status default "Pending"

                        if (!adao.exists(app.getStudentId(), app.getCourseId())) {
                            adao.insertApplication(app);
                        } else {
                            System.out.println("Application for student ID " + app.getStudentId() +
                                               " and course ID " + app.getCourseId() + " already exists in DB.");
                        }

                        System.out.print("Add another application? (y/n): ");
                        if (!sc.nextLine().equalsIgnoreCase("y")) break;
                    }
                    break;

                case 4:
                    while (true) {
                        System.out.println("Enter Ticket ID, Student ID, and Issue:");
                        int tid = sc.nextInt();
                        int stid = sc.nextInt();
                        sc.nextLine(); 
                        String issue = sc.nextLine();

                        SupportTicket ticket = new SupportTicket(tid, stid, issue);
                        tdao.insertTicket(ticket);  // status will be set to 'Raised' automatically

                        System.out.print("Add another support ticket? (y/n): ");
                        if (!sc.nextLine().equalsIgnoreCase("y")) break;
                    }
                    break;


                case 5:
                    run = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }
}
