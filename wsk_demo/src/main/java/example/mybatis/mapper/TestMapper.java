package example.mybatis.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @Description TODO
 * @Author wsk
 * @Date 2022/12/30 15:39
 * @Version 1.0
 */
//@Mapper
public interface TestMapper{
    abstract Map selectByPrimaryId(@Param("id") String id);
}
