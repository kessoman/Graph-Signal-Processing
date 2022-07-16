package core;
import java.io.* ;
import java.util.* ;
import core.*;
import disc.*;
import loaded.*;

public abstract class Metric {
	public abstract double calculate(GraphSignal nextSignal, GraphSignal previousSignal) ;
}
