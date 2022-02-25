package example.easyexcel;

import com.alibaba.excel.EasyExcel;
import example.easyexcel.pojo.EasyExcelPojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试通过EasyExcel生成xlsx文件
 */
public class EasyExcelTest {
    public static void main(String[] args) {
        String dirPath = "D:\\test\\easyexcel1.xlsx";
        String sheetName = "第一页";
        EasyExcel.write(dirPath, EasyExcelPojo.class).sheet(sheetName).doWrite(dataList());
    }
    public static List<EasyExcelPojo> dataList(){
        List<EasyExcelPojo> list = new ArrayList<EasyExcelPojo>();
        for(int i=0; i< 1000000; i++){
            EasyExcelPojo easyExcelPojo = new EasyExcelPojo();
            easyExcelPojo.setDateData(new Date());
            easyExcelPojo.setDounleData(12.12);
            easyExcelPojo.setIntegerData(i);
            easyExcelPojo.setStringData(i + "");
            list.add(easyExcelPojo);
        }
        return list;
    }
}
