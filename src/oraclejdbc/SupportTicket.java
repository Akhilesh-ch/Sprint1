package oraclejdbc;

import java.util.Arrays;
import java.util.List;

public class SupportTicket {
    private int ticketId;
    private int studentId;
    private String issueDescription;
    private String status;

    private static final List<String> VALID_STATUSES = Arrays.asList("Raised", "In Progress", "Resolved", "Closed");

    // Constructor for inserting new tickets – status will default to 'Raised' in DB
    public SupportTicket(int ticketId, int studentId, String issueDescription) {
        this.ticketId = ticketId;
        this.studentId = studentId;
        this.issueDescription = issueDescription;
        this.status = "Raised";
    }

    // Constructor for loading ticket from DB
    public SupportTicket(int ticketId, int studentId, String issueDescription, String status) {
        this.ticketId = ticketId;
        this.studentId = studentId;
        this.issueDescription = issueDescription;
        this.status = VALID_STATUSES.contains(status) ? status : "Raised";
    }

    // Getters
    public int getTicketId() {
        return ticketId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public void setStatus(String newStatus) {
        if (VALID_STATUSES.contains(newStatus)) {
            this.status = newStatus;
        } else {
            System.out.println("Invalid status. Allowed: " + VALID_STATUSES);
        }
    }

    // For display
    @Override
    public String toString() {
        return ticketId + "\t" + studentId + "\t" + issueDescription + "\t" + status;
    }
}
