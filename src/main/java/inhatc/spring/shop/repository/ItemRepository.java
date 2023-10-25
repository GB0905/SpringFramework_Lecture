package inhatc.spring.shop.repository;

import inhatc.spring.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm);     // 해당 이름에 대한 상품 리스트 가져오기

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail); // JPQL임 :itemDetail 변수로 전달

    @Query(value="select * from item where item_detail like %:itemDetail% order by price asc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail); // Native

    //////////////////////////////////////////////Report//////////////////////////////////////////////
    /*
    아래 조건에 맞게 테스트 코드를 작성하고 결과를 캡쳐해서 제출 하세요.

    조건 : 재고량 -> 160개 이상 이면서 상품명에 5가 들어 있는 내용을 추출하시오.
    1.  쿼리 메소드 (재고량과 이름으로 검색)
    2.  JPQL 이용해서 위에 조건
    3.  Native 로 위에 조건
    4.  querydsl로 위에 조건
    */

    // 1. 쿼리 메소드 (재고량과 이름으로 검색)
    List<Item> findByStockNumberGreaterThanEqualAndItemNmContaining(Integer stockNumber, String itemNm);
    // 이상 = GreaterThanEqual , 포함 = Containing

    // 2. JPQL 이용해서 위에 조건
    @Query("SELECT i FROM Item i WHERE i.stockNumber >= %:stockNumber% AND i.itemNm LIKE %:itemNm%")
    List<Item> findByStockNumberAndItemNm(@Param("stockNumber") Integer stockNumber, @Param("itemNm") String itemNm);

    // 3. Native 로 위에 조건
    @Query(value = "SELECT * FROM Item WHERE stock_number >= %:stockNumber% AND item_nm LIKE %:itemNm%", nativeQuery = true)
    List<Item> findByStockNumberAndItemNmNative(@Param("stockNumber") Integer stockNumber, @Param("itemNm") String itemNm);

    // 4. querydsl로 위에 조건
    //Test2에 작성

    //////////////////////////////////////////////////////////////////////////////////////////////////
}