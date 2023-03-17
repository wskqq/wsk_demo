package example.jdk8;

/**
 * @Description 测试java8的方法引用
 * @Author wsk
 * @Date 2022/11/18 16:42
 * @Version 1.0
 */
public class WskMethodReference {
    /**
     * 测试静态方法
     * @param wskMethodReference
     */
    public static void staticCreate(WskMethodReference wskMethodReference){
        System.out.println("静态方法测试：" + wskMethodReference.toString());
    }

    /**
     * 测试普通方法
     * @param wskMethodReference
     */
    private void create(WskMethodReference wskMethodReference){
        System.out.println("普通方法测试：" + wskMethodReference.toString());
    }
}
