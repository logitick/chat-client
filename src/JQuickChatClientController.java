import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Timer;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class JQuickChatClientController  {

	private JQuickChatClient client;
	private ChatView view;
	private InputListener inputs;
	private Timer timer;
	public JQuickChatClientController() {
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		view = new ChatView();
		addListeners();
		view.setVisible(true);
		client = new JQuickChatClient();
		inputs = new InputListener(client, this);
		
	}
	
	public void connect(String ip) throws UnknownHostException, IOException {
		client.connect(ip);
	}
	
	private void addListeners() {
		view.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
				disconnect();
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {}
			
		});
		view.addConnectActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent ae) {
				String ip = JOptionPane.showInputDialog("Enter the server's IP address:");
				try {
					connect(ip);
					client.send(System.getProperty("user.name"));
					inputs.start();
				} catch (UnknownHostException e) {
					JOptionPane.showMessageDialog(view, "Host unknown");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(view, "Unable to connect to "+ ip);
				} catch (IllegalStateException e) {
					
				}
			}
		});
		
		view.addDisconnectActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				disconnect();
			}
			
		});
		view.addExitActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				disconnect();
				System.exit(0);
			}
			
		});
		view.addTextFieldActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.send(view.getMessage());
				view.clearMessage();
			}});
		view.addBtnSendActionListener();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new JQuickChatClientController();
			}
		});
	}
	
	public void appendToView(String msg) {
		view.append(msg);
	}
	
	public void disconnect() {
		try {
			if (client.isConnected()) {
				client.send("/quit");
				client.close();
				inputs.close();
			}
		} catch (IOException ee) {
			
		}
	}
}
