package com.xjgzinfo.vo;

import com.xjgzinfo.vo.TForm;
import com.xjgzinfo.vo.TFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TFormMapper {
    int countByExample(TFormExample example);

    int deleteByExample(TFormExample example);

    int insert(TForm record);

    int insertSelective(TForm record);

    List<TForm> selectByExample(TFormExample example);

    int updateByExampleSelective(@Param("record") TForm record, @Param("example") TFormExample example);

    int updateByExample(@Param("record") TForm record, @Param("example") TFormExample example);
}