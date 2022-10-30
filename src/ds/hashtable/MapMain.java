
package ds.hashtable;

public class MapMain {
    
    public static void main(String[] args) {
        String paragraph = "Paranoids are not"
                + " paranoid because they are paranoid but"
                + " because they keep putting themselves"
                + " deliberately into paranoid avoidable"
                + " situations";
        
        String[] words = paragraph.split(" ");

        LinkedMap<String, Integer> wordCount = new LinkedMap<>();

        for (String word : words) {
            int countValue;
            try {
                countValue = wordCount.get(word);
            } catch (NullPointerException npe) {
                countValue = 0;
            }
            countValue++;
            wordCount.add(word, countValue);
        }

        wordCount.remove("avoidable");
        
        System.out.println("In sentence \"" + paragraph + "\":");
        wordCount.display();
    }

}
