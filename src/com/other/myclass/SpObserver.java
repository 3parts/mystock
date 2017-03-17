package com.other.myclass;

public class SpObserver {
	@SuppressWarnings("unchecked")
	private static ThreadLocal<String> local = new ThreadLocal();

	public static void putSp(String sp) {
		// System.out.println("set方法");
		local.set(sp);
	}

	public static String getSp() {
		// System.out.println("get方法");
		return local.get();
	}
}
