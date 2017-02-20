package com.asiainfo.demo.aop;

import java.util.LinkedList;

public class TransactionSignManager {
	
	private static ThreadLocal<String> transactionTL = new ThreadLocal<String>();// 保存事务标识
	private static ThreadLocal<LinkedList<String>> transactionTLList = new ThreadLocal<LinkedList<String>>();
	
	public static String getTransaction() {
		return transactionTL.get();
	}
	
	public static void pushTransaction(String value) {
		transactionTL.set(value);
		if (transactionTLList.get() == null) {
			transactionTLList.set(new LinkedList<String>());
		}
		transactionTLList.get().push(value);
	}
	
	public static void popTransaction() {
		if (transactionTLList.get() == null) {
			transactionTLList.set(new LinkedList<String>());
		}
		transactionTLList.get().pop();
		if (transactionTLList.get().size() > 0) {
			transactionTL.set(transactionTLList.get().getFirst());
		} else {
			transactionTL.set("");
		}
	}

}
