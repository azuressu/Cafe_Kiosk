import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Goods extends Menu{
    Scanner sc =  new Scanner(System.in);
    private double price;     // 메뉴의 가격
    private double price2;     // 메뉴의 가격
    private double totalp;    // 메뉴 가격의 총합
    private double totalp2;    // 메뉴 가격의 총합
    private int number = 1;   // 메뉴의 개수
    private int number2 = 1;   // 메뉴의 개수

    public double getPrice() {
        return price;
    }              // 메뉴 가격 가져오기
    @Override
    public String getName() {
        return super.getName();
    }     // 메뉴 명 가져오기 (상속)
    @Override
    public String getDetail() {
        return super.getDetail();
    } // 설명 가져오기 (상속)
    @Override
    public String getNum() {
        return super.getNum();
    }       // 번호 가져오기 (상속)
    public String setName(String newname) { return super.setName(newname);}
    public double getTotalp() {
        this.totalp = price * number;
        return this.totalp;
    }    // 주문한 메뉴의 총 합을 저장하기 위한 메소드 (array에 넣어줄 값)
    public int getNumber() { return number; }               // 메뉴 개수에 접근할 메소드
    public int getNumber2() { return number2; }               // 메뉴 개수에 접근할 메소드
    public int setNumber() {
        this.number += 1;
        return this.number;
    }           // 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    // 기본 생성자
    public Goods() {}
    // 생성자
    public Goods(String num, String name, double price, String detail) {
        super(num, name, detail);
        this.price = price;
    }

} // Goods.java
