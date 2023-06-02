public class Goods extends Menu{
    private double price;          // Small 메뉴의 가격
    private double price2;         // Regular 메뉴의 가격
    private double totalp;         // Small 메뉴 가격의 총합
    private double totalp2;        // Regular 메뉴 가격의 총합
    private int number;            // Small 메뉴의 개수
    private int number2;           // Regular 메뉴의 개수
    private int number_total = 0;  // Small 메뉴의 총 개수
    private int number2_total = 0; // Regular 메뉴의 총 개

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
    public String getName2() { return super.getName()+"(Regular)"; }
    @Override
    public String getDetail() { return super.getDetail(); }           // 메뉴 설명
    @Override
    public String getNum() { return super.getNum(); }                 // 메뉴 번호

    public double getTotalp() {                                       // 주문한 Small 사이즈 메뉴의 총 합을 저장하기 위한 메소드
        this.totalp = price * number_total;
        return this.totalp;
    }
    public double getTotalp2() {                                      // 주문한 Regular 메뉴의 총 합을 저장하기 위한 메소드 (array에 넣어줄 값)
        this.totalp2 = price2 * number2_total;
        return this.totalp2;
    }

    public int getNumber() { return this.number; }                    // Small 메뉴 개수에 접근할 메소드
    public int getNumber2() { return this.number2; }                  // Regular 메뉴 개수에 접근할 메소드
    public void setNumber1(int j) { this.number = j; }                // Small 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    public void setNumber2(int i) { this.number2 = i; }               // Regular 메뉴 개수를 수정할 메소드 (같은 메뉴를 추가하면 값을 하나씩 늘리도록)
    public void setTotalNum1() {this.number_total = 0;}
    public void setTotalNum2() {this.number2_total = 0;}
    public void set_Number1(int ii) { this.number_total += ii; }      // Small 메뉴 개수의 총 합을 수정할 메소드
    public void set_Number2(int jj) { this.number2_total += jj; }     // Regular 메뉴 개수의 총 합을 수정할 메소드

    // 기본 생성자
    public Goods() {}
    public Goods(Goods g) {
        super(g.getNum(), g.getName(), g.getDetail());
        this.price = g.price;
        this.price2 = g.getPrice2();
        this.number = g.getNumber();
        this.number2 = g.getNumber2();
        this.number_total = g.getNumber_total();
        this.number2_total = g.getNumber2_total();
        this.totalp = g.getTotalp();
        this.totalp2 = g.getTotalp2();
    }
    // 생성자
    public Goods(String num, String name, double price, String detail) {
        super(num, name, detail);
        this.price = price;
    }

} // Goods.java
