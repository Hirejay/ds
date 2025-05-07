import java.util.*;
public class RingAlgo{

    public static class Ring{

        int coordinator;
        int max_processes;
        boolean processes[];
        
        Ring(int max_processes){
            this.max_processes=max_processes;
            this.coordinator=max_processes-1;
            this.processes=new boolean[max_processes];
            System.out.println("Creating The Processes");
            for(int i=0;i<max_processes;i++){
                processes[i]=true;
                System.out.println("P"+i+" Created");
            }
            System.out.println("Coordinator Is : P"+coordinator);
        }

        void displayProcesses(){
            for(int i=0;i<max_processes;i++){
                if(processes[i]){
                    System.out.println("P"+i+" Is UP");
                }else{
                    System.out.println("P"+i+" Is DOWN");
                }
            }
            System.out.println("Coordinator Is : P"+coordinator);
        }

        void upProcess(int process_id){
            if(process_id<0 || process_id>=max_processes){
                System.out.println("Invalid Process ID");
                return;
            }
            if(!processes[process_id]){
                processes[process_id]=true;
                System.out.println("Process P"+process_id+" Is UP Now");
            }else{
                System.out.println("Process P"+process_id+" Is Already UP");
            }
        }

        void downProcess(int process_id){
            if(process_id<0 || process_id>=max_processes){
                System.out.println("Invalid Process ID");
                return;
            }
            if(processes[process_id]){
                processes[process_id]=false;
                System.out.println("Process P"+process_id+" Is DOWN Now");
            }else{
                System.out.println("Process P"+process_id+" Is Already DOWN");
            }
        }

        void displayArrayList(ArrayList<Integer> pidlist){
            System.out.print("[");
            for(int i=0;i<pidlist.size();i++){
                System.out.print(" "+pidlist.get(i)+" ");
            }
            System.out.println("]");
        }


        void initElection(int process_id){
            if(process_id<0 || process_id>=max_processes){
                System.out.println("Invalid Process Id");
                return;
            }
            if(processes[process_id]==false){
                System.out.println("P"+process_id+" Is Down and Can Not Initiate Election");
                return;
            }
            ArrayList<Integer> pidlist=new ArrayList<>();
            int current=process_id;
            System.out.println("Election Initiated By P"+process_id);
            do { 
                if(processes[current]){
                    pidlist.add(current);
                    System.out.println("P"+current+" Sends List: ");
                    displayArrayList(pidlist);
                }
                current=(current+1)%max_processes;
                
            } while (current!=process_id);
            coordinator=Collections.max(pidlist);
            System.out.println("P"+coordinator+" Is Elected As New Coordinator");
        }

    }
    public static void main(String args[]){

        Ring ring=null;
        int choice=0;
        int max_processes=0;
        int process_id;
        Scanner sc=new Scanner(System.in);


        while(true){
            System.out.println("Ring Algorithm");
            System.out.println("1. Create Processes");
            System.out.println("2. Display Processes");
            System.out.println("3. UP Process");
            System.out.println("4. DOWN Process");
            System.out.println("5. Run Election Algorithm");
            System.out.println("6. Exit Program");
            System.out.println("Enter Your Choice: ");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter The Total Number Of Processes:");
                    max_processes=sc.nextInt();
                    ring=new Ring(max_processes);
                    break;

                case 2:
                    if(ring!=null){
                        ring.displayProcesses();
                    }else{
                        System.out.println("Create Processes First");
                    }
                    break;
                
                case 3:
                    if(ring!=null){
                        System.out.println("Enter The Process Number To Bring UP : ");
                        process_id=sc.nextInt();
                        ring.upProcess(process_id);
                    }else{
                        System.out.println("Create Processes First");
                    }
                    break;

                case 4:
                    if(ring!=null){
                        System.out.println("Enter The Process Number To Bring DOWN : ");
                        process_id=sc.nextInt();
                        ring.downProcess(process_id);
                    }else{
                        System.out.println("Create Processes First");
                    }
                    break;

                case 5:
                    if(ring!=null){
                        System.out.println("Enter The Process To Initiate Election : ");
                        process_id=sc.nextInt();
                        ring.initElection(process_id);
                    }else{
                        System.out.println("Create Processes First");
                    }
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Enter The Valid Choice");

            }
        }


    }
}