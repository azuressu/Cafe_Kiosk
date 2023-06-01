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
