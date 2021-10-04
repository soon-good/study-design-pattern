package io.study.javatemplatepattern.caffe;

public abstract class CaffeineDrink {

	public void servingMenu(){
		boilingWater();
		dripping();
		pourIntoCup();
		addCondiments();
	}

	public abstract void dripping();

	public abstract void addCondiments();

	public void boilingWater(){
		System.out.println("물을 끓입니다.");
	}

	public void pourIntoCup(){
		System.out.println("컵에 따릅니다.");
	}
}
