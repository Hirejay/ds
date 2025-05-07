import mpi.MPI;
import mpi.MPIException;


public class DistributedArraySum {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args);  // Initialize MPI

        int rank = MPI.COMM_WORLD.Rank();  // Process rank
        int size = MPI.COMM_WORLD.Size();  // Total number of processes

        int unitSize = 5;  // Elements per process (change as needed)
        int totalSize = unitSize * size;
        int[] sendBuffer = new int[totalSize];
        int[] receiveBuffer = new int[unitSize];
        int[] gatheredSums = new int[size];

        if (rank == 0) {
            System.out.println("Root process: distributing " + totalSize + " elements.");
            for (int i = 0; i < totalSize; i++) {
                sendBuffer[i] = i + 1;  // Example values: 1, 2, ..., totalSize
                System.out.println("Element " + i + " = " + sendBuffer[i]);
            }
        }

        // Scatter the array from root to all processes
        MPI.COMM_WORLD.Scatter(
            sendBuffer, 0, unitSize, MPI.INT,
            receiveBuffer, 0, unitSize, MPI.INT,
            0
        );

        // Calculate local sum
        int localSum = 0;
        for (int i = 0; i < unitSize; i++) {
            localSum += receiveBuffer[i];
        }

        System.out.println("Process " + rank + " calculated intermediate sum: " + localSum);

        // Gather local sums at root
        MPI.COMM_WORLD.Gather(
            new int[]{localSum}, 0, 1, MPI.INT,
            gatheredSums, 0, 1, MPI.INT,
            0
        );

        // Root process calculates final sum
        if (rank == 0) {
            int finalSum = 0;
            for (int sum : gatheredSums) {
                finalSum += sum;
            }
            System.out.println("Final sum of all elements: " + finalSum);
        }

        MPI.Finalize();  // Finalize MPI
    }
}
