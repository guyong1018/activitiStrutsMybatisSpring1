package com.xjgzinfo.example;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

public class Example6_javaInvoke1 implements JavaDelegate{

	private Expression exp1;

	public void execute(DelegateExecution arg0) throws Exception {
		String str = (String)exp1.getValue(arg0);
		System.out.println("------java类调用---star,将v_flow值修改为java1-Example6_javaInvoke1--");
		System.out.println("exp1(v_flow) = " + str);
		Map<String,Object> paramMap= arg0.getVariables();
		Set<String> set = paramMap.keySet();
		Iterator<String> iterator = set.iterator();
		System.out.println("#####读取变量开始#####");
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println(key + "=" + paramMap.get(key));
		}
		arg0.setVariable("v_flow", "java1");
		System.out.println("#####读取变量结束#####");
		System.out.println("------java类调用---end,将v_flow值修改为java1--------------------");
	}
	
	public Expression getExp1() {
		return exp1;
	}
	
	
	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}

}
