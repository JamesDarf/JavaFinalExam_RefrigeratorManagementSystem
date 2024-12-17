package main;

import controller.Refrigerator;
import view.RefrigeratorGUI;

// program start!!
public class Main {
	public static void main(String[] args) {
		Refrigerator refrigerator = new Refrigerator();
		new RefrigeratorGUI(refrigerator);
		Runtime.getRuntime().addShutdownHook(new Thread(refrigerator::saveToFile)); // 종료 시 저장
	}
}
