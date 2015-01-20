package goFishServer;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerLogFrame extends JFrame implements SocketEventListener {

	private static final long serialVersionUID = 1L;
	private JTextArea area;

	public ServerLogFrame(String title) {

		setSize(800, 600);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		area = new JTextArea();

		setLayout(new BorderLayout());
		add(new JScrollPane(area), BorderLayout.CENTER);

	}

	public JTextArea getArea() {
		return area;
	}

	public void append(String text) {
		area.append(text);
		area.append("\n");
	}

	@Override
	public void onConnect(Socket socket) {
		append("CONNECT " + socket.getInetAddress().toString());
	}

	@Override
	public void onDisconnect(Socket socket) {
		append("DISCONNECT " + socket.getInetAddress().toString());
	}

	@Override
	public void onMessage(Socket socket, String message) {
		append(socket.getInetAddress().toString() + " " + message);
	}

	@Override
	public void onServerStart(ServerSocket serverSocket) {
		append("GO FISH SERVER START " + serverSocket.getLocalPort());
	}

	public static void main(String args[]) throws Exception {
		ServerLogFrame frame = new ServerLogFrame("Server");
		frame.setVisible(true);
		MultiChatServer server = new MultiChatServer(3773, frame);
		server.run();
	}

}
