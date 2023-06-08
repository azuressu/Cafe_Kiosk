public class Goods extends Menu{
    private double price;          // Small 메뉴의 가격
    private double priceR;         // Regular 메뉴의 가격
    private double totalp;         // 메뉴 가격의 총합
    private int number;            // Small 메뉴의 개수
    private int numberR;           // Regular 메뉴의 개수
    private int number_total = 0;  // Small 메뉴의 총 개수
    private int numberR_total = 0; // Regular 메뉴의 총 개수

    public int getNumber_total() { return number_total; }
    public int getNumber2_total() { return numberR_total; }
    public double getPrice() { return price; }
    public double getPriceR() {     // 메뉴 총 합계
        this.priceR = price + 0.5;
        return priceR;
    }
    public String getNameR() { return super.getName()+"(Regular)"; }
    public double getTotalp() {                                       // 주문한 Small 사이즈 메뉴의 총 합을 저장하기 위한 메소드
        this.totalp = (price * number_total) + (priceR * numberR_total);
        return this.totalp;
    }
    public int getNumber() { return this.number; }                    // Small 메뉴 개수에 접근할 메소드
    public int getNumber2() { return this.numberR; }                  // Regular 메뉴 개수에 접근할 메소드
    public void setNumber1(int j) { this.number = j; }                // Small 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    public void setNumber2(int i) { this.numberR = i; }               // Regular 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    public void setTotalNum1() {this.number_total = 0;}
    public void setTotalNum2() {this.numberR_total = 0;}
    public void set_Number1(int ii) { this.number_total += ii; }      // Small 메뉴 개수의 총 합을 수정할 메소드
    public void set_Number2(int jj) { this.numberR_total += jj; }     // Regular 메뉴 개수의 총 합을 수정할 메소드

    // 기본 생성자
    public Goods() {}
    // Goods 아이템을 새로 담아줄 Goods 생성자
    public Goods(Goods g) {
        super(g.getNum(), g.getName(), g.getDetail());
        this.price = g.price;
        this.priceR = g.getPriceR();
        this.number = g.getNumber();
        this.numberR = g.getNumber2();
        this.number_total = g.getNumber_total();
        this.numberR_total = g.getNumber2_total();
        this.totalp = g.getTotalp();
    }
    // 생성자
    public Goods(String num, String name, double price, String detail) {
        super(num, name, detail);
        this.price = price;
    }

} // Goods.java
