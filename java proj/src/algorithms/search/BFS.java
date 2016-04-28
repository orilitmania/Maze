package algorithms.search;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


/**
 * The Class BFS uses the bfs algorithm to solve any type of search problem.
 * @author liron
 *
 * @param <T> the generic type
 */
public class BFS<T> extends CommonSearcher<T> {

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#search(algorithms.search.Searchable)
	 */
	public Solution<T> search(Searchable<T> s) {

		Queue<State<T>> q = getQueue();

		
		HashSet<State<T>> visited = new HashSet<State<T>>();//a HashSet to know what states were visited by the algorithm

		q.add(s.getInitialState());
		visited.add(s.getInitialState());		
		while (!q.isEmpty()) {									
			State<T> currentState = new State<T> (q.poll());	
			this.setEvaluatedNodes(this.getNumberOfNodesEvaluated()+1);//when we pull a state out of the queue we increment the number of NodesEvaluated by one.
			if (currentState.equals(s.getGoalState())) {
				return backTrace(currentState, s.getInitialState()); //calls for backTrace that returns the Solution with the path from the GoalState to the start state.
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
		return new Solution<T>(); // returns an empty Solution if the algorithm didn't find the goal state.
		
	}
	
	/**
	 * Calculate the cost of a current state 
	 *
	 * @param previousState the state we came from
	 * @param newState the state we are now
	 * @param s the Searchable for the use of BestFs to get the passage cost between states.
	 * @return  previousState + 1 because the bfs increments by one for each new neighbor
	 */
	public double calculateCost(State<T> previousState ,State<T> newState ,Searchable<T> s){
		return (previousState.getCost() + 1);
	}
	
	
	public Queue<State<T>> getQueue(){
		Queue<State<T>> q = new LinkedList<State<T>>();
		return q;
	}
	
	
}
