---
layout: single
title: "개인 과제 포스팅 - 카페 키오스크 수정본"
---

## 카페 키오스크 만들기 수정본

클래스는 총 5개로 구성되어 있습니다.

Main.java, Menu.java, Goods.java, Order.java, MyMenu.java


### 1) Menu.java
```java
public class Menu {
    private String num;     // 메뉴 번호
    private String name;    // 메뉴 이름
    private String detail;  // 메뉴 설명

    // 각 메뉴에 접근 가능한 getter 메소드
    public String getNum() {
        return this.num;
    }
    public String getName() {
        return this.name;
    }
    public String getDetail() {
        return detail;
    }
    // 기본 생성자
    public Menu() {}
    // 생성자
    public Menu(String num, String name, String detail) {
        this.num = num;
        this.name = name;
        this.detail = detail;
    }
} // Menu.java
```

메뉴를 구성하는 필드와 메소드가 필요하다. 그리고 각 메뉴 아이템을 담을 수 있는 생성자가 필요하다.

따라서, 각 메뉴의 번호와 이름, 설명을 지칭하는 필드를 private으로 선언하고,

private으로 선언한 각 필드에 접근 가능하도록 public getter 메소드를 선언한다.

그리고 Menu() 생성자를 통해서 메뉴 인스턴스를 생성할 수 있도록 했다.


### 2) Goods.java
```java
public class Goods extends Menu{
    private double price;          // Small 메뉴의 가격
    private double price2;         // Regular 메뉴의 가격
    private double totalp;         // 상품 가격의 총합 (가격 * 개수)
    private int number;            // Small 메뉴의 개수
    private int number2;           // Regular 메뉴의 개수
    private int number_total = 0;  // Small 메뉴의 총 개수
    private int number2_total = 0; // Regular 메뉴의 총 개수

    public int getNumber_total() { return number_total; }

    public int getNumber2_total() { return number2_total; }
    // 메뉴 가격 가져오기
    public double getPrice() { return price; }              
    public double getPrice2() {
        this.price2 = price + 0.5;
        return price2;
    }
    // 상속받은 메소드
    @Override
    public String getName() { return super.getName(); }               // 메뉴 이름
    public String getName2() { return super.getName()+"(Regular)"; }  // 메뉴 이름2 (Regular 버전)
    @Override
    public String getDetail() { return super.getDetail(); }           // 메뉴 설명
    @Override
    public String getNum() { return super.getNum(); }                 // 메뉴 번호

    public double getTotalp() {                                       // 주문한 Small 사이즈 메뉴의 총 합을 저장하기 위한 메소드
        this.totalp = ( price * number_total ) + ( price2 * number2_total );
        return this.totalp;
    } 

    public int getNumber() { return this.number; }                    // Small 메뉴 개수에 접근할 메소드
    public int getNumber2() { return this.number2; }                  // Regular 메뉴 개수에 접근할 메소드
    public void setNumber1(int j) { this.number = j; }                // Small 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    public void setNumber2(int i) { this.number2 = i; }               // Regular 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    public void setTotalNum1() {this.number_total = 0;}               // Small 메뉴 개수를 초기화하는 메소드
    public void setTotalNum2() {this.number2_total = 0;}              // Regular 메뉴 개수를 초기화하는 메소드
    public void set_Number1(int ii) { this.number_total += ii; }      // Small 메뉴 개수의 총 합을 수정할 메소드
    public void set_Number2(int jj) { this.number2_total += jj; }     // Regular 메뉴 개수의 총 합을 수정할 메소드

    // 기본 생성자
    public Goods() {}
    // Goods 아이템을 새로 담아줄 Goods 생성자
    public Goods(Goods g) {
        super(g.getNum(), g.getName(), g.getDetail());
        this.price = g.price;
        this.price2 = g.getPrice2();
        this.number = g.getNumber();
        this.number2 = g.getNumber2();
        this.number_total = g.getNumber_total();
        this.number2_total = g.getNumber2_total();
        this.totalp = g.getTotalp();
    }
    // 생성자
    public Goods(String num, String name, double price, String detail) {
        super(num, name, detail);
        this.price = price;
    }
} // Goods.java
```
Goods.java는 Menu.java를 상속받는다.

그래서 Menu.java에 만들어놓은 필드에 접근 가능하다.

겹치는 메소드를 제외하고 Goods.java에서 필요한 필드들을 따로 생성해주었다.

