package cube.QQ;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VCodeSwing extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -830446125019493835L;
	private JPanel contentPane;
	private JTextField textField;
	static QQInterface qqii;
	interface QQInterface{
		public void CodeOK(String Code);
		public void CodeFail();
	}
	/**
	 * Launch the application.
	 */
	public static void ShowCode(final String uri,QQInterface qqi) {
		qqii=qqi;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VCodeSwing frame = new VCodeSwing(uri);
					frame.setResizable(false); 
					Image icon = Toolkit.getDefaultToolkit().getImage("");
					frame.setIconImage(icon); 
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
	public VCodeSwing(String uri) {
		setBounds(100, 100, 171, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblVcode = new JLabel("VCode");
		lblVcode.setBounds(26, 10, 93, 31);
		lblVcode.setText("<html><img src='"+uri+"' /><html>");
		contentPane.add(lblVcode);
		
		textField = new JTextField();
		textField.setBounds(26, 51, 93, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					qqii.CodeOK(textField.getText());
				}
			}
		});
		button.setBounds(26, 82, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					qqii.CodeFail();
				}
			}
		});
		button_1.setBounds(26, 115, 93, 23);
		contentPane.add(button_1);
		
		
	}
}
