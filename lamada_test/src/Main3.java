import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main3 {
    public  void func() {
        String comenVar="lambda : ";
        List<String> names = Arrays.asList(new String[]{"a","b","c"});
        List<String> after = names.stream().map(name->{
            Long startTime=System.currentTimeMillis();
            //comenVar=comenVar+" ";  //不可
            return comenVar+name+"  :  "+startTime;
        }).collect(Collectors.toList());

                        //System.out::println
        after.forEach(name-> System.out.println(name));


        // this
        List<String> bigNames=names.stream().
                map(str->{
                    //这个this就是这个Main3对象
                    System.out.println(this.getClass().getName());  //不可以直接写main
                    return str.toUpperCase();
                }).collect(Collectors.toList());
        bigNames.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Main3 m=new Main3();
        m.func();
    }
}
