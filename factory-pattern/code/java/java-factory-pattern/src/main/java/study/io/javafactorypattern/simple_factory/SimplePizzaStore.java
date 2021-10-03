package study.io.javafactorypattern.simple_factory;

import study.io.javafactorypattern.domain.Pizza;

public class SimplePizzaStore {

	SimplePizzaFactory simplePizzaFactory;

	public SimplePizzaStore(SimplePizzaFactory simplePizzaFactory){
		this.simplePizzaFactory = simplePizzaFactory;
	}

	public Pizza orderPizza(String type){
		Pizza pizza;

		pizza = simplePizzaFactory.createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}
