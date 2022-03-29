import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) {

        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        String s = new String(chars);
        String t = " ab cd i ";
        System.out.println(s);
        System.out.println(t);
        System.out.println(t.trim());
    }
}
