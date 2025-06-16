package oraclejdbc;

//import java.sql.Date;
import java.util.Scanner;

public class Deletion {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            StudentDAO sdao = new StudentDAO();
            CourseDAO cdao = new CourseDAO();
            ApplicationDAO adao = new ApplicationDAO();
            SupportTicketDAO tdao = new SupportTicketDAO();

            while (true) {
                System.out.println("1. Student\n2. Course\n3. Application\n4. Support Ticket\n5. Exit\nSelect option to delete:");
                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        System.out.println("Delete Student by:\n1. ID\n2. Name\n3. Email\n4. Phone\n5. DOB\n6. Delete All Students");
                        int sd = sc.nextInt();
                        sc.nextLine();
                        switch (sd) {
                            case 1:
                                System.out.print("Enter ID: ");
                                sdao.deleteStudent(sc.nextInt());
                                break;
                            case 2:
                                System.out.print("Enter Name: ");
                                sdao.deleteStudentByName(sc.nextLine());
                                break;
                            case 3:
                                System.out.print("Enter Email: ");
                                sdao.deleteStudentByEmail(sc.nextLine());
                                break;
                            case 4:
                                System.out.print("Enter Phone: ");
                                sdao.deleteStudentByPhone(sc.nextLine());
                                break;
                            case 5:
                                System.out.print("Enter DOB (yyyy-mm-dd): ");
                                sdao.deleteStudentByDOB(sc.nextLine());
                                break;
                            case 6:
                                System.out.println("Deleting all students...");
                                sdao.deleteAllStudents();
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 2:
                        System.out.println("Delete Course by:\n1. ID\n2. Name\n3. Duration\n4. Delete All Courses");
                        int cd = sc.nextInt();
                        sc.nextLine();
                        switch (cd) {
                            case 1:
                                System.out.print("Enter ID: ");
                                cdao.deleteCourse(sc.nextInt());
                                break;
                            case 2:
                                System.out.print("Enter Name: ");
                                cdao.deleteCourseByName(sc.nextLine());
                                break;
                            case 3:
                                System.out.print("Enter Duration: ");
                                cdao.deleteCourseByDuration(sc.nextInt());
                                break;
                            case 4:
                                System.out.println("Deleting all courses...");
                                cdao.deleteAllCourses();
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 3:
                        System.out.println("Delete Application by:\n1. ID\n2. Student ID\n3. Course ID\n4. Status\n5. Date\n6. Delete All Applications");
                        int ad = sc.nextInt();
                        sc.nextLine();
                        switch (ad) {
                            case 1:
                                System.out.print("Enter ID: ");
                                adao.deleteApplication(sc.nextInt());
                                break;
                            case 2:
                                System.out.print("Enter Student ID: ");
                                adao.deleteApplicationByStudentId(sc.nextInt());
                                break;
                            case 3:
                                System.out.print("Enter Course ID: ");
                                adao.deleteApplicationByCourseId(sc.nextInt());
                                break;
                            case 4:
                                System.out.print("Enter Status: ");
                                adao.deleteApplicationByStatus(sc.nextLine());
                                break;
                            case 5:
                                System.out.print("Enter Date (dd-MM-yy): ");
                                String input = sc.nextLine();
                                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yy");
                                java.util.Date utilDate = sdf.parse(input);
                                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                                adao.deleteApplicationByDate(sqlDate);
                                break;
                            case 6:
                                System.out.println("Deleting all applications...");
                                adao.deleteAllApplications();
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 4:
                        System.out.println("Delete Ticket by:\n1. ID\n2. Student ID\n3. Status\n4. Delete All Tickets");
                        int td = sc.nextInt();
                        sc.nextLine();
                        switch (td) {
                            case 1:
                                System.out.print("Enter ID: ");
                                tdao.deleteTicket(sc.nextInt());
                                break;
                            case 2:
                                System.out.print("Enter Student ID: ");
                                tdao.deleteTicketByStudentId(sc.nextInt());
                                break;
                            case 3:
                                System.out.print("Enter Status: ");
                                tdao.deleteTicketByStatus(sc.nextLine());
                                break;
                            case 4:
                                System.out.println("Deleting all support tickets...");
                                tdao.deleteAllTickets();
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }
}
