package com.xjgzinfo.example;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

public class Example6_listener4 implements JavaDelegate{
	private Expression exp1;
	public Expression getExp1() {
		return exp1;
	}
	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("------转移线take监听调用---star--Example6_listener4-");
		Map<String,Object> paramMap= arg0.getVariables();
		Set<String> set = paramMap.keySet();
		Iterator<String> iterator = set.iterator();
		System.out.println("#####读取变量开始#####");
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println(key + "=" + paramMap.get(key));
		}
		System.out.println("#####读取变量结束#####");
		System.out.println("------转移线take监听调用---end----------------");
		
	}

}
