public class SubstringMatchHelper {

    private SubstringMatchHelper(){}

    public static void matchTest(String name, String s, String t){

        int pos = -1;

        long startTime = System.nanoTime();
        if(name.equals("bruteforce")){
            pos = SubstringMatch.bruteforce(s, t);
        }
        else if(name.equals("rabinKarp")){
            pos = SubstringMatch.rabinKarp(s, t);
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        // 偷懒的写法，直接用Java实现的字符串查找
//        if(s.indexOf(t) != pos)
//            throw new RuntimeException(name + " failed.");
        System.out.println(String.format("%s : res = %d, time = %f s", name, pos, time));
    }
}
