package algorithms.search;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The Class BestFS extends BFS with the use of states costs.
 * i decided to use an this way to build the bestFS because its much more generic flexible and effortless this way.
 * its more efficient clean when looking at it from a design point of view.
 *@author liron
 * @param <T> the generic type
 */
public class BestFS<T> extends BFS<T> {
	
	/* (non-Javadoc)
	 * @see algorithms.search.BFS#calculateCost(algorithms.search.State, algorithms.search.State, algorithms.search.Searchable)
	 */
	@Override
	public double calculateCost(State<T> previousState,State<T> newState,Searchable<T> s){
		
		return previousState.getCost()+ s.getCostForStates(previousState, newState);
	}
	
	public Queue<State<T>> getQueue(){
			Queue<State<T>> q = new PriorityQueue<State<T>>();
			return q;
	}
	
}

