public class Task1_3_7_1 {
    public static void main(String[] args) {
        System.out.println("""
                Задание:\s
                1. Реализуйте метод, который находит из трех чисел то, которое делится на 2
                   остальных; или возвращает -1, если такого нет

                Решение:\s""");

        System.out.println("Вводим три исходных целых числа:");
        int num1 = 3;
        int num2 = 15;
        int num3 = 5;
        System.out.println("Первое число: " + num1 + ", второе число: " + num2 + ", третье число: " + num3);
        System.out.println();  //  перенос строки
        System.out.println("Реализуем метод getResult: ");
        getResult(num1, num2, num3);
    }

    private static void getResult(int num1, int num2, int num3) {
        String negativeResult = "-1";
        String result;

        if (num1 % num2 == 0 && num1 % num3 == 0) {
            result = "Искомое число - первое число, " + num1;
            System.out.println("Результат: " + result);
            System.exit(0);

        } else if (num2 % num1 == 0 && num2 % num3 == 0) {
            result = "Искомое число - второе число, " + num2;
            System.out.println("Результат: " + result);
            System.exit(0);
        } else if (num3 % num1 == 0 && num3 % num2 == 0) {
            result = "Искомое число - третье число, " + num3;
            System.out.println("Результат: " + result);
            System.exit(0);
        } else {
            result = negativeResult;
            System.out.println("Результат: " + result);
        }
    }
}