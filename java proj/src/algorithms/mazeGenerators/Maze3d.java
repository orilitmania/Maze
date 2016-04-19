package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * the class is a type of maze
 * it holds all the information of the maze3d
 * @author liron
 *
 */

public class Maze3d implements Maze {

	/**
	 *maze represent the 3d maze as a3d array of int that gets:
	 * 1 - for the maze walls and 0 - for the maze path
	 * the currentPos is a temp position that we use to navigate through the maze
	 * the dims are the maze borders
	 */
	private int[][][] maze;
	private Position currentPos;
	private Position startPos;
	private Position goalPos;
	int dimX, dimY, dimZ;

	/**
	 * a constructor for Maze3d
	 * @param f the number of floors in the maze
	 * @param r the number of rows in the maze
	 * @param c the number of columns in the maze
	 */
	public Maze3d(int f, int r, int c) // f- floor ,r- row ,c-column
	{
		this.maze = new int[f][r][c];
		this.currentPos = new Position(0, 0, 0);
		this.startPos = new Position(0, 0, 0);
		this.goalPos = new Position(0, 0, 0);
		this.dimX = c;
		this.dimY = r;
		this.dimZ = f;
	}

	/**
	 * getter for maze
	 * @return the 3d array of int maze.
	 */
	public int[][][] getMaze() {
		return maze;
	}

	/**
	 * getter for currentPos
	 * @return the current position we are in the maze
	 */
	public Position getCurrentPos() {
		return currentPos;
	}

	/**
	 * 
	 * @param Pos
	 */
	public void setCurrentPos(Position Pos) {
		this.currentPos.setPos(Pos.getZ(), Pos.getY(), Pos.getX());
	}

	public void setStartPos(int z, int y, int x) {
		this.startPos = new Position(z, y, x);
	}

	public Position getStartPosition() {
		return startPos;
	}

	public Position getGoalPosition() {
		return goalPos;
	}

	public void setStartPosition(Position startPos) {
		this.startPos = startPos;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPos = new Position(goalPosition.getZ(), goalPosition.getY(), goalPosition.getX());
	}
	
	
	
	// other methods

	
	// sets all the maze with 1 - walls
	public void init() {
		for (int i = 0; i < dimZ; i++) {
			for (int j = 0; j < dimY; j++) {
				for (int k = 0; k < dimX; k++) {
					maze[i][j][k] = 1;
				}
			}
		}
	}

	// gets position and value and set the position
	public void setMazePosVal(Position pos, int val) {
		setMazePosVal(pos.getZ(), pos.getY(), pos.getX(), val);
	}

	public void setMazePosVal(int z, int y, int x, int val) {
		maze[z][y][x] = val;
	}

	// printing the maze by floors separate by "****" , start and goal printed
	// as "2" , walls - "1" , free path - "0"
	public void printMaze() {
		System.out.println("****************");
		System.out.println("0-maze path");
		System.out.println("1-maze wall");
		System.out.println("2-maze entrance");
		System.out.println("4-maze exit");
		System.out.println("****************");
		for (int i = 0; i < this.dimZ; i++) {
			for (int j = 0; j < this.dimY; j++) {
				for (int k = 0; k < this.dimX; k++) {
					if (new Position(i, j, k).equals(getStartPosition()))
						System.out.print(this.maze[i][j][k] + 2);
					else if (new Position(i, j, k).equals(getGoalPosition()))
							System.out.print(this.maze[i][j][k] + 4);
						else
							System.out.print(this.maze[i][j][k]);
				}
				System.out.println("");

			}
			System.out.println("****************");
		}
	}

	// checks the wall around a position and add the walls position to array
	// list , use in the method generate
	public void addWallPosArry(Position pos, ArrayList<Position> wallArry) {
		if (wallArry.contains(pos)) {
			return;
		}
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (x + 1 < this.dimX && maze[z][y][x + 1] == 1) {
			if (!wallArry.contains(new Position(z, y, x + 1))) {
				wallArry.add(new Position(z, y, x + 1));
			}
		}

		if (x - 1 >= 0 && maze[z][y][x - 1] == 1) {
			if (!wallArry.contains(new Position(z, y, x - 1))) {
				wallArry.add(new Position(z, y, x - 1));
			}
		}

		if (y + 1 < this.dimY && maze[z][y + 1][x] == 1) {
			if (!wallArry.contains(new Position(z, y + 1, x))) {
				wallArry.add(new Position(z, y + 1, x));
			}
		}

		if (y - 1 >= 0 && maze[z][y - 1][x] == 1) {
			if (!wallArry.contains(new Position(z, y - 1, x))) {
				wallArry.add(new Position(z, y - 1, x));
			}
		}

		if (z + 1 < this.dimZ && maze[z + 1][y][x] == 1) {
			if (!wallArry.contains(new Position(z + 1, y, x))) {
				wallArry.add(new Position(z + 1, y, x));
			}
		}

		if (z - 1 >= 0 && maze[z - 1][y][x] == 1) {
			if (!wallArry.contains(new Position(z - 1, y, x))) {
				wallArry.add(new Position(z - 1, y, x));
			}
		}
	}

