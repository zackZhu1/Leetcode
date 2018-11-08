package Graph;
import java.util.*;

public class Graph_1024_07 {
	// solution1: using hashmap
	// time: O(n)  space: O(n)
	public static boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
		
		int count = 0;
		for (char key : map.keySet()) {
			count += map.get(key) % 2;
		}
		return count <= 1;
    }
	
	// solution2: using array
	// time: O(n) space: O(1)
	public static boolean canPermutePalindrome2(String s) {
		int[] map = new int[128];
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i)]++;
		}
		
		int count = 0;
		// add an early return
		for (int i = 0; i < map.length && count <= 1; i++) {
			count += map[i] % 2;
		}
		return count <= 1;
	}
	
	// solution3: single pass
	public static boolean canPermutePalindrome3(String s) {
		int[] map = new int[128];
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i)]++;
			if (map[s.charAt(i)] % 2 == 0) {
				count--;
			} else {
				count++;
			}
		}
		return count <= 1;
	}
	
	public static void main(String[] args) {
		char ch = 'A';
		int i = (int) ch;
		System.out.println(i);
	}
}
