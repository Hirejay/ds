import java.util.*;

public class BullyAlgo {

    public static class Bully {
        int coordinator;
        int max_processes;
        boolean[] processes;

        public Bully(int max_processes) {
            this.max_processes = max_processes;
            this.coordinator = max_processes - 1;
            this.processes = new boolean[max_processes];
            System.out.println("Creating the processes...");
            for (int i = 0; i < max_processes; i++) {
                processes[i] = true;
                System.out.println("P" + i + " Created");
            }
            System.out.println("Coordinator is: P" + coordinator);
        }

        public void displayProcesses() {
            for (int i = 0; i < max_processes; i++) {
                if (processes[i]) {
                    System.out.println("P" + i + " is UP");
                } else {
                    System.out.println("P" + i + " is DOWN");
                }
            }
            System.out.println("Coordinator is: P" + coordinator);
        }

        public void upProcess(int i) {
            if (i >= max_processes || i < 0) {
                System.out.println("Invalid process ID.");
                return;
            }
            if (!processes[i]) {
                processes[i] = true;
                System.out.println("P" + i + " is now UP");
            } else {
                System.out.println("P" + i + " is already UP");
            }
        }

        public void downProcess(int i) {
            if (i >= max_processes || i < 0) {
                System.out.println("Invalid process ID.");
                return;
            }
            if (processes[i]) {
                processes[i] = false;
                System.out.println("P" + i + " is now DOWN");
            } else {
                System.out.println("P" + i + " is already DOWN");
            }
        }

        public void runElection(int process_id) {
            if (!processes[process_id]) {
                System.out.println("Process P" + process_id + " is down and cannot initiate election.");
                return;
            }

            System.out.println("Election initiated by Process P" + process_id);
            boolean higherAlive = false;

            for (int i = process_id + 1; i < max_processes; i++) {
                System.out.println("Election message sent from P" + process_id + " to P" + i);
                if (processes[i]) {
                    System.out.println("P" + i + " is alive and responds with OK");
                    higherAlive = true;
                    runElection(i); 
                    return; 
                }
            }

            if (!higherAlive) {
                coordinator = process_id;
                System.out.println("P" + process_id + " becomes the new coordinator.");
                announceCoordinator();
            }
        }

        public void announceCoordinator() {
            for (int i = 0; i < max_processes; i++) {
                if (i != coordinator && processes[i]) {
                    System.out.println("Coordinator message sent from P" + coordinator + " to P" + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Bully bully = null;
        int max_processes = 0;
        int process_id = 0;
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bully Algorithm ---");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Processes");
            System.out.println("3. Up Process");
            System.out.println("4. Down Process");
            System.out.println("5. Run Election Algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of processes: ");
                    max_processes = sc.nextInt();
                    bully = new Bully(max_processes);
                    break;

                case 2:
                    if (bully != null)
                        bully.displayProcesses();
                    else
                        System.out.println("Create processes first!");
                    break;

                case 3:
                    System.out.print("Enter process number to bring UP: ");
                    process_id = sc.nextInt();
                    if (bully != null)
                        bully.upProcess(process_id);
                    else
                        System.out.println("Create processes first!");
                    break;

                case 4:
                    System.out.print("Enter process number to bring DOWN: ");
                    process_id = sc.nextInt();
                    if (bully != null)
                        bully.downProcess(process_id);
                    else
                        System.out.println("Create processes first!");
                    break;

                case 5:
                    System.out.print("Enter process number to initiate election: ");
                    process_id = sc.nextInt();
                    if (bully != null)
                        bully.runElection(process_id);
                    else
                        System.out.println("Create processes first!");
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Enter a valid choice.");
            }
        }
    }
}
