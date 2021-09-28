# 김영한 스프링강의

### 스프링의 핵심

좋은 객체 지향적으로 개발할수 있도록 도와주는 프레임워크

### 다형성

역할과 구현을 분리

유연하고 변경이 용이함.

확장 가능한 설계

클라이언트에 영향을 주지 않는 변경 가능

### 다형성의 본질

클라이언트를 변경하지 않고 서버의 구현기능을 유연하게 변경 할 수 있다.

### SOLID

클린코드로 로버트 마틴이좋은 객체 지향 설계 5가지 원칙을 정리

SRP(Single Responsibility Principle) : 단일 책임 원칙

한 클래스는 하나의 책임만 가져야 한다.

중요한 기준은 변경이며 변경이 있을때 파급 효과가 적으면 단일 책임 원칙을 잘 따른것

OCP(Open/Closed Principle) : 개방-폐쇄 원칙

확장에는 열려 있으나 변경에는 닫혀 있어야 한다.

LSP(Liskov Substitution Principle) : 리스코프 치환 원칙

객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀수 있어야 한다.

ISP(Interface Segregation Principle) : 인터페이스 분리 원칙

특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.

DIP(Dependency Inversion Principle) : 의존관계 역전 원칙

추상화에 의존해야지 구체화에 의존하면 안된다.( =역할에 의존한다.)

제어의 역전 IoC(Inversion of Control) 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는것.

의존관계 주입 DI(Dependency Injection) 정적인 클래스 의존관계와 

프레임워크 VS 라이브러리

프레임워크 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크이다.(Junit, Spring)

라이브러리 내가 작성한 코드를 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니다.(ex. gson, poi)

스프링 컨테이너 생성

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

'ApplicationContext ' 를 스프링 컨테이너라고 한다.

'ApplicationContext ' 는 인터페이스 이다.

'new AnnotationConfigApplicationContext' 는 'ApplicationContext' 인터페이스의 구현체이다.