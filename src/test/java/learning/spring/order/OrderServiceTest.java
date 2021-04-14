package learning.spring.order;

import learning.spring.discount.DiscountPolicy;
import learning.spring.discount.FixDiscountPolicy;
import learning.spring.discount.RateDiscountPolicy;
import learning.spring.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    MemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberServiceImpl(memberRepository);
    DiscountPolicy fixDiscountPolicy = new FixDiscountPolicy();
    OrderService fixOrderService = new OrderServiceImpl(fixDiscountPolicy);
    DiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
    OrderService rateOrderService = new OrderServiceImpl(rateDiscountPolicy);

    @Test
    void createFixOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = fixOrderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void createRateOrder() {
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = rateOrderService.createOrder(memberId, "itemA", 20000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }
}