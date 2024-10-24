
/** The main driver class for the Evil Hangman program.
  * 
  * This class handles all the user interaction and file I/O operations for
  * the "cheating" computer Hangman player.

  * Time spent: 3 hours
*
* @author David Zhang
* @author Andrea Liu
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class HangmanManager {
    public int count;
    public Set<String> wordConsidered;
    public SortedSet<Character> guessedLetters = new TreeSet<Character>();
    public String currentPattern;

    /** Initializes the Hangman game with given dictionary, length, and maximum guesses.
     * 
     * This method creates a TreeSet of words with the given word length,
     * and initiates the pattern printed by the program. The method throws an excption
     * if there are less than or equal to 0 guesses left, or if the length of the word in quesiton is less than 1. 
     * 
     * @param dictionary gives the list of possible words to choose from.
     * @param length gives the length of the word in question. 
     * @param max gives the maximum number of guesses the user is able to use. 
     */

    public HangmanManager(List<String> dictionary, int length, int max){
        if(length<1||max<0){
            throw new IllegalArgumentException();
        }
        count = max;
        wordConsidered = new TreeSet<String>();
        for(String words:dictionary){
            if(words.length()==length){
                wordConsidered.add(words);
            }
        }
        currentPattern = "";
        for(int i=0; i<length-1; i++){
            currentPattern +="- ";
        }
        currentPattern +="-";
    }

    /** 
     * Returns the current of words being considered in the Hangman game. 
     */
    public Set<String> words(){
        return wordConsidered;
    }

    /** 
     * Returns the number of guesses the player has left in the game. 
     */
    public int guessesLeft(){
        return count;
    }

    /** 
     * Returns the set of letters that have been guessed by the player. 
     */
    public SortedSet<Character> guesses(){
        return guessedLetters;
        
    }

    /** Displays the current pattern of the game, including guesses made. 
     * 
     * This method returns the current pattern of guessed words to be displayed for the Hangman game, 
     * considering all guesses that have been made. Letters that haven't been guessed are dashes, and
     * spaces separate the letters.  
     * 
     */
    public String pattern(){
        if(wordConsidered.isEmpty()){
            throw new IllegalStateException();
        }
        return currentPattern;
    }

    /** Records the guess made by the user, decides what set of words to use, updates number of guesses,
     * and returns the number of occurrences of the guessed letter. 
     * 
     * This method returns the number of occurrences of the guessed letter and records the guesses 
     * made by the user as well as the number of guesses remaining. Exceptions are thrown when the number of 
     * guesses is less than one or if the list of words is empty, and if the list of words is non-empty 
     * but the character guesses was previously guessed. 
     */
    public int record(char guess){
        if(count<1||wordConsidered.isEmpty()){
            throw new IllegalStateException();
        }
        if(wordConsidered.isEmpty()&&guessedLetters.contains(guess)){
            throw new IllegalArgumentException();
        }
        guessedLetters.add(guess);

    // Creates a map with possible patterns as the keys and sets of word families with that same pattern
    // as the values.
        Map<String, Set<String>> families = new HashMap<String, Set<String>>();
        Set<String> family;
        String possiblePatterns = currentPattern;

    // Go through considered words and fill in the map.
        for(String word: wordConsidered){
            int length = word.length();
            char[] characters = word.toCharArray();
            for(int i=0; i<length; i++){
                if(characters[i]==guess){
                    char[] charPatterns = possiblePatterns.toCharArray();
                    charPatterns[i*2]=guess;
                    possiblePatterns=String.valueOf(charPatterns);
                }
            }
            if(families.containsKey(possiblePatterns)){
                families.get(possiblePatterns).add(word);
            }
            else{
                family = new TreeSet<String>();
                family.add(word);
                families.put(possiblePatterns,family);
            }
            possiblePatterns = currentPattern;
        }

    // Choose the pattern with largest wordset.
        int largestFamily = 0;
        for(String pattern: families.keySet()){
            if(families.get(pattern).size()>largestFamily){
                largestFamily = families.get(pattern).size();
                currentPattern = pattern;
            }    
        }
        wordConsidered = families.get(currentPattern);

    // Count the occurance of the geussed character in that pattern.
        int occur = 0;
        for(Character chars: currentPattern.toCharArray()){
            if(chars==guess){
                occur++;
            }
        }
        if(occur==0){
            count-=1;
        }

        return occur;
    }
}