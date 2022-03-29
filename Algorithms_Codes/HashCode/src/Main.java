import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int a = 66;
        System.out.println(((Integer)a).hashCode());

        int b = -20;
        System.out.println(((Integer)b).hashCode());

        double c = 3.14;
        System.out.println(((Double)c).hashCode());

        String d = "hello";
        System.out.println(d.hashCode());

        Student student = new Student(2, 1, "bobo", "liu");
        System.out.println(student.hashCode());

        Student student2 = new Student(2, 1, "bobo", "liu");
        System.out.println(student2.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> map = new HashMap<>();
        map.put(student, 100);

    }
}
