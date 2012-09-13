package com.xjgzinfo.vo;

import com.xjgzinfo.vo.TQjd;
import com.xjgzinfo.vo.TQjdExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TQjdMapper {
    int countByExample(TQjdExample example);

    int deleteByExample(TQjdExample example);

    int deleteByPrimaryKey(BigDecimal qjid);

    int insert(TQjd record);

    int insertSelective(TQjd record);

    List<TQjd> selectByExample(TQjdExample example);

    TQjd selectByPrimaryKey(BigDecimal qjid);

    int updateByExampleSelective(@Param("record") TQjd record, @Param("example") TQjdExample example);

    int updateByExample(@Param("record") TQjd record, @Param("example") TQjdExample example);

    int updateByPrimaryKeySelective(TQjd record);

    int updateByPrimaryKey(TQjd record);
}