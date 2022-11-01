import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Яблоки", "Молоко", "Сыр", "Мука"};
        int[] prices = {60, 89, 80, 120, 90};

        System.out.println("Список возможных товаров для покупки: ");

        IntStream.range(0, products.length).mapToObj(i -> (i + 1) + ". " + products[i] + " "
                + prices[i] + " руб.").forEach(System.out::println);

        Basket basket = new Basket(prices, products);
        int sumProducts = 0;

        while (true) {
            System.out.println("Выберите товар и количество или введите \"end\": ");
            String inputString = scanner.nextLine();

            if (inputString.equals("end")) {
                break;
            }
            String[] count = inputString.split(" ");

            int productNumber = Integer.parseInt(count[0]);
            int productCount = Integer.parseInt(count[1]);
            int currentPrice = prices[productNumber - 1];
            sumProducts += currentPrice * productCount;

            basket.addToCart(productNumber - 1, productCount);
        }

        basket.saveBin(new File("basket.bin"));
        Basket basketNew = Basket.loadFromBinFile(new File("basket.bin"));

        basketNew.printCart();
    }
}