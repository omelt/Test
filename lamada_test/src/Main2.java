import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    public static void main(String[] args) {
        //使用lambada 配合stream api使用
        //将逻辑代码变得简洁
        List<String> names= Arrays.asList(new String[]{"a","b","c"});
        List<String> bigNames=new ArrayList<>();
        for (String name:names
             ) {
            bigNames.add(name.toUpperCase());
        }

        System.out.println(bigNames);

                                    //像python的lambda一样  集合类 steam（）提供了一系列方法
                                    //  可省略写成 (name->name.toUpperCase())
                                    //或者使用方法引用      String::toUpperCase
        List<String> bigNames1=names.stream().map(name->{return name.toUpperCase();}).collect(Collectors.toList());

        System.out.println(bigNames1);
    }
}
