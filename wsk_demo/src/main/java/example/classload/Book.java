package example.classload;

/**
 * @Description 初始化顺序：类初始化（类变量（静态变量非final）/类静态方法(先声明的先执行)）
 *  -》对象初始化（对象变量/普通代码块方法(先声明的先执行) -》 无参构造器 -》set注入）
 *
 *  对象的初始化顺序
 * 静态变量/静态代码块 -> 普通代码块 -> 构造函数
 *
 * 1. 父类静态变量和静态代码块（先声明的先执行）；
 * 2. 子类静态变量和静态代码块（先声明的先执行）；
 * 3. 父类普通成员变量和普通代码块（先声明的先执行）；
 * 4. 父类的构造函数；
 * 5. 子类普通成员变量和普通代码块（先声明的先执行）；
 * 6. 子类的构造函数。
 *
 * @Author wsk
 * @Date 2022/9/29 15:49
 * @Version 1.0
 */
public class Book {
    public static void main(String[] args)
    {
        staticFunction();
    }

    static Book book = new Book();

    static
    {
        System.out.println("书的静态代码块");
    }

    Book()
    {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    // 普通代码快执行顺序再Book()构造器之前
    {
        System.out.println("书的普通代码块");
    }

    public static void staticFunction(){
        System.out.println("书的静态方法");
    }

    // 1.在普通代码块之前，普通代码块执行顺序在Book()构造器之前
    int price = 110;
    static int amount = 112;
}

