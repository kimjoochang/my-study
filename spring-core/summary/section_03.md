# 스프링 핵심원리_기본편

## 스프링 핵심 원리 이해2 - 객체 지향 원리 적용

1. 새로운 할인 정책 개발 
   (기존 정액 할인 정책 ---> 정률 할인 정책으로 변경)

    - 정률 할인 정책 구현체 생성

    - 테스트 작성 및 완료
    

2. 새로운 할인 정책 적용과 문제점

    - 할인 정책 변경 시, 클라이언트인 OrderServiceImpl 코드를 고쳐야 함

    * 문제점 

        - 역할과 구현을 충실하게 분리 -> Ok

        - 다형성도 활용하고, 인터페이스와 구현 객체를 분리 -> Ok

        - OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수 -> No
            -> 주문서비스 클라이언트(OrderServiceImple)는 인터페이스에 의존하면서, 구체 클래스에도 의존! (DIP 위반) 
               그래서 구체 클래스를 변경하는 순간, 클라이언트의 소스 코드도 함께 변경해야 한다! (OCP 위반)

    * 해결방안

        - DIP 위반 해결 방안

            - 인터페이스에만 의존하도록 설계 변경

            - private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
                -> private DiscountPolicy discountPolicy;

                => 메모리만 존재하고 실제 참조하고 있지 않으므로 NPE (NullPointerException) 발생

                    -> 누군가가 클라이언트에 구현체를 대신 생성하고 주입해주어야 한다.


3. 관심사의 분리

    - 애플리케이션을 하나의 공연이라고 보면, 각각의 인터페이스를 배역(배우의 역할) 이라고 생각하자

    - 이전에는 마치 로미오 역할을 하는 배우가 줄리엣 역할을 하는 배우를 직접 초빙하는 것과 같다.

    - 로미오 역할을 하는 배우는 공연도 해야하고 동시에 줄리엣 역할을 하는 배우도 초빙해야 하는 다양한 책임을 가지고 있다!

    * 관심사를 분리하자
     
        - 배우는 배역을 수행하는 것에만 집중해야 한다

        - 공연을 구성하고 배우를 섭외하는 별도의 공연 기획자가 따로 필요하다.

        - 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자

    - AppConfig 등장

        - 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스를 만든다
        
        - AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성하고, 생성한 객체의 레퍼런스를 생성자를 통해서 주입해준다.

            ex) Public MemberService memberService() {
                rerturn new MemberServiceImpl(new MemoryMemberRepository());
            }

        - 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않는다.
            (AppConfig가 생성자를 통해 구현체를 주입해주기 때문)

        - MemberServiceImpl 입장에서는 생성자를 통해 어떤 구현 객체가 들어올지 알 수 없다.

        - MemberServiceImpl은 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.

    * DIP 완성

        - MemberServiceImpl은 추상에만 의존하게 됨


4. AppConfig 리팩토링

    - 현재 AppConfig는 중복이 있고, 역할게 따른 구현이 잘 안보인다.

    - 객체 생성하는 메서드를 따로 구현해준다.

        - ex) 기존

            Public MemberService memberService() {
                rerturn new MemberServiceImpl(new MemoryMemberRepository());
            }

            변경

            Public MemberService memberService() {
                rerturn new MemberServiceImpl(memberRepository());
            }

            public MemberRepository memberRepository() {
                return new MemoryMemberRepository();
            }


5. 새로운 구조와 할인 정책 적용

    - AppConfig에서 할인 정책 역할을 담당하는 구현을 FixDiscountPolicy -> RateDiscountPolicy 객체로 변경

    - 이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다.
    
    - 클라이언트 코드를 포함해서 사용 영역의 어떤 코드도 변경할 필요가 없다.


6. 좋은 객체 지향 설계의 5가지 원칙의 적용

    - SRP 단일 책임 원칙
    
        - 한 클래스는 하나의 책임만 가져야 한다.

        - 클라이언트 객체는 직접 구현 객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고 있었음

        - SRP 단일 책임 원칙을 따르면서 관심사를 분리함

        - 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당

        - 클라이언트 객체는 실행하는 책임만 담당

    - DIP 의존관계 역전 원칙

        - 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.

        - 새로운 할인 정책을 개발하고 적용하려고 하니 클라이언트 코드도 함께 변경해야 하는 문제 발생 (구체화 구현 클래스에도 함께 의존했기 때문)

        - 클라이언트 코드가 추상화 인터페이스에만 의존하도록 코드를 변경했지만 NPE 발생
            - private DiscountPolicy discountPolicy;
        
        - AppConfig가 대신 객체를 생성해서 주입해주면서 DIP 원칙을 지킴

    - OCP 개방-폐쇄 원칙

        - 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.

        - 다형성 사용하고 클라이언트가 DIP를 지킴

        - AppConfig가 의존관계를 변경해서 클라이언트 코드에 주입하므로 클라이언트 코드는 변경하지 않아도 됨

        - 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다!


7. IoC, DI, 그리고 컨테이너

    - 제어의 역전 IoC (Inversion Of Control)

        - 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행함 즉, 구현 객체가 프로그램의 제어 흐름을 스스로 컨트롤 (개발자 입장에서는 자연스러운 흘름)
            
        - 반면에 AppConfig가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당 (프로그램의 제어 흐름은 AppConfig가 가져감)

        - 프로그램의 제어 흐름을 직접 제어하는 것이 아닌 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.

    * 프레임워크 vs 라이브러리

        - 프레임워크 : 내가 작성한 코드를 제어하고 대신 실행

        - 라이브러리 : 내가 작성한 코드가 직접 제어의 흐름을 담당

    - 의존관계 주입 DI (Dependency Injection)

        - 의존관계는 정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계 둘을 분리해서 생각해야 한다.

        - 정적인 클래스 의존관계

            - 클래스가 사용하는 import 코드만 보고 의존관계를 쉽게 판단할 수 있다.

            - 정적인 의존관계는 애플리케이션을 실행하지 않아도 분석할 수 있다.

        - 동적인 객체 의존 관계

            - 애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계다.

            - 런타임 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라 한다.

            - 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.

            - 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.

        - IoC 컨테이너, DI 컨테이너

            - AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC컨테이너 또는 DI 컨테이너라 한다.

            - 의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.

            - 또는 어셈블러, 오브젝트 팩토리 등으로 불리기도 한다.


8. 스프링으로 전환하기

        - 스프링 컨테이너

            - ApplicationContext를 스프링 컨테이너라고 한다.

            - 기존에는 AppConfig를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.

            - 스프링 컨테이너는 @Configration이 붙은 AppConfig를 설정(구성)정보로 사용하며, @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다 (이렇게 등록된 객체를 스프링 빈이라고 한다)

            - 스프링 빈은 @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용

            - 이전에는 개발자가 필요한 객체를 AppConfig를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다.

            - applicationContext.getBean() 메서드를 사용해서 스프링 빈 조회 가능

            - 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.

