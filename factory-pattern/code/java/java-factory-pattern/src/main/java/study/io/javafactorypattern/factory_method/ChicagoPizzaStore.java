package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.Pizza;
import study.io.javafactorypattern.domain.hardcoded.ChicagoStyleCheesePizza;
import study.io.javafactorypattern.domain.hardcoded.ChicagoStylePepperoniPizza;

public class ChicagoPizzaStore extends PizzaStore{
	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if("cheese".equals(type)) pizza = new ChicagoStyleCheesePizza();
		if("pepperoni".equals(type)) pizza = new ChicagoStylePepperoniPizza();

		return pizza;
	}
}
