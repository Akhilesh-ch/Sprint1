package oraclejdbc;

import java.sql.*;
import java.util.*;

public class CourseDAO {

    public void insertCourse(Course c) throws SQLException {
        String sql = "INSERT INTO Course (course_id, course_name, duration_months) VALUES (?, ?, ?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
            ps.setInt(3, c.getDuration());
            ps.executeUpdate();
            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add course: " + e.getMessage());
        }
    }

    public void updateName(int id, String name) throws SQLException {
        updateField("course_name", name, id);
    }

    public void updateDuration(int id, int duration) throws SQLException {
        String sql = "UPDATE Course SET duration_months=? WHERE course_id=?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, duration);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Duration updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update: " + e.getMessage());
        }
    }

    private void updateField(String field, String value, int id) throws SQLException {
        String sql = "UPDATE Course SET " + field + "=? WHERE course_id=?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, value);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println(field + " updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update " + field + ": " + e.getMessage());
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course ORDER BY course_id";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new Course(rs.getInt("course_id"), rs.getString("course_name"), rs.getInt("duration_months")));
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Course Table Record: " + e.getMessage());
        }
        return list;
    }

    public Course getCourseById(int id) {
        String sql = "SELECT * FROM Course WHERE course_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)) : null;
        } catch (SQLException e) {
            System.out.println("Error Fetching Course Record: " + e.getMessage());
        }
        return null; // Return null if exception occurs
    }


    public List<Course> getCoursesByName(String name) throws SQLException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE course_name = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Course Record: " + e.getMessage());
        }
        return list;
    }

    public List<Course> getCoursesByDuration(int duration) throws SQLException {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course WHERE duration_months = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, duration);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Course Record: " + e.getMessage());
        }
        return list;
    }

    public void deleteCourse(int id) throws SQLException {
        String sql = "DELETE FROM Course WHERE course_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Course deleted." : "No course found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Course Record: " + e.getMessage());
        }
    }

    public void deleteCourseByName(String name) throws SQLException {
        String sql = "DELETE FROM Course WHERE course_name = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Course(s) deleted." : "No course found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Course Record: " + e.getMessage());
        }
    }

    public void deleteCourseByDuration(int duration) throws SQLException {
        String sql = "DELETE FROM Course WHERE duration_months = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, duration);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Course(s) deleted." : "No course found.");
        }
        catch (SQLException e) {
            System.out.println("Error deleting course Record: " + e.getMessage());
        }
    }
    public void deleteAllCourses() throws SQLException {
        String sql = "DELETE FROM Course";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int count = ps.executeUpdate();
            System.out.println(count + " course(s) deleted.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Course Table Record: " + e.getMessage());
        }
    }
}
