package learning.spring;

import learning.spring.discount.FixDiscountPolicy;
import learning.spring.discount.RateDiscountPolicy;
import learning.spring.member.MemberService;
import learning.spring.member.MemberServiceImpl;
import learning.spring.member.MemoryMemberRepository;
import learning.spring.order.OrderService;
import learning.spring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public FixDiscountPolicy discountPolicy() {
//        return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }

}
