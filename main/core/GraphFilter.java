package core;
import normalizations.*;

public interface GraphFilter {
	public  GraphSignal run(GraphNorm graphNorm, GraphSignal testSignal) ;	
}
