/**
 * Created by Drapegnik on 07.03.17.
 */

package app;

import app.config.Options;
import app.ui.MainWindow;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <p>Class for handling client ui</p>
 *
 * @author Ivan Pazhitnykh
 * @version 1.0
 */
public class Client {

    private Client() {
        int port = Integer.parseInt(System.getenv().get(Options.PORT_ENV_NAME));
        String host = System.getenv().get(Options.HOST_ENV_NAME);

        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            new MainWindow((RemoteService) registry.lookup(RemoteService.class.getSimpleName()));
        } catch (Exception ex) {
            System.err.println("Client exception: " + ex.toString());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {new Client();}
}