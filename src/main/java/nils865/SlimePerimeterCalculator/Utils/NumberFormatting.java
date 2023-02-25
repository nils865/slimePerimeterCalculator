package nils865.SlimePerimeterCalculator.Utils;

public class NumberFormatting {
    public static String beautifyNumber(int number) {
        char[] digits = String.valueOf(number).toCharArray();

        String formattedNumber = "";

        int digitCounter = 0;

        for (int i = digits.length - 1; i >= 0; i--) {
            digitCounter++;

            formattedNumber += digits[i];

            if (digitCounter >= 3) {
                formattedNumber += '.';
                digitCounter = 0;
            }
        }

        return reverseString(formattedNumber);
    }

    public static String reverseString(String s) {
        String newString = "";

        for (int i = s.length() - 1; i >= 0; i--) {
            newString += s.toCharArray()[i];
        }

        return newString;
    }
}
