package example.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;

import java.util.Date;
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class EasyExcelPojo {
    @ExcelProperty("字符串标题")
    private String stringData;
    @ExcelProperty("整型标题")
    private Integer  integerData;
    @ExcelProperty("日期标题")
    private Date dateData;
    @ExcelProperty("浮点数据标题")
    @NumberFormat("#.##%")
    private Double  dounleData;

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public Integer getIntegerData() {
        return integerData;
    }

    public void setIntegerData(Integer integerData) {
        this.integerData = integerData;
    }

    public Double getDounleData() {
        return dounleData;
    }

    public void setDounleData(Double dounleData) {
        this.dounleData = dounleData;
    }

    public Date getDateData() {
        return dateData;
    }

    public void setDateData(Date dateData) {
        this.dateData = dateData;
    }
}
