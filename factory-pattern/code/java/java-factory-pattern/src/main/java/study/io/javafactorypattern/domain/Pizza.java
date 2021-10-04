package study.io.javafactorypattern.domain;

import java.util.ArrayList;

public abstract class Pizza {
	protected String productName;
	protected String dough;
	protected String sauce;
	protected ArrayList<String> toppings = new ArrayList<>();

	public void prepare() {
		System.out.println(String.format("Preparing : %s ", productName));
		System.out.println("도우 굽는 중...");
		System.out.println("소스 추가 중 ... ");
		System.out.println("토핑 추가 중  ... ");
		for(String topping : toppings){
			System.out.println("토핑 :: " + topping);
		}
	}

	public void bake(){
		System.out.println("will be 20 minute");
	}

	public void box(){
		System.out.println("boxing the Pizza");
	}

	public void cut() {
		System.out.println("Cutting the pizza");
	}

	public String getProductName() {
		return productName;
	}
}
