package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.Pizza;

public class FactoryMethodApp {
	public static void main(String [] args){

		PizzaStore newYorkStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();

		Pizza newYorkStylePizza = newYorkStore.orderPizza("cheese");
		System.out.println(newYorkStylePizza);
		System.out.println();

		Pizza chicagoStylePizza = chicagoStore.orderPizza("cheese");
		System.out.println(chicagoStylePizza);
	}
}
