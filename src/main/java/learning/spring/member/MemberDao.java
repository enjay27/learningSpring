package learning.spring.member;

import lombok.Data;

@Data
public class MemberDao {
    private Long id;
    private String name;
    private Grade grade;

    public MemberDao(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
