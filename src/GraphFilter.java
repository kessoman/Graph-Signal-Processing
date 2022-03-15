
public abstract class GraphFilter {
	
	protected GraphSignal testSignal ;
	
	protected Graph graph ;

	public abstract GraphSignal run(Graph graph, GraphSignal testSignal) ;
	
}
