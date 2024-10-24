import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Quiz2 {
	/**
	 * Returns the index (0-based) of the first element of "orders"
	 * that only requests ingredients contained in "available".
	 * 
	 * @param available An array listing ingredients available in the sandwich bar.
	 * @param orders An array of orders, each a space-separated description of ingredients.
	 * 
	 * @return The first index of "orders" (0-based) that only describes ingredients present
	 *         in the "available" array.
	 */
	public static int sandwich(String[] available, String[] orders) {
        // Creates a set for available ingredients.
        Set<String> doHave = new HashSet<String>();
        for(String material: available){
            doHave.add(material);
        }
        for(int i=0; i<orders.length; i++){
            String order = orders[i];
            String[] ingredients = order.split(" ");
            Set<String> elements = new HashSet<String>();
            for(String ingredient: ingredients){
                elements.add(ingredient);
            }
            if(doHave.containsAll(elements)){
                return i;
            }
        }
        return -1;
    }

	/**
	 * Determine if the equation is balanced using (), [] and {}.
	 * 
	 * @param equation a String containing characters using (, ), [, ], {, and }.
	 * @return a boolean - true if the equation is balanced; false otherwise.
	 */
	public static boolean balanced(String equation){
        Stack<Character> bracket = new Stack<Character>();
        char[] chars = equation.toCharArray();
        for(int i=0; i<chars.length; i++){
            if(chars[i]=='('||chars[i]=='['||chars[i]=='{'){
                bracket.push(chars[i]);
            }
            if(chars[i]==')'||chars[i]==']'||chars[i]=='}'){
                if(bracket.isEmpty()){
                    return false;
                }
                if(chars[i]==')' && bracket.peek()=='('||chars[i]==']' && 
                bracket.peek()=='['||chars[i]=='}' && bracket.peek()=='{'){
                    bracket.pop();
                }
            }
        }
        return bracket.isEmpty();
	}

	// /*
	// // // For testing. Comment it back when you are done.
	public static void main(String[] args) {
        Quiz2 q = new Quiz2();
        String s = "( ) [ ] { }";
        System.out.println(q.balanced(s));
        String[] a = { "foo", "bar", "baz", "gazonk", "quux", "bat", "xyzzy",
        "shme", "hukarz", "grault", "waldo", "bleh" };

        String[] b = { "kalatehas", "spam eggs", "needle haystack", "bleh blarg",
        "the best sandwich in the universe" };

        System.out.println(q.sandwich(a, b));
	}
}