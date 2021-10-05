# 템플릿 메서드 패턴 (Template Method Pattern)

언젠가 한번 정리하고 넘어가겠지만, abstract 키워드는 정말 많이 쓰이는 것 같다. Spring Batch 의 코드를 열어서 확인해볼 때도 abstract 키워드를 자주 봤었고, 예전 회사에서는 OO 프레임워크라는 프레임워크를 직접 만들어서 사용하는 코드를 자주 접했던 것 같고, 첫 회사에서도 디자인 패턴을 어느 정도는 접한것 같다.<br>

운영연차를 어느 정도 거쳤거나, 개발을 하면서 제품 도메인이 확실하게 성립된 경우 이렇게 디자인 패턴으로 새로운 요구사항에 대응하기 위한 방식을 자주 스터디하고 적용하는 과정을 거치게 되는 것 같다.<br>

개발 코드가 커지면 커질 수록 디자인 패턴을 고려하는 것 같다. 특히 abstract 키워드는 정말 빠지지 않고 나오는 것 같다. 새롭게 추가해야 하는 특정 요구사항을 하나씩 리스트업해서 공통적인 부분과 다형성을 적용할 곳에 대해 정의해서 클래스화 할 때 자주 사용하는 것 같다.<br>

어제 새벽에 졸면서 잠깐 정리를 마무리했었고 오늘 새벽에 커밋했다. 그런데 다시 생각할 수록 그때 글이 너무 번잡스럽고, 정말 졸면서 쓴것 같은 느낌이 들어서 점심에 잠깐 시간을 내서 수정했다. 아... 내 점심시간 ㅋㅋ<br>

<br>

## 참고자료

- [Head First Design Patterns](http://www.yes24.com/product/goods/1778966) 
- [jusungpark.tistory.com - 템플릿 메서드 패턴](https://jusungpark.tistory.com/24?category=630296)
- [swk3169.tistory.com - 헤드퍼스트 디자인 패턴 : 디자인 원칙 정리](https://swk3169.tistory.com/170)

<br>

## 할리우드 원칙

> 먼저 연락하지 마세요. 저희가 연락드리겠습니다.

참고: [swk3169.tistory.com](https://swk3169.tistory.com/170)<br>

할리우드 원칙을 사용하면 의존성 부패를 방지할 수 있다. 서브 클래스에서는 구체적인 동작을 정의하고 있다. 하지만 서브클래스를 사용해서 동작을 하는 것은 상위클래스에서 결정된다. 서브클래스가 동작을 결정하고 직접 동작하지는 않는다.<br>

이렇게 서브 클래스의 구체적인 동작은 미리 정의되어 있고, 이러한 구체적인 동작을 호출하는 것은 상위 클래스에서 결정하는 것을 헐리우드 원칙이라고 한다.<br>

<br>

## 템플릿 메서드 패턴

템플릿 메서드 패턴은 템플릿이 될만한 아래의 명세들에 대해 하나의 템플릿 처럼 명세를 만들어둔다.

- 공통적인 행위지만 서브클래스 별로 동작이 다를 경우 
  - abstract 메서드로 만들어 오버라이딩 할 수 있게 해둔다.
- 공통적인 행위인데, 고정된 행위일 경우
  - abstract 클래스 내에 일반 메서드로 정의해둔다.



## 예제 시나리오

커피화 홍차를 메뉴로 하는 카페가 있다고 하자. 커피와 홍차를 만들때 하는 동작 중 아래의 동작은 공통적으로 꼭 수행해야 하지만, 세부적인 내용은 다르다.

- 차를 우려내는 동작 (드립)
- 부가적인 재료(설탕, 꿀 등)를 추가하는 작업

이렇게 공통적이면서 각 클래스마다 다른 동작을 해야 하는 경우 다형성을 부여하기 위해 abstract 키워드를 사용해 다형성 코드를 작성한다. <br>

<br>

커피, 홍차 모두 공통적으로 모두 같은 동작이어야만 하는 동작

- 물을 끓이는 동작
- 컵에 음료를 따르는 동작

<br>

모두 같은 동작으로 통일 된 동작은 abstract 메서드에 메서드 바디를 정의한다.<br>

<br>

## 예제

### CoffeinDrink.java

```java
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
```

<br>

### Coffee

```java
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
```

<br>

### EarlGrey

```java
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
```

<br>

### CaffeApp

```java
package io.study.javatemplatepattern.caffe;

public class CaffeApp {
	public static void main(String [] args){
		CaffeineDrink coffee = new Coffee();
		coffee.servingMenu();
		System.out.println("");

		CaffeineDrink tea = new Coffee();
		tea.servingMenu();
		System.out.println("");
	}
}
```



### 출력결과

```plain
물을 끓입니다.
아메리카노를 핸드드립 중 ...
컵에 따릅니다.
설탕 시럽은 넣지 않습니다  ...

물을 끓입니다.
아메리카노를 핸드드립 중 ...
컵에 따릅니다.
설탕 시럽은 넣지 않습니다  ...
```



