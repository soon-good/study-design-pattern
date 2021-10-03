package study.io.javafactorypattern.simple_factory;

import study.io.javafactorypattern.domain.CheesePizza;
import study.io.javafactorypattern.domain.PepperoniPizza;
import study.io.javafactorypattern.domain.Pizza;

public class SimplePizzaFactory {
	public static Pizza createPizza(String type) {
		Pizza pizza = null;
		if("cheese".equals(type)) pizza = new CheesePizza();
		if("pepper".equals(type)) pizza = new PepperoniPizza();
		return new Pizza();
	}
}
