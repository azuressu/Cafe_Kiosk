import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class MyMenu {
    // 장바구니로 사용할 order 객체 생성
    Order order = new Order();
    // 대기 번호를 출력해줄 변수 생성
    private int orderNumber = 0;
    // 메인 화면을 호출하는 메소드
    public void mainMenu() throws InterruptedException{
        System.out.println("\"COFFEE BEAN에 오신 것을 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요. \n");
        System.out.println("[ COFFEE BEAN MENU ]");
        // 메뉴를 차례로 출력해줌
        for (Menu m: menuList) {
            if (Objects.equals(m.getNum(), "5.")) {
                System.out.println("\n[ ORDER MENU ]");
                System.out.printf("%-2s %-13s | %s\n", m.getNum(), m.getName(), m.getDetail());
            } else {
                System.out.printf("%-2s %-13s | %s\n", m.getNum(), m.getName(), m.getDetail());
            }
        }
        inputMainMenu();
    }
    // 메인 메뉴 메서드 쪼개기 2 - 입력받고 판단하여 다음 메소드를 호출하는 부분의 메소드
    public void inputMainMenu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String i = sc.nextLine();
        if ((menuList.get(0).getNum() + menuList.get(0).getName()).contains(i)) {
            detailMenu("Coffee", goodsListCoffee);
        } else if ((menuList.get(1).getNum() + menuList.get(1).getName()).contains(i)) {
            detailMenu("Tea", goodsListTea);
        } else if ((menuList.get(2).getNum() + menuList.get(2).getName()).contains(i)) {
            detailMenu("Ice Blended", goodsListIB);
        } else if ((menuList.get(3).getNum() + menuList.get(3).getName()).contains(i)) {
            detailMenu("Dessert", goodsListDessert);
        } else if ((menuList.get(4).getNum() + menuList.get(4).getName()).contains(i)) {
            order();
        } else if ((menuList.get(5).getNum() + menuList.get(5).getName()).contains(i)) {
            orderCancel();
        } else if (Objects.equals(i, "0")) {
            sellingGoods(); // 새로운 메소드 호출  - 판매 상품 목록을 불러옴 (Hidden Menu1)
        } else if (Objects.equals(i, "00")) {
            getTotalPrice(); // 새로운 메소드 호출 - 판매 상품 총 가격을 불러옴 (Hidden Menu2)
        } else {
            System.out.println("잘못된 메뉴명입니다. 메뉴를 다시 입력해주세요.");
            inputMainMenu(); // 메뉴를 잘 못 입력받으면 다시 메소드를 호출하여 입력받게 함
        }
    }
    // 추가 기능 - 총 판매상품 목록 조회 기능 (regular 상품도 추가해주는 코드로 만들기)
    public void sellingGoods() throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.\n");
        List<Goods> goodsList = order.getSellList(); // sellList라는 리스트를 따로 출력해줌
        for (Goods item: goodsList) {
            if (item.getNumber_total() > 0) {
                System.out.printf("%-35s | %s\n", "- "+item.getName(), "W " + item.getPrice());
            }
            if (item.getNumber2_total() > 0) {
                System.out.printf("%-35s | %s\n", "- "+item.getNameR(), "W " + item.getPriceR());
            }
        }
        System.out.println("1. 돌아가기");
        while (true) {
            String ss = sc.nextLine();
            if (ss.contains("1") || ss.contains("돌아가기")) {
                mainMenu();
            }
            System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");
        }
    } // sellingGoods()
    // 추가기능 - 총 판매금액 조회 기능 (Regular상품 총 가격도 추가해서 함께 sum 계산하는 코드로 수정하기)
    public void getTotalPrice() throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 총 판매금액 현황 ]");
        double totalPrice = order.getSellList().stream().mapToDouble(Goods::getTotalp).sum(); //  + order.getSellList().stream().mapToDouble(Goods::getTotalp2).sum();
        System.out.println("현재까지 총 판매된 금액은 [ W " + totalPrice + " ] 입니다.\n");
        System.out.println("1. 돌아가기");
        while (true) {
            String ss = sc.nextLine();
            if (ss.contains("1") || ss.contains("돌아가기")) {
                mainMenu();
            }
            System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");
        }
    } // getTotalPrice()
    // 상세 메뉴판을 불러오는 메소드
    public void detailMenu(String detail, List<Goods> detaillist) throws InterruptedException{
        System.out.println("\"COFFEE BEAN에 오신 것을 환영합니다.\"");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요. \n");
        System.out.println("[ " + detail + " MENU ]");
        for (Goods item: detaillist) {
            System.out.printf("%-2s %-30s | %5s | %s\n", item.getNum(), item.getName(), "W " + item.getPrice(), item.getDetail());
        }
        inputDetailMenu(detail, detaillist);
    } // detailMenu()
    // 상세 페이지 쪼개기2 - 메뉴명을 입력받는 부분
    public void inputDetailMenu(String detail, List<Goods> detailList) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        Goods detailGoods = null; // 일단 Goods 타입의 변수 초기값을 null
        // 입력받는 부분을 반복하는 문장
        while (true) {
            String s1 = sc.nextLine();
            for (int i=0; i<detailList.size(); i++) {
                if ((detailList.get(i).getNum() + detailList.get(i).getName()).contains(s1)) {
                    detailGoods = detailList.get(i);
                    if (detail.equals("Coffee") || detail.equals("Ice Blended")) { getGood(detailGoods); }
                    else { getGood2(detailGoods); }
                }
            }
            // 리스트 내에 없으면 잘못된 메뉴명을 입력했다는 사실을 알려주고 다시 입력받기
            System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");
        }
    } // inputDetailMenu()
    public void getGood2(Goods goods) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.printf("%-35s | %5s | %s", "\""+goods.getName(), "W " +goods.getPrice(), goods.getDetail()+"\""); // 입력받은 상품 그대로 출력하기
        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
        String call = sc.nextLine();
        if ("1.확인".contains(call)) {
            System.out.print("수량을 입력해주세요: ");
            int i = sc.nextInt();
            sc.nextLine();
            Goods newgoods = new Goods(goods);
            System.out.println(newgoods.getName() + " 가 장바구니에 추가되었습니다.");
            newgoods.setNumber1(i);
            order.addOrderList(newgoods, 1, i); // 장바구니 객체에 담을 메소드 호출
            mainMenu();
        } else {
            mainMenu();
        }
    } // getGood2()
    public void getGood(Goods goods) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        System.out.printf("%-35s | %5s | %s", "\""+goods.getName(), "W " +goods.getPrice(), goods.getDetail()+"\""); // 입력받은 상품 그대로 출력하기
        System.out.println("\n위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        System.out.printf("%-2s %-15s %-2s %-15s\n", "1.", "Small(W " + goods.getPrice() + ")", "2.", "Regular(W " + (goods.getPrice()+0.5) +")");
        String s = sc.nextLine();
        if (s.contains("Small") || s.contains("1") || s.contains("small")) {
            getGood2(goods);
        } else if ((s.contains("Regular") || s.contains("2") || s.contains("regular"))){
            System.out.printf("%-35s | %5s | %s", "\""+goods.getNameR(), "W " +goods.getPriceR(), goods.getDetail()+"\""); // 찾아낸 Regular 상품으로 출력하기
            System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
            System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
            String call = sc.nextLine();
            if ("1.확인".contains(call)) {
                System.out.print("수량을 입력해주세요: ");
                int i = sc.nextInt();
                sc.nextLine();
                Goods newgoods = new Goods(goods);
                System.out.println(newgoods.getNameR() + " 가 장바구니에 추가되었습니다.");
                newgoods.setNumber2(i);
                order.addOrderList(newgoods, 2, i); // 장바구니 객체에 담을 메소드 호출
            }
        }
        mainMenu(); // 다시 메인 메뉴로 돌아가기
    } // getGood()
    // 주문 화면 - 주문을 누르면 장바구니에 담겼던 내용을 출력해줌
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
            // 상품명과 상품가격 상품개수 상품설명 출력 - 추가기능: 상품 개수
            for (Goods good : goodsList) {
                if (good.getNumber2() > 0) {
                    System.out.printf("%-35s | %-5s | %-3s | %s\n", good.getNameR(), "W " + good.getPriceR(), good.getNumber2_total() + "개", good.getDetail());
                }
                if (good.getNumber() > 0) {
                    System.out.printf("%-35s | %-5s | %-3s | %s\n", good.getName(), "W " + good.getPrice(), good.getNumber_total() + "개", good.getDetail());
                }
            }
            System.out.println("\n[ Total ]");
            double total = goodsList.stream().mapToDouble(Goods::getTotalp).sum(); // goodsList.stream().mapToDouble(Goods::getTotalp2).sum() +
            System.out.println("W " + total); // 토탈 가격(price * number) 계산해서 출력해주기

            System.out.printf("\n%-2s %-7s %-2s %-7s\n", "1.", "주문", "2.", "메뉴판");
            String option = sc.nextLine();
            if ("1.주문".contains(option)) {
                for (Goods goods : goodsList) {
                    Goods new_good = new Goods(goods);
                    order.addSellList(new_good);
                }
                orderComplete();   // 대기번호를 발급해주는 주문완료 화면 출력
            } else if ("2.메뉴판".contains(option)) {
                mainMenu();        // 다시 메인 메뉴판으로 돌아가는 화면
            }
        }
    } // order()
    // 주문 완료 화면 - 주문이 완료됨을 알려주고 대기번호를 발급해줌
    public void orderComplete() throws InterruptedException {
        System.out.println("주문이 완료되었습니다!\n");
        this.orderNumber += 1;
        System.out.println("대기번호는 [ " + this.orderNumber + " ] 번 입니다.");

        System.out.println("(3초 후 메뉴판으로 돌아갑니다.)");
        TimeUnit.SECONDS.sleep(3); // 3초를 지연시킴 (throws InterruptedException 필요)
        order.getOrderList().stream().forEach(goods -> {goods.setTotalNum1(); goods.setTotalNum2();});
//        goodsListCoffee.stream().forEach(goods -> {goods.setTotalNum1(); goods.setTotalNum2();});
//        goodsListTea.stream().forEach(goods -> {goods.setTotalNum1(); goods.setTotalNum2();});
//        goodsListIB.stream().forEach(goods -> {goods.setTotalNum1(); goods.setTotalNum2();});
//        goodsListDessert.stream().forEach(goods -> {goods.setTotalNum1(); goods.setTotalNum2();});
        order.clearOrderList();            // 장바구니 초기화 후 메뉴판으로 돌아가기
        mainMenu();                        // 그 후에 메뉴판으로 돌아가는 메소드 호출하기
    } // orderComplete()
    // 주문 취소 화면 - 진행하던 주문을 취소할 것이냐는 화면
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
        }
    } // orderCancel()

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

} // MyMenu.java