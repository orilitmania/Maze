package algorithms.mazeGenerators;

public interface Maze3dGenerator {

	public abstract Maze3d generate(int x,int y,int z);
	
	public String measureAlgorithmTime(int x,int y,int z);

}
