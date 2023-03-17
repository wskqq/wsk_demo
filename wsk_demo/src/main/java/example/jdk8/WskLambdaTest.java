package example.jdk8;

import java.util.function.Supplier;

/**
 * @Description lambda表达式测试类
 * @Author wsk
 * @Date 2022/11/18 16:08
 * @Version 1.0
 */
public class WskLambdaTest {
    public static void main(String[] args) {
//        operationTest();
//        greetTest();

//        Supplier<WskMethodReference> WskMethodReference.staticCreate();

    }



    /**
     * 测试lambda表达式
     */
    private static void greetTest() {
        // 两种方式效果一样
        WskGreet wskGreet = greetMsg -> System.out.println("hello " + greetMsg);

//        WskGreet wskGreet = new WskGreet() {
//            @Override
//            public void greet(String greetMsg) {
//                System.out.println("hello " + greetMsg);
//            }
//        };

        wskGreet.greet("jack");
    }

    /**
     * 计算相关的lambda表达式：用于替换匿名内部类的写法
     */
    private static void operationTest() {
        // 测试WskOperation的lambda表达式:加减乘除
        WskOperation wskAddition = (a, b) -> {return a + b;};
        int add = wskAddition.operation(1, 2);
        System.out.println(add);
        WskOperation wskSubtraction = (a, b) -> {return a - b;};
        int sub = wskSubtraction.operation(3, 1);
        System.out.println(sub);
    }
}
