package algorithms.mazeGenerators;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h1>Maze3d</h1> kind of a maze implements InteFace "Maze" 3D maze using:
 * 
 * @param
 * 
 * 			<p>
 * @author Ori Litman
 * @version 1.0
 */
public class Maze3d implements Maze {

	// Variables
	/**
	 * This is a int[][][] that represents the maze.
	 */
	private int[][][] maze;
	/**
	 * This is position that in use during building and searching in the maze.
	 */
	private Position currentPos;
	private Position startPos;
	private Position goalPosition;
	/**
	 * This is a 3- int's that represents the mazes border. z-number of floors
	 * in the maze y- number of Rows in the Maze x- number of columns in the
	 * Maze
	 */
	int dimX, dimY, dimZ; // mazes border

	/**
	 * This C'tor is in use to build the 3dMaze.
	 * 
	 * @param f
	 *            This is the number of floors in the maze
	 * @param r
	 *            This is the number of Rows in the maze
	 * @param c
	 *            This is the number of Columns in the maze
	 * @return Maze3d.
	 */
	public Maze3d(int f, int r, int c) // f- floor ,r- row ,c-coulom
	{
		this.maze = new int[f][r][c];
		this.currentPos = new Position(0, 0, 0);
		this.startPos = new Position(0, 0, 0);
		this.goalPosition = new Position(0, 0, 0);
		this.dimX = c;
		this.dimY = r;
		this.dimZ = f;
	}

	// in use only for ex2
	public Maze3d() {
	}

	public Maze3d(byte[] b) throws IOException {

		// get X, Y, Z from bytes array.
		this.dimZ = ByteBuffer.wrap(Arrays.copyOfRange(b, 0, 4)).getInt();
		this.dimY = ByteBuffer.wrap(Arrays.copyOfRange(b, 4, 8)).getInt();
		this.dimX = ByteBuffer.wrap(Arrays.copyOfRange(b, 8, 12)).getInt();

		// get start position from bytes array.
		this.startPos = new Position(ByteBuffer.wrap(Arrays.copyOfRange(b, 12, 16)).getInt(),
				ByteBuffer.wrap(Arrays.copyOfRange(b, 16, 20)).getInt(),
				ByteBuffer.wrap(Arrays.copyOfRange(b, 20, 24)).getInt());

		// get goal poisiton from bytes array.
		this.goalPosition = new Position(ByteBuffer.wrap(Arrays.copyOfRange(b, 24, 28)).getInt(),
				ByteBuffer.wrap(Arrays.copyOfRange(b, 28, 32)).getInt(),
				ByteBuffer.wrap(Arrays.copyOfRange(b, 32, 36)).getInt());

		// get maze from bytes array.
		this.maze = new int[dimZ][dimY][dimX];
		int counter = 36; // the last index we stopped reading from bytes array.

		for (int i = 0; i < this.dimZ; i++) {
			for (int j = 0; j < this.dimY; j++) {
				for (int k = 0; k < this.dimX; k++) {
					this.maze[i][j][k] = (int) b[counter];
					counter++;
				}
			}
		}
	}

	// Setters and getters
	public int[][][] getMaze() {
		return maze;
	}

	public Position getCurrentPos() {
		return currentPos;
	}

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
		return goalPosition;
	}

	public void setStartPosition(Position startPos) {
		this.startPos = startPos;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = new Position(goalPosition.getZ(), goalPosition.getY(), goalPosition.getX());
	}

	// other methods

	/**
	 * This method is in use to build walls in all the maze. in use during the
	 * generate part of the maze - the first part is building all maze with
	 * walls then each method is finding a path
	 */
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
		System.out.println("~~~~~~~~~~~~~~");
		for (int i = 0; i < this.dimZ; i++) {
			System.out.println("Floor number: " + i);
			for (int j = 0; j < this.dimY; j++) {
				for (int k = 0; k < this.dimX; k++) {
					System.out.print(this.maze[i][j][k]);
				}
				System.out.println("");

			}
			System.out.println("~~~~~~~~~~~~~~");
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

	@Override
	/** {@inheritDoc} */
	/**
	 * add to array list the possible moves (looks for "0" around the position)
	 */
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

	public byte[] toByteArray() throws IOException {

		// variables
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(out);

		// write maze size : x, y, z.
		data.writeInt(this.dimZ);
		data.writeInt(this.dimY);
		data.writeInt(this.dimX);

		// write start position
		data.writeInt(this.startPos.getZ());
		data.writeInt(this.startPos.getY());
		data.writeInt(this.startPos.getX());

		// write goal position
		data.writeInt(this.goalPosition.getZ());
		data.writeInt(this.goalPosition.getY());
		data.writeInt(this.goalPosition.getX());

		// write maze
		for (int i = 0; i < this.dimZ; i++) {
			for (int j = 0; j < this.dimY; j++) {
				for (int k = 0; k < this.dimX; k++) {
					data.write(this.maze[i][j][k]);
				}
			}
		}

		return out.toByteArray();
	}

	public boolean equals(Object obj) {

		if ((obj instanceof Maze3d) == false) {
			return false;
			
		} 
		
		else if ((this.dimX != ((Maze3d) obj).dimX) || (this.dimY != ((Maze3d) obj).dimY)
				|| (this.dimZ != ((Maze3d) obj).dimZ)) {
			return false;
		} 
		
		
		else if (this.startPos.equals(((Maze3d) obj).startPos) == false
				|| this.goalPosition.equals(((Maze3d) obj).goalPosition) == false) {
			return false;
		} 
		
		
		else {
			for (int i = 0; i < this.dimZ; i++) {
				for (int j = 0; j < this.dimY; j++) {
					for (int k = 0; k < this.dimX; k++) {
						if (this.maze[i][j][k] != ((Maze3d) obj).maze[i][j][k]) {
							return false;
						}
					}
				}
			}
		}
		
		
		return true;

	}

}
