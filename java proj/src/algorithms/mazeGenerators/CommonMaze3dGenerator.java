package algorithms.mazeGenerators;

import java.util.Date;

/**
* this class is an abstract class that implements the interface Maze3dGenerator.
* it implements the measureAlgorithmTime method.
* its part of the maze generators strategy design pattern.
* @author liron
*/
public abstract class CommonMaze3dGenerator implements Maze3dGenerator {

	@Override
	public abstract Maze3d generate(int z,int y,int x);
		
	/**
	 *this method calculate the time it takes for any maze generator class,
	 *to generate a new maze.
	 * @param z the floor number
	 * @param y the row number
	 * @param x the column
	 * @return the time in millisec
	 */
	@Override
	public String measureAlgorithmTime(int z,int y,int x) {
		long start,end,time;
		start = System.currentTimeMillis();
		
		generate(z,y,x);
		
		end = System.currentTimeMillis();
		
		Date sartDate = new Date(start);
		Date endDate = new Date(end);
		
		time = endDate.getTime()-sartDate.getTime();
		
		return time+"";
		
	}

}
