package study.io.javafactorypattern.domain.hardcoded;

import study.io.javafactorypattern.domain.Pizza;

public class ChicagoStyleCheesePizza extends Pizza {
	public ChicagoStyleCheesePizza(){
		this.productName = "시카고 스타일 피자";
		this.dough = "굉장히 두꺼운 도우";
		this.sauce = "토마토 소스";
		this.toppings.add("레지아노 치즈 추가");
	}

	@Override
	public void cut() {
		System.out.println("Cutting the Pizza into square slices");
	}
}
