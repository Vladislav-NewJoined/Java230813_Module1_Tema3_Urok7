import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task1_3_7_5 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                5. Реализуйте метод, который возвращает случайную цитату Уолтера Уайта

                Решение:\s""");

        String page = downloadWeBPage("https://text-Box.ru/quotes/source/Breaking-Bad");

        getReturnQuote(page);
    }

    private static void getReturnQuote(String page) {
        int quoteStart = page.indexOf("text__line__content__rus text__line--cell");
        int quoteEnd = page.indexOf("text__footer");
        String returnQuote = page.substring(quoteStart + 43, quoteEnd - 82);

        System.out.println("Возвращаемая цитата: ");
        System.out.println(returnQuote);
    }

    private static String downloadWeBPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader Br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = Br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();

    }
}
