public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        // 可以用匿名函数，也可以使用lambda表达式
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a+b);
        System.out.println(segTree);

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));

    }
}
