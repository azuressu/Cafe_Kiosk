import java.util.*;
import java.util.concurrent.TimeUnit;

public class MyMenu {
    Scanner sc = new Scanner(System.in);
    Menu menu = new Menu();
    Goods goods = new Goods();
    // 장바구니로 사용할 order 객체 생성
    Order order = new Order();

    private int orderNumber = 0;
    public int setorderNumber(int i) {
        return this.orderNumber += i;
    }

    public void mainMenu() throws InterruptedException{
        System.out.println("\"COFFEE BEAN에 오신 것을 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요. \n");
        System.out.println("[ COFFEE BEAN MENU ]");
        // 메뉴를 차례로 출력해줌
        for (Menu m: menuList) {
            if (Objects.equals(m.getNum(), "5.")) {
                System.out.println("\n[ ORDER MENU ]");
                System.out.printf("%-2s %-13s %s %s\n", m.getNum(), m.getName(), "|", m.getDetail());
            } else if (Objects.equals(m.getNum(), "6.")){
                System.out.printf("%-2s %-13s %s %s", m.getNum(), m.getName(), "|", m.getDetail());
            } else {
                System.out.printf("%-2s %-13s %s %s\n", m.getNum(), m.getName(), "|", m.getDetail());
            }
        }
        System.out.println();
        inputMainMenu();
    }

    // 메인 메뉴 메서드 쪼개기 2 - 입력받고 판단하는 부분만 따로 하기
    public void inputMainMenu() throws InterruptedException{
        String i = sc.nextLine();
        // 입력받은 i 변수를 통해서 다음 화면 (detail) 불러오기
        // 각 메소드를 생성해줄 필요가 있는 듯
        // 하나의 메소드를 호출하되, 각 조건마다 다른 리스트를 불러오면 됨 !
        if (Objects.equals(i, "1.Coffee") || Objects.equals(i, "Coffee") || Objects.equals(i, "coffee")) {
            detailMenu("Coffee",goodsListCoffee);
        } else if (Objects.equals(i, "2.Tea") || Objects.equals(i, "Tea") || Objects.equals(i, "tea")) {
            detailMenu("Tea",goodsListTea);
        } else if (Objects.equals(i, "3.Ice Blended") || Objects.equals(i, "Ice Blended") || Objects.equals(i, "ice blended")) {
            detailMenu("Ice Blended", goodsListIB);
        } else if (Objects.equals(i, "4.Dessert") || Objects.equals(i, "Dessert")|| Objects.equals(i, "dessert")) {
            detailMenu("Dessert", goodsListDessert);
        } else if (Objects.equals(i, "5.Order") || Objects.equals(i, "Order") || Objects.equals(i, "order")) {
            order();
        } else if (Objects.equals(i, "6.Cancel") || Objects.equals(i, "Cancel") || Objects.equals(i, "calcel")) {
            orderCancel();
        } else if (Objects.equals(i, "0")) {
            // 새로운 메소드 호출 !
            // 판매 상품 목록을 불러옴
            sellingGoods();
        } else if (Objects.equals(i, "00")) {
            getTotalPrice();
        }  else {
            System.out.println("잘못된 메뉴명입니다. 메뉴를 다시 입력해주세요.");
            inputMainMenu();
        }
    }


    // 추가 기능
    // 총 판매상품 목록 조회 기능 추가
    public void sellingGoods() throws InterruptedException{
        System.out.println("[ 총 판매상품 목록 현황 ]");
        System.out.println("현재까지 총 판매된 상품 목록은 아래와 같습니다.\n");
        List<Goods> goodsList = order.getSellList();
        for (Goods item: goodsList) {
            System.out.printf("%-13s %s %s\n", "- "+item.getName(), "|", "W " + item.getPrice());
        }

        System.out.println("1. 돌아가기");
        String ss = sc.nextLine();
        // 조건 바꿔보자 : Objects.equals(ss, "1.돌아가기") || Objects.equals(ss, "돌아가기")
        if (ss.contains("1") || ss.contains("돌아가기")) {
            mainMenu();
        }
    }
    // 추가기능
    // 총 판매금액 현황
    public void getTotalPrice() throws InterruptedException{
        System.out.println("[ 총 판매금액 현황 ]");
        double totalPrice = order.getSellList().stream().mapToDouble(Goods::getTotalp).sum();
        System.out.println("현재까지 총 판매된 금액은 [ W " + totalPrice + " ] 입니다.\n");
        System.out.println("1. 돌아가기");
        String menu = sc.nextLine();
        if (Objects.equals(menu, "1.돌아가기") || Objects.equals(menu, "돌아가기")) {
            mainMenu();
        }
    }

