package Graph;
import java.util.*;

public class Num03 {
	public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = getDict(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        
        int level = 1;
        if (endWord.equals(beginWord)) {
        	return level;
        }
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		String cur = queue.poll();
        		System.out.println("expand node: " + cur);
                List<String> neis = findAllNeighbors(cur, dict);
                for (String nei : neis) {
                	System.out.println("generate node: " + nei);
                    if (visited.contains(nei)) continue;
                    visited.add(nei);
                    if (nei.equals(endWord)) {
                        return level + 1;
                    }
                    queue.offer(nei);
                }
        	}
        	level++;
        }
        return 0;
    }
    
    private static Set<String> getDict(List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        return set;
    }
    
    private static List<String> findAllNeighbors(String cur, Set<String> dict) {
        List<String> neis = new ArrayList<>();
        for (String word : dict) {
            if (isValid(word, cur)) {
                neis.add(word);
            }
        }
        return neis;
    }
    
    private static boolean isValid(String word, String cur) {
        int p1 = 0, p2 = 0;
        int diff = 0;
        while (p1 < word.length() && p2 < cur.length()) {
            if (word.charAt(p1) != cur.charAt(p2)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
            p1++;
            p2++;
        }
        return diff == 1;
    }
    
    public static void main(String[] args) {
    	List<String> wordList = new ArrayList<>();
    	wordList.add("hot");
    	wordList.add("dot");
    	wordList.add("dog");
    	wordList.add("lot");
    	wordList.add("log");
    	wordList.add("cog");
    	int length = ladderLength("hit", "cog", wordList);
//    	wordList.add("a");
//    	wordList.add("b");
//    	wordList.add("c");
//    	int length = ladderLength("a", "c", wordList);
    	System.out.println(length);
    }
}
