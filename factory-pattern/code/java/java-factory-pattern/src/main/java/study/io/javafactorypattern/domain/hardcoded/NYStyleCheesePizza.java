package study.io.javafactorypattern.domain.hardcoded;

import study.io.javafactorypattern.domain.Pizza;

public class NYStyleCheesePizza extends Pizza {
	public NYStyleCheesePizza(){
		this.productName = "뉴욕 스타일 치즈 피자";
		this.dough = "뉴욕 스타일 도우, 얇음";
		this.sauce = "토마토 소스";

		this.toppings.add("올리브");
		this.toppings.add("햄");
		this.toppings.add("버섯");
	}
}
