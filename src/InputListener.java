import java.io.BufferedReader;
import java.io.IOException;
import java.util.TimerTask;


public class InputListener implements Runnable {
	private Thread t;
	private JQuickChatClient client;
	private JQuickChatClientController controller;
	public InputListener(JQuickChatClient client, JQuickChatClientController controller) {
		this.client = client;
		this.controller = controller;
		t = new Thread(this);
	}
	@Override
	public void run() {
		BufferedReader input = client.getInput();
		String fromServer = null;
		while (true) {
			;
			try {
				fromServer = input.readLine();
				if (fromServer != null && !fromServer.isEmpty())
					controller.appendToView(fromServer);
				
			} catch (IOException e) {
				
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
			
		
	}
	
	public void start() {
		t.start();
	}
	public void close() {
		t.interrupt();
	}
}