우선 메뉴별로 가격이 필요하다. Small / Regular 선택지를 줄 예정이라 price 필드를 2개 생성했다.

그리고 각 Small / Regular 를 소비자로부터 개수를 입력받아 설정하는 number 필드를 2개 생성했다.

Small / Regular 마다 판매량을 누적해서 보여주기 위해서 number_total 필드를 2개 생성했다.

상품 가격의 총합을 계산하기 위해서 (Small 사이즈와 Regular 사이즈 둘 다) totalp 필드를 생성했다.

모두 private으로 선언하여 접근 가능한 getter 메소드를 각각 생성해주었고, 값을 수정할 필요가 있는 number와 number_total 필드들에 대해 setter 메소드도 생성해 주었다.


### 3) Order.java
```java
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
    public void clearOrderList() { this.orderList.clear(); }                  // 장바구니를 초기화

} // Order.java
```
Order.java에서는 상품을 담아주는 역할을 담당한다.

Order.java안에서 리스트를 두 개 생성했다.

하나는 사용자가 주문 한 번을 할 때마다 사용하는 장바구니 역할(orderList), 다른 하나는 지금까지 판매된 모든 상품들을 담아놓은 문서 역할(sellList)이다.


먼저 orderList와 sellList 각각에 접근 가능한 getter 메소드를 선언했다.

그리고 orderList에 사용할 메소드 두개와, sellList에 사용할 메소드를 하나 더 생성했다.

addOrderList() 메소드는, 사용자가 입력한 메뉴가 Small 사이즈인지 Regular 사이즈인지 판단하여 그에 맞게 Goods.java의 메소드를 불러온다.

clearOrderList() 메소드는, 한 번 주문을 완료하고 나면 장바구니가 비어있어야 하므로 그 리스트를 비워주는 (clear) 역할을 한다.

addSellList() 메소드는 사용자가 주문을 완료하고 나면, 장바구니에 담겨있던 내용들을 차곡차곡 담아두는 역할이다.


### 4) MyMenu.java

코드가 너무 길어서, 메소드별로 설명하고자 한다.
```java
public class MyMenu {
  // 밑의 모든 코드들은 이 클래스 내에 선언되어 있다.
}
```

```java
Scanner sc = new Scanner(System.in);
Order order = new Order();            // 장바구니로 사용할 order 객체 생성
private int orderNumber = 0;          // 대기 번호를 출력해줄 변수 생성   
```
먼저 클래스 내에 선언한 변수들이다.

입력받아야 해서 Scanner를 선언했고, 장바구니 사용을 위해서 Order, 주문 완료 후 대기번호 출력을 위한 orderNumber 변수를 생성했다.

#### mainMenu() 메소드: 메인 화면 호출
```java
// mainMenu() - 메인 화면을 호출하는 메소드
public void mainMenu() throws InterruptedException{
    System.out.println("\"COFFEE BEAN에 오신 것을 환영합니다.\"");
    System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요. \n");
    System.out.println("[ COFFEE BEAN MENU ]");
    // 메뉴를 차례로 출력해줌
    for (Menu m: menuList) {
        if (Objects.equals(m.getNum(), "5.")) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.printf("%-2s %-13s | %s\n", m.getNum(), m.getName(), m.getDetail());
        } else { System.out.printf("%-2s %-13s | %s\n", m.getNum(), m.getName(), m.getDetail()); }
    }
    inputMainMenu(); // 메뉴를 입력받는 메소드 호출
} // mainMenu() 종료
```
메인 화면을 출력해주는 메소드이다.

메인 메뉴판의 리스트를 차례로 출력해준다.

그리고 메뉴를 입력받기 위해서 inputMainMenu() 메소드를 호출한다.


