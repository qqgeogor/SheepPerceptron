package com.qqgeogor.perceptron.test;

import org.apache.mahout.classifier.discriminative.LinearModel;
import org.apache.mahout.classifier.discriminative.PerceptronTrainer;
import org.apache.mahout.classifier.discriminative.TrainingException;
import org.apache.mahout.math.DenseMatrix;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Matrix;
import org.apache.mahout.math.Vector;
import org.jfree.util.Log;

public class PerceptronTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] d0 = {1.0,1.0,0};
		Vector v = new DenseVector(d0);
		double[] d1 = {1.0,1.0,-1.0};
		//m.set(0,d1);
		double[] d2= {1.0,1.0,1.0};
		double[] d3= {-1.0,-1.0,-1.0};
		double[][] matrix = {d1,d2,d3};
		Matrix m = new DenseMatrix(matrix);
		m = m.transpose();
		PerceptronTrainer trainer = new PerceptronTrainer(m.rowSize(),0,1.0,-0.5,-0.5);
		
		try {
			
			trainer.train(v, m);
		} catch (TrainingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//trainer.getModel()
		System.out.println(trainer.getModel());
		LinearModel model = trainer.getModel();
	
		//double[] W={0.5,0.5,2.5};
		double bias = 0.0;
		//Vector W_M = new DenseVector(W);
		double[] P={-1.0,-1.0,-1.0};
		Vector P_M=new DenseVector(P);
		//Vector output = W_M.times(new DenseVector(d3));
		//Vector output = W_M.times(P_M);
		System.out.println(model.classify(new DenseVector(d3)));
		System.out.println(model.classify(P_M));
		//System.out.println((output.get(0)));
		
	}

}
