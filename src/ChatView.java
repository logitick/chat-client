import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class ChatView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSend;
	private JMenu mnNewMenu;
	private JMenuBar menuBar;
	private JMenuItem mntmConnect;
	private JMenuItem mntmDisconnect;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JScrollPane scrollPane;
	private JTextArea textArea;


	/**
	 * Create the frame.
	 */
	public ChatView() {
		setTitle("JQuickChatClient");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 719, 467);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Chat");
		mnNewMenu.setMnemonic('C');
		menuBar.add(mnNewMenu);
		
		mntmConnect = new JMenuItem("Connect");
		mntmConnect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmConnect);
		
		mntmDisconnect = new JMenuItem("Disconnect");
		mntmDisconnect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmDisconnect);
		
		separator = new JSeparator();
		mnNewMenu.add(separator);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnNewMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnSend = new JButton("Send");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend))
				.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(getScrollPane(), GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend)))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public String getMessage() {
		return textField.getText();
	}
	
	public void clearMessage() {
		textField.setText("");
	}
	
	public void append(String msg) {
		textArea.append(msg+"\n");	
	}
	
	public void addConnectActionListener(ActionListener m) {
		mntmConnect.addActionListener(m);
	}
	
	public void addDisconnectActionListener(ActionListener m) {
		mntmDisconnect.addActionListener(m);
	}
	
	public void addExitActionListener(ActionListener m) {
		mntmExit.addActionListener(m);
	}
	
	public void addTextFieldActionListener(ActionListener m) {
		textField.addActionListener(m);
	}
	
	public void addBtnSendActionListener() {
		btnSend.addActionListener(textField.getActionListeners()[0]);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setRows(5);
			//textArea.setLineWrap(true);
			textArea.setEditable(false);
		}
		return textArea;
	}
}