	public int countZerosAroundPos()// count the number of zeros around a
									// position(for possible moves around the
									// position
	{
		int i = this.currentPos.getX();
		int j = this.currentPos.getY();
		int k = this.currentPos.getZ();

		int count = 0;
		if (i - 1 >= 0 && this.maze[k][j][i - 1] == 0) {
			count++;
		}
		if (i + 1 < this.dimX && this.maze[k][j][i + 1] == 0) {
			count++;
		}
		if (j - 1 >= 0 && this.maze[k][j - 1][i] == 0) {
			count++;
		}
		if (j + 1 < this.dimY && this.maze[k][j + 1][i] == 0) {
			count++;
		}
		if (k - 1 >= 0 && this.maze[k - 1][j][i] == 0) {
			count++;
		}
		if (k + 1 < this.dimZ && this.maze[k + 1][j][i] == 0) {
			count++;
		}
		return count;
	}

	// check if position in the maze are in the shell of the maze - use in
	// generate method
	public boolean isCurrentPosInShell() {
		if (this.currentPos.getX() == this.dimX - 1 || this.currentPos.getY() == this.dimY - 1
				|| this.currentPos.getZ() == this.dimZ - 1 || this.currentPos.getX() == 0 || this.currentPos.getY() == 0
				|| this.currentPos.getZ() == 0) {
			return true;
		} else {
			return false;
		}
	}

	// add to array list the possible moves (looks for "0" around the position)
	// - use in ex1 , generate ,search algorithms
	public ArrayList<String> getPossibleMoves(Position p) {

		// variables, define ArrayList with strings.
		ArrayList<String> tempPossibleMoves = new ArrayList<String>();

		// check availability for moving in x,y,z coordinates.

		// X
		if ((p.getX() + 1 < this.dimX) && (this.maze[p.getZ()][p.getY()][p.getX() + 1] == 0))
			tempPossibleMoves.add("Right");
		if ((p.getX() - 1 >= 0) && (this.maze[p.getZ()][p.getY()][p.getX() - 1] == 0))
			tempPossibleMoves.add("Left");

		// Y
		if ((p.getY() + 1 < this.dimY) && (this.maze[p.getZ()][p.getY() + 1][p.getX()] == 0))
			tempPossibleMoves.add("Forward");
		if ((p.getY() - 1 >= 0) && (this.maze[p.getZ()][p.getY() - 1][p.getX()] == 0))
			tempPossibleMoves.add("Back");

		// Z
		if ((p.getZ() + 1 < this.dimZ) && (this.maze[p.getZ() + 1][p.getY()][p.getX()] == 0))
			tempPossibleMoves.add("Up");
		if ((p.getZ() - 1 >= 0) && (this.maze[p.getZ() - 1][p.getY()][p.getX()] == 0))
			tempPossibleMoves.add("Down");

		return tempPossibleMoves;
	}

	// create a 2d maze using one of the dim's as fixes - one method for each
	// dim
	public int[][] getCrossSectionByX(int x) throws IndexOutOfBoundsException {
		if (x < 0 || x >= this.dimX) {
			throw new IndexOutOfBoundsException();
		}

		int[][] maze2d = new int[this.dimZ][this.dimY];
		for (int i = 0; i < this.dimZ; i++) {
			for (int j = 0; j < this.dimY; j++) {
				maze2d[i][j] = maze[i][j][x];
			}
		}
		return maze2d;
	}

	public int[][] getCrossSectionByY(int y) throws IndexOutOfBoundsException {
		if (y < 0 || y >= this.dimY) {
			throw new IndexOutOfBoundsException();
		}

		int[][] maze2d = new int[this.dimZ][this.dimX];
		for (int i = 0; i < this.dimZ; i++) {
			for (int j = 0; j < this.dimX; j++) {
				maze2d[i][j] = maze[i][y][j];
			}
		}
		return maze2d;
	}

	public int[][] getCrossSectionByZ(int z) throws IndexOutOfBoundsException {
		if (z < 0 || z >= this.dimZ) {
			throw new IndexOutOfBoundsException();
		}

		int[][] maze2d = new int[this.dimY][this.dimX];
		for (int i = 0; i < this.dimY; i++) {
			for (int j = 0; j < this.dimX; j++) {
				maze2d[i][j] = maze[z][i][j];
			}
		}
		return maze2d;
	}

}
