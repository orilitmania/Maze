package algorithms.search;

import java.util.HashSet;
import java.util.Stack;

public class DFS<T> extends CommonSearcher<T> {

	@Override
	public Solution<T> search(Searchable<T> s) {
		Stack<State<T>> ds = new Stack<State<T>>();
		HashSet<State<T>> blacks = new HashSet<State<T>>();// Container for the nodes that the Algorithm finish
															// checking them and Their "sons"
		HashSet<State<T>> grays = new HashSet<State<T>>();// Container for
															// visited but not finish
		ds.push(s.getInitialState());// starting from the start position
		while (!ds.isEmpty()) {
			State<T> currentState = new State<T>(ds.pop());
			this.setEvaluatedNodes(this.getNumberOfNodesEvaluated()+1);
			if (!grays.contains(currentState) && !blacks.contains(currentState)) {
				grays.add(currentState);
			}
			if (currentState.equals(s.getGoalState()))// Finishing the loop
			{
				return backTrace(currentState, s.getInitialState());
			}
			for (State<T> st : s.getAllPossibleStates(currentState)) {
				if (!grays.contains(st) && !blacks.contains(st)) {
					st.setCost(currentState.getCost() + 0);
					ds.push(st);
					st.setCameFrom(currentState);
					
				}
			}
			grays.remove(currentState);
			blacks.add(currentState);
		}
		return new Solution<T>(); // the "real" return happens in backTrace in
		// the end of the while-loop.;
	}

}
