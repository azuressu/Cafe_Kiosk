import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Goods extends Menu{
    Scanner sc =  new Scanner(System.in);
    private double price;
    private double totalp;
    private int number = 1;


    // 메뉴 가격 가져오기
    public double getPrice() {
        return price;
    }
    // 메뉴 명 가져오기 (상속)
    @Override
    public String getName() {
        return super.getName();
    }
    // 설명 가져오기 (상속)
    @Override
    public String getDetail() {
        return super.getDetail();
    }
    // 번호 가져오기 (상속)
    @Override
    public String getNum() {
        return super.getNum();
    }
    public double getTotalp() { return price * number; }

    public int getNumber() { return number; }
    public void setNumber() { this.number += 1; }

    public Goods() {}
    // 생성자
    public Goods(String num, String name, double price, String detail) {
        super(num, name, detail);
        this.price = price;
    }

}
