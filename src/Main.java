public class Main {
    public static void main(String[] args) {
        MyMenu myMenu = new MyMenu();           // 주문할 수 있는 인스턴스 객체 생성
        try {
            myMenu.mainMenu();
        } catch (Exception e) {                 // 예외 처리 필수
            System.out.println(e.getMessage()); // 예외 관련 메시지 출력
        }
    }
}