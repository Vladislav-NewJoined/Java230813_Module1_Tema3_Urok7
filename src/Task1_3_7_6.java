import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Task1_3_7_6 {
    public static void main(String[] args) throws IOException {
        System.out.println("""
            Задание:\s
            6. Реализуйте метод, который выводит explanation сегодняшнего снимка дня NASA

            Решение:\s""");

        String pageNasa = downloadWeBPage("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");

        System.out.print("Вариант 1. С методом getExplanation. Пояснение к фотографии: \n");
        getExplanation();
        System.out.println();  //  перенос строки

        System.out.print("Вариант 2. С методом getExplanation2. Пояснение к фотографии: \n");
        String explanation = getExplanation2(pageNasa);
        System.out.println(explanation);
        System.out.println();  //  перенос строки

        System.out.print("Вариант 3. С методом getExplanation3. Пояснение к фотографии:\n"); getExplanation3(explanation);
    }

    static void getExplanation() throws IOException {
        String pageNasa = downloadWeBPage("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        int explanationBegin = pageNasa.lastIndexOf("explanation");
        int explanationEnd = pageNasa.lastIndexOf("hdurl");
        String explanation = pageNasa.substring(explanationBegin + 14, explanationEnd - 3);
        System.out.println(explanation);
    }

    static String getExplanation2(String pageNasa) {
        int explanationBegin = pageNasa.lastIndexOf("explanation");
        int explanationEnd = pageNasa.lastIndexOf("hdurl");
        String explanation = pageNasa.substring(explanationBegin + 14, explanationEnd - 3);

        return explanation;
    }

    static void getExplanation3(String explanation){
        System.out.println(explanation);
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