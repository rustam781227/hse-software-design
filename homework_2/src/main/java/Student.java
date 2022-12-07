import java.util.LinkedList;
import java.util.List;

public class Student {
    private String fullName;
    private final int[] grades = new int[]{};
    private boolean isPresent = false;

    public Student() {
    }

    public Student(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int[] getGrades() {
        return grades;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
