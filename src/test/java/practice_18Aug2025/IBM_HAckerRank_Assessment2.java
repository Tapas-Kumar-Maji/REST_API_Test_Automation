package practice_18Aug2025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class IBM_HAckerRank_Assessment2 {

    /**
     * Problem Restatement
     * 
     * You have an array of integers dataPoints.
     * You can repeatedly merge two smallest elements:
     * 
     * Cost of merging = sum of those two numbers.
     * That sum becomes a new element in the array.
     * Repeat until only one element remains.
     * Return total cost (sum of all merge costs).
     */

    /*
     * Example Walkthrough 
     * Input 3 20 30 40
     * 
     * Steps Merge 20 + 30 = 50 → cost = 50 → heap = [40, 50] Merge 40 + 50 = 90 →
     * cost = 90 → heap = [90]
     * 
     * Total cost = 50 + 90 = 140
     */

    /*
     * Input 3 30 10 20
     * 
     * Steps
     * Merge 10 + 20 = 30 → cost = 30 → heap = [30, 30]
     * Merge 30 + 30 = 60 → cost = 60 → heap = [60]
     * 
     * Total cost = 30 + 60 = 90
     */

    public static int getMinCost(List<Integer> dataPoints) {
	// Use PriorityQueue as a Min Heap
	PriorityQueue<Integer> pq = new PriorityQueue<>(dataPoints);

	int totalCost = 0;

	// Keep merging until only one element remains
	while (pq.size() > 1) {
	    int first = pq.poll(); // smallest
	    int second = pq.poll(); // second smallest

	    int merged = first + second;
	    totalCost += merged;
	    pq.add(merged);
	}

	return totalCost;
    }

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine().trim());

	List<Integer> dataPoints = new ArrayList<>();
	for (int i = 0; i < n; i++) {
	    dataPoints.add(Integer.parseInt(br.readLine().trim()));
	}

	int result = IBM_HAckerRank_Assessment2.getMinCost(dataPoints);
	System.out.println(result);
    }

}