    // 각 상세 메뉴판을 불러오는 메소드 생성하기
    public void detailMenu(String detail, List<Goods> detaillist) throws InterruptedException{
        System.out.println("\"COFFEE BEAN에 오신 것을 환영합니다.\"");
        System.out.println("아래 상품메뉴판을 보시고 상품을 골라 입력해주세요. \n");
        System.out.println("[ " + detail + " MENU ]");
        for (Goods good: detaillist) {
            System.out.printf("%-2s %-13s %s %s %s %s\n", good.getNum(), good.getName(), "|", "W "+good.getPrice(), "|", good.getDetail());
        }
        inputDetailMenu(detaillist);
    }

    // 상세 페이지 쪼개기 - 입력받는 부분
    public void inputDetailMenu(List<Goods> detailList) throws InterruptedException{
        Goods detailGoods = null; // 일단 초기값을 null로 설정해두기
        // 입력받는 부분을 반복하는 문장
        while (true) {
            String s1 = sc.nextLine();
            if (s1.contains(".")) {
                s1 = s1.substring(2);
            } else {
                s1 = s1;
            }
            for (int i=0; i<5; i++) {
                if (detailList.get(i).getName().equals(s1)) {
                    detailGoods = detailList.get(i);
                    getGood(detailGoods);
                }
            }
            System.out.println("잘못된 메뉴명입니다. 다시 입력해주세요.");
        }
        // 입력받은 값과 일치하는 Goods 아이템 선별하기
        // 그 해당 메뉴를 선별하는 코드 작성하기 (해당 메뉴를 list에서 찾아서 다음 메소드에서 매개변수로 넘겨주어야 함 !!)

        // 입력받고 나면 주문 하실거냐는 구매화면으로 넘어가기 (메소드 호출)
//        getGood(detailGoods);
    }

