import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class RemoteClient extends UnicastRemoteObject implements ClientIn {

    private ServerIn server;
    Scanner in = new Scanner(System.in);
    public RemoteClient() throws RemoteException {
    }

    @Override
    public void sendMessage(String name, String message) throws RemoteException, NotBoundException {
        server.boardCastToAll(message, name);
        server.clientToAll(name, name + ": " + message);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public void startClient(String name) throws RemoteException, NotBoundException, AlreadyBoundException {
        Registry registry = LocateRegistry.getRegistry(Server.hostname, Server.port);
        server = (ServerIn)registry.lookup("Server");
        server.setNameAndId(name, server.getId());
        server.welcome(name);
    }



}
