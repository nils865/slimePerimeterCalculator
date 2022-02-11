/**
 * Does the calculation in 6 threads
 */
public class MultiThread implements Runnable {
    // Attributes
    private Calculation c;
    private int startNum;

    /**
     * Constructor
     * @param startNum
     */
    public MultiThread(int startNum) {
        this.c = new Calculation();
        this.startNum = startNum;
    }
    
    /**
     * Does the Multithreading 
     * @Override run method
     */
    @Override
    public void run() {
        // TODO implement the main calculation
    }
}