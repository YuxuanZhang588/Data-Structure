import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Quiz4 {
	/*A lookup table that you can fill with values as you calculate greatest odd divisors*/
	private static Map<Integer, Integer> LOOK_UP_TABLE = new HashMap<Integer, Integer>();
	
	/** 
	 * Sort an array of names and format the names into all the same format
	 * "first last"
	 * 
	 * 
	 * @param names - a String[] of names in "first last" or "last, first" format
	 * @return the names in "first last" format sorted alph by last name, then by first
	 */
	public String[] dataCleanup(String[] names) {
        //An arraylist with Name objects. 
        ArrayList<Name> theNames = new ArrayList<Name>();
        //Go through each name, check if the name is in "first last" format, if not, change it
        //to the correct format. Then add them to theNames.
        for(String name: names){
            ArrayList<Character> chars = new ArrayList<Character>();
            for(char c: name.toCharArray()){
                chars.add(c);
            }
            if(chars.contains(',')){
                String[] elements = name.split(" ");
                elements[0] = elements[0].substring(0,elements[0].length()-1);
                theNames.add(new Name(elements[1]+" "+elements[0]));
            }
            else{
                theNames.add(new Name(name));
            }
        }
        Collections.sort(theNames);
        for(int i=0; i<names.length; i++){
            names[i]=theNames.get(i).toString();
        }
		return names;
    }
    
    public class Name implements Comparable<Name>{

        public String myName;
        public int myIndex;

        public Name(String name){
            myName = name;
            String[] elements = name.split(" ");
            //Creates a map with characters as keys, and idexes as values.
            Map<Character, Integer> indexes = new HashMap<Character, Integer>();
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            char[] character = characters.toCharArray();
            for(int i=0; i<character.length; i++){
                indexes.put(character[i],i);
            }
            char[] nameChar = elements[1].toCharArray();
            myIndex = indexes.get(nameChar[0]);
        }

        public String toString(){
            return myName;
        }

        @Override
        public int compareTo(Name o) {
            int dif = this.myIndex - o.myIndex;
            if(dif == 0){
                return this.myName.compareTo(o.myName);
            }
            return dif;
        }
    }
	
	/**
	 * Given a positive integer n. Return f(1)+f(2)+...+f(n) where
	 * f(x) is the greatest odd divisor of x, and x is a positive integer.
	 * 
	 * @param n - the positive integer to compute the sum of the greatest odd divisors
	 * @return - the sum of the greatest odd divisors of f(1)+f(2)+...+f(n)
	 */
	public static int sumOfGreatestOddDivisors(int n){
        //Check if the number is already calculated. 
        boolean contains = LOOK_UP_TABLE.containsKey(n);
        //Base case: If the number is zero, return 0.
        if(n==0){
            return 0;
        }
        //if the number is odd, should add the number itself.
        if(n%2==1){
            if(!contains){
                LOOK_UP_TABLE.put(n,n);
                return n+sumOfGreatestOddDivisors(n-1);
            }
            else{
                return LOOK_UP_TABLE.get(n)+sumOfGreatestOddDivisors(n-1); 
            }
        }
        //if the number is even, should add the greatestOddDivisor of this number.
        else{
            if(!contains){
                int oddDivisor=n;
                while(oddDivisor%2==0){
                    oddDivisor/=2;
                }
                LOOK_UP_TABLE.put(n,oddDivisor);
                return oddDivisor+sumOfGreatestOddDivisors(n-1);
            }
            else{
                return LOOK_UP_TABLE.get(n)+sumOfGreatestOddDivisors(n-1);
            }
        }      
	}
	
	public static void main(String[] args){
		Quiz4 q = new Quiz4();

		System.out.println("**********Testing dataCleanup**************");
		String[] names = {"Kelly, Anthony", "Kelly Anthony", "Thompson, Jack"};
		//add more test cases here
		String[] answer = q.dataCleanup(names);

		for(String n : answer){
			System.out.println(n);
		}
		System.out.println("*******************************************\n");
		System.out.println("********Testing sumDivisors************");
		
		int[] testCases = {3,7,1,777}; // Answers: 5, 21, 1, 201537
		
		for(int i : testCases){
			System.out.println("sumOfGreatestOddDivisors(" + i + ") = " + sumOfGreatestOddDivisors(i));
		}
		
        System.out.println("*******************************************\n");

	}
}