package com.qqgeogor.perceptron.myperceptron;

import org.apache.mahout.classifier.discriminative.LinearModel;
import org.apache.mahout.math.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SheepPerceptronModel {
	private static final Logger log = LoggerFactory
			.getLogger(LinearModel.class);

	public SheepPerceptronModel(Vector hyperplane) {

		this.hyperplane = hyperplane;
		// TODO Auto-generated constructor stub
	}

	private double threshold;

	public double getThreshold() {
		return threshold;
	}

	private double bias;

	public double getBias() {
		return bias;
	}

	private Vector hyperplane;

	public SheepPerceptronModel(Vector hyperplane, double displacement,
			double threshold) {

		this.hyperplane = hyperplane;
		this.threshold = threshold;
		this.bias = displacement;
		// TODO Auto-generated constructor stub
	}

	public Vector getHyperplane() {
		return hyperplane;
	}

	public void addDelta(Vector delta) {
		this.hyperplane = this.hyperplane.plus(delta);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Model: ");
		for (int i = 0; i < this.hyperplane.size(); i++) {
			builder.append("  ").append(this.hyperplane.get(i));
		}
		builder.append(" C: ").append(this.bias);
		return builder.toString();
	}

	/**
	 * Classify a point to either belong to the class modeled by this linear
	 * model or not.
	 * 
	 * @param dataPoint
	 *            the data point to classify.
	 * @return returns true if data point should be classified as belonging to
	 *         this model.
	 */
	public boolean classify(Vector dataPoint) {
		double product = this.hyperplane.dot(dataPoint);
		if (log.isDebugEnabled()) {
			log.debug("model: {} product: {} Bias: {} threshold: {}",
					new Object[] { this, product, bias, threshold });
		}
		return product + this.bias > this.threshold;
	}

	/**
	 * Shift the bias of the model.
	 * 
	 * @param factor
	 *            factor to multiply the bias by.
	 */
	public void shiftBias(double factor) {
		this.bias += factor;
	}

	/**
	 * Multiply the weight at index by delta.
	 * 
	 * @param index
	 *            the index of the element to update.
	 * @param delta
	 *            the delta to multiply the element with.
	 */
	public void timesDelta(int index, double delta) {
		double element = this.hyperplane.get(index);
		element *= delta;
		this.hyperplane.setQuick(index, element);
	}
}
