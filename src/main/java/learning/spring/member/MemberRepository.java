package learning.spring.member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

    List<MemberDao> findAll();

}