#### inputMainMenu() 메소드: 메인 화면에서 메뉴를 입력받는 부분
```java
// inputMainMenu() - 메인 메뉴2: 입력받고 판단하여 다음 메소드를 호출하는 부분의 메소드
public void inputMainMenu() throws InterruptedException {
    String i = sc.nextLine();
    if ((menuList.get(0).getNum() + menuList.get(0).getName()).contains(i)) {               // 1.Coffee 메뉴
        detailMenu("Coffee", goodsListCoffee);                                              // String 타입의 Coffee와 List 타입의 goodsListCoffee를 매개변수로 담아 detailMenu() 메소드 호출
    } else if ((menuList.get(1).getNum() + menuList.get(1).getName()).contains(i)) {        // 2.Tea 메뉴
        detailMenu("Tea", goodsListTea);                                                    // String 타입의 Tea와 List 타입의 goodsListTea를 매개변수로 담아 detailMenu() 메소드 호출
    } else if ((menuList.get(2).getNum() + menuList.get(2).getName()).contains(i)) {        // 3.Ice Blended 메뉴
        detailMenu("Ice Blended", goodsListIB);                                             // String 타입의 Ice Blended와 List 타입의 goodsListIB를 매개변수로 담아 detailMenu() 메소드 호출
    } else if ((menuList.get(3).getNum() + menuList.get(3).getName()).contains(i)) {        // 4.Dessert 메뉴
        detailMenu("Dessert", goodsListDessert);                                            // String 타입의 Dessert와 List 타입의 goodsListDessert를 매개변수로 담아 detailMenu() 메소드 호출
    } else if ((menuList.get(4).getNum() + menuList.get(4).getName()).contains(i)) {        // 5.Order 메뉴
        order();                                                                            // 주문 화면인 order() 메소드 호출
    } else if ((menuList.get(5).getNum() + menuList.get(5).getName()).contains(i)) {        // 6.Cancel 메뉴
        orderCancel();                                                                      // 주문 취소 화면인 orderCancel() 메소드 호출
    } else if (Objects.equals(i, "0")) {                                                    // Hidden Menu1 : 0
        sellingGoods();                                                                     // 판매 상품 목록을 불러오는 메소드 호출
    } else if (Objects.equals(i, "00")) {                                                   // Hidden Menu2 : 00
        getTotalPrice();                                                                    // 판매 상품들의 총 가격을 불러오는 메소드 호출
    } else {                                                                                // 잘못된 메뉴명을 입력했을 경우
        System.out.println("잘못된 메뉴명입니다. 메뉴를 다시 입력해주세요.");                   // 잘못 입력했다는 문장을 출력해주고
        inputMainMenu();                                                                    // 메뉴를 잘못 입력받으면 다시 메소드를 호출하여 입력받게 함 
    }
} // inputMainMenu() 종료
```
메인 메뉴를 입력받는 메소드이다.

입력받는 메뉴의 이름이 menuList안의 Num + Name 안에 포함되면, 해당 상세 메뉴판을 출력하는 메소드로 넘어가고 (1~4번의 경우)

주문이나 주문취소의 경우는 해당 메소드로 넘어간다. (5~6번의 경우)

그리고 0이나 00을 입력하면 숨겨진 메뉴를 보여주는 메소드로 넘어간다.

어느것도 해당하지 않는 경우의 메뉴를 입력하면, 다시 입력받도록 한다.


#### detailMenu() 메소드: 상세 메뉴판을 불러오는 메소드
```java
// detailMenu() - 상세 메뉴판을 불러오는 메소드
public void detailMenu(String detail, List<Goods> detaillist) throws InterruptedException{
    System.out.println("\"COFFEE BEAN에 오신 것을 환영합니다.\"");
    System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요. \n");
    System.out.println("[ " + detail + " MENU ]");                                // 입력받은 메인 메뉴명 출력
    for (Goods item: detaillist) {                                                // 입력받은 상세 리스트 출력
        System.out.printf("%-2s %-23s | %5s | %s\n", item.getNum(), item.getName(), "W " + item.getPrice(), item.getDetail());
    }
    inputDetailMenu(detail, detaillist);                                          // 입력받았던 메뉴명과 리스트를 매개변수로 담아 inputDetailMenu() 메소드 호출
} // detailMenu() 종료
```
위에서 불러올 때, String 타입과 List 타입의 변수를 각각 매개변수로 받아오게 설정했다.

String 타입의 변수는 메뉴 명을 출력할 때 사용하는 변수이다.

List는 상세 메뉴판의 내용을 출력해주기 위해 받아왔다.

상세 메뉴판의 내용을 다 출력해주고 나면, 상품을 입력받는 inputDetailMenu() 메소드를 호출한다.


