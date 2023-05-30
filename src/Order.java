import java.util.*;

public class Order {

    private List<Goods> orderList = new ArrayList<>();
    private List<Goods> sellList = new ArrayList<>();

    // private 리스트에 접근하는 방법
    public List<Goods> getOrderList() {
        return orderList;
    }

    public List<Goods> getSellList() {
        return sellList; // 자기 자신의 메소드를 호출하면 어째
        // return getSellList(); 이렇게 해놨다
        // 그래서 이딴 오류 발생 Exception in thread "main" java.lang.StackOverflowError
        //	at Order.getSellList(Order.java:14)
    }
    // orderlist에 Goods 아이템 더하기 !!
    public void addOrderList(Goods goods) {
        if (getOrderList().contains(goods)) {
            goods.setNumber();
        } else {
            this.orderList.add(goods);
        }
    }
    public void addSellList(Goods goods) {
        this.sellList.add(goods);
    }

    public void clearOrderList() {
        this.orderList.clear();
    }

    // Order 생성자에 Goods 인스턴스를 넣어 ..?
    // 어떻게 해야하지 ... 허미 ....................
    // 그니까 Goods를 따로따로 넣어주어도 그걸 계속 save 해두어야 하는건데 ..
    // 그럼 리스트 아니뇨 ..?

    // 리스트를 생성해서
    // 생성자를 만들어서 Goods를 넣어주면
    // 그 생성자 안에서 리스트를 넣어주는거야 ..?
    // 그러면 private의 리스트 하나, 접근 가능한 getter 하나, 생성자 하나 ?

}
