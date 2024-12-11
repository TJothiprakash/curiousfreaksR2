package strings;
import java.util.*;
public class RomantoDecimal { public int romanToDecimal(String s) {
        // Define the Roman numeral values
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int total = 0;
        int prevValue = 0;

        // Process each character from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(s.charAt(i));

            // Apply subtractive rule if necessary
            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                total += currentValue;
            }

            prevValue = currentValue;
        }

        return total;
    }

    public static void main(String[] args) {
        RomantoDecimal converter = new RomantoDecimal();
        System.out.println(converter.romanToDecimal("III"));  // Output: 3
        System.out.println(converter.romanToDecimal("IV"));   // Output: 4
        System.out.println(converter.romanToDecimal("IX"));   // Output: 9
        System.out.println(converter.romanToDecimal("LVIII")); // Output: 58
        System.out.println(converter.romanToDecimal("MCMXCIV")); // Output: 1994
    }
}