    // 구매 화면
    public void getGood(Goods goods) throws InterruptedException{
        System.out.printf("%-9s %s %5s %s %s", "\""+goods.getName(), "|", "W " +goods.getPrice(), "|", goods.getDetail()+"\""); // 입력받은 상품 그대로 출력하기
        System.out.println("위 메뉴의 어떤 옵션으로 추가하시겠습니까?");
        System.out.printf("%-2s %-10s %-2s %-10s", "1.", "Small(W " + goods.getPrice() + ")", "2.", "Regular(W " + goods.getPrice());






        System.out.println("\n위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
        // 해당 번호를 입력하면 장바구니에 추가하는 메소드 호출 (혹은 객체 - 장바구니 객체 안에 Goods 객체 담기)
        String call = sc.nextLine();
        if (Objects.equals(call, "1.확인") || Objects.equals(call, "확인")) {
            System.out.println(goods.getName() + " 가 장바구니에 추가되었습니다.");
            // 장바구니 객체에 담을 메소드 호출
            order.addOrderList(goods);
        } // else if (Objects.equals(call, "2.취소") || Objects.equals(call, "취소")) {
          // mainMenu(); // 추가 없이 메인화면으로 넘어가기
        //}
        mainMenu();
    }

    // 주문 화면 (매개변수로 장바구니를 넣어주어야 하나 ..?)
    public void order() throws InterruptedException {
        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        // 장바구니에 넣은 리스트들 갖고오기
        List<Goods> goodsList = order.getOrderList();
        // 차례로 출력해주되, 겹치는 item이 생기면 개수를 표시해보자 ..?! 어떻게 하냐묘 .. ^^
        for (Goods good: goodsList) {
//            good.getName();
            System.out.printf("%-13s %s %s %s %2s %s %s\n", good.getName(), "|", "W "+good.getPrice(), "|", good.getNumber() +"개", "|", good.getDetail());
        }
        System.out.println("\n[ Total ]");
        System.out.println("W " + goodsList.stream().mapToDouble(Goods::getTotalp).sum()); // 토탈 가격 계산해서 출력해주기

        System.out.printf("\n%-2s %-7s %-2s %-7s\n", "1.", "주문", "2." ,"메뉴판");
        String option = sc.nextLine();
        if (option.equals("1.주문") || option.equals("주문")) {
            // 대기번호를 발급해주는 주문완료 화면 출력
            // 그리고 장바구니를 초기화 해주어야 함 ... 하 골때리네 여기서
            goodsList.stream().forEach(order::addSellList); // 추가 기능 !! 총 판매상품 목록 조회 기능 추가
            orderComplete();
        } else if (option.equals("2.메뉴판") || option.equals("메뉴판")) {
            // 다시 메뉴판으로 돌아가는 화면 (메인으로 가겠지 ..?)
            mainMenu();
        }
    }

    // 주문 완료 화면
    public void orderComplete() throws InterruptedException {
        System.out.println("주문이 완료되었습니다!\n");
        setorderNumber(1);
        System.out.println("대기번호는 [ " + this.orderNumber + " ] 번 입니다."); // 이 대기번호도 어떻게 해야하지 .. 흠 thread가 필요한가 ? 그럼 엄청 겹칠텐데..

        // getTotalPrice();

        System.out.println("3초 후 메뉴판으로 돌아갑니다.");
        TimeUnit.SECONDS.sleep(3); // 3초를 지연시킴
        // 장바구니 초기화 후 메뉴판으로 돌아가기
        // 위에서 선언한 Order 인스턴스를 비우는 코드 필요 - Order 객체 내의 orderslist를 지우는 메소드 호출
        order.clearOrderList();
        // 그 후에 메뉴판으로 돌아가는 메소드 호출하기
        mainMenu();
    }

    // 주문 취소 화면
    public void orderCancel() throws InterruptedException{
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.printf("%-2s %-7s %-2s %-7s\n", "1.", "확인", "2.", "취소");
        String option = sc.nextLine();
        if (Objects.equals(option, "1.확인") || Objects.equals(option, "확인")) {
            System.out.println("진행하던 주문이 취소되었습니다.\n");
            // 하고 장바구니 초기화 후 !! 메인 메뉴 출력해줌
            order.clearOrderList();
            // 메인 메뉴 화면 출력~
        } // else if (option.equals("2.취소") || option.equals("취소")){ // 아니면 그냥 메인 메뉴로 다시 돌아가자 ! (하 .. 디테일 메뉴로는 돌아갈 수 없나 ?)
//            mainMenu();
//        }
        mainMenu();
    }

    public List<Menu> menuList = Arrays.asList(
        new Menu("1.", "Coffee", "고소한 커피 메뉴"),
        new Menu("2.", "Tea", "향기로운 차 메뉴"),
        new Menu("3.", "Ice Blended", "시원한 얼음을 함께 간 음료"),
        new Menu("4.", "Dessert", "커피나 음료와 함께 즐기는 디저트"),
        new Menu("5.", "Order", "장바구니를 확인 후 주문합니다."),
        new Menu("6.", "Cancel", "진행중인 주문을 취소합니다.")
    ); // menuList

    List<Goods> goodsListCoffee = Arrays.asList(
        new Goods("1." , "아메리카노", 5.0, "에스프레소와 물의 컴비네이션"),
        new Goods("2.", "카페라떼", 5.8, "에스프레소와 우유의 컴비네이션"),
        new Goods("3.", "헤이즐넛 아메리카노", 5.5, "에스프레소와 물과 헤이즐넛 파우더의 컴비네이션"),
        new Goods("4.", "바닐라라떼", 6.3, "에스프레소와 프렌치 디럭스 바닐라파우더, 저지방 우유"),
        new Goods("5.", "카페수아", 6.9, "깊고 진한 더블 에스프레소와 달콤한 연유의 만남"),
        new Goods("6." , "아메리카노(Regular)", 5.5, "에스프레소와 물의 컴비네이션"),
        new Goods("7.", "카페라떼(Regular)", 6.3, "에스프레소와 우유의 컴비네이션"),
        new Goods("8.", "헤이즐넛 아메리카노(Regular)", 6.0, "에스프레소와 물과 헤이즐넛 파우더의 컴비네이션"),
        new Goods("9.", "바닐라라떼(Regular)", 6.8, "에스프레소와 프렌치 디럭스 바닐라파우더, 저지방 우유"),
        new Goods("10.", "카페수아(Regular)", 7.4, "깊고 진한 더블 에스프레소와 달콤한 연유의 만남")
            ); // goodsListCoffee

    List<Goods> goodsListTea = Arrays.asList(
        new Goods("1." , "잉글리쉬블렉퍼스트", 6.0, "다질링과 실론 차의 완벽한 블렌드"),
        new Goods("2.", "레몬캐모마일", 6.0, "100% 이집트산 캐모마일와 향기로운 레몬 그래스가 혼합된 허브티"),
        new Goods("3.", "스웨디쉬베리즈", 6.0, "히비스커스와 건포도, 베리믹스의 혼합으로 만들어진 과일티"),
        new Goods("4.", "진생페퍼민트", 6.0, "중국산 홍삼, 시베리아 인삼, 페퍼민트와 여러가지 허브와 혼합된 티"),
        new Goods("5.", "파미그레네이트 블루베리 티", 6.0, "블루베리와 석류 맛이 가향된 녹차, 우롱차, 홍차의 조합")
    ); // goodsListTea

    List<Goods> goodsListIB = Arrays.asList(
        new Goods("1." , "헤이즐넛 아이스 블랜디드", 7.0, "커피 원액과 헤이즐넛 파우더, 얼음과 저지방 우유의 블렌드"),
        new Goods("2.", "모카 아이스 블랜디드", 6.5, "커피 원액과 스페셜 더치 초코렛 파우더, 얼음과 저지방 우유의 블렌드"),
        new Goods("3.", "후레쉬망고 아이스 블랜디드", 6.7, "달콤한 망고시럽과 망고 과육이 들어간 시원하고 깔끔한 맛의 블렌드"),
        new Goods("4.", "퓨어더블초코렛 아이스 블랜디드", 6.5, "다크 초콜릿과 저지방 우유, 얼음의 블랜드"),
        new Goods("5.", "제주 첫물 차광 녹차 아이스 블랜디드", 7.0, "제주 유기농 차와 녹차, 말차 파우더와 얼음, 저지방 우유의 블랜드"),
        new Goods("6." , "헤이즐넛 아이스 블랜디드(Regular)", 7.5, "커피 원액과 헤이즐넛 파우더, 얼음과 저지방 우유의 블렌드"),
        new Goods("7.", "모카 아이스 블랜디드(Regular)", 7.0, "커피 원액과 스페셜 더치 초코렛 파우더, 얼음과 저지방 우유의 블렌드"),
        new Goods("8.", "후레쉬망고 아이스 블랜디드(Regular)", 7.2, "달콤한 망고시럽과 망고 과육이 들어간 시원하고 깔끔한 맛의 블렌드"),
        new Goods("9.", "퓨어더블초코렛 아이스 블랜디드(Regular)", 7.0, "다크 초콜릿과 저지방 우유, 얼음의 블랜드"),
        new Goods("10.", "제주 첫물 차광 녹차 아이스 블랜디드(Regular)", 7.5, "제주 유기농 차와 녹차, 말차 파우더와 얼음, 저지방 우유의 블랜드")
    ); // goodsListIB

    List<Goods> goodsListDessert = Arrays.asList(
        new Goods("1." , "까망베르치즈타르트", 5.5, "바삭한 초코크런치와 까망베르치즈의 환상적인 조화의 타르트"),
        new Goods("2.", "시카고치즈케익", 5.9, "진한 크림치즈의 맛이 입안을 감싸고 달콤한 여운을 남겨주는 케익"),
        new Goods("3.", "뉴 티라미수 케익", 6.1, "부드러운 마스카포네 치즈크림, 쌉싸름한 에스프레소를 머금은 케익"),
        new Goods("4.", "순 우유 허니케익", 6.2, "부드럽고 고소한 우유크림과 사양벌꿀의 달콤함을 느낄 수 있는 케익"),
        new Goods("5.", "조청 약과 케익", 6.7, "조청과 약과를 넣어 쫀득하고 꾸덕한 시트에 바닐라 마스카포네크림이 더해진 케익합")
    ); // goodsListDessert


}
