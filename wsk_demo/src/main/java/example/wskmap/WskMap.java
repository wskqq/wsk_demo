package example.wskmap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class WskMap {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(new String("都是".getBytes("GBK"),"GBK"));
        String protyValue = "只是";
        System.out.println(URLEncoder.encode(protyValue,"GBK"));

        Map map = new HashMap(4);
        map.put("key1","value1");

        CopyOnWriteArrayList cowal = new CopyOnWriteArrayList();
        cowal.add("test");
        cowal.get(0);

        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<String,String>(4);
        chm.put("cKey1","cValue1");
        chm.get("cKey1");
    }
}