#### inputDetailMenu() 메소드: 상세 메뉴판의 상품을 입력받는 메소드
```java
// inputDetailMenu() - 상세 페이지 쪼개기2: 메뉴명을 입력받는 부분
public void inputDetailMenu(String detail, List<Goods> detailList) throws InterruptedException{
    Goods detailGoods = null;        // 일단 Goods 타입의 변수 초기값을 null로 설정
    while (true) {                   // 입력받는 부분을 반복
        String s1 = sc.nextLine();   // 메뉴명을 입력받음
        for (int i=0; i<detailList.size(); i++) {                                                  // 매개변수로 받은 리스트 반복문 돌리기
            if ((detailList.get(i).getNum() + detailList.get(i).getName()).contains(s1)) {         // 만약 그 안에 있는 번호+이름이 입력받은 문자열을 포함하고 있다면
                detailGoods = detailList.get(i);                                                   // detailGoods안에 해당 Goods 인스턴스를 넣어줌
                if (detail.equals("Coffee") || detail.equals("Ice Blended")) {                     // 입력받은 문자열이 coffee나 ice blended 라면
                    getGood(detailGoods);                                                          // Goods 타입의 detailGoods를 매개변수로 받는 getGood() 메소드 호출
                } else { getGood2(detailGoods); }                                                  // 다른 문자열을 입력받았다면 Goods 타입의 detailGoods를 매개변수로 받는 getGoods2() 메소드 호출
            } // if문
        } // for문
        System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");                                // 리스트 내에 없으면 잘못된 메뉴명을 입력했다는 사실을 알려주고 다시 입력받기
    }
} // inputDetailMenu() 종료
```
상세 메뉴판에서 상품을 입력받는 메소드이다.

상품명을 잘못 입력받았을 경우를 대비해서 while문을 이용했다.

리스트 안에 상품의 Num+Name이, 입력받은 문자열을 포함한다면 위에 선언해둔 Goods 타입의 변수에 값을 넣어준다.

그리고 상세 메뉴가 Coffee나 IceBlended인 경우에는 getGood() 메소드로, 아닌 경우는 getGood2() 메소드로 이동한다.


#### getGood2() 메소드: 선택한 메뉴를 장바구니에 추가할 것인지를 입력받는 메소드
```java
// getGood2() - 선택한 메뉴를 장바구니에 추가할 것인지 판단
public void getGood2(Goods goods) throws InterruptedException {
    System.out.printf("%-25s | %5s | %s", "\""+goods.getName(), "W " +goods.getPrice(), goods.getDetail()+"\"");   // 입력받은 상품 그대로 출력하기
    System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
    System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
    String call = sc.nextLine();
    if ("1.확인".contains(call)) {                     // 1.확인을 눌렀다면
        System.out.print("수량을 입력해주세요: ");      // 상품 수량을 입력받음
        int i = sc.nextInt();
        sc.nextLine();
        System.out.println(goods.getName() + " 가 장바구니에 추가되었습니다.");
        goods.setNumber1(i);                           // 해당 goods 객체 안의 상품 개수를 입력받은 수량으로 set 해주는 메소드 호출
        order.addOrderList(goods, 1, i);               // 그 후 장바구니 리스트(Order 클래스의 orderList)에 담을 메소드 호출
        mainMenu(); 
    } else {                                           // 2. 취소를 눌렀다면
        mainMenu();                                    // 메인 메뉴로 돌아가기
    }
} // getGood2()
```
상세 메뉴판에서 입력받은 상품을 실제로 장바구니에 담을 것인지 판단하는 메소드이다. (Tea 메뉴와 Dessert 메뉴에만 해당)

만약 확인을 입력했다면 상품의 수량을 입력받고, 장바구니에 추가되었음을 알린다.

그 후, 상품의 수량을 입력받은 수량으로 설정하고, 장바구니 리스트(orderList)에 해당 상품을 추가해준다.

취소를 입력했다면 메인 메뉴로 돌아간다.


