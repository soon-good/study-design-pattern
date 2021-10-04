package io.study.javatemplatepattern.caffe;

public class EarlGrey extends CaffeineDrink {

	@Override
	public void dripping() {
		System.out.println("티를 우려내는 중 ... ");
	}

	@Override
	public void addCondiments() {
		System.out.println("꿀과 시럽을 넣고 휘저어 주는 중  ...");
	}
}
