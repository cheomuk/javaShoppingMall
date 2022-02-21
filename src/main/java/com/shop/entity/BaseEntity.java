package com.shop.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy          // BaseTimeEntity의 @CreatedDate를 상속받아 사용하기 때문에 @CreatedBy를 사용한다.
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy     // BaseTimeEntity의 @LastModifiedDate를 상속받아 사용하기 때문에 @LastModifiedBy를 사용한다.
    private String modifiedBy;
}
