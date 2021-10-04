package io.study.javatemplatepattern.caffe;

public class Coffee extends CaffeineDrink{

	@Override
	public void dripping() {
		System.out.println("아메리카노를 핸드드립 중 ...");
	}

	@Override
	public void addCondiments() {
		System.out.println("설탕 시럽은 넣지 않습니다  ...");
	}
}
