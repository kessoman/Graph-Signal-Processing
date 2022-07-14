package metrics;
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
import core.GraphSignal;

public class MannUTest implements Metric {
	public double calculate(GraphSignal firstSignal, GraphSignal secondSignal) {
		ArrayList<Double> sample1 = new ArrayList<Double>();
		ArrayList<Double> sample2 = new ArrayList<Double>();
		for(Node node : firstSignal.getkeySet()) {
			if(secondSignal.getNodeScore(node) == 1.)
				sample1.add(firstSignal.getNodeScore(node));
			else 
				sample2.add(firstSignal.getNodeScore(node));
		}
		System.out.println("Sample 1 size :" + " " + sample1.size() + " " + "Sample 2 size : " + " " + sample2.size());
		MergeSort sortedSample1 = new MergeSort(sample1);
		MergeSort sortedSample2 = new MergeSort(sample2);
		sortedSample1.sortGivenArray();
		sortedSample2.sortGivenArray();
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
	    ArrayList<Integer> tempRanks = new ArrayList<Integer>();
	    ArrayList<Double> finalRanks = new ArrayList<Double>();
	    for(int z=1; z<mergedSamples.size(); z++) {
	    	tempRanks.add(z);
	    	if(mergedSamples.get(z-1).doubleValue() != mergedSamples.get(z).doubleValue()) {
	    		double mean = calculateMean(tempRanks);
	    		for(int y=0; y<tempRanks.size(); y++) {
	    			finalRanks.add(mean);
	    		}
		    	tempRanks.clear();
	    	}
	    }
	    double sum = 0 ;
	    for(int z=0; z<finalRanks.size(); z++) {
	    	if(ids.get(z)==1) {
	    		sum += finalRanks.get(z);
	    	}
	    }
	    double tempVal = (sample1.size()*(sample1.size() + 1))/2;
	    double u1 = sum - tempVal ;
	    double auc = u1/(sample1.size()*sample2.size());
	    return auc;
	    //System.out.println(auc);
	    }
    public static double calculateMean(ArrayList<Integer> means) {
    	double sum = 0 ;
    	for(int i =0; i<means.size(); i++)
    		sum+=means.get(i);
    	return sum/means.size();
	}
	}



