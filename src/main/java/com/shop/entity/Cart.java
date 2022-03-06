package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)   // 1대1 매핑 선언, 패치 값은 지연 로딩이다.
    @JoinColumn(name = "member_id")     // 매핑할 외래키 지정. name을 지정 안해도 자동으로 지정해주지만 확실히 하기 위해 지정함.
    private Member member;
}
