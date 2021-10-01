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

BeanFactory

- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
- 스프링 컨테이너의 최상위 인터페이스이다.
- getBean() 을 제공한다.

ApplicationContext

- BeanFactory 기능을 모두 상속받아 제공한다.
- 빈을 관리하고 검색하는 기능 외에 부가기능을 제공한다.
  - 메시지소스를 활용한 다국어 기능
  - 환경변수
  - 애플리케이션 이벤트
  - 편리한 리소스 조회


XML 을 이용한  Bean 등록

```java
ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
```

new GenericXmlApplicationContext 이용하여 Bean 을 등록할수 있다.

.Class 파일과 .XML 을 지원할수 있는 이유는 BeanDefinition 이 있기에 가능하다.

역할과 구현을 개념적으로 분리 되어있기 때문에 스프링 컨테이너는 자바코드인지 XML 인지 몰라도 된다.

자바 코드의 경우 AnnotatedBeanDefinitionReader, XML의 경우 XmlBeanDefinitionReader 가  읽어 들어 추상화인 BeanDefinition을 생성하여 스프링컨테이너가 사용하게 된다.

```java
public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void DefinitionTest(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for( String key : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(key);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("bean Name : " + beanDefinitionNames + " beanDefinition :" + beanDefinition);
            }
        }

    }
}
```

### 싱글톤 컨테이너

    클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴

```java
private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }
```

싱글톤 패턴을 사용하는 이유

- 레지스트리 설정 및 DBCP 와 같이 인스턴스 딱 1개만 존재해야 하거나 DBCP 와 같이 공통된 객체를 여러개 생성할 경우 메모리 낭비가 되고 두번째 사용시 객체 로딩 시간이 줄어 성능 향상.

인스턴스(instance) : 컴퓨터 저장공간에서 할당된 실체를 의미한다

싱글톤 패턴의 문제점

- 구현 코드가 많이 들어간다.
- 클라이언트가 구체 클래스에 의존하기에 DIP를 위반한다.
- 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
- 내부 속성을 변경하거나 초기하 하기 어렵고 자식클래스를 만들기 어렵다.

싱글톤 컨테이너

스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개) 으로 관리한다.

스프링 컨테이너는 싱글톤 컨테이너 역할을 한다.

싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.

스프링 컨테이너의 기능 덕분에 위에 싱글톤 패턴에서 발생되는 단점을 해결해준다.

- 복잡한 구현코드가 들어가지 않아도 된다.
- 기존 싱글톤 패턴에서 위배되던 DIP, OCP등 위반하지 않고 자유롭게 사용가능하다.

싱글톤 컨테이너 적용후

스프링 컨테이너의 기능 덕분에 고객의 요청이 올 때 마다 생성하는것이 아니라 이미 만들어진 객체를 공유하여 효율적으로 사용이 가능하다.

싱글톤 방식의 주의점

무상태(stateless)로 설계해야 한다!

싱글톤 패턴이나 싱글톤 컨테이너나 어떤것을 사용하든 여러 클라이언트가 하나의 같은 객체 인스턴스를 사용하기 때문에 공유 객체의 상태를 유지하게 설계하면 절대 안된다.

- 특정 클라이언트에 의존적인 필드가 있으면 안된다.
- 특정 클라이언트가 값을 변경할 수 있는 필가 있으면 안된다.
- 가급적 읽기만 가능해야한다.
- 필드 대신 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야한다.

```java
public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name : " + name + " price: " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
```

결과 값을 return 하고 실제 사용하는곳에서 지역변수로 사용하여 해결한다.

```java
public int order(String name, int price) {
        System.out.println("name : " + name + " price: " + price);
        return price;
    }
```

### @Configuration과 바이트의 마법

```java
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig discountPolicy");
        return new RateDiscountPolicy();
    }
}
```

```java
public class configurationTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println(bean.getClass());
    }
}
```

new AnnotationConfigApplicationContext(xxxx.class);

위와 같이 config 에 대한 명시가 있기에 굳이 @Configuration 을 사용해야하나....

@Configuration 추가하지 않을 경우 memberRepository가 여러번 호출되어 싱글톤 패턴이 깨진게 된다. Configuration 이 있으면 스프링컨테이너로 관리가 되어 추가한 Bean 들에 대해 싱글톤이 유지가 된다.

그럼 어떻게 싱글톤이 유지가 되는가.?

@Configuation 이 스프링 컨테이너에서 동작이 되며 해당 클래스가 실행 되는 시점에 동적으로

CGLIB 바이트코드 조작라이브러리를 이용하여 AppConfig 클래스를 상속받은 임의의 하위클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.

컴포넌트 스캔과 의존성 자동주입

자동으로 스프링 빈을 등록(@ComponentScan), 의존성 자동주입(@Autowired)

기존 수동으로 AppConfig 에서 @Bean을 이용하여 등록하는 방식에서 등록이 필요로하는 곳(구현체)에서 @Component 를 사용하여 자동으로 빈을 등록해준다.

@ComponentScan 은 @Compoent가 붙은 모든 클래스를 스프링 빈으로 등록한다.

```java
@ComponentScan(
	basePackages = "hello.core.member"
)
```

basePackages 를 위와 같이 적용하면 member 패키지 안에 있는 @Component 를 찾아 Bean 으로 등록한다.

@ComponentScan  Default 사용시 설정정보 클래스의 패키지가 시작 위치가 된다.

ComponentScan 기본 대상

- @Component : 컴포넌트 스캔에서 사용
- @Controller : 스프링 MVC 컨트롤러에서 사용
- @Service : 스프링 비즈니스 로직에서 사용
- @Repository : 스프링 데이터 접근 계층에서 사용
- @Configuration : 스프링 설정 정보에서 사용
