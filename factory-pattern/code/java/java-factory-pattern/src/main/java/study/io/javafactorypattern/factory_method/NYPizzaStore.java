package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.hardcoded.NYStyleCheesePizza;
import study.io.javafactorypattern.domain.Pizza;
import study.io.javafactorypattern.domain.hardcoded.NYStylePepperoniPizza;

public class NYPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if("cheese".equals(type)) pizza = new NYStyleCheesePizza();
		if("pepper".equals(type)) pizza = new NYStylePepperoniPizza();

		return pizza;
	}
}
