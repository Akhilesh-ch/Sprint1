package oraclejdbc;

import java.sql.*;
import java.util.*;

public class SupportTicketDAO {

    public void insertTicket(SupportTicket ticket) throws SQLException {
        String sql = "INSERT INTO SupportTicket (ticket_id, student_id, issue, status) VALUES (?, ?, ?, ?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, ticket.getTicketId());
            ps.setInt(2, ticket.getStudentId());
            ps.setString(3, ticket.getIssueDescription());
            ps.setString(4, ticket.getStatus());
            ps.executeUpdate();
            System.out.println("Support ticket added with status: " + ticket.getStatus());
        } catch (SQLException e) {
            System.out.println("Failed to insert ticket: " + e.getMessage());
        }
    }

    public void updateTicketStatus(int ticketId, String newStatus) throws SQLException {
        String sql = "UPDATE SupportTicket SET status = ? WHERE ticket_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, newStatus);
            ps.setInt(2, ticketId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Ticket status updated." : "Ticket not found.");
        } catch (SQLException e) {
            System.out.println("Status update failed: " + e.getMessage());
        }
    }

    public List<SupportTicket> getAllTickets() throws SQLException {
        List<SupportTicket> list = new ArrayList<>();
        String sql = "SELECT * FROM SupportTicket ORDER BY ticket_id";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new SupportTicket(
                    rs.getInt("ticket_id"),
                    rs.getInt("student_id"),
                    rs.getString("issue"),
                    rs.getString("status")
                ));
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching SupportTicket Table Record: " + e.getMessage());
        }
        return list;
    }

    public SupportTicket getTicketById(int id) throws SQLException {
        String sql = "SELECT * FROM SupportTicket WHERE ticket_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SupportTicket(
                    rs.getInt("ticket_id"),
                    rs.getInt("student_id"),
                    rs.getString("issue"),
                    rs.getString("status")
                );
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching SupportTicket Record: " + e.getMessage());
        }
        return null;
    }

    public List<SupportTicket> getTicketsByStudentId(int sid) throws SQLException {
        List<SupportTicket> list = new ArrayList<>();
        String sql = "SELECT * FROM SupportTicket WHERE student_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SupportTicket(
                    rs.getInt("ticket_id"),
                    rs.getInt("student_id"),
                    rs.getString("issue"),
                    rs.getString("status")
                ));
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching SupportTicket Record: " + e.getMessage());
        }
        return list;
    }

    public List<SupportTicket> getTicketsByStatus(String status) throws SQLException {
        List<SupportTicket> list = new ArrayList<>();
        String sql = "SELECT * FROM SupportTicket WHERE LOWER(status) = LOWER(?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SupportTicket(
                    rs.getInt("ticket_id"),
                    rs.getInt("student_id"),
                    rs.getString("issue"),
                    rs.getString("status")
                ));
            }
        }
        catch (SQLException e) {
            System.out.println("Failed Fetching SupportTicket Record: " + e.getMessage());
        }
        return list;
    }

    public void deleteTicket(int id) throws SQLException {
        String sql = "DELETE FROM SupportTicket WHERE ticket_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Ticket deleted." : "No ticket found.");
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    public void deleteTicketByStudentId(int sid) throws SQLException {
        String sql = "DELETE FROM SupportTicket WHERE student_id = ?";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, sid);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Ticket(s) deleted." : "No tickets found.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting SupportTicket Record: " + e.getMessage());
        }
    }

    public void deleteTicketByStatus(String status) throws SQLException {
        String sql = "DELETE FROM SupportTicket WHERE LOWER(status) = LOWER(?)";
        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, status);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Ticket(s) deleted." : "No tickets found.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting SupportTicket Record: " + e.getMessage());
        }
    }
    public void deleteAllTickets() throws SQLException {
        String sql = "DELETE FROM SupportTicket";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int count = ps.executeUpdate();
            System.out.println(count + " support ticket(s) deleted.");
        }
        catch (SQLException e) {
            System.out.println("Failed Deleting SupportTicket Table Record: " + e.getMessage());
        }
    }
}
