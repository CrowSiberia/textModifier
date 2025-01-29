import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        System.out.print(textModifier());
    }

    public static String textModifier () {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            list.add(i, String.valueOf(text.charAt(i)));
        }

        for (int i = 0; i < list.size(); i++) {
            // a. В тексте между словами присутствует несколько пробелов подряд, надо оставить только один из них.
            if(list.get(i).equals(" ") && list.get(i - 1).equals(" ")) {
                list.remove(i);
                i--;
            }
            // b. В тексте присутствует знак минус (-), символ слева от этого знака поменять местами с символом, справа от этого знака.
            // После смены символов местами, знак минус (-) надо удалить
            if(list.get(i).equals("-")) {
                Collections.swap(list, i - 1, i + 1);
                list.remove(i);
                i--;
            }
            // c. В тексте присутствует знак плюс (+), заменить его на восклицательный знак (!).
            if(list.get(i).equals("+")) {
                list.set(i, "!");
                i--;
            }
        }
        // d. В тексте могут присутствовать цифры (от 0 до 9). Необходимо посчитать их сумму и удалить из текста.
        // Сумму цифр нужно добавить в конец результирующей строки через пробел (пробел должен стоять перед суммой цифр).
        int sum = 0;
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (Character.isDigit(element.charAt(0))) {
                sum += Character.getNumericValue(element.charAt(0));
                iterator.remove();
            }
        }

        text = String.join("", list);
        if(sum > 0) {
            text = text + " " + sum;
        }

        return text;
    }
}