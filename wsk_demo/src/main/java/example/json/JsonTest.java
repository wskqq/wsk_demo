package example.json;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2022/6/17 17:53
 * @Version 1.0
 */
public class JsonTest {
    public static void main(String[] args) {
        List<Map> lst = new ArrayList<>(4);
        Map map = new HashMap(4);
        map.put("test","testvalue");
        lst.add(map);
        String jsonString = JSON.toJSONString(lst);
        List<Map> list = JSON.parseArray(jsonString, Map.class);
        System.out.println(list);
        System.out.println(list.get(0).get("test"));
    }
}
