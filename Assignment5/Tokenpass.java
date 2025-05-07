
import java.util.*;

public class Tokenpass {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Toatal Number Of Nodes : ");
        int n = sc.nextInt();
        System.out.println("The Node In Ring : ");
        for (int i = 0; i < n; i++) {
            System.out.print(i + " -> ");
        }
        System.out.println(0);

        int token = 0;
        int choice = 0;
        do {
            System.out.println("Enter Sender : ");
            int sender = sc.nextInt();
            System.out.println("Enter Receiver : ");
            int receiver = sc.nextInt();
            System.out.println("Enter Data : ");
            int data = sc.nextInt();
            System.out.println("Token Passing : ");
            for (int i = token; i != sender; i = (i + 1) % n) {
                System.out.print(i + " -> ");
            }
            System.out.println(sender);

            System.out.println("Sender : " + sender + " Sending Data : " + data);
            int temp = (sender + 1) % n;
            for (int i = temp; i != receiver; i = (i + 1) % n) {
                System.out.println("Data " + data + " Forwarded By " + i);
            }

            System.out.println("Receiver " + receiver + " Received Data : " + data);

            token = sender;

            System.out.println("Do you Want To Send Data Again If Yes Enter 1 Or No Enter 0:");
            choice = sc.nextInt();

        } while (choice == 1);
    }
}
