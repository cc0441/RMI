import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIn extends Remote {
    void sendMessage(String name, String message) throws RemoteException, NotBoundException;
    void receiveMessage(String message) throws RemoteException;
    void startClient(String name) throws RemoteException, NotBoundException, AlreadyBoundException;
}
