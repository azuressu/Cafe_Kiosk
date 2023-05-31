import java.util.*;

public class Order {
    private List<Goods> orderList = new ArrayList<>(); // 상품을 담을 리스트 (장바구니)
    private List<Goods> sellList = new ArrayList<>(); // 판매된 상품을 담을 리스트 (장바구니) - 현재까지 상품 판매 현황을 출력

    // private 리스트에 접근
    public List<Goods> getOrderList() {
        return orderList;
    }
    public List<Goods> getSellList() {
        return sellList; // 자기 자신의 메소드를 호출하면 어째
        // return getSellList(); 이렇게 해놨다
        // 그래서 이런 오류 발생 Exception in thread "main" java.lang.StackOverflowError
        //	at Order.getSellList(Order.java:14)
    }
    // orderList에 (장바구니에) 상품 더해주기
    public void addOrderList(Goods goods) {
        if (getOrderList().contains(goods)) {// 만약 상품이 이미 담겨있었다면,
             goods.setNumber();
        } else {
            this.orderList.add(goods);                 // 그렇지 않으면 상품을 장바구니에 넣어줌
        }
    }
    public void addSellList(Goods goods) {
        this.sellList.add(goods);             // 판매목록 리스트에 상품을 넣어줌
    }
    public void clearOrderList() {
        this.orderList.clear();               // 장바구니를 초기화
    }

} // Order.java
