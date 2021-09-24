package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member =  new Member(memberId, "test", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "item", 100000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(90000);


    }
}
