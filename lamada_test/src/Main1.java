import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        new Thread(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        })).start();  //原始写法

        //上述写法可以写成
                   //不用这个new......
        new Thread(()-> System.out.println("456")).start();


        System.out.println("\n 排序演示");

        List<Integer> list=Arrays.asList( new Integer[]{3,2,1});

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        //使用lambda
        //可以作为语法糖一样 使用匿名内部类的时候 可以直接些方法实现
        Collections.sort(list,(o1,o2)->o1.compareTo(o2));

    }

}
