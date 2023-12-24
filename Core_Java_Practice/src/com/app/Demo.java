package com.app;


public class Demo {

	int i = 20;// instance var
	static int j = 15;// static var

	void fun1() {
		System.out.println("inside fun1 of class Demo");
	}
	
	static void fun2() {
		System.out.println("inside fun2 of class Demo");
	}
	
	public static void main(String[] args) {

		int x = 10;// local var
		System.out.println(x);

		//instatiating a class
		Demo d1 = new Demo();
		
		System.out.println(d1);
		
		System.out.println(d1.i);
		System.out.println(j);

		d1.fun1();
	
		fun2();
		
	}

}