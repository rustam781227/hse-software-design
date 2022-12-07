import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Group group = new Group();
        Student tmpStudent;
        tmpStudent = new Student("Антошкин Алексей Александрович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Балабанов Максим Михайлович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Белов Максим Михайлович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Ганина Ксения Андреевна");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Гачевский Богдан");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Демков Михаил Кириллович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Евсеев Евгений Васильевич");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Карпов Михаил Дмитриевич");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Кузьмин Максим Михайлович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Куприн Никита Александрович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Лазарев Василий Юрьевич");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Маланьин Артём Денисович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Мальченко Андрей Романович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Миронов Павел Андреевич");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Мыскин Николай Андреевич");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Наговицын Александр Олегович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Парахин Николай Викторович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Подвицкий Никита Артёмович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Росинский Дмитрий Александрович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Салпагаров Рустам Мунирович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Сафаров Сардор Хайруллаевич");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Смагин Максим Сергеевич ");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Степанов Алексей Александрович");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Тихонов Тимофей Павлович ");
        group.addStudent(tmpStudent);
        tmpStudent = new Student("Шатравка Даниил Александрович");
        group.addStudent(tmpStudent);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(group);
    }
}