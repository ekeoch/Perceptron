/**
 * Created by Ekeocha on 1/31/2017.
 *
 * update formula w = w + (y - y(w.x))x
 *
 */
public class PerceptronImpl {

    //intercept
    public static int[] Z = {1,1,1,1,1,1};
    //inputs X,Y
    public static int[] X = {1,1,2,3,4,4};
    public static int[] Y = {1,2,1,5,5,6};
    //outputs
    static int[] OUT = {0,0,0,1,1,1};

    //model w
    int[] weights = {0,0,0};

    //maximum number of iterations
    static int MAX_ITER = 100;
    int iterations = 0;

    int gError = 0;

    int[] computeIdealModel(){
        int error = 0;
        do {
            iterations++;
            for(int i = 0; i < 6; i++){
                error = OUT[i] - computeOutput(weights , Z[i], X[i], Y[i]);
                weights[0] += error * Z[i];
                weights[1] += error * X[i];
                weights[2] += error * Y[i];
            }

        } while(iterations < MAX_ITER && checkModel(weights) == false);
        System.out.println("Iterations: " + iterations);
        return weights;
    }

    boolean checkModel(int[] weights){
        for(int i = 0; i < OUT.length; i++){
            if(computeOutput(weights,Z[i],X[i],Y[i]) != OUT[i]){
                return false;
            }
        }
        return true;
    }

    int computeOutput(int[] model, int x, int y, int z){
        int out = model[0] * x + model[1] * y + model[2] * z;
        return out > 0 ? 1 : 0;
    }

    public static void test(int[] model, int[] x, int[] y, int[] z){

        for(int i = 0; i < 6; i++){
            int out = model[0] * z[i] + model[1] * x[i] + model[2] * y[i];
            System.out.println("[" + z[i] + "\t" + x[i] + "\t" + y[i] + "]\t => " + out );
        }
    }

    public static void main(String[] args) {
        PerceptronImpl Pl = new PerceptronImpl();
        int[] w = Pl.computeIdealModel();
        System.out.println("[" + w[0] + "\t" + w[1] + "\t" + w[2] + "]");
        PerceptronImpl.test(new int[]{-7,0,3},  PerceptronImpl.X, PerceptronImpl.Y, PerceptronImpl.Z);
    }


}
