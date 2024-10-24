import java.util.*;

/** Solutions to the recursive exercises from HW 5.
 * 
 * @author David Zhang and Andrea Liu
 * Time Spent: 20 hours
 * 
 */
public class Recursion{

	/** Returns the value of the McCarthy 91 function for a given integer.
	 * 
	 * @param n the input integer.
	 * @return the McCarthy 91 value of n.
	 */
	public static int mcCarthy91(int n) {
		if (n > 100) {
            return n - 10;
        } else {
            return mcCarthy91(mcCarthy91(n + 11));
        }
	}


	/** Returns the base-2 expansion of a given integer as a String.
	 * 
	 * @param n the base-10 number we wish to convert to binary (>= 0).
	 * @return the binary expansion of n.
	 */

    public static String toBinary(int n) {
        //base case: n = 0
        if(n==0){
            return "0";
        }
        //base case: n = 1
        else if(n==1){
            return "1";
        }
        //when n>1
        return toBinary(n/2)+n%2;    
     }


	/** Returns the number of ways a staircase can be climbed using 1 or 2 
	 * steps at a time.
	 * 
	 * @param numStairs the total height of the staircase (>= 1).
	 * @return the number of ways to climb the entire flight of stairs.
	 */
    public static int countWays(int numStairs) {
        //base case when numStairs is 1
        if(numStairs==1){
            return 1;
        }
        //base case when numStairs is 2
        if(numStairs==2){
            return 2;
        }
        return countWays(numStairs-1)+countWays(numStairs-2);
    }

	/** Returns whether the given marker puzzle is solvable.
	 * 
	 * The puzzle consists of an array of positive integers, with a marker 
	 * that starts off at position 0. A move consists of moving the marker by 
	 * the number of steps indicated in the current position, either to the 
	 * left or the right. The goal is to determine whether there exists a 
	 * sequence of moves that brings the marker to the right-most position in 
	 * the array (i.e., whether the puzzle is solvable).
	 * 
	 * @param board the board state.
	 * @return true iff the puzzle is solvable.
	 */
    public static boolean solvable(int[] board) {
        int move = 0;
        int[] boardCopy = board.clone();
        //returns true is the value at index is already 0
        if(board[move] == 0){
            return true;
        }
        else{
            return solvable(boardCopy, move);
        }
    }

	/**
	 * Marks a position in the puzzle.
	 * 
	 * @param board Game board.
	 * @param position Position to be marked.
	 */

    private static void mark(int[] board, int position) {
        //makes the value at index position negative to mark while maintaining the value
        board[position] = -1*board[position];
	}

	/**
	 * Unmarks a position in the puzzle, if it is marked.
	 * 
	 * @param board Game board.
	 * @param position Position to be marked.
	 */
	private static void unmark(int[] board, int position) {
        //when the value is negative (marked) reverts the value at index position to unmark
        if(board[position] <0){
            board[position]=-1*board[position];
        }
	}

	/**
	 * Checks if a position is marked in the puzzle.
	 * 
	 * @param board Game board.
	 * @param position Position to be marked.
	 * 
	 * @return True if the position is marked, false otherwise.
	 */
	private static boolean isMarked(int[] board, int position) {
        //if the value at index position is negative, number is marked, so we unmark and 
        //indicate that it was previously marked
        if(board[position]<0){
            unmark(board, position);
            return true;
        }
        //when not previously marked, return false
        return false;
	}

	/** Helper method for the solvable method above.
	 * 
	 * @param board the board state.
	 * @param position the current position of the marker on the board.
	 * @return true iff the puzzle is solvable from the given position.
	 */
	private static boolean solvable(int[] board, int position) {
        //base case: if the marker is at the last position in the board, return true.
        if(position == board.length-1){
            return true;
        }
        //base case: if position is out of bounds, return false.
        else if(position>board.length-1 || position<0){
            return false;
        }
        //base case: if we've visited this place, return false.
        else if(isMarked(board, position)){
            return false;
        }
        else{
            int move=board[position];
            //mark because we've been here; tracks the previous moves
            mark(board,position);
            boolean solvableLeft = solvable(board,position-move);
            boolean solvableRight = solvable(board,position+move);
            //unmark position after fully running through solvable for position+move or position-move
            unmark(board,position);
            //if neither moving right or left was successful, then the puzzle is not solvable; return false
            if(solvableLeft==false && solvableRight==false){
                return false;
            }
            //if at least one of moving right or left is successful, puzzle is solvable; return true
            return true;
    }
}
	/** Iterative version of the solvable() method.
	 * 
	 * @param board the board state.
	 * @return true iff the puzzle is solvable.
	 */
	public static boolean iterativeSolvable(int[] board) {
        Stack<Integer> positions = new Stack<Integer>();
        Set<Integer> been = new HashSet<Integer>();
        //adds initial position to positions
        positions.push(0);
        int position=0;
        //while position is in bounds and the stack is not empty
        while(position<board.length && !positions.isEmpty()){
            //if index position is already the last number, return true--we made it
            if(position==board.length-1){
                return true;
            }
            //if the set of values tracking visited indexes contains current index
            //move on to next value in stack (an unvisited index)
            if(been.contains(position)){
                position = positions.pop();
            }
            //if moving right would still be in bounds, push the new position to stack
            if(position+board[position]<board.length){
                positions.push(position+board[position]);
            }
            //if moving left would still be in bounds, push the new position to stack
            if(position-board[position]>0){
                positions.push(position-board[position]);
            }
            //add index visted to been Set
            been.add(position);
            //reset position to the next value on the stack
            position = positions.pop();
        }
		return false;        
    }
	
}