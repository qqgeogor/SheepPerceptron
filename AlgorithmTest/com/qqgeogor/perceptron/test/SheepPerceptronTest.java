package com.qqgeogor.perceptron.test;

import org.apache.mahout.classifier.discriminative.LinearModel;
import org.apache.mahout.classifier.discriminative.PerceptronTrainer;
import org.apache.mahout.classifier.discriminative.TrainingException;
import org.apache.mahout.math.DenseMatrix;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Matrix;
import org.apache.mahout.math.Vector;

import com.qqgeogor.perceptron.myperceptron.SheepPerceptronModel;
import com.qqgeogor.perceptron.myperceptron.SheepPerceptronTrainer;

public class SheepPerceptronTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] d0 = {1.0,1.0,0.0};
		Vector v = new DenseVector(d0);
		
		Matrix m = null;
		//苹果1
		double[] d1 = {1.0,1.0,-1.0};
		//苹果2
		double[] d2= {1.0,1.0,1.0};
		//橘子1
		double[] d3= {-1.0,-1.0,-1.0};
		//不知道
		double[] d4= {-1.0,1,1};
		double[][] matrix = {d1,d2,d3};
		
		
		m = new DenseMatrix(matrix);
		m=m.transpose();
		//m.set(1, d2);
		//double[] d3= {1.0,-1.0,1.0};
		//m.set(2, d3);
		
		SheepPerceptronTrainer trainer = new  SheepPerceptronTrainer(m.rowSize(),0,1.0,-0.5,-0.5);
		
		try {
			trainer.train(v, m);
		} catch (TrainingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//trainer.getModel()
		//System.out.println(trainer.getModel());
		//SheepPerceptronModel model = trainer.getModel();
	
		//double[] W={0.5,0.5,2.5};
		//double bias = 0.0;
		//Vector W_M = new DenseVector(W);
		double[] P={-1.0,-1.0,-1.0};
		//Vector P_M=new DenseVector(P);
		//Vector output = W_M.times(new DenseVector(d3));
		//Vector output = W_M.times(P_M);
		//double a = SheepPerceptronTrainer.hardlim((model.getHyperplane().dot(new DenseVector(d4))+model.getBias()));
		double a = trainer.classify(d4);
		System.out.println(a);
		//System.out.println(model.classify(P_M));
		//System.out.println((output.get(0)));
		
	}

}
