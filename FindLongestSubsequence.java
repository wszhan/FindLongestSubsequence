import java.util.stream.IntStream;

/**
 * @author Weisi Zhan
 * @create 2018-12-12 08:13
 **/
public class FindLongestSubsequence
{
    public static String longestSubsequence(String x, String y)
    {
        // String buffer as placeholder
        StringBuffer sf = new StringBuffer();

        // For the helper function
        int xStart = 0, yStart = 0;

        // Iterating through the strings to find common chars and append
        while (findNextCommon(x, y, xStart, yStart) != null)
        {
            // Find the common char indices
            int[] commonIndices = findNextCommon(x, y, xStart, yStart);

            // Append characters in common
            sf.append(x.charAt(commonIndices[0]));

            // Stopping condition
            // Increment for the helper function to find the next character in common
            xStart = commonIndices[0] + 1;
            yStart = commonIndices[1] + 1;
        }

        // Return the result string
        return sf.toString();
    }

    /**
     *
     * Helper method: to find the next character in common, starting from the previous
     * index plus 1.
     *
     * @param x
     * @param y
     * @param xStart
     * @param yStart
     * @return
     */
    public static int[] findNextCommon(String x, String y, int xStart, int yStart)
    {
//        System.out.println("Input xStart:yStart - " + xStart + ":" + yStart);
        if (xStart > x.length() - 1 || yStart > y.length() - 1) return null;

        int[] resultA = new int[2];
        int[] resultB = new int[2];

        boolean breakA = false;
        boolean breakB = false;

        // Two iterations needed to make sure the smaller indices where
        // common character occurs could be returned
        for (int i = xStart; i < x.length(); i++)
        {
            for (int j = yStart; j < y.length(); j++)
            {
                if (x.charAt(i) == y.charAt(j))
                {
                    //System.out.println("Result: " + i + " - " + j);
                    //return new int[]{i, j};
                    resultA[0] = i;
                    resultA[1] = j;
                    breakA = true;
                }
                if (breakA) break;
            }
            if (breakA) break;
        }
//        System.out.println("ResultA: " + resultA[0] + " - " + resultA[1]);

        for (int j = yStart; j < y.length(); j++)
        {
            for (int i = xStart; i < x.length(); i++)
            {
                if (x.charAt(i) == y.charAt(j))
                {
                    resultB[0] = i;
                    resultB[1] = j;
                    breakB = true;
                }
                if (breakB) break;
            }
            if (breakB) break;
        }

        // Make sure the smaller indices of common character is returned
        // rather than vice versa
        int sumA = IntStream.of(resultA).sum();
        int sumB = IntStream.of(resultB).sum();

        if (sumA <= sumB)
        {
            return resultA;
        }
        else
        {
            return resultB;
        }
    }

    /**
     * Unit test
     */
    public static void main(String[] args)
    {
        // Test cases
        String[][] testStrings = new String[5][2];

        // Test #1
        testStrings[0][0] = "hackers";
        testStrings[0][1] = "hackerqwerwerwers";

        // Test #2
        testStrings[1][0] = "nothingmatches";
        testStrings[1][1] = "thingches";

        // Test #3
        testStrings[2][0] = "abceeed";
        testStrings[2][1] = "abcd";

        // Test #4
        testStrings[3][0] = "xxxxxxxxStringxxxxxxxxAyyyyymatheskkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkB";
        testStrings[3][1] = "ppppppppStringppppppppApppppmatchesppppppppppppppB";

        // Test #5
        testStrings[4][0] = "ShouldMatch";
        testStrings[4][1] = "SxhxoxuxlxdxMxaxtxcxhxyyy";

        for (String[] testSet : testStrings)
        {
            String result = longestSubsequence(testSet[0], testSet[1]);
            System.out.println(
                    "Longest subsequence of" +
                            "\nString x: " + testSet[0] +
                            "\nString y: " + testSet[1] +
                            "\nis " + result +
                            "\n========================"
            );
        }
    }
}
