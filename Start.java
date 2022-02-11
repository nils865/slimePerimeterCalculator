/**
 * Starts the process of calculation
 */
public class Start {
    // Attributes
    private Thread t0;
    private Thread t1;
    private Thread t2;
    private Thread t3;
    private Thread t4;
    private Thread t5;

    // Constructor
    public Start() {
        // init all threads
        this.t0 = new Thread(new MultiThread(1));
        this.t1 = new Thread(new MultiThread(1));
        this.t2 = new Thread(new MultiThread(1));
        this.t3 = new Thread(new MultiThread(1));
        this.t4 = new Thread(new MultiThread(1));
        this.t5 = new Thread(new MultiThread(1));

        // start all threads
        this.t0.start();
        this.t1.start();
        this.t2.start();
        this.t3.start();
        this.t4.start();
        this.t5.start();
    }
}
