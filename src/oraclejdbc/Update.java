package oraclejdbc;

import java.util.*;
import java.sql.Date;

public class Update {
    public static void main(String[] args) throws Exception {
       try( Scanner sc = new Scanner(System.in)){
        StudentDAO sdao = new StudentDAO();
        CourseDAO cdao = new CourseDAO();
        ApplicationDAO adao = new ApplicationDAO();
        SupportTicketDAO tdao = new SupportTicketDAO();

        System.out.println("\n1. Student\n2. Course\n3. Application\n4. SupportTicket\nChoose table to update:");
        int tbl = sc.nextInt();
        sc.nextLine();

        switch (tbl) {
            case 1:
                System.out.println("Update Student:\n1. Name\n2. Email\n3. Phone\n4. DOB\n5. Address");
                int sf = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter student ID: ");
                int sid = sc.nextInt();
                sc.nextLine();
                switch (sf) {
                    case 1: System.out.print("New name: "); sdao.updateName(sid, sc.nextLine()); break;
                    case 2: System.out.print("New email: "); sdao.updateEmail(sid, sc.nextLine()); break;
                    case 3: System.out.print("New phone: "); sdao.updatePhone(sid, sc.nextLine()); break;
                    case 4: System.out.print("New DOB (yyyy-mm-dd): "); sdao.updateDOB(sid, Date.valueOf(sc.nextLine())); break;
                    case 5: System.out.print("New address: "); sdao.updateAddress(sid, sc.nextLine()); break;
                }
                break;

            case 2:
                System.out.println("Update Course:\n1. Name\n2. Duration");
                int cf = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter course ID: ");
                int cid = sc.nextInt();
                sc.nextLine();
                if (cf == 1) {
                    System.out.print("New name: ");
                    cdao.updateName(cid, sc.nextLine());
                } else {
                    System.out.print("New duration (in weeks): ");
                    cdao.updateDuration(cid, sc.nextInt());
                }
                break;

            case 3:
                System.out.println("Update Application Status");
                System.out.print("Enter Application ID: ");
                int appId = sc.nextInt();

                System.out.println("Select Status:\n1. Pending\n2. Accepted\n3. Rejected");
                int appChoice = sc.nextInt();
                String statusApp = "";

                switch (appChoice) {
                    case 1:
                        statusApp = "Pending";
                        break;
                    case 2:
                        statusApp = "Accepted";
                        break;
                    case 3:
                        statusApp = "Rejected";
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }

                adao.updateStatus(appId, statusApp);
                break;


            case 4:
                System.out.print("Enter Ticket ID: ");
                int tid = sc.nextInt();

                System.out.println("Select Status:\n1. Raised\n2. In Progress\n3. Resolved\n4. Closed");
                int tChoice = sc.nextInt();
                String tstatus = "";

                switch (tChoice) {
                    case 1:
                        tstatus = "Raised";
                        break;
                    case 2:
                        tstatus = "In Progress";
                        break;
                    case 3:
                        tstatus = "Resolved";
                        break;
                    case 4:
                        tstatus = "Closed";
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                tdao.updateTicketStatus(tid, tstatus);
                break;


            default:
                System.out.println("Invalid table choice.");
        }
       }
    }
}
