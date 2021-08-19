import java.io.ObjectStreamClass;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RemoteServer extends UnicastRemoteObject implements ServerIn {
    private List<String> clientsForBroadcast;
    public int id = 1;

    public RemoteServer() throws RemoteException {
        clientsForBroadcast = new ArrayList<>();
    }

    @Override
    public int getId() throws RemoteException {
        return id++;
    }

    @Override
    public void welcome(String name) throws RemoteException{
        clientsForBroadcast.add(name);
    }

    @Override
    public void boardCastToAll(String message, String name) throws RemoteException {
        System.out.println(name + ": " + message);
    }

    @Override
    public void clientToAll(String name, String s) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Server.hostname, Server.port);
        for(int i = 0; i < clientsForBroadcast.size(); i++){
            if(clientsForBroadcast.equals(name)) continue;
            ClientIn c = (ClientIn)registry.lookup(clientsForBroadcast.get(i));
            c.receiveMessage(s);
        }
    }

    @Override
    public void removeClient(String name) throws RemoteException, NotBoundException {
        for(int i = 0; i < clientsForBroadcast.size(); i++){
            if(clientsForBroadcast.get(i).equals((name))){
                clientsForBroadcast.remove(i);
                System.out.println(name + " left the chatRoom");
                clientToAll(name, name + " left the chatRoom");
            }
        }
    }

    public void setNameAndId(String name, int ID) throws RemoteException {
        System.out.println(name+" has joined the ChatRoom");
        System.out.println(name+"'s ID is ="+ID);
    }
}
