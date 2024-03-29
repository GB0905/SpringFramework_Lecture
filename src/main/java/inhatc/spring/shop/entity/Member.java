package inhatc.spring.shop.entity;

import inhatc.spring.shop.common.entity.BaseEntity;
import inhatc.spring.shop.constant.Role;
import inhatc.spring.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * 회원가입을 위한 Member 객체 생성<br>
     * memberFormDto로부터 사용자 정보를 획득한 후 password를 인코딩하여 함께 저장
     * @param memberFormDto
     * @param passwordEncoder
     * @return Member 객체 생성 후 반환
     */
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .password(memberFormDto.getPassword())
                .address(memberFormDto.getAddress())
                .role(Role.USER)
                .build();

        member.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));

        return member;
    }
}