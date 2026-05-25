package kaminruslan;

public class Main {
    public static void main(String[] args) {

        //Арифметические операции над двумя int
        int a = 55;
        int b = 6;

        System.out.println(a + " + " + b + " = " + (a + b));
        System.out.println(a + " - " + b + " = " + (a - b));
        System.out.println(a + " * " + b + " = " + (a * b));
        System.out.println(a + " / " + b + " = " + (a / b));
        System.out.println(a + " % " + b + " = " + (a % b));


        // 1) Арифметические операции над int и double
        double c = 1.5;
        double result = (a + b) * c - 4.0 / 2 + b;

        System.out.println("1) Арифметические операции над int и double:");
        System.out.println("c = " + c);
        System.out.println("result = (a + b) * c - 4.0 / 2 + b = " + result);
        System.out.println();


        // 2) Логические операции
        System.out.println("2) Логические операции:");
        System.out.println("a < b  = " + (a < b));
        System.out.println("a > b  = " + (a > b));
        System.out.println("a >= b = " + (a >= b));
        System.out.println("a <= b = " + (a <= b));
        System.out.println();

        // 3) Диапазоны вещественных чисел
        System.out.println("=== 3) Диапазоны float и double ===");
        System.out.println("float:");
        System.out.println("  Максимум: " + Float.MAX_VALUE);
        System.out.println("  Минимум: " + Float.MIN_VALUE);
        System.out.println("double:");
        System.out.println("  Максимум: " + Double.MAX_VALUE);
        System.out.println("  Минимум: " + Double.MIN_VALUE);



        // 4) Переполнение
        System.out.println("=== 4) Переполнение ===");
        int maxInt = Integer.MAX_VALUE;
        System.out.println("Максимальный int: " + maxInt);
        System.out.println("maxInt + 1 = " + (maxInt + 1));

        int minInt = Integer.MIN_VALUE;
        System.out.println("Минимальный int: " + minInt);
        System.out.println("minInt - 1 = " + (minInt - 1));

    }
}
