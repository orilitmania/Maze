package algorithms.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS<T> extends CommonSearcher<T> {

	public Solution<T> search(Searchable<T> s) {

		Queue<State<T>> q = new PriorityQueue<State<T>>(10,new Comparator<State<T>>(){
			@Override
				public int compare(State<T> o1, State<T> o2) {
				
				return (int)(o1.getCost()-o2.getCost());
				
				}});

		
		HashSet<State<T>> visited = new HashSet<State<T>>();

		q.add(s.getInitialState());
		visited.add(s.getInitialState());		
		while (!q.isEmpty()) {									
			State<T> currentState = new State<T> (q.poll());	
			this.setEvaluatedNodes(this.getNumberOfNodesEvaluated()+1);
			if (currentState.equals(s.getGoalState())) {
				return backTrace(currentState, s.getInitialState());
			}
			for (State<T> st : s.getAllPossibleStates(currentState)) {
				
				double currCost=this.calculateCost(currentState, st, s);
				
				if (!visited.contains(st)&&!q.contains(st)) {
					st.setCost(currCost);
					visited.add(st);
					q.add(st);
					st.setCameFrom(currentState);
					
				}
			}
		}
		return new Solution<T>(); // the "real" return happens in backTrace in
		// the end of the while-loop.;
	}
	
	public double calculateCost(State<T> currState,State<T> neighbor,Searchable<T> s){
		return (currState.getCost()+0);
	}
	
}
