package example.easyexcel.pojo;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 利用GsonFormatPlus插件由json生成pojo类
 * @Author wsk
 * @Date 2022/12/29 11:45
 * @Version 1.0
 */
@NoArgsConstructor
@Data
public class Test {

    private String stringData;
    private Integer integerData;
    private String dateData;
    private Double dounleData;
}
