import java.util.Locale;

// 对Student类进行排序，就必须实现Comparable接口
public class Student implements Comparable<Student> {

    private String name;
    private int score;

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student another) {
        return this.score - another.score;
    }

    @Override
    // 为了实现线性查找，覆盖了equals()方法
    public boolean equals(Object student){ //用ojbect和父类签名一致，是覆盖
        if(this == student)
            return true;
        if(student == null)
            return false;
        if(this.getClass() != student.getClass())
            return false;

        Student another = (Student)student;
        return this.name.equalsIgnoreCase(another.name);
    }

    @Override
    // 打印输出Student类的信息
    public String toString(){
        return String.format("Student(name: %s, score: %d)", name, score);
    }

}
