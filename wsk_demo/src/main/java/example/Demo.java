package example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Demo {
    private static final String regex = "^[a-z]+$";
    // 预编译
    private static final Pattern pattern = Pattern.compile(regex);

    public static void main(String[] args) throws Exception {

        String randomStr = "a";
        if(!pattern.matcher(randomStr).matches()){
            throw new Exception("参数格式错误");
        }

        char[] chars = randomStr.toCharArray();
        Map<String,Integer> map = new HashMap<>(4);
        for (int i = 0; i< chars.length; i++){
            String charStr = String.valueOf(chars[i]);
            if(map.containsKey(charStr)){
                map.put(charStr, map.get(charStr)+1);
            }else{
                map.put(charStr, 1);
            }
        }

        StringBuilder printStr = new StringBuilder();
        for(String str: map.keySet()){
            printStr.append(str).append(map.get(str));
        }
        System.out.println(printStr);
    }
}
