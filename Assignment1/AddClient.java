import java.rmi.Naming;
public class AddClient {
    public static void main(String args[]){
        if(args.length>3){
            System.out.println("Enter The Argument In <Server_IP> <Num1> <Num2>" );
            return;
        }
        try {
            AddServerIntf addServerIntf=(AddServerIntf)Naming.lookup("//"+args[0]+"/AddServer");
            System.out.println("Number 1 : "+args[1]);
            double d1=Double.parseDouble(args[1]);
            System.out.println("Number 2 : "+args[2]);
            double d2=Double.parseDouble(args[2]);
            System.out.println("Addition Is : "+addServerIntf.add(d1,d2));

            
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
            e.printStackTrace();
        }
    }
}
