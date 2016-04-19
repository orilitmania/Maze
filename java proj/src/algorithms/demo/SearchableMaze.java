package algorithms.demo;

import java.util.ArrayList;
import java.util.Random;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

public class SearchableMaze implements Searchable<Position> {

	Maze myMaze;

	public SearchableMaze(Maze myMaze) {
		super();
		this.myMaze = myMaze;
	}

	@Override
	public State<Position> getInitialState() {
		State<Position> s1 = new State<Position>(myMaze.getStartPosition());
		return s1;
	}

	@Override
	public State<Position> getGoalState() {
		State<Position> s1 = new State<Position>(myMaze.getGoalPosition());
		return s1;
	}

	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {

		ArrayList<String> list = new ArrayList<String>();
		list = myMaze.getPossibleMoves(s.getState());
		ArrayList<State<Position>> sl = new ArrayList<State<Position>>();
		for (int i=0;i<list.size();i++) {
			if (list.get(i).equals("Right")){
				sl.add(new State<Position>(
						new Position(s.getState().getZ() , s.getState().getY(), s.getState().getX()+ 1)));
			}
			if (list.get(i).equals("Left")) {
				sl.add(new State<Position>(
						new Position(s.getState().getZ() , s.getState().getY(), s.getState().getX()- 1)));
			}
			if (list.get(i).equals("Up")) {

				sl.add(new State<Position>(
						new Position(s.getState().getZ()+1, s.getState().getY(), s.getState().getX())));

			}
			if (list.get(i).equals("Down")) {
				sl.add(new State<Position>(
						new Position(s.getState().getZ()-1, s.getState().getY(), s.getState().getX())));

			}
			if (list.get(i).equals("Back")) {
				sl.add(new State<Position>(
						new Position(s.getState().getZ(), s.getState().getY() - 1, s.getState().getX())));

			}
			if (list.get(i).equals("Forward")) {
				sl.add(new State<Position>(
						new Position(s.getState().getZ(), s.getState().getY() + 1, s.getState().getX())));

			}
		}
		return sl;
	}

	@Override
	public double getCostForStates(State<Position> a, State<Position> b) {
		Random rand = new Random();
		int randNum = rand.nextInt(10);
	
		return randNum;
	}

}