#### getGood(): 선택한 메뉴의 사이즈를 입력받아 (옵션) 장바구니에 추가할 것인지 판단
```java
// getGood() - 선택한 메뉴의 사이즈를 입력받아 장바구니에 추가할 것인지 판단
public void getGood(Goods goods) throws InterruptedException{
    System.out.printf("%-25s | %5s | %s", "\""+goods.getName(), "W " +goods.getPrice(), goods.getDetail()+"\"");                    // 입력받은 상품 그대로 출력하기
    System.out.println("\n위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
    System.out.printf("%-2s %-15s %-2s %-15s\n", "1.", "Small(W " + goods.getPrice() + ")", "2.", "Regular(W " + goods.getPrice2() +")");  // 1.Small(가격) 2.Regular(가격) 출력
    String s = sc.nextLine();
    if (s.contains("Small") || s.contains("1") || s.contains("small")) {                                                                        // 1.Small을 입력했다면
        getGood2(goods);                                                                                                                         // 원래 상품을 출력해주는 getGood() 메소드 호출
    } else if ((s.contains("Regular") || s.contains("2") || s.contains("regular"))){                                                            // 2.Regular를 입력했다면
        System.out.printf("%-25s | %5s | %s", "\""+goods.getName2(), "W " +goods.getPrice2(), goods.getDetail()+"\"");              // 찾아낸 Regular 상품 출력하기
        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
        String call = sc.nextLine();
        if ("1.확인".contains(call)) {                     // 1.확인을 눌렀다면
          System.out.print("수량을 입력해주세요: ");        // 상품 수량을 입력받음
          int i = sc.nextInt();
          sc.nextLine();
          System.out.println(goods.getName2() + " 가 장바구니에 추가되었습니다.");
          goods.setNumber2(i);                            // 해당 goods 객체 안의 상품 개수를 입력받은 수량으로 set 해주는 메소드 호출
          order.addOrderList(goods, 2, i);                // 그 후 장바구니 리스트(Order 클래스의 orderList)에 담을 메소드 호출
        }
    } // if ~ else 종료
    mainMenu(); // 다시 메인 메뉴로 돌아가기
} // getGood()
```
getGood2()의 메소드와 다른 점은, 옵션을 입력받는다는 점이다. (Coffee와 Ice Blended 메뉴에만 해당)

먼저 선택한 상품의 내용을 한 번 출력해주고, 옵션을 출력해준다.

1.Small을 선택했다면, 위에 선언해둔 getGood() 메소드로 이동한다.

2.Regular를 선택했다면, 원래 입력받았던 상품의 이름, 가격을 미리 설정해둔 Regular 버전으로 출력해주고, 입력받은 수량 역시 Regular 버전의 수량에 추가하고, 장바구니에 넣어준다.

그리고 메인 메뉴로 돌아가게 된다.


#### order(): 주문을 누르면 장바구니에 담긴 내용을 출력해주는 메소드
```java
// order() - 주문 화면: 주문을 누르면 장바구니에 담겼던 내용을 출력해줌
public void order() throws InterruptedException {
    Scanner sc = new Scanner(System.in);
        if (order.getOrderList().isEmpty()) {
            System.out.println("장바구니가 비어있습니다.");
            System.out.println("잠시 후 메뉴판으로 돌아갑니다.");
            try {
                TimeUnit.SECONDS.sleep(1);
                mainMenu();
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("아래와 같이 주문 하시겠습니까?\n");
            System.out.println("[ Orders ]");
            List<Goods> goodsList = order.getOrderList(); // 장바구니에 넣은 리스트들 갖고오기
            for (Goods good: goodsList) {                 // 상품명과 상품가격 상품개수 상품설명 출력
                if (good.getNumber2() > 0) {              // Regular 사이즈의 상품 개수가 0보다 크다면 (즉 하나라도 들어가있다면)
                    System.out.printf("%-25s | %s | %-3s | %s\n", good.getName2(), "W "+good.getPrice2(), good.getNumber2_total() +"개", good.getDetail());
                }
                if (good.getNumber() > 0) {               // Small 사이즈의 상품 개수가 0보다 크다면 (즉 하나라도 들어가있다면)
                    System.out.printf("%-25s | %s | %-3s | %s\n", good.getName(), "W " + good.getPrice(), good.getNumber_total() + "개", good.getDetail());
                }
            } // for문
            System.out.println("\n[ Total ]");
            double total = goodsList.stream().mapToDouble(Goods::getTotalp).sum();  // 총 금액 계산한 메소드로 합계 계산
            System.out.println("W " + total);                                                                                                 // 토탈 가격(price * number) 계산해서 출력해주기

            System.out.printf("\n%-2s %-7s %-2s %-7s\n", "1.", "주문", "2." ,"메뉴판");
            String option = sc.nextLine();
            if ("1.주문".contains(option)) {                                   // 1.주문을 입력했다면
                goodsList.stream().forEach(order::addSellList);                // sellList에 orderList에 있던 아이템을 추가해줌 (누적 리스트)
                orderComplete();                                               // 대기번호를 발급해주는 주문완료 메소드 호출
            } else if ("2.메뉴판".contains(option)) { mainMenu(); }            // 2.메뉴판을 입력했다면 다시 메인 메뉴판으로 돌아가는 메소드 호출
        }
} // order()
```
메인 화면에서 5.Order를 눌렀을 때 호출되는 메소드이다.

