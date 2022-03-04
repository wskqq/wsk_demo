package example.xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * xml报文格式化
 */
/**
 * @Description TODO
 * @Author acer
 * @Date 2022/3/3 10:39
 * @Version 1.0
 */
public class WskXmlTest {

    public static void main(String[] args) {
        String inputData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><PARAM><DBIDS><DBID>35</DBID></DBIDS><SEQUENCE>atgtca</SEQUENCE><MAXNS>10</MAXNS><MINIDENTITIES>90</MINIDENTITIES><MAXEVALUE>10</MAXEVALUE><USERNAME>admin</USERNAME><PASSWORD>111111</PASSWORD><TYPE>P</TYPE><RETURN_TYPE>2</RETURN_TYPE></PARAM>";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(inputData));
            try {
                Document document = documentBuilder.parse(inputSource);
                OutputFormat outputFormat = new OutputFormat(document);
                // 是否使用默认的缩进（4）及行宽（72）
                outputFormat.setIndenting(true);
                // 设置新的行宽覆盖默认值
                outputFormat.setLineWidth(8);
                // 设置新的缩进覆盖默认值
                outputFormat.setIndent(2);
                Writer writer = new StringWriter();
                XMLSerializer xmlSerializer = new XMLSerializer(writer,outputFormat);
                xmlSerializer.serialize(document);
                System.out.println(writer.toString());
            } catch (SAXException e) {
                System.out.println("解析数据报错SAX");
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (ParserConfigurationException e) {
            System.out.println("创建DocumentBuilider对象失败");
        }
    }
}
