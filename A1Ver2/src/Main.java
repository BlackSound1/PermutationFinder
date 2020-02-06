import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // The initial short String and the long String
        String shortString = "abc";
        String longString = "abcd";

        PrintWriter writer = null; // Allow writing to file

        try {
            // Try to create a PrintWriter, catch if this fails
            writer = new PrintWriter(new BufferedWriter(new FileWriter("A1Ver2/out.txt",true)));
        }catch (IOException e){
            System.out.println("Couldn't write to file");
        }

        long beginLoopTime = System.nanoTime(); // Gets the initial time that the final time will compare against

        // MAIN LOOP
        do {
        writer.println("The shortStr size is:" + shortString.length());
            long startTime = System.nanoTime(); // Sets the initial time for this individual run of the algorithm
            permu2(shortString, longString, writer); // RUNS MAIN ALGORITHM
            long endTime = System.nanoTime(); // Sets the final time for this individual run of the algorithm

            long totalTime = endTime - startTime; // Computes total time taken for a single iteration

            writer.println("This took: " + totalTime + "ns\n");

            shortString += shortString; // Appends the short String to itself, increasing its length by 5
            longString += shortString; // Appends the short String to the long String. If longStr is initially longer
                                       // than the shortStr, then it will always remain so
        } while (shortString.length() <= 200); // repeats until the shortStr is about 200 characters long

        long endLoopTime = System.nanoTime(); // Gets the final time to compute the total time
        long totalLoopTime = endLoopTime - beginLoopTime; // Computes total time taken (all iterations)

        writer.println("Total time taken for all loops: " + totalLoopTime +"ns");
        writer.close(); // Closes PrintWriter
    }

    /*private static void permu2(String shortStr,String longStr){
        for (int i = 0; i < longString.length(); i++){
            miniPermu(shortStr,longStr,i,"");

        }
    }

    private static String miniPermu(String sh, String lo, int index, String matchString){
        if (sh.length() == 0){
            return matchString;
        }else if (contains(shortString,longString.charAt(index))){
            matchString = String.valueOf(longString.charAt(index));

            String newShort = shortString.replaceFirst(
                    String.valueOf(longString.charAt(index)),"");
            String newLong = lo.substring(1);
            miniPermu(newShort,newLong,++index,matchString);
        }else{
            return "";
        }
        return matchString;
    }

    private static void permu2(String shortStr, String longStr,int index, String matchString){
        //String workingLongStr = longStr.substring(index);
        if (matchString.length() == shortString.length()){
            System.out.println(matchString);
            System.out.println("Found a match!");
            //permu2(shortString,longString,index,"");
        }else if (contains(shortStr,longStr.charAt(index))){
            matchString += longStr.charAt(index);
            String newShort = shortStr.replaceFirst(String.valueOf(longStr.charAt(index)),"");
            String newLong = longStr.substring(1);
            permu2(newShort,newLong,index,matchString);
        }else {
            matchString = "";
            String newLong = longStr.substring(1);
            permu2(shortString,newLong,index,matchString);
        }
    }*/

    private static void permu2(String shortStr,String longStr,PrintWriter writer){
        // These lines sort the incoming sortStr.
        // The goal is to find a single (objective) string that contains all
        // letters a hypothetical permutation might have, that we can test
        // a similarly sorted portion of the longStr against.
        char[] characters = shortStr.toCharArray();
        Arrays.sort(characters); // According to Java documentation, is O(n log(n))
        String sortedShortStr = new String(characters);

        // Moves through the longStr in 'groups' that are as long as the shortStr
        for (int i = 0; i <= longStr.length()-shortStr.length(); i++){
            String longStrPortion = longStr.substring(i,sortedShortStr.length()+i); // Creates substring of longStr
                                                                                    // as long as the shortStr

            // Performs same task as above but for the portion of the longStr, this time
            char[] longStrPortionCharacters = longStrPortion.toCharArray();
            Arrays.sort(longStrPortionCharacters);
            String sortedLongStringPortion = new String(longStrPortionCharacters);

            // If the 2 sorted Strings are equal, there is a permutation there!
            // The permutation is the unsorted longStrPortion
            if (sortedShortStr.equals(sortedLongStringPortion)){
                writer.println("Found a match! There is the permutation " + longStrPortion +
                        " of the shortStr " + shortStr + "\nin the longStr " + longStr + " at index " + i);
            }
        }
    }

    /*private static boolean contains(String shortS,char character){
        //char[] longStringCharacters = longS.toCharArray();
        char[] shortStringCharacters = shortS.toCharArray();

        for (char shortChar: shortStringCharacters) {
            if (shortChar == character) return true;
        }
        return false;
    }*/
}
