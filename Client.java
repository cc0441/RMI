import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client{
    private static String name;
    public static String hostName = "localhost";
    public static int port = 3000;


    public static void main(String[] args) throws RemoteException {
        try {
            ServerIn s = new RemoteServer();
            hostName = args[0];
            port = Integer.parseInt(args[1]);
            System.out.println("Welcome to the chatroom.");
            Scanner in = new Scanner(System.in);
            System.out.println("Please Enter your name: ");
            name=in.next();
            in.nextLine();
            ClientIn c = new RemoteClient();
            Registry registry = LocateRegistry.getRegistry(hostName, port);
            registry.bind(name, c);
            c.startClient(name);

            System.out.println("Welcome to the chatroom, " + name);
            System.out.println("Send message or enter \"exit\" to leave the chatRoom");

            while(true) {
                String line = in.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    ServerIn server = (ServerIn) registry.lookup("Server");
                    server.removeClient(name);
                    System.out.println(name + " left the chatRoom");
                    break;
                }
                c.sendMessage(name, line);


        }
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
        }
