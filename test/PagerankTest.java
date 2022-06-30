import org.junit.Assert;
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
import experiments.*;
import filters.LegacyPageRank;

import org.junit.Test;

import core.GraphFilter;

public class PagerankTest {
	@Test
	public void positiveTest() {
		ArrayList<Double> sample1 = new ArrayList<Double>();
		ArrayList<Double> sample2 = new ArrayList<Double>();
		sample1.add(0, 6.);
		sample1.add(1, 5.);
		sample1.add(2, 4.);
		sample1.add(3, 3.);
		sample1.add(4, 2.);
		sample1.add(5, 1.);
		sample2.add(0, 1.);
		sample2.add(1, 2.);
		sample2.add(2, 3.);
		sample2.add(3, 4.);
		sample2.add(4, 5.);
		sample2.add(5, 6.);
	    RandomExperiments.mergeSort(sample1, sample1.size());
	    Assert.assertArrayEquals(sample2.toArray(), sample1.toArray());
	}
}
