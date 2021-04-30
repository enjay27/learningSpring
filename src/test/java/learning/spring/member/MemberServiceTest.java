package learning.spring.member;

import learning.spring.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberDao memberDao;

    @Mock
    MemberService memberService;

    @InjectMocks
    MemberServiceImpl memberServiceImpl;

    @Mock
    List mockList;

    List<MemberDao> members = new ArrayList<>();

    {
        members.add(new MemberDao(1L, "a", Grade.BASIC));
        members.add(new MemberDao(2L, "b", Grade.VIP));
        members.add(new MemberDao(3L, "c", Grade.BASIC));
        members.add(new MemberDao(4L, "d", Grade.VIP));
        members.add(new MemberDao(5L, "e", Grade.BASIC));
        members.add(new MemberDao(6L, "f", Grade.VIP));
    }

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    void mockTest() {

        mockList = mock(ArrayList.class);

        mockList.add("apple");
        when(mockList.get(0)).thenReturn("apple");
        assertThat("apple").isEqualTo(mockList.get(0));

//        memberRepository = mock(MemoryMemberRepository.class);
//
//        memberServiceImpl = new MemberServiceImpl(memberRepository);
//        when(memberRepository.findAll()).thenReturn(members);
//
//        memberServiceImpl.upgradeLevels();
//
//        verify(memberRepository, times(2)).findById(any(Long.class));
//        verify(memberRepository).findById(members.get(0).getId());
//        assertThat(members.get(0).getGrade()).isEqualTo(Grade.VIP);
//        verify(memberRepository).findById(members.get(2).getId());
//        assertThat(members.get(2).getGrade()).isEqualTo(Grade.VIP);
    }
}