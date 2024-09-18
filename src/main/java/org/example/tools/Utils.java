package org.example.tools;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class containing various utility methods for WebDriver operations.
 *
 * @author Moustafa Elbaih
 */
public class Utils
{





    public static boolean isSorted(List<String> list, String orderType)
    {
        boolean sorted = true;

        for (int i = 1; i < list.size(); i++)
        {
            if (orderType.equalsIgnoreCase("az"))
                if (list.get(i - 1).compareTo(list.get(i)) > 0) sorted = false;
            if (orderType.equalsIgnoreCase("za"))
                if (list.get(i - 1).compareTo(list.get(i)) < 0) sorted = false;

        }

        return sorted;
    }

    public static List<Float> extractFloats(String input)
    {
        List<Float> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?"); // Regex to match sequences of digits
        Matcher matcher = pattern.matcher(input);

        while (matcher.find())
        {
            // Convert the matched sequence to an integer and add to the list
            result.add(Float.parseFloat(matcher.group()));
        }

        return result;
    }
}
