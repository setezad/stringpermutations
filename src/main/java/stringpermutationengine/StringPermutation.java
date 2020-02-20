package stringpermutationengine;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation
{
    // If a string has duplicate characters, we will have duplicate permutations.
    // If we use a HashSet instead of the list, we can return distinct permutations.
    /**
     * List of permutations for a given string.
     */
    private List<String> listOfPermutations;

    // Users can get the list of permutations in uppercase or lowercase.

    /**
     * Returns all the permutations for a given string.
     *
     * @param input         Input string.
     * @param upperCase     True, if we want the output in uppercase. False, otherwise.
     *
     * @return  all the permutations for a given string.
     * @throws IllegalArgumentException
     */
    public List<String> getAllPermutations(String input, boolean upperCase) throws IllegalArgumentException
    {
        // To handle invalid input, we can throw IllegalArgumentException or return null or empty list.
        // I added an upper bound to limit the scope since a string of size n has n! permutations.
        if (input == null || input.isEmpty() || input.length() > 10)
        {
            throw new IllegalArgumentException("Input string should at least contain one character");
        }

        listOfPermutations = new ArrayList<String>();
        getPermutations(input, "", upperCase);

        return listOfPermutations;
    }

    // Method overloading to provide different method signatures for users
    // If users don't specify the upper or lower case, the output will be in lowercase.
    public List<String> getAllPermutations(String input) throws IllegalArgumentException
    {
        return getAllPermutations(input, false);
    }

    /**
     * Helper method that generates all the permutations.
     * The runtime of this algorithm is O(n * n!) where n is the size of the input string.
     *
     * @param str           Input string.
     * @param prefix        String representing the permutation, built character by character in recursion.
     * @param upperCase     Flag indicating where we expect the output in uppercase or not.
     *                      True, if we want the output in uppercase. False, otherwise.
     */
    private void getPermutations(String str, String prefix, boolean upperCase)
    {
        if (str.isEmpty())
        {
            listOfPermutations.add((upperCase) ? prefix.toUpperCase() : prefix.toLowerCase());
            return;
        }

        for (int i = 0; i < str.length(); i++)
        {
            String strSub = str.substring(0, i) + str.substring(i + 1);
            getPermutations(strSub, prefix + (str.charAt(i)), upperCase);
        }
    }
}
