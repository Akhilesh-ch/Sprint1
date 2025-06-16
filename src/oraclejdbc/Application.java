package oraclejdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Application {
    private int applicationId;
    private int studentId;
    private int courseId;
    private Date date;
    private String status;

    private static final List<String> VALID_STATUSES = Arrays.asList("Pending", "Accepted", "Rejected");

    // Constructor without date and status – assigns current date and "Pending"
    public Application(int applicationId, int studentId, int courseId) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = Date.valueOf(LocalDate.now());
        this.status = "Pending";
    }

    // Constructor with date, status defaults to Pending
    public Application(int applicationId, int studentId, int courseId, Date date) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = "Pending";
    }

    // Full constructor with date and status
    public Application(int applicationId, int studentId, int courseId, Date date, String status) {
        this.applicationId = applicationId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = VALID_STATUSES.contains(status) ? status : "Pending";
    }

    // Getters
    public int getApplicationId() { return applicationId; }
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public Date getDate() { return date; }
    public String getStatus() { return status; }

    // Setters
    public void setApplicationId(int applicationId) { this.applicationId = applicationId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setDate(Date date) { this.date = date; }

    public void setStatus(String newStatus) {
        if (VALID_STATUSES.contains(newStatus)) {
            this.status = newStatus;
        } else {
            System.out.println("Invalid status. Allowed: Pending, Accepted, Rejected.");
        }
    }

    // equals() and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    // toString
    @Override
    public String toString() {
        return applicationId + "\t" + studentId + "\t" + courseId + "\t" + date + "\t" + status;
    }
}
