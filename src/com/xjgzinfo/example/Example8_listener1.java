package com.xjgzinfo.example;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

public class Example8_listener1 implements TaskListener{
	public void notify(DelegateTask arg0) {
		System.out.println("------节点complete监听调用---star-创建v_flow值为2-Example8_listener1-");
		System.out.println("Assignee = " + arg0.getAssignee());
		Map<String,Object> paramMap= arg0.getVariables();
		Set<String> set = paramMap.keySet();
		Iterator<String> iterator = set.iterator();
		System.out.println("#####读取变量开始#####");
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println(key + "=" + paramMap.get(key));
		}
		System.out.println("#####读取变量结束#####");
//		arg0.createVariableLocal("v_flow", "2");
		System.out.println("创建v_flow变量值为2");
		System.out.println("------节点complete监听调用---end-------------------------");
	}

}
