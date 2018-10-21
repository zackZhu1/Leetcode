package Graph;
import java.util.*;

// bi-directional BFS1
public class Num04 {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = getDict(wordList);
        Map<String, List<String>> prevMap = bfs(beginWord, endWord, dict);
        if (prevMap.size() == 0) {
            return new ArrayList<>();
        }
        return allShortestPaths(prevMap, endWord);
    }    
    
    private Map<String, List<String>> bfs(String beginWord, String endWord, Set<String> dict) {
        Map<String, List<String>> prevMap = new HashMap<>();
        Map<String, Integer> levels = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        
        queue.offer(beginWord);
        prevMap.put(beginWord, new ArrayList<>());
        levels.put(beginWord, 0);
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> neis = findAllNeighbors(cur, dict);
                for (String nei : neis) {
                    if (!levels.containsKey(nei)) {
                        levels.put(nei, level + 1);
                        prevMap.put(nei, new ArrayList<>());
                        prevMap.get(nei).add(cur);
                        queue.offer(nei);
                    } else if (level + 1 == levels.get(nei)) {
                        prevMap.get(nei).add(cur);
                    }
                }
            } 
            
            if (levels.containsKey(endWord)) {
                return prevMap;
            }
            
            level++;
        }
        return new HashMap<>();
    }
    
    private List<List<String>> allShortestPaths(Map<String, List<String>> prevMap, String endWord)  {
        List<List<String>> allShortestPaths = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(prevMap, endWord, path, allShortestPaths);
        return allShortestPaths;
    }
    
    private void dfs(Map<String, List<String>> prevMap, String cur, List<String> path, List<List<String>> allPaths) {
        path.add(cur);
        if (prevMap.get(cur).isEmpty()) {
            List<String> curPath = new ArrayList<>(path);
            Collections.reverse(curPath);
            allPaths.add(curPath);
        } else {
            for (String nei : prevMap.get(cur)) {
                dfs(prevMap, nei, path, allPaths);
            }
        }
        path.remove(path.size() - 1);
    }
    
    private Set<String> getDict(List<String> wordList) {
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        return set;
    }
    
    private List<String> findAllNeighbors(String cur, Set<String> dict) {
        List<String> neis = new ArrayList<>();
        for (String word : dict) {
            if (isValid(word, cur)) {
                neis.add(word);
            }
        }
        return neis;
    }
    
    private boolean isValid(String word, String cur) {
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
}
