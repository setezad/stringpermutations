import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import stringpermutationengine.StringPermutation;

public class Main {

    // The keyword used to stop reading data from console.
    // The application accepts input strings until we reach this keyword.
    private static final String EXIT_KEYWORD = "STOP";

    public static void main(String[] args) {

        //read input from user
        String info = "Enter a valid string (String should not be null or empty).\n " +
                "You can enter multiple input strings on each line. When you are done. enter \"STOP\"\n" +
                "If the entered word doesn't exactly match the stop keyword, it will be considered as an input.";

        System.out.println(info);
        Scanner scanner = new Scanner(System.in);
        List<String> listOfInputs = new ArrayList<String>();
        StringBuilder sb;
        while (scanner.hasNext())
        {
            sb = new StringBuilder();
            sb.append(scanner.nextLine());
            if (sb.toString().equals(EXIT_KEYWORD))
            {
                break;
            }
            listOfInputs.add(sb.toString());
        }

        System.out.println("We have " + listOfInputs.size() + " input strings");

        if (listOfInputs.size() < 1)
        {
            return;
        }
        StringPermutation solution = new StringPermutation();
        listOfInputs.forEach(input -> {
            System.out.println(solution.getAllPermutations(input));
        });
    }
}
