package inhatc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static inhatc.spring.shop.entity.QItem.item;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional  //테스트 후 롤백해서 지워줌
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

//  @PersistenceContext
    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("상품 생성 테스트")
    public void createItemTest(){
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .stockNumber(100)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        System.out.println("============ item : " + item);

        Item savedItem = itemRepository.save(item);

        System.out.println("============ savedItem : " + savedItem);
    }


    public void createItemList(){
        for (int i = 1; i <= 10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000+i)
                    .stockNumber(100+i)
                    .itemDetail("테스트 상품 상세 설명"+i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 이름 검색 테스트")
    public void findByItemNmTest(){
        createItemList();


        //List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        //for (Item item : itemList) {System.out.println(item);}
        //itemRepository.findByItemNm("테스트 상품1").forEach(System.out::println);
        itemRepository
                .findByItemNm("테스트 상품1")
                .forEach((item) -> {
                    System.out.println(item);
                });
        //itemList.forEach((item) -> System.out.println(item) );
        //itemList.forEach(System.out::println);
    }

    @Test
    @DisplayName("OR 테스트")
    public void findByItemNmOrItemDetailTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품2", "테스트 상품 상세 설명8");
        itemList.forEach((item)->{
            System.out.println(item);
        });
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest(){
        createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("OrderBy 테스트(가격 내림차순)")
    public void findByPriceLessThanOrderByPriceDescTest(){
        createItemList();
        itemRepository.findByPriceLessThanOrderByPriceDesc(10005)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("JPQL 테스트")
    public void findByDetailTest() {
        createItemList();
        itemRepository.findByDetail("1")
                .forEach((item) -> {
                    System.out.println(item);
                });
    }

    @Test
    @DisplayName("Native 테스트")
    public void findByDetailNativeTest(){
        createItemList();
        itemRepository.findByDetailNative("1")
                .forEach((item) -> {
                    System.out.println(item);
                });
    }

    @Test
    @DisplayName("querydsl 테스트")
    public void querydslTest(){
        createItemList();

        JPAQueryFactory query = new JPAQueryFactory(em);
        //QItem qItem = item;

        //query.select().from().where().orderBy().fetch();

        List<Item> itemList = query.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.itemDetail.like("%" + "1" + "%"))
                .orderBy(item.price.desc())
                .fetch();
        itemList.forEach((item -> System.out.println(item)));

//        List<Item> itemList = query.select(qItem)
//                .from(qItem)
//                .where(qItem.itemDetail.like("%테스트%"))
//                .orderBy(qItem.price.desc())
//                .fetch();
//        itemList.forEach(item -> System.out.println(item));
//        for (Item item : itemList) {
//            System.out.println(item);
//        }

    }

    public void createItemList2(){
        for (int i = 1; i <= 5; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000+i)
                    .stockNumber(100+i)
                    .itemDetail("테스트 상품 상세 설명"+i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
        for (int i = 6; i <= 10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000+i)
                    .stockNumber(100+i)
                    .itemDetail("테스트 상품 상세 설명"+i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("querydsl 테스트2")
    public void querydslTest2() {
        createItemList2();

        String itemDetail = "테스트";
        Integer price = 10002;
        String itemSellStatus = "SELL";

        QItem item = QItem.item;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(item.itemDetail.like("%" + itemDetail + "%"));
        builder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)){
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0,5);    // Pageable.ofSize(5).withPage(0);

        //itemRepository.findAll(builder, pageable).forEach(i -> System.out.println(i));
        Page<Item> page = itemRepository.findAll(builder, pageable);
        List<Item> content = page.getContent();
        content.stream().forEach((e) -> {
            System.out.println(e);
        });

    }
}