package algorithms.search;



public class BestFS<T> extends BFS<T> {
	
	@Override
	public double calculateCost(State<T> currState,State<T> neighbor,Searchable<T> s){
		
		return currState.getCost()+ s.getCostForStates(currState, neighbor);
	}
}