장바구니에 내역이 없으면, 없다는 사실을 알려주고 1초후에 메뉴판으로 돌아간다.

장바구니에 내용물이 있으면 장바구니에 담았던 상품들을 출력해준다.

장바구니에 넣은 리스트를 가져와서 새로운 리스트에 넣어주고, 해당 상품을 출력해준다.

단, getNumber2() 값이 0보다 크다면 (=Regular 상품이 한 개라도 있다면) Regular 버전의 메뉴를 출력해준다.

또, getNUmber() 값이 0보다 크다면 (=Small 상품이 한 개라도 있다면) Small 버전의 메뉴를 출력한다.

그리고 stream()을 이용해서 장바구니에 담긴 총 금액을 출력해준다.

1.주문을 입력했다면 주문 완료 메소드 orderComplete()를 호출하고, 누적 판매 상품을 저장하는 sellList에 상품들을 더해준다.

2.메뉴판을 입력했다면 메인 메뉴 메소드를 호출한다.


#### orderComplete(): 주문이 완료됨을 알려주고 대기번호를 발급해주는 메소드
```java
// orderComplete() - 주문 완료 화면: 주문이 완료됨을 알려주고 대기번호를 발급해줌
public void orderComplete() throws InterruptedException {
    System.out.println("주문이 완료되었습니다!\n");
    System.out.println("대기번호는 [ " + ++this.orderNumber + " ] 번 입니다.");    // 대기번호 출력
    System.out.println("(3초 후 메뉴판으로 돌아갑니다.)");
    TimeUnit.SECONDS.sleep(3);                                                 // 3초를 지연시킴 (throws InterruptedException 필요)
    order.clearOrderList();                                                    // 장바구니 초기화 후 메뉴판으로 돌아가기
    mainMenu();                                                                // 그 후에 메뉴판으로 돌아가는 메소드 호출하기
}
```
사용자가 주문을 입력하면 출력되는 메소드이다.

주문 완료되었다는 문장을 출력해주고 대기번호를 출력해준다.

출력 전에 대기 번호의 값을 1 증가 시켜주고 출력해준다.

그리고 3초 후에 돌아가야 하므로, 3초를 지연시키고, 장바구니를 초기화 한 후, 메인 메뉴판으로 돌아간다.


#### orderCancel(): 진행하던 주문의 취소 여부를 물어보는 메소드
```java
// orderCancel() - 주문 취소 화면: 진행하던 주문의 취소 여부를 물어보는 화면
public void orderCancel() throws InterruptedException{
    Scanner sc = new Scanner(System.in);
    if (order.getOrderList().isEmpty()) {
        System.out.println("장바구니가 비어 있습니다.");
        System.out.println("잠시 후 메뉴판으로 돌아갑니다.");
        try {
            TimeUnit.SECONDS.sleep(1);
            mainMenu();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    } else {
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
        String option = sc.nextLine();
        if ("1.확인".contains(option)) {
            System.out.println("진행하던 주문이 취소되었습니다.\n");
            order.clearOrderList(); // 장바구니 초기화
        }
        mainMenu(); // 메인 메뉴로 돌아가기
    }                                                            // 메인 메뉴로 돌아가기
}
```
메인 화면에서 6.Cancel 메뉴를 입력했을 때 호출되는 메소드이다.

장바구니가 비어있으면 1초 후 다시 메뉴판으로 돌아간다.

그렇지 않으면 진행하던 주문을 취소하겠다고 입력을 받고, 입력받은 값에 따 장바구니를 초기화한다.

그리고 메인메뉴로 넘어간다.


