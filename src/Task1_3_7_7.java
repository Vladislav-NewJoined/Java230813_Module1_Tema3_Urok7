import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class Task1_3_7_7 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
                Задание:\s
                7. Реализуйте метод, который возвращает explanation снимка дня NASA, в
                качестве параметра принимайте LocalDate - дату, на которую нужно брать
                снимок

                Решение:\s""");

        String localDate = String.valueOf(LocalDate.now());
        String pageNasaAsText = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=2019-10-14";
        int urlBeginAsText = pageNasaAsText.lastIndexOf("date=");
        String substringUrlAsText = pageNasaAsText.substring(urlBeginAsText + 5);
        String pageNasaAsText_NEW = pageNasaAsText.replace(substringUrlAsText, localDate);
        String pageNasa = downloadWeBPage(pageNasaAsText_NEW);

        int urlBegin = pageNasa.lastIndexOf("url");
        int urlEnd = pageNasa.lastIndexOf("}");
        String urlPhoto = pageNasa.substring(urlBegin + 6, urlEnd - 1);
        try (InputStream from = new URL(urlPhoto).openStream()) {
            Path to = Paths.get("photo.jpg");
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        }

        //  Реализуем метод getExplanation:
        getExplanation(localDate, pageNasa);
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

    private static void getExplanation(String localDate, String pageNasa) {
        System.out.println("Исходная дата, LocalDate: " + localDate);  //  Печатаем подставленную дату
        int explanationBegin = pageNasa.lastIndexOf("explanation");
        int explanationEnd = pageNasa.lastIndexOf("hdurl");
        String explanation = pageNasa.substring(explanationBegin + 14, explanationEnd - 3/* или 8, три - правильно */);
        System.out.println("Сохранена картинка за эту дату, " + localDate);
        System.out.println("Сохранено пояснение к фотографии, при реализации метода getExplanation: \n" + explanation);
    }
}