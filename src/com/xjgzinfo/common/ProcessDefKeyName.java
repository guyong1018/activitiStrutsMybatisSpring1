package com.xjgzinfo.common;
/**
 * 示例流程的ProcessDefKey，流程定义ID列表类
 * @author Administrator
 *
 */
public interface ProcessDefKeyName {
	/**
	 * 普通流程-请假流程示例
	 */
	public static final String EXAMPLE1_PTLC_QJ="example1_ptlc_qj";
	/**
	 * 分支-排他分支流程示例
	 */
	public static final String EXAMPLE2_PTFZ="example2_ptfz";
	/**
	 * 分支-并行分支
	 */
	public static final String EXAMPLE3_BXFZ="example3_bxfz";
	/**
	 * 分支-包容分支
	 */
	public static final String EXAMPLE4_BRFZ="example4_brfz";
	/**
	 * 定时启动
	 */
	public static final String example5_ptlc_qj="";
	/**
	 * 调用脚本、java类、监听
	 */
	public static final String EXAMPLE6_SER_JAVA_LIS="example6_ser_java_lis";
	/**
	 * 手工、接收触发、邮件任务
	 */
	public static final String EXAMPLE7_MAIL_REC="example7_mail_rec";
	/**
	 * 子过程、边界事件、外部调用
	 */
	public static final String EXAMPLE8_SUB_BJEVENT="example8_sub_bjevent";
	/**
	 * 会签（多实例）
	 */
	public static final String EXAMPLE9_HUIQIAN="example9_huiqian";
	/**
	 * 自由流回退
	 */
	public static final String EXAMPLE10_ZYLBACK="example10_zylback";
	/**
	 * 参与者
	 */
	public static final String EXAMPLE11_ORG="example11_org";
	
	
}
