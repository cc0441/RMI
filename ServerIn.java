import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIn extends Remote {
    int getId()throws RemoteException;

    void welcome(String name) throws RemoteException;

    void boardCastToAll(String message, String name) throws RemoteException;

    void clientToAll(String name, String s) throws RemoteException, NotBoundException;

    void removeClient(String name) throws RemoteException, NotBoundException;

    void setNameAndId(String name, int ID) throws RemoteException;
}
