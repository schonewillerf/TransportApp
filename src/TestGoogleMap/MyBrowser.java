
package TestGoogleMap;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;



public class MyBrowser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIUtils.setPreferredLookAndFeel();
		NativeInterface.isOpen();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBrowser frame = new MyBrowser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyBrowser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 308);
		contentPane.add(panel);
		
		final JWebBrowser browser = new JWebBrowser();
		browser.navigate("C:\\googlemapsHTML\\simple_map.html");
		panel.add(browser, BorderLayout.CENTER);
	}
}