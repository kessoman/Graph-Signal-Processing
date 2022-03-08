
public abstract class GraphFilter {
	
	protected GraphSignal testSignal ;
	
	protected Graph graph ;

	public abstract GraphSignal Run(Graph graph, GraphSignal testSignal) ;
	
}
