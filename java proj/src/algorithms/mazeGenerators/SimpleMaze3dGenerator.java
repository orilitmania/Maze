package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends CommonMaze3dGenerator {

	@Override
	public Maze3d generate(int x,int y,int z) {
		
		Maze3d simpleMaze = new Maze3d(x,y,z);
		simpleMaze.init();
		Random rand = new Random();
		
		int pathLen = rand.nextInt(x*y*z);
		
		int ySTartPos = rand.nextInt(y);
		int zSTartPos = rand.nextInt(z);
		
		
		
		simpleMaze.setStartPos(0, ySTartPos, zSTartPos);
		
		simpleMaze.setCurrentPos(simpleMaze.getStartPosition());
		simpleMaze.setMazePosVal(simpleMaze.getStartPosition(), 0);

		
		//randomize 0 or 1 in the maze;
		for(int i=0; i < x; i++)
		{
			for(int j=0; j < y; j++)
			{
				for(int k=0; k < z; k++)
				{
					simpleMaze.setMazePosVal(i,j,k, rand.nextInt(2));
				}
			}
		}

		//the loop will walk in the maze and sets a path the size of pathLen but if a goal is not set the loop will continue.
		//when the size has ended and a goal is set the loop will exit.
		while(pathLen != 0){
			int randDir = rand.nextInt(5);
			
			int tempX = simpleMaze.getCurrentPos().getX();
			int tempY = simpleMaze.getCurrentPos().getY();
			int tempZ = simpleMaze.getCurrentPos().getZ();
			int goalFlag = 0;
			
			switch (randDir) {
			
			case 0: if( tempY+1 < y){; //FORWARD
						simpleMaze.setCurrentPos(new Position(tempZ, tempY+1, tempX));
						simpleMaze.setMazePosVal(simpleMaze.getCurrentPos(), 0);
					}
			break;
				
			case 1: if( tempY-1 >= 0){; //BACKWARDS
						simpleMaze.setCurrentPos(new Position(tempZ, tempY-1, tempX));
						simpleMaze.setMazePosVal(simpleMaze.getCurrentPos(), 0);
			}
			break;
				
			case 2: if( tempX-1 >= 0){; //LEFT
						simpleMaze.setCurrentPos(new Position(tempZ, tempY, tempX-1));
						simpleMaze.setMazePosVal(simpleMaze.getCurrentPos(), 0);
			}
			break;
			
			case 3: if( tempX+1 < x){; //RIGHT
						simpleMaze.setCurrentPos(new Position(tempZ, tempY, tempX+1));
						simpleMaze.setMazePosVal(simpleMaze.getCurrentPos(), 0);
			}
			break;
		
			case 4: if( tempZ+1 < z){; //UP
						simpleMaze.setCurrentPos(new Position(tempZ+1, tempY, tempX));
						simpleMaze.setMazePosVal(simpleMaze.getCurrentPos(), 0);
			}
			break;
			
			case 5: if( tempZ-1 >= 0){; //Down
						simpleMaze.setCurrentPos(new Position(tempZ-1, tempY, tempX));
						simpleMaze.setMazePosVal(simpleMaze.getCurrentPos(), 0);
			}
			break;

			default:
				break;
			}
			
			if( simpleMaze.isCurrentPosInShell() ){
				simpleMaze.setGoalPosition(simpleMaze.getCurrentPos());
				goalFlag = 1;
			}
			
			pathLen--;
			if( pathLen == 0 && goalFlag == 0){
				pathLen++;
			}
			
		}
		
		return simpleMaze;
	}

}
