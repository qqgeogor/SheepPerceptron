package com.qqgeogor.perceptron.myperceptron;

import org.apache.mahout.classifier.discriminative.LinearModel;
import org.apache.mahout.classifier.discriminative.LinearTrainer;
import org.apache.mahout.classifier.discriminative.TrainingException;
import org.apache.mahout.math.CardinalityException;
import org.apache.mahout.math.DenseMatrix;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Matrix;
import org.apache.mahout.math.Vector;

public class SheepPerceptronTrainer{
	
	public static double hardlim(double n){
		return n<0?0:1;
	}
	private SheepPerceptronModel model;
	private double learningRate;
	
	public SheepPerceptronTrainer(int dimension, double threshold,
            double learningRate, double init, double initBias) {
		this(dimension, threshold, init, initBias);
		this.learningRate = learningRate;
}
	public SheepPerceptronTrainer(int dimension, double threshold, double init,
			double initBias) {
			DenseVector initialWeights = new DenseVector(dimension);
		    initialWeights.assign(init);
		    this.model = new SheepPerceptronModel(initialWeights, initBias, threshold);
		// TODO Auto-generated constructor stub
	}
	public double classify(double[] d){
		double a = SheepPerceptronTrainer.hardlim((model.getHyperplane().dot(new DenseVector(d))+model.getBias()));

		return a;
		
	}
	public double classify(Vector dataSet){
		double a = SheepPerceptronTrainer.hardlim((model.getHyperplane().dot(dataSet)+model.getBias()));

		return a;
		
	}

	public void train(Vector labelset, Matrix dataset) throws TrainingException {
		//System.out.println(labelset);
		
		
		if (labelset.size() != dataset.columnSize()){
	      throw new CardinalityException(labelset.size(), dataset.columnSize());
	    }
	    
	    boolean converged = false;
	    int iteration = 0;
	    while (!converged) {
	      if (iteration > 1000) {
	        throw new TrainingException("Too many iterations needed to find hyperplane.");
	    	//  break;
	      }
	      
	      converged = true;
	      int cCount = dataset.columnSize();
	      
	      for (int i = 0; i < cCount; i++) {
	        Vector dataPoint = dataset.viewColumn(i);
	      
	       // log.debug("Training point: {}", dataPoint);
	        
	        synchronized (this.model) {
	          //boolean prediction = this.model.classify(dataPoint);
	          double label = labelset.get(i);
	          double t = label;
	          double a = SheepPerceptronTrainer.hardlim(model.getHyperplane().dot(dataPoint)+model.getBias());
	       //  System.out.println("a: "+a+", t: "+t+", "+dataPoint+model.getHyperplane());
	          double e = t-a;
	          if (e!=0) {
	           // log.debug("updating");
	        	 // System.out.println(e);
	            converged = false;
	            this.update(e, dataPoint, this.model);
	          }
	        }
	      }
	      iteration++;
	    }
	  }
	
	protected void update(double e, Vector dataPoint, SheepPerceptronModel model) {
		// TODO Auto-generated method stub
		 Vector updateVector = dataPoint.times(e).times(this.learningRate);
		    //log.debug("Updatevec: {}", updateVector);
		  //System.out.println("================");
		    //System.out.println(updateVector);
		    model.addDelta(updateVector);
		   // System.out.println("e: "+e);
		    //System.out.println(model.getHyperplane());
		    
		    model.shiftBias(e * this.learningRate);
		    //System.out.println(model.getBias());
		    //System.out.println("================");
		    //log.debug("{}", model);

	}
	public SheepPerceptronModel getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}

}
