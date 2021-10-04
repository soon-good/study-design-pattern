package io.study.javatemplatepattern.caffe;

public class CaffeApp {
	public static void main(String [] args){
		CaffeineDrink coffee = new Coffee();
		coffee.servingMenu();
		System.out.println("");

		CaffeineDrink tea = new Coffee();
		tea.servingMenu();
		System.out.println("");
	}
}
