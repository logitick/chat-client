import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class JQuickChatClient {
	private String user;
	private Socket socket;
	private final int SERVER_PORT = 5000;
    private PrintWriter output = null;
    private BufferedReader input = null;
    private InputStreamReader inputStream = null;
    public JQuickChatClient() {
    	user = System.getProperty("user.name");
				
    }
    
    public BufferedReader getInput() {
		return input;
	}

	public void connect(String ip) throws UnknownHostException, IOException {
    	socket = new Socket(InetAddress.getByAddress(JQuickChatClient.stringToAddr(ip)), SERVER_PORT);
    	output = new PrintWriter(socket.getOutputStream(), true);
    	inputStream = new InputStreamReader(socket.getInputStream());
    	input = new BufferedReader(inputStream);
    }
    
	protected static byte[] stringToAddr(String ip) {

	    byte addr[] = new byte[4];
	    int beg = 0;
	    int end = ip.indexOf('.'); // index of the . character in the ip address
	    for (int i = 0; i < addr.length; i++) {
	        addr[i] = Byte.parseByte(ip.substring(beg, end));
	        if (ip.indexOf('.') > -1)
	            ip = ip.substring(end+1, ip.length());
	        end = ip.indexOf('.') > -1 ? ip.indexOf('.') : ip.length();
	    }
	    return addr;
	}
	
	public void send(String str) {
		output.println(str);
	}
	
	public boolean isClosed() {
		return socket.isClosed();
	}

	public PrintWriter getOutput() {
		return output;
	}
	
	public void close() throws IOException {
		output.close();
		inputStream.close();
		input.close();
		socket.close();
	}

	
	public boolean isConnected() {
		return socket != null && socket.isConnected();
	}
}
