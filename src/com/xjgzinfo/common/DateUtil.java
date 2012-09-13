package com.xjgzinfo.common;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.sql.TIMESTAMP;
/**
 * 如期公共处理类
 * @author guyong
 *
 */
public class DateUtil {

	/**
	 * 根据sql的Timestamp获取字符串日期时间
	 * @param t Timestamp时间
	 * @param formatStr 格式化字符串，如果是null默认yyyy-MM-dd hh:mm:ss
	 * @return 格式化后的字符串
	 */
	public String getDateBySqlTimestamp(Object obj, String formatStr) {
		try {
			TIMESTAMP t = (TIMESTAMP)obj;
			if (formatStr == null || formatStr.equals("")) {
				formatStr = "yyyy-MM-dd hh:mm:ss";
			}
			Timestamp tt;
			tt = t.timestampValue();
			Date date = new Date(tt.getTime());
			SimpleDateFormat sd = new SimpleDateFormat(formatStr);
			return sd.format(date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
