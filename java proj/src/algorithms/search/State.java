package algorithms.search;

import algorithms.mazeGenerators.*;

public class State<T>{
	 T MyState;
	 double cost;
	 State<T> cameFrom;

	 
	 
	 public State(T state) {
	  this.MyState= state;
	  this.cost=0;
	  this.cameFrom=null;
	 }

	 public State(State<T> State) {
		 this.MyState=State.MyState;
		  this.cost=State.cost;
		  this.cameFrom=State.cameFrom;
		 
	 }

		@Override
		public boolean equals(Object obj) {
			return this.MyState.equals(((State<?>)obj).getState());
		}
		
	
		@Override
		public String toString(){
			if(this.getState() instanceof Position){
				return ((Position)(this.getState())).toString();
			}
			return this.getState().toString();
		}
		
			
		public T getState() {
			return MyState;
		}

		public void setState(T state) {
			this.MyState = state;
		}

		@Override
		public int hashCode(){
			return toString().hashCode();
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
		public State<T> getCameFrom() {
			return cameFrom;
		}
		public void setCameFrom(State<T> cameFrom) {
			this.cameFrom = cameFrom;
		}

	}
	