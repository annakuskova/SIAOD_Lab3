import java.util.Scanner;
import java.util.TreeMap;

public class BM_algorithm {
    public static void main(String[] args) {
        String text, sample;
        Scanner input = new Scanner(System.in);

        System.out.println("Введите строку: ");
        text = input.nextLine();
        System.out.println("Введите подстроку для поиска: ");
        sample = input.nextLine();

        BM_algorithm(text, sample);
    }


    static void BM_algorithm(String text, String sample) {
        int textLen = text.length();
        int sampleLen = sample.length();

        TreeMap<Character, Integer> offsetTable = new TreeMap<Character, Integer>(); // таблица со значениями символов образца

        for (int i = 0; i <= 255; i++) {
            offsetTable.put((char) i, sampleLen);
        }
        for (int i = 0; i < sampleLen - 1; i++) {
            offsetTable.put(sample.charAt(i), sampleLen - i - 1);// значение каждого символа образца равно
            System.out.println(sample.charAt(i) + "->" + (sampleLen - i - 1)); // удалению его от конца образца
        }

        int i = sampleLen - 1;
        int j = i;
        int k = i;

        while (j >= 0 && i <= textLen - 1) {
            j = sampleLen - 1;
            k = i;
            //пока символ в тексте равен символу в образце, смещаемся влево на 1 для дальнейшей проверки
            while (j >= 0 && text.charAt(k) == sample.charAt(j)) {
                k -= 1;
                j -= 1;
            }
            i += offsetTable.get(text.charAt(i));
        }
        if (k >= textLen - sampleLen) {
            System.out.println("Не найдено");
        } else {
            System.out.println("Позиция первого вхождения: " + (k + 1));
        }
    }
}

