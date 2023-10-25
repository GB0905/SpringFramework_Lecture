package inhatc.spring.shop.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.constant.ItemSellStatus;
import inhatc.spring.shop.entity.Item;
import inhatc.spring.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static inhatc.spring.shop.entity.QItem.item;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest2 {

    ///////////////////////Report///////////////////////
    /*
    아래 조건에 맞게 테스트 코드를 작성하고 결과를 캡쳐해서 제출 하세요.

    조건 : 재고량 -> 160개 이상 이면서 상품명에 5가 들어 있는 내용을 추출하시오.
    1.  쿼리 메소드 (재고량과 이름으로 검색)
    2.  JPQL 이용해서 위에 조건
    3.  Native 로 위에 조건
    4.  querydsl로 위에 조건
    */

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager em;

    public void createItemList() {
        for (int i = 100; i <= 200; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("1. 쿼리 메소드 (재고량과 이름으로 검색)")
    void findByStockNumberGreaterThanEqualAndItemNmContainingTest() {
        createItemList();
        List<Item> itemList = itemRepository.findByStockNumberGreaterThanEqualAndItemNmContaining(160, "5");
        itemList.forEach(item -> System.out.println("쿼리 메소드 : "+item));
    }

    @Test
    @DisplayName("2. JPQL 이용해서 위에 조건")
    void findByStockNumberAndItemNmTest() {
        createItemList();
        List<Item> itemList = itemRepository.findByStockNumberAndItemNm(160, "5");
        itemList.forEach(item -> System.out.println("JPQL : " + item));
    }

    @Test
    @DisplayName("3. Native 로 위에 조건")
    void findByStockNumberAndItemNmNativeTest() {
        createItemList();
        List<Item> itemList = itemRepository.findByStockNumberAndItemNmNative(160, "5");
        itemList.forEach(item -> System.out.println("Native : " + item));
    }

    @Test
    @DisplayName("4. querydsl로 위에 조건")
    public void querydslTest() {
        createItemList();
        JPAQueryFactory query = new JPAQueryFactory(em);

        List<Item> itemList = query.selectFrom(item)
                .where(item.stockNumber.gt(160))
                .where(item.itemNm.contains("5"))
                .fetch();

        itemList.forEach(item -> System.out.println("querydsl : " + item));
    }

    ////////////////////////////////////////////////////
}