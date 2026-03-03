package redart15.helver.math;

import java.util.Random;

public class MathHelver {

	private MathHelver(){/* no need to initiate*/}

	/**
	 * @return value between 0 and 1 with a normal distribution
	 * */
	public static double posGausssian(Random random){
		return (random.nextGaussian() + 2.0D)/ 2.0D;
	}

	/**
	 * @return value between 0 and 2 * mean with a normal distribution and expected value of mean
	 * */
	public static double posGausssian(Random random, int mean){
		return MathHelver.posGausssian(random) * 2 * mean;
	}


	public static int posGausssianInt(Random random, int mean){
		return (int) Math.round(posGausssian(random, mean));
	}

	public static int posGausssianIntBounded(Random random, int mean, int lower, int upper){
		return Math.max((Math.min(posGausssianInt(random, mean), upper)), lower);
	}

	/**
	 * @implNote Exponential can return any value between [0, INF) and as such this function caps it.
	 */
	public static int invertedExponentialCapped(Random random, float mean, int cap) {
		return Math.min((int) Math.floor(invertedExponential(random, mean)), cap);
	}

	/**
	 * @implNote Inverse Exponential function where the expected value is the parameter mean.
	 */
	public static double invertedExponential(Random random, float mean) {
		return nextExponential(random) * mean;
	}

	/**
	 * @implNote Generate an exponential distributed random values function with lambda set to 1.
	 */
	public static double nextExponential(Random random) {
		return -Math.log(1 - random.nextDouble());
	}
}
