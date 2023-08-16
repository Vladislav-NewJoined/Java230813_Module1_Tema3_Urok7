import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Task1_3_7_8 {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("""
            Задание:\s
            8. Реализуйте метод, который принимает два LocalDate, и сохраняет все
               снимки дня NASA в указанный промежуток

            Решение:\s""");

        Scanner scanner1 = new Scanner (System.in);
        System.out.print("Введите дату localDate1, в формате \"yyyy-MM-dd\", например 2019-10-14: ");
        String localDate1 = scanner1.nextLine();

        Scanner scanner2 = new Scanner (System.in);
        System.out.print("Введите более позднюю дату localDate2, в формате \"yyyy-MM-dd\", например 2019-10-18: ");
        String localDate2 = scanner2.nextLine();
        String localDateCount = localDate1;
        System.out.println(); //  перенос строки

        System.out.println("По результатам метода getExplanationsInPeriod сохранены снимки за указанный период");

        getExplanationsInPeriod(localDate1, localDate2, localDateCount);
    }

    private static void getExplanationsInPeriod(String localDate1, String localDate2, String localDateCount) throws ParseException, IOException {
        int i = 0;
        while (!localDateCount.equals(localDate2)) {
            System.out.println("\nИТЕРАЦИЯ " + (i + 1));
            localDateCount = localDate1;

            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            final Date date = format.parse(localDate1);
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            String pageNasaAsText = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=2019-10-14";
            int urlBeginAsText = pageNasaAsText.lastIndexOf("date=");
            String substringUrlAsText = pageNasaAsText.substring(urlBeginAsText + 5);
            String pageNasaAsText_NEW = pageNasaAsText.replace(substringUrlAsText, localDate1);

            String pageNasa = downloadWeBPage(pageNasaAsText_NEW); //  ВОТ ЗДЕСЬ ЗАДАЕТСЯ ДАТА
            int urlBegin = pageNasa.lastIndexOf("url");
            int urlEnd = pageNasa.lastIndexOf("}");
            String urlPhoto = pageNasa.substring(urlBegin + 6, urlEnd - 1);
            try (InputStream from = new URL(urlPhoto).openStream()) {
                Files.copy(from, Paths.get("photo" + (i + 1) + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Сохранено photo" + (i + 1));
            }

            int explanationBegin = pageNasa.lastIndexOf("explanation");
            int explanationEnd = pageNasa.lastIndexOf("hdurl");
            String explanation = pageNasa.substring(explanationBegin + 14, explanationEnd - 3);
            System.out.println("Пояснение к фотографии: \n" + explanation);

            localDate1 = format.format(calendar.getTime());
            i++;
        }
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