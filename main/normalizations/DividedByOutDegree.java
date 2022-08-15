package normalizations;
import core.Edge;
import core.Graph;
import core.Normalization;
public class DividedByOutDegree extends Normalization{
	public DividedByOutDegree(Graph graph) {
		super(graph);
	}
	@Override
	public double calculateWeight(Edge edge) {
		return 1 / graph.getOutDegree(edge.getSource());
	}
}
