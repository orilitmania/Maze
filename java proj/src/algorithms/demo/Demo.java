package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.BFS;
import algorithms.search.BestFS;
import algorithms.search.DFS;
import algorithms.search.Searcher;
import algorithms.search.Solution;

public class Demo {
	
	static void run(){
			Maze3dGenerator mazeGen= new MyMaze3dGenerator();
			Maze3d myMaze=mazeGen.generate(1,10, 10);
			
			if(myMaze != null){ 
				System.out.println("maze generated");
			}
			
			System.out.println("printing maze");
			myMaze.printMaze();
			
			
			System.out.println("******BFS******");
			SearchableMaze sm=new SearchableMaze(myMaze);
	
			Searcher<Position> bfsSearcher=new BFS<Position>();
			
			Solution<Position> bfsSolution=bfsSearcher.search(sm);
			System.out.println("printing Solution for BFS");
			bfsSolution.printSolution();
			System.out.println("nodes Evaluated " +bfsSearcher.getNumberOfNodesEvaluated());
			
			
			System.out.println("******BESTFS******");
			SearchableMaze sm2=new SearchableMaze(myMaze);
			
			Searcher<Position> bestFsSearcher=new BestFS<Position>();
			
			Solution<Position> bestSsSolution=bestFsSearcher.search(sm2);
			System.out.println("printing Solution for BestFS");
			bestSsSolution.printSolution();
			System.out.println("nodes Evaluated " +bestFsSearcher.getNumberOfNodesEvaluated());
			
			
			
			
			System.out.println("******DFS******");
			SearchableMaze sm3=new SearchableMaze(myMaze);
			
			Searcher<Position> dfsSearcher=new DFS<Position>();
			
			Solution<Position> dfsSolution=dfsSearcher.search(sm3);
			System.out.println("printing Solution for DFS");
			dfsSolution.printSolution();
			System.out.println("nodes Evaluated " +dfsSearcher.getNumberOfNodesEvaluated());
	}

	public static void main(String[] args) {
		run();
	}

}
