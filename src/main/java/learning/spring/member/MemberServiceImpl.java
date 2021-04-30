package learning.spring.member;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<MemberDao> findAll() {
        return memberRepository.findAll();
    }

    public void upgradeLevels() {
        List<MemberDao> allMembers = findAll();

        for (MemberDao member : allMembers) {
            if (member.getGrade() == Grade.BASIC) {
                memberRepository.findById(member.getId()).setGrade(Grade.VIP);
            }
        }

    }

}