#### sellingGoods(): 총 판매상품 조회 기능
```java
// sellingGoods() - 추가 기능: 총 판매상품 목록 조회 기능
public void sellingGoods() throws InterruptedException{
    Scanner sc = new Scanner(System.in);
    System.out.println("[ 총 판매상품 목록 현황 ]");
    System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.\n");
    List<Goods> goodsList = order.getSellList();                                                     // sellList(현재까지의 누적된 상품들의 리스트) 출력
    for (Goods item: goodsList) {
        if (item.getNumber_total() > 0) {                                                            // Small 상품의 개수가 0개보다 많으면 (하나라도 있으면)
            System.out.printf("%-25s | %s\n", "- "+item.getName(), "W " + item.getPrice());   // Small 상품의 이름과 가격 출력
        }
        if (item.getNumber2_total() > 0) {                                                           // Regular 상품의 개수가 0개보다 많으면 (하나라도 있으면)
            System.out.printf("%-25s | %s\n", "- "+item.getName2(), "W " + item.getPrice2()); // Regular 상품의 이름과 가격 출력
        }
    }
    System.out.println("1. 돌아가기");
    while (true) {
        String ss = sc.nextLine();
        if ("1.돌아가기".contains(ss)) {                              // 1.돌아가기를 입력했다면
            mainMenu();                                              // 메인메뉴로 돌아가기
        }
        System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");  // 잘못 입력했다면 다시 입력받기
    }
}
```
총 판매 상품을 조회하는 메소드이다.

sellList에 판매된 상품들의 내용을 저장해 두었으므로 sellList를 출력한다.

getNumber_total()의 개수가 0보다 크면 (=Small 상품의 총 개수가 하나라도 있으면) Small 상품의 내용을 출력하고,

getNumber2_total()의 개수가 0보다 크면 (=Regular 상품의 총 개수가 하나라도 있으면) Regular 상품의 내용을 출력한다.

그리고 돌아가기를 입력하면 메인메뉴로 돌아간다.


#### getTotalPrice(): 총 누적 판매금액 조회 기능
```java
// getTotalPrice() - 추가기능: 총 판매금액 조회 기능
public void getTotalPrice() throws InterruptedException{
    System.out.println("[ 총 판매금액 현황 ]");
    double totalPrice = order.getSellList().stream().mapToDouble(Goods::getTotalp).sum();   // 현재까지의 총 판매금액 계산
    System.out.println("현재까지 총 판매된 금액은 [ W " + totalPrice + " ] 입니다.\n");
    System.out.println("1. 돌아가기");
    while (true) {
        String ss = sc.nextLine();
        if (ss.contains("1") || ss.contains("돌아가기")) {             // 1.돌아가기를 입력했다면
            mainMenu();                                               // 메인메뉴로 돌아가기
        }
        System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");   // 잘못 입력했다면 다시 입력받기
    }
}
```
총 누적된 판매 금액을 출력하는 메소드이다.

sellList에 누적 판매 상품을 넣어두었으므로 sellList에 담긴 상품들의 값을 계산해주면 된다.

stream을 이용해서 getTotalp(Small 사이즈 상품), getTotalp2(Regular 사이즈 상품)의 합계를 계산한다.

그리고 돌아가기를 입력하면 메인메뉴로 돌아간다.


