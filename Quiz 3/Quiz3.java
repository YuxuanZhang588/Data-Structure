import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Quiz3 {
	/**
	 * This method returns the number of isomorphicPairs in the given String Array.
	 * 
	 * Use integers to represent the structure of the word. e.g, "abcc"="1233"
	 * "efgg"="1233", "aadf"="1123". So the words with the same numbers are isomorphic
	 * pairs.
	 * 
	 * @param words an String array or words.
	 * @return the number of isomorphic pairs in this array.
	 */
	public int isomorphicPairs(String[] words) {
		Map<String, int[]> wordStructures = new HashMap<String, int[]>();
		for(String word: words){
			char[] charcs = word.toCharArray();
			Map<Character, Integer> creatStructure = new HashMap<Character, Integer>();
			//I created a map for the structure of the word, and use a number to represent
			//each character.
			int index = 1;
			int[] structure = new int[charcs.length];
			for(int i=0; i<charcs.length; i++){
				if(!creatStructure.containsKey(charcs[i])){
					creatStructure.put(charcs[i],index);
					structure[i] = index;
					index++;
				}
				else{
					structure[i] = creatStructure.get(charcs[i]);
				}
			}
			wordStructures.put(word, structure);
		}
		int counter = 0;
		int length = words[0].length();
		for(int i=0; i<words.length-1; i++){
			for(int j=i+1; j<words.length; j++){
				int checker = 0;
				for(int a=0; a<length; a++){
					if(wordStructures.get(words[i])[a]==wordStructures.get(words[j])[a]){
						checker++;
					}
				}
				if(checker==length){
					counter++;
				}
			}
		}
		return counter;
	}

	public static void main(String[] args){
		Quiz3 quiz = new Quiz3();

		System.out.println("Testing isomorphicWords():");

		String[] array1 = {"abca", "zbxz", "opqr"};
		String[] array2 = {"aa", "ab", "bb", "cc", "cd"};
		String[] array3 = { "cacccdaabc", "cdcccaddbc", "dcdddbccad", "bdbbbaddcb",
				"bdbcadbbdc", "abaadcbbda", "babcdabbac", "cacdbaccad",
				"dcddabccad", "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca" };
		String[] array4 = {"abcd", "bcda"};

		System.out.println(quiz.isomorphicPairs(array1)); //Should print 1
		System.out.println(quiz.isomorphicPairs(array2)); //Should print 4
		System.out.println(quiz.isomorphicPairs(array3)); //Should print 13
		System.out.println(quiz.isomorphicPairs(array4)); //Should print 1

		System.out.println("Testing concatenate():");

		MyLinkedList listA = new MyLinkedList();
		MyLinkedList listB = new MyLinkedList();

		listA.addLast(1);
		listA.addLast(3);
		listA.addLast(5);
		
		// Should print only the elements in listA because nothing existed on listB
		System.out.println("Nothing added:");
		System.out.println(listA);
		
		
		// Adding elements to listB
		// listB.addLast(7);
		// listB.addLast(9);
		// listB.addLast(11);
		
		listA.concatenate(listB);
		
		// Should print all elements formerly on listA and listB
		System.out.println("Elements present on listB:");
		// This will use the toString() in the MyLinkedList implementation
		System.out.println(listA);
		System.out.println(listA.head);
		System.out.println(listA.head.next);
		System.out.println(listA.head.next.next);
		System.out.println(listA.tail);
		// Should print nothing, because listB became empty
		System.out.println("Leftover on listB:");
		// This will use the toString() in the MyLinkedList implementation
		System.out.println(listB);
	}

}
