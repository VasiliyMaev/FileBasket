import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Basket {

    protected int[] arrPrice;
    protected String[] arrNameProduct;
    protected int[] count;

    public Basket(int[] arrPrice, String[] arrNameProduct) {
        this.arrPrice = arrPrice;
        this.arrNameProduct = arrNameProduct;
        this.count = new int[arrPrice.length];
    }

    public void addToCart(int productNum, int amount) {
        count[productNum] += amount;
    }

    public void printCart() {
        System.out.println("Итого в корзине: ");
        IntStream.range(0, count.length)
                .filter(i -> count[i] != 0)
                .mapToObj(i -> arrNameProduct[i] + " " + count[i]
                        + " шт. Общей суммой на " + (arrPrice[i] * count[i]) + " руб.")
                .forEach(System.out::println);
    }

    public void saveTxt(File textFile) throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(textFile)) {

            for (String s : arrNameProduct) out.print(s + "   ");
            out.println();

            for (int i : arrPrice) out.print(i + "      ");
            out.println();

            for (int j : count) out.print(j + "       ");
            out.println();
        }

    }

    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream(textFile))) {
            String[] product = scanner.nextLine().split("   ");
            int[] price = Arrays.stream(scanner.nextLine().split("      ")).mapToInt(Integer::parseInt).toArray();
            int[] count = Arrays.stream(scanner.nextLine().split("       ")).mapToInt(Integer::parseInt).toArray();

            Basket basket = new Basket(price, product);
            basket.count = count;
            return basket;
        }
    }

}
