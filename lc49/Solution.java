public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        String[] sortedStrs = new String[strs.length];
        for (int i = 0; i < strs.length; ++i) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            sortedStrs[i] = new String(chars);
        }
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; ++i) {
            List<String> lst = map.get(sortedStrs[i]);
            if (lst == null) {
                List<String> newList = new ArrayList<String>();
                newList.add(strs[i]);
                map.put(sortedStrs[i], newList);
            } else {
                lst.add(strs[i]);
            }
        }
        List<List<String>> result = new LinkedList<List<String>>();
        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            Collections.sort(e.getValue());
            result.add(e.getValue());
        }
        return result;
    }
}
