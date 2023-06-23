package example.wsk.test;

import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2023/6/22 22:40
 * @Version 1.0
 */
@Service(value = "WskService")
public class WskServiceImpl extends WskBaseServiceImpl implements WskService {
    @Override
    public void testWskService() {
        System.out.println("testWskService");
    }


}
