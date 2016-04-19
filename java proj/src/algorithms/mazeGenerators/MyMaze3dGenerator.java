package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3dGenerator extends CommonMaze3dGenerator {

	@Override
public Maze3d generate(int x,int y,int z) {
		
		
		Maze3d myMaze = new Maze3d(x,y,z);
		myMaze.init();
		ArrayList<Position> wallArr=new ArrayList<Position>();
		Random rand=new Random();
		int ySTartPos = rand.nextInt(y);
		int zSTartPos = rand.nextInt(z);
		
		myMaze.setStartPos(0, ySTartPos, zSTartPos);
		myMaze.setMazePosVal(myMaze.getStartPosition(),0);
		
	
		myMaze.addWallPosArry(myMaze.getStartPosition(), wallArr);//adds all the walls around the pos in the wallAryy
		
		while( wallArr.isEmpty()== false ){
			
			int randomCellIndex=rand.nextInt(wallArr.size());//take a random wall from array of walls
			myMaze.setCurrentPos(wallArr.get(randomCellIndex));//set it to be the currentpos.
			wallArr.remove(randomCellIndex);
			
			if( myMaze.countZerosAroundPos() == 1){
				myMaze.setMazePosVal(myMaze.getCurrentPos(),0);
				myMaze.addWallPosArry(myMaze.getCurrentPos(), wallArr);
				if( myMaze.isCurrentPosInShell() ){
					
					myMaze.setGoalPosition(myMaze.getCurrentPos());
				}
		
			}
				
		}
		
		return myMaze;
	}

}
