package oraclejdbc;

import java.sql.Date;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private Date dob;
    private String address;

    public Student(int id, String name, String email, String phone, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getDob() { return dob; }
    public String getAddress() { return address; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setDob(Date dob) { this.dob = dob; }
    public void setAddress(String address) { this.address = address; }

    // toString method to print student info
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + email + "\t" + phone + "\t" + dob + "\t" + address;
    }
}
