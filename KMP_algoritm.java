import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class KMP_algoritm {
    public static void main(String[] args) {
        String text, sample;
        Scanner input = new Scanner(System.in);

        System.out.println("Введите строку: ");
        text = input.nextLine();
        System.out.println("Введите подстроку для поиска: ");
        sample = input.nextLine();

        System.out.println("Позиция первого вхождения: " + Arrays.toString(KMPSearch(text, sample).toArray()));

    }
    

    static int[] prefixFunction(String sample) {
        int[] values = new int[sample.length()];
        for (int i = 1; i < sample.length(); i++) { // начинаем со второго символа, тк в первой ячейке всегда 0
            int j = 0;
            //пока не дошли до конца образца и если
            //символ в образце совпадает с символом начала образца (ищем себя же внутри себя)
            while (i + j < sample.length() && sample.charAt(j) == sample.charAt(i + j)) {
                values[i + j] = Math.max(values[i + j], j + 1); // записываем максимальное значение или позицию совпавшего символа внутри образца
                j++;
            }
        }
        return values;
    }

    public static ArrayList<Integer> KMPSearch(String text, String sample) {
        ArrayList<Integer> found = new ArrayList<>(); // массив для найденных вхождений
        // вычисляем префиксную функцию
        int[] prefixFunc = prefixFunction(sample);

        int i = 0, j = 0; // i - позиция внутри текста, j - внутри образца

        while (i < text.length()) {
            if (sample.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == sample.length()) { // если все символы образца совпали
                found.add(i - j); // записываем первый символ начала вхождения
                j = prefixFunc[j - 1];
            } else if (i < text.length() && sample.charAt(j) != text.charAt(i)) { // если нет совпадений, не дошли до конца текста
                if (j != 0) { // если не 1-й символ образца
                    j = prefixFunc[j - 1]; // возвращаем индекс образца на то, что находится в префиксной функции
                } else {
                    i = i + 1;
                }
            }
        }
        return found;
    }

//    static ArrayList<Integer> search(String text, String sample){
//        ArrayList<Integer> foundPosition = new ArrayList<>();
//        for (int i = 0; i < text.length(); i++){
//            int j = 0;
//            while (j < sample.length() && i+j < text.length() && sample.charAt(j) == text.charAt(i+j)){
//                j++;
//            }
//            if (j == sample.length()){
//                foundPosition.add(i);
//            }
//        }
//        return foundPosition;
//    }
}
