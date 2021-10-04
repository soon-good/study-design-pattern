# 팩토리 패턴 (Factory Pattern)

디자인 패턴을 처음 배울 때 접하는 패턴들이 대부분 싱글턴 -> 팩토리패턴의 순서이지 않을까 싶다. 팩토리 패턴은 Head First Design Pattern 에서 제공하는 예제는 굉장히 잘 지어진 예제인 것 같다는 생각이 든다. 다만 내용이 길다 보니 매번 까먹을 수밖에 없는 예제이고, 최소 5번 이상은 까먹었을 때마다 다시 보는 과정을 반복해야 기억에 남는 것 같다는 생각이 든다.<br>

책의 예제에서는 단순 팩토리 패턴 -> 팩토리 메서드 패턴 -> 추상팩토리 패턴 의 순서로 개념을 정리하고 있는데, 내용이 꽤 길다. 팩토리 패턴을 다시 공부하면서 조금은 속도를 내서 공부해야 했기에 [jusungpark.tistory.com - 팩토리패턴](https://jusungpark.tistory.com/14?category=630296) 의 자료를 어느 정도는 참고했다.<br>

<br>

## 참고자료

- [Head First Design Patterns](http://www.yes24.com/product/goods/1778966) 
- [jusungpark.tistory.com - 팩토리패턴](https://jusungpark.tistory.com/14?category=630296)

<br>

## 생각해볼 내용

이 책의 예제 중에서는 이런 내용도 등장한다. 

```java
PizzaStore newYorkStore = new PizzaStore(new NYPizzaFactory());
newYorkStore.orderPizza("cheese");
```

PizzaStore 의 생성자 내에 구체적인 Factory 객체를 생성해서 넘겨주는데, 이렇게 생성된 구체 인스턴스를 다른 클래스의 추상타입 멤버변수에 바인딩하는 것을 스트래티지라고 한다. 이런 경우는 좋지 않은 경우라고 이야기하고 있다.<br>

이 경우 각각의 피자 체인 별로 다른 구현 방식으로 남기에 구현 방식에 어느 정도의 공통화와 다형성을 적용하기 힘들어지게 된다.<br>

<br>

또 위와 같은 스트래티지 패턴으로 인해, 피자 공장마다 제각각인 것을 팩토리 메서드 패턴으로 abstract 클래스 내에 다형성을 적용하는 과정을 다루고 나면, 추상 팩토리 패턴을 다룬다. 

추상팩토리 역시도 스트래티지 같은 방식으로 행위를 결정한다. 하지만 첫번째와 다른 점은 아래와 같다.<br>

추상 팩토리 패턴은 아래와 같이 Pizza 에 IngredientFactory 의 구상 객체를 전달해준다. 맨 처음 다뤘던 스트래티지와 다른 점은 제품 클래스(CheesePizza, PepperoniPizza)에 재료공장의 종류(NYPizzaIngredientFactory 등)를 지정해준다는 점이 다르다.

```java
public class NYPizzaStore extends PizzaStore{
  @Override
  public Pizza createPizza(String type){
    Pizza pizza = null;
    PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
    
    if(type.equals("cheese")){
      pizza = new CheesePizza(ingredientFactory);
      pizza.setName(ingredientFactory.NY_STYLE + " Cheese Pizza");
    }
    else if(type.equals("peper")){
      pizza = new PeperoniPizza(ingredientFactory);
      pizza.setName(ingredientFactory.NY_STYLE + " Pepperoni Pizza");
    }
    // ...
    return pizza;
  }
}
```

<br>

요약해보면

- 단순한 팩토리, 디자인 채턴이 아닌 방식은 이런 방식이었다 : `new PizzaStore(new NYPrizzaFactory())` 
  - 이렇게 피자 가게 안에 PizzaFactory() 를 넣으면, Pizza 객체를 만드는 방식을 공통화하기 어려워지기에 좋은 방식은 아니다.
  - 구체적인, 개별적인 공장을 PizzaStore 생성자의 인자로 전달해준다.
- 추상팩토리 패턴 : `new CheesePizza (new NYPizzaIngredientFactory())`
  - PizzaStore는 팩토리 메서드 패턴으로 구현해둔 상태다. 
  - 이 경우는 NYPizzaStore 클래스의 내용이 확정되었고, CheezePizza, PepperoniPizza 와 같은 DTO 역할을 하는 클래스들의 명세가 확정되어 있는 상태이다.
  - 치즈 피자의 경우는 뉴옥 스타일, 시카고 스타일, 등등 여러가지 스타일이 존재하기에, 제품군인 치즈피자, 페퍼로니 피자 등 메뉴 종류는 그대로 DTO 비슷한 형태로 만들어둔 상태이다.
  - 이때 매뉴를 만들어내는 방식(값을 바인딩하는 방식)은 뉴욕스타일, 시카고 스타일마다 다르게 생성되어야 한다. 이런 이유로 치즈피자,페퍼로니 피자의 생성자에 NYPzzaIngredientFactory, ChicacoPizzaIngredientFactory 를 인자로 지정해 객체에 따라 Dto가 다른 형식으로 생성되게금 지정해주었다.
  - 이렇게 스트래티지 패턴으로 다양하게 적용하기 전까지는



## 1 ) 단순한 팩토리 패턴

> 권장되지 않는 방식이기는 하다. <br>
>
> 하지만, Pizza의 종류가 단순해서 Pizza 하나만 생성해도 되거나, CheesePizza, PepperoniPizza 만 있어도 된다면, 굳이 팩토리 메서드 패턴과 같은 abatract 를 이용한 다형성 코드까지는 필요없을 수도 있다.<br>
>
> 하지만, 팩토리 패턴이라는 이름을 붙이기에는 조금 민망한 코드다. (책에서도 이런 간단한 팩토리 코드는 디자인패턴은 아니라고 언급.)

<br>

**SimplePizzaStore.java**<br>

```java
package study.io.javafactorypattern.simple_factory;

import study.io.javafactorypattern.domain.Pizza;

public class SimplePizzaStore {

	SimplePizzaFactory simplePizzaFactory;	// = (1)

	public SimplePizzaStore(SimplePizzaFactory simplePizzaFactory){
		this.simplePizzaFactory = simplePizzaFactory;
	}

	public Pizza orderPizza(String type){
		Pizza pizza;

		pizza = simplePizzaFactory.createPizza(type); // = (2)

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}
```

<br>

- (1) : `SimplePizzaFactory simplePizzaFactory;`
  - simpePizzaFactory 를 외부에서부터 주입받아서 초기화 한다.
- (2) : `pizza = simplePizzaFactory.createPizza(type);`
  - 외부에서 주입받은 simplePizzaFactory 객체로 Pizza 객체를 생성한다.

<br>

## 2 ) 팩토리 메서드 패턴

팩토리 메서드 페턴의 핵심은 아래와 같다.

- 객체를 생성하는 `createPizza(String type)` 메서드를 abstract 메서드로 선언한다.
- 이 abstract 메서드를 overriding 하는 클래스인 NYPizzaStore, ChicagoPizzaStore 를 생성한다.
- createPizza(String type) 메서드 호출 시에 구체 클래스인 `NYStyleCheesePizza`, `NYStylePepperoniPizza`, `ChicagoStyleCheesePizza`, `ChicagoStylePepperoniPizza` 가 아닌 `Pizza` 라는 하나의 상위 타입으로 리턴한다.

이런 것을 책에서는  `의존성 뒤집기` 라고 설명하고 있다.<br>

<br>

### 의존성 뒤집기

- 추상화된 것에 의존하도록 만들어라. 구상 클래스에 의존하지 않도록 해야 한다.

팩토리 메서드 패턴의 가장 핵심적인 코드는 아래의 PizzaStore 클래스다.

```java
package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.Pizza;

public abstract class PizzaStore {
	public Pizza orderPizza(String type){
		Pizza pizza;

		pizza = createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	public abstract Pizza createPizza(String type);
}
```

<br>

이제 위의 PizzaStore 라는 abstract 클래스르 어떻게 구체화하고, 구성해나갔는지 살펴보면 아래와 같다.<br>

**PizzaStore : NYStyleCheesePizza, ChicagoStyleCheesePizza, NYStyleVeggiePizza**<br>

피자 가게에서 뉴욕치즈피자, 시카고 치즈 피자 를 직접 생성해 리턴하고 있다.<br>

이렇게 피자가게에서 직접 각 종류별 피자(뉴욕 치즈피자, 시카고 치즈 피자 등) 만들 같이 리턴하던 코드는 아래와 같이 수정했다.<br>

<br>

**PizzaStore : Pizza**<br>

피자 가게에서는 단순히 Pizza 라는 객체만을 리턴하도록 했다.<br>

피자 가게라는 것은 단순히 abstract 역할만을 하도록 했고, 나중에 이것을 뉴욕피자가게, 시카고피자가게 에서 PizzaStore 의 공통코드는 그대로 물려받고, 다형적인 코드는 직접 오버라이딩하도록 abstract 메서드로 선언해두었다.<br>

PizzaStore 의 createPizza 는 abstract 메서드 인데, 이것을 각각 Overriding 하는 ChicagoPizzaStore, NYPizzaStore 에서는 각각 `ChicagoStyleCheesePizza` , `NYStyleVeggiePizza` 를 리턴한다.<br>

이렇게 각각의 피자 가게를 추상화한 PizzaStore 에서는 Pizza 라는 객체를 리턴하는데, 이렇게 구체 클래스의 인스턴스를 리턴하지 않고 추상타입을 리턴하는 것을 의존성 뒤집기라고 이야기한다.<br>

<br>

**Pizza : NYStyleCheesePizza, ChicagoStyleCheesePizza, NYStyleVeggiePizza**<br>

피자 가게의 추상타입 PizzaStore 의 추상메서드 createPizza() 메서드는 Pizza 라는 객체를 리턴했었다.<br>

Pizza 는 추상 타입인데 이것을 NYStyleCheesePizza, ChicagoStyleCheesePizza, NYStyleVeggiePizza 와 같은 구체 타입에서 상속하도록 구성했다.<br>

이런 방식을 의존성 뒤집기라고 이야기한다.<br>

<br>

**PizzaStore : ChicagoPizzaStore, NYPizzaStore**<br>

위에서 정리했듯이 PizzaStore 클래스는 추상클래스다. PizzaStore 클래스에는 두개의 메서드가 있다.

- orderPizza
  - orderPizza 의 동작이 거의 정해져있는 요구사항이기에 orderPizza() 메서드의 구현은 고정적으로 직접 구현되어 있다
- createPizza
  - createPizza가 하위 클래스에 따라서 동작이 변하기에 createPizza() 메서드는 PizzaStore 클래스 내에서는 abstract 메서드다.
  - ChicagoPizzaStore, NYPizzaStore 와 같은 구체 클래스에서는 createPizza 에 대한 내용을 implements 하고 있다.

<br>

### 예제코드

이제 소스코드를 살펴보자.

#### PizzaStore.java

PizzaStore의 코드는 아래와 같다.

```java
package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.Pizza;

public abstract class PizzaStore {
	public Pizza orderPizza(String type){
		Pizza pizza;

		pizza = createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	public abstract Pizza createPizza(String type);
}
```

<br>

#### NYPizzaStore

PizzaStore 클래스의 createPizza 메서드의 동작이 하위 클래스마다 다른 동작을 하도록 하기 위해서는 implements 해야 한다. 예를 들면 `NYPizzaStore`가 그렇다.<br>

```java
package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.hardcoded.NYStyleCheesePizza;
import study.io.javafactorypattern.domain.Pizza;
import study.io.javafactorypattern.domain.hardcoded.NYStylePepperoniPizza;

public class NYPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza = null;

		if("cheese".equals(type)) pizza = new NYStyleCheesePizza();
		if("pepper".equals(type)) pizza = new NYStylePepperoniPizza();

		return pizza;
	}
}
```

<br>

#### ChicagoPizzaStore

PizzaStore 클래스의 createPizza 메서드의 동작이 하위 클래스마다 다른 동작을 하도록 하기 위해서는 implements 해야 한다. 예를 들면 `ChicagoPizzaStore`가 그렇다.<br>

```java
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
```

<br>

그리고 제품 클래스인 Pizza를 구체화하는 각각의 클래스들은 아래와 같은 `ChicagoStyleCheesePizza`, `ChicagoStylePepperoniPizza` , `NYStyleCheesePizza` , `NYStylePepperoniPizza`  와 같은 클래스들이다.

<br>

#### ChicagoStyleCheesePizza

```java
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
```

<br>

#### ChicagoStylePepperoniPizza

```java
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
```

<br>

#### NYStyleCheesePizza

```java
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
```

<br>

#### NYStylePepperoniPizza

```java
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
```

<br>

그리고 각각의 NYStyleCheesePizza, NYStylePepperoniPizza, ChicagoStyleCheesePizza, ChicagoStylePepperoniPizza 의 상위 타입인 Pizza 클래스는 아래와 같다.<br>

<br>

#### Pizza

```java
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
```

<br>

#### 테스트 코드

junit 테스트를 할까 했는데, 그냥 main 문으로 처음에 작성해버려서, 굳이 junit 테스트 코드를 작성하지는 안혹 main 문에 작성했다.

```java
package study.io.javafactorypattern.factory_method;

import study.io.javafactorypattern.domain.Pizza;

public class FactoryMethodApp {
	public static void main(String [] args){

		PizzaStore newYorkStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();

		Pizza newYorkStylePizza = newYorkStore.orderPizza("cheese");
		System.out.println(newYorkStylePizza);
		System.out.println();

		Pizza chicagoStylePizza = chicagoStore.orderPizza("cheese");
		System.out.println(chicagoStylePizza);
	}
}
```

<br>

### 3 ) 추상 팩토리 패턴

일단 여기부터는 내일 부터 정리할 예정이다. 너무 오늘 하루 안에 다 정리해버리면, 이번 주 내내 정리할 다른 내용이 없기에... 일단은 여기까지만 커밋할 예정이다. 

