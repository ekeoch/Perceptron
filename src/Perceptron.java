/**
 *  The Perceptron Algorithm
 *  By Dr Noureddin Sadawi
 *  Please watch my youtube videos on perceptron for things to make sense!
 *  Copyright (C) 2014
 *  @author Dr Noureddin Sadawi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it as you wish ONLY for legal and ethical purposes
 *
 *  I ask you only, as a professional courtesy, to cite my name, web page
 *  and my YouTube Channel!
 *
 *    Code adapted from:
 *    https://github.com/RichardKnop/ansi-c-perceptron
 */

import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;

class Perceptron
{
    static int MAX_ITER = 100;
    static double LEARNING_RATE = 1;
    static int NUM_INSTANCES = 6;
    static int theta = 0;
    public static void main(String args[]){
        //three variables (features)
        double[] x = {1,1,1,1,1,1};
        double[] y = {1,1,2,3,4,4};
        double[] z = {1,2,1,5,5,6};
        int[] outputs = {0,0,0,1,1,1};

        for(int i = 0; i < NUM_INSTANCES; i++){
            System.out.println(x[i]+"\t"+y[i]+"\t"+z[i]+"\t"+outputs[i]);
        }

        double[] weights = {0,0,0,0};
        double localError, globalError;
        int i, p, iteration, output;

        iteration = 0;
        do {
            iteration++;
            globalError = 0;
            //loop through all instances (complete one epoch)
            for (p = 0; p < NUM_INSTANCES; p++) {
                // calculate predicted class
                output = calculateOutput(theta,weights, x[p], y[p], z[p]);
                // difference between predicted and actual class values
                localError = outputs[p] - output;
                //update weights and bias
                weights[0] += LEARNING_RATE * localError * x[p];
                weights[1] += LEARNING_RATE * localError * y[p];
                weights[2] += LEARNING_RATE * localError * z[p];
                weights[3] += LEARNING_RATE * localError;

                //print out current weight estimate
                System.out.println("Iteration " + iteration + ": [" + weights[0] + " " + weights[1] + " " + weights[2] +"]");


                //summation of squared error (error value for all instances)
                globalError += (localError*localError);
            }

			/* Root Mean Squared Error */
            System.out.println("Iteration "+iteration+" : RMSE = "+Math.sqrt(globalError/NUM_INSTANCES));
        } while (globalError != 0 && iteration<=MAX_ITER);

        System.out.println("\n=======\nDecision boundary equation:");
        System.out.println(weights[0] +"*x + "+weights[1]+"*y +  "+weights[2]+"*z + "+weights[3]+" = 0");

        //generate 10 new random points and check their classes
        //notice the range of -10 and 10 means the new point could be of class 1 or 0
        //-10 to 10 covers all the ranges we used in generating the 50 classes of 1's and 0's above

            double x1 = 1;
            double y1 = 3;
            double z1 = 5;

            output = calculateOutput(theta,weights, x1, y1, z1);
            System.out.println("\n=======\nNew Random Point:");
            System.out.println("x = "+x1+",y = "+y1+ ",z = "+z1);
            System.out.println("class = "+output);
        }
    //end main

    /**
     * returns a random double value within a given range
     * @param min the minimum value of the required range (int)
     * @param max the maximum value of the required range (int)
     * @return a random double value between min and max
     */
    public static double randomNumber(int min , int max) {
        DecimalFormat df = new DecimalFormat("#.####");
        double d = min + Math.random() * (max - min);
        String s = df.format(d);
        double x = Double.parseDouble(s);
        return x;
    }

    /**
     * returns either 1 or 0 using a threshold function
     * theta is 0range
     * @param theta an integer value for the threshold
     * @param weights[] the array of weights
     * @param x the x input value
     * @param y the y input value
     * @param z the z input value
     * @return 1 or 0
     */
    static int calculateOutput(int theta, double weights[], double x, double y, double z)
    {
        double sum = x * weights[0] + y * weights[1] + z * weights[2] + weights[3];
        return (sum > theta) ? 1 : 0;
    }

}