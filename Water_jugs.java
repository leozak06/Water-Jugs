import java.util.HashSet;
import java.util.Set;

public class Water_jugs extends Problem {
	
	private static final int tweleve = 0;
	private static final int eight = 1;
	private static final int three = 2;
	
	boolean goal_test(Object state) {
        StateWaterJugs jug_state = (StateWaterJugs)state;
        
        if (jug_state.jugArray[tweleve]== 1 || jug_state.jugArray[eight] == 1 || jug_state.jugArray[three] == 1)
            return true;
        else return false;
	}
	Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateWaterJugs jug_state = (StateWaterJugs)state;
		
		//fill 12
		StateWaterJugs jug_tweleve_fill = new StateWaterJugs(jug_state);
		jug_tweleve_fill.jugArray[tweleve] = 12;
		if (isValid(jug_tweleve_fill)) set.add(jug_tweleve_fill);
		
		//empty 12
		StateWaterJugs jug_tweleve_empty = new StateWaterJugs(jug_state);
		jug_tweleve_empty.jugArray[tweleve] = 0;
		if (isValid(jug_tweleve_empty)) set.add(jug_tweleve_empty);

		//fill 8
		StateWaterJugs jug_eight_fill = new StateWaterJugs(jug_state);
		jug_eight_fill.jugArray[eight] = 8;
		if (isValid(jug_eight_fill)) set.add(jug_eight_fill);
		
		//empty 8
		StateWaterJugs jug_eight_empty = new StateWaterJugs(jug_state);
		jug_eight_empty.jugArray[eight] = 0;
		if (isValid(jug_eight_empty)) set.add(jug_eight_empty);
		
		//fill 3
		StateWaterJugs jug_three_fill = new StateWaterJugs(jug_state);
		jug_three_fill.jugArray[three] = 3;
		if (isValid(jug_three_fill)) set.add(jug_three_fill);
		
		//empty 3
		StateWaterJugs jug_three_empty = new StateWaterJugs(jug_state);
		jug_three_empty.jugArray[three] = 0;
		if (isValid(jug_three_empty)) set.add(jug_three_empty);
		
		// from 12 to 8
		StateWaterJugs fromTtoE = this.fillJug(eight, tweleve, jug_state);
		if (isValid(fromTtoE)) set.add(fromTtoE);
		
		//12 to 3
		StateWaterJugs fromTtoTh = this.fillJug(three, tweleve, jug_state);
		if (isValid(fromTtoTh)) set.add(fromTtoTh);
		
		//8 to 12
		StateWaterJugs fromEtoT = this.fillJug(tweleve, eight, jug_state);
		if (isValid(fromEtoT)) set.add(fromEtoT);
		
		//8 to 3
		StateWaterJugs fromEtoTh = this.fillJug(three, eight, jug_state);
		if (isValid(fromEtoTh)) set.add(fromEtoTh);
		
		//3 to 12
		StateWaterJugs fromThtoT = this.fillJug(tweleve, three, jug_state);
		if (isValid(fromThtoT)) set.add(fromThtoT);
		
		//3 to 8
		StateWaterJugs fromThtoE = this.fillJug(eight, three, jug_state);
		if (isValid(fromThtoE)) set.add(fromThtoE);
        
	return set;
		
	}
	StateWaterJugs fillJug (int to, int from, StateWaterJugs jug_state){
		StateWaterJugs curr_state = new StateWaterJugs(jug_state);
		
		int water_from = curr_state.jugArray[from];
		
		int toAmount = 0;
		//if from is 0, then we fill jug 12
		if(from == 0) toAmount = 12;
		//if  from is 1, we fill jug 8
		else if (from == 1) toAmount = 8;
		//if from is 2, we fill jug 3
		else if (from == 2) toAmount = 3;
		
		//amount to_jug can hold
		int amount_to = toAmount - curr_state.jugArray[to];
		
		//if the amount the water is greater than or equal to the capacity of jugArray
		if(water_from >= amount_to){
			curr_state.jugArray[from] -= amount_to;
			curr_state.jugArray[to] += amount_to;
		}else{
			curr_state.jugArray[from] -= water_from;
			curr_state.jugArray[to] += water_from;
		}
		return curr_state;
		
	}
	 private boolean isValid(StateWaterJugs state){   
        //Checking to see if any element of the array is negative 
        for (int i=0; i<3; i++)
            if (state.jugArray[i] < 0) return false;
        
        //Checking to see if the jugs have more than
        //12,8,3 gallons of water respectively
		if(state.jugArray[tweleve]>12 || state.jugArray[eight]>8 || state.jugArray[three]>3 ){
				return false;
		}
        
        return true;
    }
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) { return 0; }


	public static void main(String[] args) throws Exception {
		Water_jugs problem = new Water_jugs();
		int[] jugArray = {0,0,0};
		
		problem.initialState = new StateWaterJugs(jugArray); 
		
		Search search  = new Search(problem);
		
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		
		System.out.println("AstarTreeSearch:\t" + search.AstarTreeSearch());
		
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
        
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
        
		System.out.println("DepthFirstGraphSearch:\t" + search.DepthFirstGraphSearch());
        
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
        
		System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());
        
		System.out.println("AstarGraphSearch:\t" + search.AstarGraphSearch());
		
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
}