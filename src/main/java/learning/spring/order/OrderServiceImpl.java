package learning.spring.order;

import learning.spring.annotation.RateDiscountPolicy;
import learning.spring.discount.DiscountPolicy;
import learning.spring.discount.FixDiscountPolicy;
import learning.spring.member.Member;
import learning.spring.member.MemberRepository;
import learning.spring.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {


    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // @Autowired : 필드 명 매칭
    // @Qualifier : @Qualifier끼리 매칭 - 빈 이름 매칭
    // @Primary
    public OrderServiceImpl(MemberRepository memberRepository,
                            @RateDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
