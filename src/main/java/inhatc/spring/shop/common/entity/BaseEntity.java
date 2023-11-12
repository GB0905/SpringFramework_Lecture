package inhatc.spring.shop.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass // 상속받는 클래스에게 매핑정보만 제공하는 클래스
@Getter
@Setter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy // 등록자
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy // 수정자
    private String modifiedBy;
}
