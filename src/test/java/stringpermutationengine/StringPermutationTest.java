package stringpermutationengine;

import java.util.*;
import org.junit.Before;
import static org.junit.Assert.*;

public class StringPermutationTest
{
    StringPermutation stringPermutation;

    @Before
    public void setup()
    {
        stringPermutation = new StringPermutation();
    }

    @org.junit.Test
    public void testSamples()
    {
        HashMap<String, List<String>> sample = new HashMap<>();
        sample.put("a", new ArrayList<>(Arrays.asList("a")));
        sample.put("aa", new ArrayList<>(Arrays.asList("aa", "aa")));
        sample.put("ab", new ArrayList<>(Arrays.asList("ab", "ba")));
        sample.put("abc", new ArrayList<>(Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba")));
        sample.put("abcd", new ArrayList<>(Arrays.asList("abcd", "acbd", "bacd", "bcad", "cabd", "cbad",
                "dabc", "dacb", "dbac", "dbca", "dcab", "dcba", "cadb", "cbda", "abdc", "acdb", "badc",
                "bcda", "bdac", "bdca", "cdab", "cdba", "adbc", "adcb")));

        for (Map.Entry<String, List<String>> entry : sample.entrySet())
        {
            String input = entry.getKey();
            List<String> expectedOutput = entry.getValue();
            List<String> actualOutput = stringPermutation.getAllPermutations(input, false);

            assertEquals("The number of permutations doesn't" +
                    " match what we expect.", expectedOutput.size(), actualOutput.size());

            assertTrue("The actualOutput of permutations includes strings that are not " +
                    "in the expected solution", doesResultsMatchExpectedOutput(actualOutput, expectedOutput));
        }
    }

    @org.junit.Test
    public void testStringOfLengthThree()
    {
        List<String> actualOutput = stringPermutation.getAllPermutations("dog", true);
        List<String> expectedOutput = Arrays.asList("GOD", "OGD", "DOG", "GDO", "ODG", "DGO");
        assertTrue("The number of permutations doesn't" +
                "match what we expect.", actualOutput.size() == expectedOutput.size());

        assertTrue("The actualOutput of permutations includes strings that are not in the expected solution",
                doesResultsMatchExpectedOutput(actualOutput, expectedOutput));
    }

    @org.junit.Test
    public void testLowerCaseInputWhenWeExpectUppercaseResult()
    {
        List<String> actualOutput = stringPermutation.getAllPermutations("dOg", true);
        List<String> expectedOutput = Arrays.asList("GOD", "OGD", "DOG", "GDO", "ODG", "DGO");
        assertTrue("The number of permutations doesn't" +
                "match what we expect.", actualOutput.size() == expectedOutput.size());

        assertTrue("The list of permutations includes strings that are not in the expected solution",
                doesResultsMatchExpectedOutput(actualOutput, expectedOutput));
    }

    @org.junit.Test
    public void testNullInput()
    {
        try
        {
            List<String> list = stringPermutation.getAllPermutations(null);
            fail("An IllegalArgumentException Exception should have thrown.");
        }
        catch (IllegalArgumentException e)
        {

        }
    }

    @org.junit.Test
    public void testEmptyInput()
    {
        try
        {
            List<String> list = stringPermutation.getAllPermutations("");
            fail("An IllegalArgumentException Exception should have thrown.");
        }
        catch (IllegalArgumentException e)
        {

        }
    }

    @org.junit.Test
    public void testInputWithDuplicateChars()
    {
        String input = "aaaaaa";

        // generate the expected output
        List<String> output = new ArrayList<>();
        long i = 0;
        long numOfPerms = computeFactorial(input.length());
        while (i++ < numOfPerms)
        {
            output.add(input);
        }

        List<String> list = stringPermutation.getAllPermutations(input);
        assertTrue("Failed to generate all permutations for a String of duplicate characters",
                list.equals(output));
    }

    @org.junit.Test
    public void testNumberOfPermutationsForLongInput()
    {
        // We can increase the size of the input string. My laptop got really slow for longer strings.
        String input = "acde";

        // generate the expected output
        long numOfPerms = computeFactorial(input.length());

        List<String> list = stringPermutation.getAllPermutations(input);
        assertTrue("Failed to generate all permutations for the String",
                list.size() == numOfPerms);
    }

    /**
     * Helper method to verify actual output matches expected output.
     *
     * @param actualOutput
     * @param expectedOutput
     * @return
     */
    private boolean doesResultsMatchExpectedOutput(List<String> actualOutput, List<String> expectedOutput)
    {
        //We copy the list to a HashSet because we want to verify membership and lookup is effectively
        // O(1) for HashSet.
        // Having duplicate permutations will not cause an issue with HashSet.
        HashSet<String> setOfexpectedOutput = new HashSet<>(expectedOutput);
        for (String word : actualOutput)
        {
            if (!setOfexpectedOutput.contains(word))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to calculate the factorial for a given number.
     *
     * @param num       The input number.
     * @return          The factorial for num.
     */
    private long computeFactorial(int num)
    {
        if (num <= 2)
        {
            return num;
        }

        return num * computeFactorial(num - 1);
    }
}
