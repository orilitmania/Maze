package algorithms.search;

import java.util.Stack;

public class Solution<T> {
	

	State<T> startState;
	State<T> goalState;
	Stack <State<T>> solution;
	
	public Solution(State<T> startState, State<T> goalState, Stack<State<T>> solution) {
		super();
		this.startState = startState;
		this.goalState = goalState;
		this.solution = solution;
	}
	
	public Solution(){
		solution = new Stack<State<T>>();
	}
	
	public Solution(Stack<State<T>> temp) {
		solution = new Stack<State<T>>();
		solution.addAll(temp);
		this.startState=temp.firstElement();
		this.goalState=temp.lastElement();
		
		
	}

	public void printSolution(){
		for (State<T> state : solution) {
			System.out.println(state);
		}
	}

	public State<T> getStartState() {
		return startState;
	}

	public void setStartState(State<T> startState) {
		this.startState = startState;
	}

	public State<T> getGoalState() {
		return goalState;
	}

	public void setGoalState(State<T> goalState) {
		this.goalState = goalState;
	}

	public Stack<State<T>> getSolution() {
		return solution;
	}

	public void setSolution(Stack<State<T>> solution) {
		this.solution = solution;
	}
	

}
