import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int reverseNumber = 0;

        int i = 1;
        int j = 100;
        while (i < 1000) {
            int digit = number / i % 10;
            reverseNumber += digit * j;

            i *= 10;
            j /= 10;
        }
        System.out.println(reverseNumber);
    }


}
