package example.wsk.test;

import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2023/6/22 22:38
 * @Version 1.0
 */
//@Service(value="WskBaseService1")
public class WskBaseServiceImpl implements WskBaseService {
    @Override
    public void test() {
        System.out.println("WskBaseServiceImpl");
    }

    public void testPrivate(){
        System.out.println("testBasePrivate");
    }
}
