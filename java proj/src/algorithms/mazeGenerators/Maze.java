package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * an interface for all types of mazes.
 * @author liron
 */

public interface Maze {

	/**
	 * getter for the maze start position 
	 * @return the start position of the maze
	 */
	Position getStartPosition();

	/**
	 * setter for the maze start position
	 * @param startPos the position to set
	 */
	void setStartPosition(Position startPos);

	/**
	 * getter for the maze goal position 
	 * @return the goal position of the maze
	 */
	Position getGoalPosition();
	
	/**
	 * setter for the maze goalPos position
	 * @param goalPos the position to set
	 */
	void setGoalPosition(Position goalPos);

	/**
	 * 
	 * @param P a position in the maze
	 * @return an ArrayList of string for all the Possible Moves from the position p 
	 */
	ArrayList<String> getPossibleMoves(Position P);

	/**
	 * method that prints the maze
	 */
	void printMaze();

}