#### 생성해둔 리스트들 (메인 메뉴, 각 상세 메뉴)
```java
// 메인 화면의 메뉴 리스트
List<Menu> menuList = Arrays.asList(
    new Menu("1.", "Coffee", "고소한 커피 메뉴"),
    new Menu("2.", "Tea", "향기로운 차 메뉴"),
    new Menu("3.", "Ice Blended", "시원한 얼음을 함께 간 음료"),
    new Menu("4.", "Dessert", "커피나 음료와 함께 즐기는 디저트"),
    new Menu("5.", "Order", "장바구니를 확인 후 주문합니다."),
    new Menu("6.", "Cancel", "진행중인 주문을 취소합니다.")
); // menuList
// 상세 화면의 coffee 리스트
List<Goods> goodsListCoffee = Arrays.asList(
    new Goods("1." , "Americano", 5.0, "에스프레소와 물의 컴비네이션"), // 아메리카노
    new Goods("2.", "Cafe Latte", 5.8, "에스프레소와 우유의 컴비네이션"), // 카페 라떼
    new Goods("3.", "Hazelnut Americano", 5.5, "에스프레소와 물과 헤이즐넛 파우더의 컴비네이션"), // 헤이즐넛아메리카노
    new Goods("4.", "Vanilla Latte", 6.3, "에스프레소와 프렌치 디럭스 바닐라파우더, 저지방 우유"), // 바닐라 라떼
    new Goods("5.", "Cafe Sua", 6.9, "깊고 진한 더블 에스프레소와 달콤한 연유의 만남") // 카페 수아
); // goodsListCoffee
// 상세 화면의 tea 리스트
List<Goods> goodsListTea = Arrays.asList(
    new Goods("1." , "English Breakfast", 6.0, "다질링과 실론 차의 완벽한 블렌드"), // 잉글리쉬브렉퍼스트
    new Goods("2.", "Lemon Chamomile", 6.0, "100% 이집트산 캐모마일와 향기로운 레몬 그래스가 혼합된 허브티"), // 레몬캐모마일
    new Goods("3.", "Swedish Berries", 6.0, "히비스커스와 건포도, 베리믹스의 혼합으로 만들어진 과일티"), // 스웨디쉬베리즈
    new Goods("4.", "Ginseng Peppermint", 6.0, "중국산 홍삼, 시베리아 인삼, 페퍼민트와 여러가지 허브와 혼합된 티"), // 진생페퍼민트
    new Goods("5.", "Parmigrenate Blueberry Tea", 6.0, "블루베리와 석류 맛이 가향된 녹차, 우롱차, 홍차의 조합") // 파미그레네이트 블루베리 티
); // goodsListTea
// 상세 화면의 Ice Blended 리스트
List<Goods> goodsListIB = Arrays.asList(
    new Goods("1." , "Hazelnut Ice Blended", 7.0, "커피 원액과 헤이즐넛 파우더, 얼음과 저지방 우유의 블렌드"),  // 헤이즐넛 아이스 블랜디드
    new Goods("2.", "Mocha Ice Blended", 6.5, "커피 원액과 스페셜 더치 초코렛 파우더, 얼음과 저지방 우유의 블렌드"), // 모카 아이스 블랜디드
    new Goods("3.", "Fresh Mango Ice Blended", 6.7, "달콤한 망고시럽과 망고 과육이 들어간 시원하고 깔끔한 맛의 블렌드"), // 후레쉬망고 아이스 블랜디드
    new Goods("4.", "Pure Double Chocolate Ice Blended", 6.5, "다크 초콜릿과 저지방 우유, 얼음의 블랜드"), // 퓨어 더블 초코렛 아이스 블랜디드
    new Goods("5.", "Jeju Green Tea Ice Blended", 7.0, "제주 유기농 차와 녹차, 말차 파우더와 얼음, 저지방 우유의 블랜드") // 제주 첫물 차광 녹차 아이스 블랜디드
); // goodsListIB
// 상세 화면의 Dessert 리스트
List<Goods> goodsListDessert = Arrays.asList(
    new Goods("1." , "Camembert Cheese Tart", 5.5, "바삭한 초코크런치와 까망베르치즈의 환상적인 조화의 타르트"), // 까망베르치즈타르트
    new Goods("2.", "Chicago Cheese Cake", 5.9, "진한 크림치즈의 맛이 입안을 감싸고 달콤한 여운을 남겨주는 케익"), // 시카고치즈케익
    new Goods("3.", "New Tiramisu Cake", 6.1, "부드러운 마스카포네 치즈크림, 쌉싸름한 에스프레소를 머금은 케익"), // 뉴 티라미수 케익
    new Goods("4.", "Fresh Milk Honey Cake", 6.2, "부드럽고 고소한 우유크림과 사양벌꿀의 달콤함을 느낄 수 있는 케익"),  // 순 우유 허니 케익
    new Goods("5.", "Sweet Yakgwa Cake", 6.7, "조청과 약과를 넣어 쫀득하고 꾸덕한 시트에 바닐라 마스카포네크림이 더해진 케익합") // 조청 약과 케익
); // goodsListDessert
```


### 5) Main.java
```java
public class Main {
    public static void main(String[] args) {
        MyMenu myMenu = new MyMenu();                      // 주문할 수 있는 인스턴스 객체 생성
        try {
            myMenu.mainMenu(); 
        } catch (Exception e) {                            // 예외 처리
            System.out.println(e.getMessage());            // 예외 관련 메시지 출력
        }
    }
} // Main.java
```
Main.java의 main 메소드에서 코드를 실행한다.

MyMenu 인스턴스를 myMenu라는 이름으로 생성해주고, try ~ catch 문으로 작성한다.

mainMenu() 메소드를 불러오면 그 안에서 돌아가도록 코드를 짰기 때문에, mainMenu()를 실행해주면 된다.

예외가 발생할 경우 catch 문을 통해 예외 메세지가 출력된다.
