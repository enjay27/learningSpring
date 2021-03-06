package learning.spring.discount;

import learning.spring.member.Grade;
import learning.spring.member.Member;
import org.springframework.stereotype.Component;

//@Component
//@learning.spring.annotation.RateDiscountPolicy

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountRateAmount = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountRateAmount / 100;
        } else {
            return 0;
        }
    }
}
