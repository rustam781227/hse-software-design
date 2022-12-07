import java.util.LinkedList;
import java.util.List;

public class Group {
    private final List<Student> group = new LinkedList<>();

    public void addStudent(Student student) {
        group.add(student);
    }
}
