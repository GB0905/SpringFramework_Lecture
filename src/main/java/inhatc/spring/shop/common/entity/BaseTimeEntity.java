package inhatc.spring.shop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class}) // JPA에게 해당 Entity는 Auditing 기능을 사용한다는 것을 알리는 어노테이션
@MappedSuperclass // 상속받는 클래스에게 매핑정보만 제공하는 클래스
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate // 등록시간
    @Column(updatable = false) // 수정불가
    private LocalDateTime regDate;

    @LastModifiedDate // 수정시간
    private LocalDateTime updateDate;
}
