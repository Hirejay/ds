import java.rmi.Naming;
public class AddServer{
    public static void main(String args[]){
        try {

            AddServerImpl addServerImpl=new AddServerImpl();
            Naming.rebind("//localhost/AddServer",addServerImpl);
            
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
            e.printStackTrace();
        }
    }
}
