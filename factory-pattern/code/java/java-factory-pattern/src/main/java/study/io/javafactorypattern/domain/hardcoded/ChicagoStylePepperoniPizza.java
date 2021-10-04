package study.io.javafactorypattern.domain.hardcoded;

import study.io.javafactorypattern.domain.Pizza;

public class ChicagoStylePepperoniPizza extends Pizza {
	public ChicagoStylePepperoniPizza(){
		this.productName = "시카고 스타일 페퍼로니 피자";
		this.dough = "시카고 스타일 두꺼운 도우";
		this.sauce = "토마토 소스";
		this.toppings.add("치즈");
		this.toppings.add("할라피뇨");
		this.toppings.add("올리브");
	}
}
