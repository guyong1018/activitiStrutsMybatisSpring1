package com.xjgzinfo.example;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.TaskListener;

public class Example6_listener1 implements TaskListener{
	private Expression exp1;
	public Expression getExp1() {
		return exp1;
	}
	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}
	public void notify(DelegateTask arg0) {
		System.out.println("-----节点create监听调用--star--将v_flow值改为listener1-Example6_listener1-");
		System.out.println("Assignee = " + arg0.getAssignee());
		Map<String,Object> paramMap= arg0.getVariables();
		Set<String> set = paramMap.keySet();
		Iterator<String> iterator = set.iterator();
		System.out.println("#####读取变量开始#####");
		while(iterator.hasNext()){
			String key = iterator.next();
			System.out.println(key + "=" + paramMap.get(key));
		}
		arg0.setVariable("v_flow", "listener1");
		System.out.println("#####读取变量结束#####");
		System.out.println("-----节点create监听调用--end--将v_flow值改为listener1------------------");
	}

}
