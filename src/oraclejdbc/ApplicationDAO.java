package oraclejdbc;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.Date;

public class ApplicationDAO {

    public void insertApplication(Application a) throws SQLException {
        if (exists(a.getStudentId(), a.getCourseId())) {
            System.out.println("Duplicate application: Student already applied to this course.");
            return;
        }

        String sql = "INSERT INTO Application (application_id, student_id, course_id, application_date, status) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, a.getApplicationId());
            ps.setInt(2, a.getStudentId());
            ps.setInt(3, a.getCourseId());
            ps.setDate(4, a.getDate());
            ps.setString(5, a.getStatus());
            ps.executeUpdate();
            System.out.println("Application added successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Insert failed due to constraint: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Failed to insert application: " + e.getMessage());
        }
    }
    public void exportApplicationsToFile(String filePath) {
        try {
            List<Application> apps = getAllApplications();
            if (apps.isEmpty()) {
                System.out.println("No application records found in the database.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Application app : apps) {
                    String line = app.getApplicationId() + "," +
                                  app.getStudentId() + "," +
                                  app.getCourseId() + "," +
                                  app.getDate() + "," +
                                  app.getStatus();
                    writer.write(line);
                    writer.newLine();
                    System.out.println(line); // ✅ Also print each line
                }
                System.out.println("Exported applications to file: " + filePath);
            }
        } catch (Exception e) {
            System.out.println("Error exporting applications: " + e.getMessage());
        }
    }




    public boolean exists(int studentId, int courseId) {
        String sql = "SELECT 1 FROM Application WHERE student_id = ? AND course_id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Existence check failed: " + e.getMessage());
            return false;
        }
    }

    public void updateStatus(int applicationId, String newStatus) {
        String sql = "UPDATE Application SET status = ? WHERE application_id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, newStatus);
            ps.setInt(2, applicationId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Application status updated." : "Application not found.");
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public List<Application> getAllApplications() throws SQLException {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM Application ORDER BY application_id";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(mapResultSetToApplication(rs));
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching Application Table Record: " + e.getMessage());
        }
        return list;
    }

    public Application getApplicationById(int id) {
        String sql = "SELECT * FROM Application WHERE application_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToApplication(rs);
            }
        } catch (SQLException e) {
            System.out.println("Failed Fetching Application Record: " + e.getMessage());
        }
        return null;
    }


    public List<Application> getApplicationsByStudentId(int sid) throws SQLException {
        return getApplicationsBy("student_id", sid);
    }

    public List<Application> getApplicationsByCourseId(int cid) throws SQLException {
        return getApplicationsBy("course_id", cid);
    }

    public List<Application> getApplicationsByStatus(String status) throws SQLException {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM Application WHERE LOWER(status) = LOWER(?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToApplication(rs));
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching Application Record: " + e.getMessage());
        }
        return list;
    }

       public List<Application> getApplicationsByDate(Date date) throws SQLException {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM Application WHERE TRUNC(application_date) = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setDate(1, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Application(
                        rs.getInt("application_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("application_date"),
                        rs.getString("status")
                    ));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching Application Record: " + e.getMessage());
        }
        return list;
    }
       private List<Application> getApplicationsBy(String column, Object value) throws SQLException {
           List<Application> list = new ArrayList<>();
           String sql = "SELECT * FROM Application WHERE " + column + " = ?";
           try (
               Connection con = DBConnection.getConnection();
               PreparedStatement ps = con.prepareStatement(sql)
           ) {
               if (value instanceof Integer) {
                   ps.setInt(1, (int) value);
               } else if (value instanceof String) {
                   ps.setString(1, (String) value);
               }
               ResultSet rs = ps.executeQuery();
               while (rs.next()) {
                   list.add(mapResultSetToApplication(rs));
               }
           }
           catch (SQLException e) {
               System.out.println("Failed Fetching Application Record: " + e.getMessage());
           }
           return list;
       }

    public void deleteApplicationByStudentId(int sid) throws SQLException {
        deleteBy("student_id", sid);
    }

    public void deleteApplicationByCourseId(int cid) throws SQLException {
        deleteBy("course_id", cid);
    }

    public void deleteApplicationByStatus(String status) throws SQLException {
        String sql = "DELETE FROM Application WHERE LOWER(status) = LOWER(?)";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, status);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Application(s) deleted." : "No applications found.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting Application Record: " + e.getMessage());
        }
    }


    public void deleteApplicationByDate(Date date) throws SQLException {
    	String sql = "DELETE FROM Application WHERE TRUNC(application_date) = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setDate(1, date);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Application(s) deleted." : "No applications found.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting Application Record: " + e.getMessage());
        }
    }

  

    private void deleteBy(String column, Object value) throws SQLException {
        String sql = "DELETE FROM Application WHERE " + column + " = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            if (value instanceof Integer) {
                ps.setInt(1, (int) value);
            } else if (value instanceof String) {
                ps.setString(1, (String) value);
            }
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Application(s) deleted." : "No applications found.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting Application Record: " + e.getMessage());
        }
    }

    private Application mapResultSetToApplication(ResultSet rs) throws SQLException {
        return new Application(
            rs.getInt("application_id"),
            rs.getInt("student_id"),
            rs.getInt("course_id"),
            rs.getDate("application_date"),
            rs.getString("status")
        );
    }
    
    public void deleteApplication(int id) throws SQLException {
        String sql = "DELETE FROM Application WHERE application_id = ?";
        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Application deleted." : "No application found.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting Application Record: " + e.getMessage());
        }
    }
    
    public void deleteAllApplications() throws SQLException {
        String sql = "DELETE FROM Application";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int count = ps.executeUpdate();
            System.out.println(count + " application(s) deleted.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting Application Table Record: " + e.getMessage());
        }
    }

}
