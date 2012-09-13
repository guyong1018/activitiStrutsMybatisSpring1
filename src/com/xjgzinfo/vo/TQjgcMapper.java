package com.xjgzinfo.vo;

import com.xjgzinfo.vo.TQjgc;
import com.xjgzinfo.vo.TQjgcExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TQjgcMapper {
    int countByExample(TQjgcExample example);

    int deleteByExample(TQjgcExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(TQjgc record);

    int insertSelective(TQjgc record);

    List<TQjgc> selectByExample(TQjgcExample example);

    TQjgc selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") TQjgc record, @Param("example") TQjgcExample example);

    int updateByExample(@Param("record") TQjgc record, @Param("example") TQjgcExample example);

    int updateByPrimaryKeySelective(TQjgc record);

    int updateByPrimaryKey(TQjgc record);
}