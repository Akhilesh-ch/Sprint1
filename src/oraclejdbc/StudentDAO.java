package oraclejdbc;

import java.sql.*;
import java.util.*;
import java.sql.Date;

public class StudentDAO {

    public void insertStudent(Student s) {
        String sql = "INSERT INTO Student (student_id, name, email, phone, dob, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getPhone());
            ps.setDate(5, s.getDob());
            ps.setString(6, s.getAddress());
            ps.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add student: " + e.getMessage());
        }
    }

    // ===================== UPDATE METHODS =====================
    public void updateName(int id, String name) {
        updateField("name", name, id);
    }

    public void updateEmail(int id, String email)  {
        updateField("email", email, id);
    }

    public void updatePhone(int id, String phone) {
        updateField("phone", phone, id);
    }

    public void updateAddress(int id, String address) {
        updateField("address", address, id);
    }

    public void updateDOB(int id, Date dob)  {
        String sql = "UPDATE Student SET dob=? WHERE student_id=?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setDate(1, dob);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("DOB updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update DOB: " + e.getMessage());
        }
    }

    private void updateField(String field, String value, int id)  {
        String sql = "UPDATE Student SET " + field + "=? WHERE student_id=?";
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

    // ===================== GET METHODS =====================
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student ORDER BY student_id";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getDate("dob"),
                    rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving student records: " + e.getMessage());
        }

        return list;
    }


    public Student getStudentById(int id)  {
        String sql = "SELECT * FROM Student WHERE student_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDate(5), rs.getString(6));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Student Record: " + e.getMessage());
        }
        return null;
    }

    public Student getStudentByEmail(String email)  {
        String sql = "SELECT * FROM Student WHERE email = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDate(5), rs.getString(6));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Student Record: " + e.getMessage());
        }
        return null;
    }

    public Student getStudentByPhone(String phone) {
        String sql = "SELECT * FROM Student WHERE phone = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDate(5), rs.getString(6));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Student Record: " + e.getMessage());
        }
        return null;
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student WHERE name = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDate(5), rs.getString(6)));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error fFetching Student Record: " + e.getMessage());
        }
        return list;
    }

    public List<Student> getStudentsByDOB(String dob) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student WHERE dob = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setDate(1, Date.valueOf(dob));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getDate(5), rs.getString(6)));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error Fetching Student Table Record: " + e.getMessage());
        }
        return list;
    }

    // ===================== DELETE METHODS =====================
    public void deleteStudent(int id)  {
        String sql = "DELETE FROM Student WHERE student_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "Student not found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Student Record: " + e.getMessage());
        }
    }

    public void deleteStudentByName(String name)  {
        String sql = "DELETE FROM Student WHERE name = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student(s) deleted." : "No student found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Student Record: " + e.getMessage());
        }
    }

    public void deleteStudentByEmail(String email) {
        String sql = "DELETE FROM Student WHERE email = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, email);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "No student found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Student Record: " + e.getMessage());
        }
    }

    public void deleteStudentByPhone(String phone){
        String sql = "DELETE FROM Student WHERE phone = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, phone);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted." : "No student found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Student  Record" + e.getMessage());
        }
    }

    public void deleteStudentByDOB(String dob) {
        String sql = "DELETE FROM Student WHERE dob = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setDate(1, Date.valueOf(dob));
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Student(s) deleted." : "No student found.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Student Record: " + e.getMessage());
        }
    }
    public void deleteAllStudents()  {
        String sql = "DELETE FROM Student";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int count = ps.executeUpdate();
            System.out.println(count + " student(s) deleted.");
        }
        catch (SQLException e) {
            System.out.println("Error Deleting Student Table Record: " + e.getMessage());
        }
    }
}
