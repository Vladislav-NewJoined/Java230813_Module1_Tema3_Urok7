import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class Task1_3_7_2 {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            2. Реализуйте метод, который из двух массивов строк собирает третий, в
               котором чередуются элементы первых двух

            Решение:\s""");

        System.out.println("Шаг 1.   Создаем два исходных строковых массива (состоящих из слов исходных строк):");
        getCombinedArray();
    }

    private static void getCombinedArray() {
        String str1 = "В теории, теория и практика неразделимы.";
        String str2 = "Насколько проще было бы писать программы, если бы не заказчики.";
        System.out.println("Шаг 2.1. Исходная строка 1: " + str1);
        System.out.println("Шаг 2.2. Исходная строка 2: " + str2);

        List<String> str1Array = new ArrayList<>();
        BreakIterator breakIterator1 = BreakIterator.getWordInstance();
        breakIterator1.setText(str1);
        int lastIndex1 = breakIterator1.first();
        while (BreakIterator.DONE != lastIndex1) {
            int firstIndex = lastIndex1;
            lastIndex1 = breakIterator1.next();
            if (lastIndex1 != BreakIterator.DONE && Character.isLetterOrDigit(str1.charAt(firstIndex))) {
                str1Array.add(str1.substring(firstIndex, lastIndex1));
            }
        }
        System.out.println("Шаг 3.1. Исходный массив 1: " + str1Array);

        List<String> str2Array = new ArrayList<>();
        BreakIterator breakIterator2 = BreakIterator.getWordInstance();
        breakIterator2.setText(str2);
        int lastIndex2 = breakIterator2.first();
        while (BreakIterator.DONE != lastIndex2) {
            int firstIndex = lastIndex2;
            lastIndex2 = breakIterator2.next();
            if (lastIndex2 != BreakIterator.DONE && Character.isLetterOrDigit(str2.charAt(firstIndex))) {
                str2Array.add(str2.substring(firstIndex, lastIndex2));
            }
        }
        System.out.println("Шаг 3.2. Исходный массив 2: " + str2Array);

        List<String> combinedArray = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i < str1Array.size() && i < str2Array.size()) {
                if (i % 2 == 0) {
                    combinedArray.add(str1Array.get(i));
                } else {
                    combinedArray.add(str2Array.get(i));
                }
            }
        }
        System.out.println("Шаг 4.   Результат: " + combinedArray);
    }
}
