package com.shop.entity;

import com.shop.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;      // 한 명의 회원이 여러 번 주문할 수 있기 때문에 주문 엔티티 기준에서 다대일 단방향 매핑을 한다.

    private LocalDateTime orderDate;        // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;        // 주문 상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // 영속성 전이 속성 all 설정. 부모가 변하면 자식도 모두 변함.     // orphanRemoval = true -> 고아 객체가 생겼을 시 제거한다.
    // 주문 상품 엔티티와 일대다 매핑함. Order 엔티티가 주인이 아니므로 mappedBy로 연관관계 주인을 설정함.
    private List<OrderItem> orderItems = new ArrayList<>();     // 하나의 주문이 여러 개의 주문 상품을 가지므로 List로 매핑함.

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
