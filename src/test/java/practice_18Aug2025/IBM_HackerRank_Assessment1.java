package practice_18Aug2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IBM_HackerRank_Assessment1 {

    /**
     * Problem Summary
     * 
     * You need to implement a function findStopWords that takes:
     * String text: space-separated words int k: minimum number of occurrences
     * Returns: List<String> of words that occur at least k times, in the order of their first appearance.
     * 
     * Input the brown fox jumps over the brown dog and runs away to a brown house
     * 2
     * 
     * Output 
     * the 
     * brown
     */
    public static List<String> findStopWords(String text, int k) {
	String[] words = text.split(" ");

	// Count word frequencies
	Map<String, Integer> freq = new HashMap<>();
	for (String word : words) {
	    freq.put(word, freq.getOrDefault(word, 0) + 1);
	}

	// Collect stop words in order of first appearance
	List<String> result = new ArrayList<>();
	Set<String> seen = new HashSet<>();

	for (String word : words) {
	    if (!seen.contains(word) && freq.get(word) >= k) {
		result.add(word);
		seen.add(word);
	    }
	}

	return result;
    }

    public static void main(String[] args) throws IOException {
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	String text = bufferedReader.readLine();
	int k = Integer.parseInt(bufferedReader.readLine());

	List<String> result = IBM_HackerRank_Assessment1.findStopWords(text, k);

	for (String word : result) {
	    System.out.println(word);
	}

	bufferedReader.close();
    }
}
