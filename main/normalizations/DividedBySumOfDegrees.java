package normalizations;
import core.Edge;
import core.Graph;
import core.Normalization;
public class DividedBySumOfDegrees extends Normalization{
	public DividedBySumOfDegrees(Graph graph) {
		super(graph);
	}
	@Override
	public double calculateWeight(Edge edge) {
		return 1/(Math.sqrt(graph.getOutDegree(edge.getSource()) * graph.getInDegree(edge.getDestination())));
	}
}
