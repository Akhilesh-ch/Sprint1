package oraclejdbc;

public class Course {
    private int id;
    private String name;
    private int duration;

    public Course(int id, String name, int duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getDuration() { return duration; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDuration(int duration) { this.duration = duration; }

    // toString for display
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + duration + " months";
    }
}
