import java.util.Locale;

public class Student {

    private String name;

    public Student(String name){
        this.name = name;
    }

    // 建议使用Override，帮助确定是否有函数签名
    @Override
    // equals()是Object父类的函数，覆盖方法要与父类的签名一致，因此传入对象是Object类
    public boolean equals(Object student){ // 把Object改成Student后左边没有覆盖的标识，相当于是自己定义了一个新方法
        // 把传来的student强制转为Student类，有可能会抛异常，使用固定套路判断
        if(this == student)
            return true;
        if(student == null)
            return false;
        if(this.getClass() != student.getClass())
            return false;

        Student another = (Student)student;
        return this.name.equalsIgnoreCase(another.name);
    }
}
