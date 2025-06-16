package oraclejdbc;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Retrieve {
    public static void main(String[] args) throws SQLException {
        try (Scanner sc = new Scanner(System.in)) {
            StudentDAO sdao = new StudentDAO();
            CourseDAO cdao = new CourseDAO();
            ApplicationDAO adao = new ApplicationDAO();
            SupportTicketDAO tdao = new SupportTicketDAO();
            boolean run = true;

            while (run) {
                System.out.println("1. Student\n2. Course\n3. Application\n4. Support Ticket\nSelect table to retrieve data:");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("1. ID\n2. Name\n3. Email\n4. Phone\n5. DOB\n6. View All\nSearch Student by:");
                        int sopt = sc.nextInt();
                        sc.nextLine();
                        switch (sopt) {
                            case 1:
                                System.out.print("Enter ID: ");
                                Student s1 = sdao.getStudentById(sc.nextInt());
                                if (s1 != null) System.out.println(s1);
                                else System.out.println("No record found.");
                                break;
                            case 2:
                                System.out.print("Enter Name: ");
                                List<Student> studentsByName = sdao.getStudentsByName(sc.nextLine());
                                if (studentsByName.isEmpty()) System.out.println("No records found.");
                                else studentsByName.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.print("Enter Email: ");
                                Student s2 = sdao.getStudentByEmail(sc.nextLine());
                                if (s2 != null) System.out.println(s2);
                                else System.out.println("No record found.");
                                break;
                            case 4:
                                System.out.print("Enter Phone: ");
                                Student s3 = sdao.getStudentByPhone(sc.nextLine());
                                if (s3 != null) System.out.println(s3);
                                else System.out.println("No record found.");
                                break;
                            case 5:
                                System.out.print("Enter DOB (yyyy-mm-dd): ");
                                List<Student> studentsByDOB = sdao.getStudentsByDOB(sc.nextLine());
                                if (studentsByDOB.isEmpty()) System.out.println("No records found.");
                                else studentsByDOB.forEach(System.out::println);
                                break;
                            case 6:
                                List<Student> allStudents = sdao.getAllStudents();
                                if (allStudents.isEmpty()) System.out.println("No records found.");
                                else allStudents.forEach(System.out::println);
                                break;
                        }
                        break;

                    case 2:
                        System.out.println("Search Course by:\n1. ID\n2. Name\n3. Duration\n4. View All");
                        int copt = sc.nextInt();
                        sc.nextLine();
                        switch (copt) {
                            case 1:
                                System.out.print("Enter ID: ");
                                Course c1 = cdao.getCourseById(sc.nextInt());
                                if (c1 != null) System.out.println(c1);
                                else System.out.println("No record found.");
                                break;
                            case 2:
                                System.out.print("Enter Name: ");
                                List<Course> coursesByName = cdao.getCoursesByName(sc.nextLine());
                                if (coursesByName.isEmpty()) System.out.println("No records found.");
                                else coursesByName.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.print("Enter Duration (weeks): ");
                                List<Course> coursesByDuration = cdao.getCoursesByDuration(sc.nextInt());
                                if (coursesByDuration.isEmpty()) System.out.println("No records found.");
                                else coursesByDuration.forEach(System.out::println);
                                break;
                            case 4:
                                List<Course> allCourses = cdao.getAllCourses();
                                if (allCourses.isEmpty()) System.out.println("No records found.");
                                else allCourses.forEach(System.out::println);
                                break;
                        }
                        break;

                    case 3:
                        System.out.println("1. ID\n2. Student ID\n3. Course ID\n4. Status\n5. Date\n6. View All\nSearch Application by:");
                        int aopt = sc.nextInt();
                        sc.nextLine();
                        switch (aopt) {
                            case 1:
                                System.out.print("Enter ID: ");
                                Application a1 = adao.getApplicationById(sc.nextInt());
                                if (a1 != null) System.out.println(a1);
                                else System.out.println("No record found.");
                                break;
                            case 2:
                                System.out.print("Enter Student ID: ");
                                List<Application> appsBySid = adao.getApplicationsByStudentId(sc.nextInt());
                                if (appsBySid.isEmpty()) System.out.println("No records found.");
                                else appsBySid.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.print("Enter Course ID: ");
                                List<Application> appsByCid = adao.getApplicationsByCourseId(sc.nextInt());
                                if (appsByCid.isEmpty()) System.out.println("No records found.");
                                else appsByCid.forEach(System.out::println);
                                break;
                            case 4:
                                System.out.print("Enter Status: ");
                                List<Application> appsByStatus = adao.getApplicationsByStatus(sc.nextLine());
                                if (appsByStatus.isEmpty()) System.out.println("No records found.");
                                else appsByStatus.forEach(System.out::println);
                                break;
                            case 5:
                                System.out.print("Enter Date (dd-MM-yy): ");
                                String input = sc.nextLine();
                                try {
                                    DateTimeFormatter oracleFormat = DateTimeFormatter.ofPattern("dd-MM-yy");
                                    LocalDate parsedDate = LocalDate.parse(input, oracleFormat);
                                    Date sqlDate = Date.valueOf(parsedDate);
                                    List<Application> appsByDate = adao.getApplicationsByDate(sqlDate);
                                    if (appsByDate.isEmpty()) System.out.println("No records found.");
                                    else appsByDate.forEach(System.out::println);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Invalid date format. Use dd-MM-yy like 15-06-25.");
                                } catch (Exception e) {
                                    System.out.println("An unexpected error occurred: " + e.getMessage());
                                }
                                break;
                            case 6:
                                List<Application> allApps = adao.getAllApplications();
                                if (allApps.isEmpty()) System.out.println("No records found.");
                                else allApps.forEach(System.out::println);
                                break;
                        }
                        break;

                    case 4:
                        System.out.println("Search Ticket by:\n1. ID\n2. Student ID\n3. Status\n4. View All");
                        int topt = sc.nextInt();
                        sc.nextLine();
                        switch (topt) {
                            case 1:
                                System.out.print("Enter ID: ");
                                SupportTicket t1 = tdao.getTicketById(sc.nextInt());
                                if (t1 != null) System.out.println(t1);
                                else System.out.println("No record found.");
                                break;
                            case 2:
                                System.out.print("Enter Student ID: ");
                                List<SupportTicket> ticketsBySid = tdao.getTicketsByStudentId(sc.nextInt());
                                if (ticketsBySid.isEmpty()) System.out.println("No records found.");
                                else ticketsBySid.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.print("Enter Status: ");
                                List<SupportTicket> ticketsByStatus = tdao.getTicketsByStatus(sc.nextLine());
                                if (ticketsByStatus.isEmpty()) System.out.println("No records found.");
                                else ticketsByStatus.forEach(System.out::println);
                                break;
                            case 4:
                                List<SupportTicket> allTickets = tdao.getAllTickets();
                                if (allTickets.isEmpty()) System.out.println("No records found.");
                                else allTickets.forEach(System.out::println);
                                break;
                        }
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
