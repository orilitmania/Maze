package algorithms.search;

import java.util.Collections;

public abstract class CommonSearcher<T> implements Searcher<T> {

	private int evaluatedNodes;

	public CommonSearcher() {
		evaluatedNodes = 0;
	}

	public abstract Solution<T> search(Searchable<T> s);

	@Override
	public int getNumberOfNodesEvaluated() {
		return evaluatedNodes;
	}

	public Solution<T> backTrace(State<T> exit, State<T> entrence) {
		Solution<T> AL = new Solution<T>();
		State<T> temp = new State<T>(exit);
		AL.solution.add(temp);
		AL.setStartState(entrence);
		AL.setGoalState(exit);
		while (!(temp.equals(entrence))) {
			AL.solution.add(temp.getCameFrom());
			temp = temp.getCameFrom();
		}

		Collections.reverse(AL.solution);

		return (AL);
	}

	public void setEvaluatedNodes(int evaluatedNodes) {
		this.evaluatedNodes = evaluatedNodes;
	}
}