import java.io.*;
import java.util.*;

public class AnagramSolver {

    private AnagramSolver() {};

    /**
     * Input: name of text file (containing English words).
     * Output: a hashmap of lists of words that are anagrams.
     * @param filename
     * @return
     */
    public static HashMap<String, ArrayList<String>> anagrams(String filename) throws IOException {
        HashMap<String, ArrayList<String>> output = new HashMap<String, ArrayList<String>>();
        List<String> wordList = new ArrayList<String>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("words_alpha.txt"));
        String line = bufferedReader.readLine();
        wordList.add(line);
        while(line != null){
            line = bufferedReader.readLine();
            wordList.add(line);
        }
        String[] wordArray = wordList.toArray(new String[0]);
        bufferedReader.close();
        for(int i = 0; i < wordArray.length; i++){
            if(output.get(sortString(wordArray[i])) != null){
                ArrayList<String> anagramWords = new ArrayList<>();
                anagramWords.add(wordArray[i]);
                output.put(sortString(wordArray[i]), anagramWords);
            }else {
                if (!output.get(sortString(wordArray[i])).contains(wordArray[i])) {
                    ArrayList<String> temp = output.get(sortString(wordArray[i]));
                    temp.add(wordArray[i]);
                    output.remove(sortString(wordArray[i]));
                    output.put(sortString(wordArray[i]), temp);
                }
            }
        }
        return output;
    }

    public static String sortString(String input){
        char[] tempArray = input.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);

    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: largest list of words in hashmap that are anagrams.
     * @param anagrams
     * @return
     */
    public static ArrayList<String> mostFrequentAnagram(HashMap<String, ArrayList<String>> anagrams) {
        ArrayList<ArrayList<String>> compare = new ArrayList<>(anagrams.values());
        ArrayList<String> largest = compare.getFirst();
        for(int i = 0; i < compare.size(); i++){
            ArrayList<String> currentList = compare.get(i);
            if(largest.size() < currentList.size()){
                largest = currentList;
            }
        }
        return largest;

    }

    /**
     * Input: hashmap of lists of words that are anagrams.
     * Output: prints all key value pairs in the hashmap.
     * @param anagrams
     */
    public static void printKeyValuePairs(HashMap<String, ArrayList<String>> anagrams) {
        for(Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()){
            System.out.println("Key: " + entry.getKey());
            for(String s: entry.getValue()){
                System.out.println("Anagram values: " + s);
            }
        }
    }

}
