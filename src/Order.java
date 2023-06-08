import java.util.*;

public class Order {
    private List<Goods> orderList = new ArrayList<>();         // 상품을 담을 리스트 (장바구니)
    private List<Goods> sellList = new ArrayList<>();          // 판매된 상품을 담을 리스트 - 누적된 판매 상품들을 담을 리스트

    public List<Goods> getOrderList() { return orderList; }    // 상품을 담을 리스트에 접근
    public List<Goods> getSellList() { return sellList; }      // 판매된 상품을 담을 리스트에 접근

    // orderList(장바구니)에 상품 더해주기 (+ regular인지 small인지 판단)
    public void addOrderList(Goods goods, int i, int num) {                   // i(1이면 small사이즈, 2면 regular 사이즈), num은 입력받은 상품의 개수
        if (getOrderList().contains(goods)) {                                 // 만약 상품이 이미 담겨있었다면, 상품의 개수만 늘려줌
            if (i == 1) { goods.set_Number1(num); }                           // Small 상품의 개수를 num 개수만큼 늘려주는 메소드 호출 (Goods 클래스에 있는 메소드)
            else { goods.set_Number2(num); }                                  // Regular 상품의 개수를 num 개수만큼 늘려주는 메소드 호출 (Goods 클래스에 있는 메소드)
        } else {                                                              // 그렇지 않으면 상품을 장바구니에 넣어줌
            if (i == 1){
                goods.set_Number1(num);                                       // Small 상품의 개수를 num 개수만큼 늘려주는 메소드 호출
                this.orderList.add(goods);                                    // 상품 자체가 리스트에 담겨있지 않았으므로 상품을 담아줌
            } else {
                goods.set_Number2(num);                                       // Regular 상품의 개수를 num 개수만큼 늘려주는 메소드 호출
                this.orderList.add(goods);                                    // 상품 자체가 리스트에 담겨있지 않았으므로 상품을 담아줌
            } // if~else
        } // if~else
    } // addOrderList() 메소드 종

    public void addSellList(Goods goods) { this.sellList.add(goods); }        // 판매목록 리스트에 상품을 넣어줌
    public void clearOrderList() {   // 장바구니를 초기화
        this.orderList.clear();
    }

} // Order.java
