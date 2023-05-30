import java.awt.color.ICC_ColorSpace;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyMenu myMenu = new MyMenu();
        try {
            myMenu.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}