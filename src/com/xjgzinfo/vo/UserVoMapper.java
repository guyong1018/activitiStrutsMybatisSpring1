package com.xjgzinfo.vo;

import com.xjgzinfo.organizational.UserVo;
import com.xjgzinfo.organizational.UserVoExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVoMapper {
    int countByExample(UserVoExample example);

    int deleteByExample(UserVoExample example);

    int deleteByPrimaryKey(BigDecimal userid);

    int insert(UserVo record);

    int insertSelective(UserVo record);

    List<UserVo> selectByExample(UserVoExample example);

    UserVo selectByPrimaryKey(BigDecimal userid);

    int updateByExampleSelective(@Param("record") UserVo record, @Param("example") UserVoExample example);

    int updateByExample(@Param("record") UserVo record, @Param("example") UserVoExample example);

    int updateByPrimaryKeySelective(UserVo record);

    int updateByPrimaryKey(UserVo record);
    
    List<UserVo> getUsersByuserids(List<String> userIds);
}