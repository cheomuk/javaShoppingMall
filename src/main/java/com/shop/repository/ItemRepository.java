package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.List;

// JpaRepository<엔티티 타입 클래스, 기본키 타입> -> 엔티티 저장 및 수정
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>{

    List<Item> findByItemNm(String itemNm);     // itemNm(상품명)으로 데이터를 조회하기 위해 선언

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    // JPQL 사용 // order by는 정렬 기능, asc는 오름차순 desc는 내림차순
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
}
