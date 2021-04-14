package learning.spring.discount;

import learning.spring.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);

}
