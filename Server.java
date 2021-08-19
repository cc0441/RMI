import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server{
    public static String name = "Server";
    public static String hostname = "localhost";
    public static int port = 3000;


    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ServerIn server = new RemoteServer();
        hostname=args[0];
        port=Integer.parseInt(args[1]);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind(name, server);
        System.out.println("ChatRoom activated.");
    }

}
