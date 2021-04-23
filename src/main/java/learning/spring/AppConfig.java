package learning.spring;

import learning.spring.discount.DiscountPolicy;
import learning.spring.discount.FixDiscountPolicy;
import learning.spring.discount.RateDiscountPolicy;
import learning.spring.member.MemberService;
import learning.spring.member.MemberServiceImpl;
import learning.spring.member.MemoryMemberRepository;
import learning.spring.order.OrderService;
import learning.spring.order.OrderServiceImpl;
import learning.spring.scan.filter.BeanA;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    //@Bean
    //@Qualifier("orderServiceImpl")
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                fixDiscountPolicy()
        );
    }

    //@Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy rateDiscountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
        return new FixDiscountPolicy();
    }


}
