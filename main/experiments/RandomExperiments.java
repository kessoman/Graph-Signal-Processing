package experiments;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;
import metrics.*;
import normalizations.*;

public class RandomExperiments {
	public static void main(String[] args) {
		//System.out.println("Creating graph");
		//DiscGraph discGraph = new DiscGraph("pagetest.csv");
		//GraphNorm graphNorm = new GraphNorm(discGraph);
		//PageRank p = new PageRank();
		//GraphSignal graphSignal = new LoadedGraphSignal();
		//for(Node node : discGraph.getNodes()) {
			//graphSignal.setNodeScore(node, 1.);
		//}
		//p.run(graphNorm, graphSignal);
		//for(Edge edge : graphNorm.getEdges())
			//System.out.println(edge.toString() + " " + edge.getEdgeWeight());
		ArrayList<Double> sample1 = new ArrayList<Double>();
		ArrayList<Double> sample2 = new ArrayList<Double>();
		sample1.add(3.);
		sample1.add(2.);
		sample1.add(4.);
		sample1.add(1.);
		sample2.add(6.);
		sample2.add(7.);
		sample2.add(4.);
		sample2.add(5.);
		for(int i=0; i<4; i++) {
			System.out.println("Sample 1 :" + " " + sample1.get(i));
		}
		for(int j=0; j<4; j++) {
			System.out.println("Sample 2 :" + " " + sample2.get(j));
		}
		MergeSort sortedSample1 = new MergeSort(sample1);
		MergeSort sortedSample2 = new MergeSort(sample2);
		sortedSample1.sortGivenArray();
		sortedSample2.sortGivenArray();
		for(double z : sortedSample1.getSortedArray()) {
			System.out.println("Sample 1 sorted : " + " " + z);
		}
		for(double z : sortedSample2.getSortedArray()) {
			System.out.println("Sample 2 sorted : " + " " + z);
		}
		int i = 0, j = 0, k = 0;
	    ArrayList<Double> newSample1 = sortedSample1.getSortedArray();
	    ArrayList<Double> newSample2 = sortedSample2.getSortedArray();
	    ArrayList<Integer> ids = new ArrayList<Integer>();
	    ArrayList<Double> mergedSamples = new ArrayList<Double>();
	    while(i<newSample1.size() && j<newSample2.size()) {
	    	if(newSample1.get(i) < newSample2.get(j)) {
	    		mergedSamples.add(newSample1.get(i));
	    		ids.add(1);
	    		i++;
		    
	    	}
	    	else {
	    		mergedSamples.add(newSample2.get(j));
	    		ids.add(2);
	    		j++;
		    	
	    	}
	    }
	    while(i<sample1.size()) {
	    	mergedSamples.add(newSample1.get(i));
    		ids.add(1);
    		i++;
    		
	    }
	    while(j<sample2.size()) {
	    	mergedSamples.add(newSample2.get(j));
    		ids.add(2);
    		j++;
    		
	    }
	    mergedSamples.add(0.);
	    for(double z : mergedSamples) {
			System.out.println("Samples merged : " + " " + z);
		}
	    ArrayList<Integer> tempRanks = new ArrayList<Integer>();
	    ArrayList<Double> finalRanks = new ArrayList<Double>();
	    for(int z=1; z<mergedSamples.size(); z++) {
	    	tempRanks.add(z);
	    	if(mergedSamples.get(z-1).doubleValue() != mergedSamples.get(z).doubleValue()) {
	    		double mean = calculateMean(tempRanks);
	    		System.out.println("Mean : " + " " + mean);
	    		for(int y=0; y<tempRanks.size(); y++) {
	    			finalRanks.add(mean);
	    		}
		    	tempRanks.clear();
	    	}
	    }
	    for(int z=0; z<finalRanks.size(); z++)
	    	System.out.println(finalRanks.get(z));
	    double sum = 0 ;
	    for(int z=0; z<finalRanks.size(); z++) {
	    	if(ids.get(z)==1) {
	    		sum += finalRanks.get(z);
	    	}
	    }
	    //System.out.println(sum);
	    double tempVal = (sample1.size()*(sample1.size() + 1))/2;
	    double u1 = sum - tempVal ;
	    //System.out.println(u1);
	    double auc = u1/(sample1.size()*sample2.size());
	    //System.out.println(auc);
	    }
    public static double calculateMean(ArrayList<Integer> means) {
    	double sum = 0 ;
    	for(int i =0; i<means.size(); i++)
    		sum+=means.get(i);
    	return sum/means.size();
	}
	
}
	