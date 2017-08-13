package cube.QQ;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

import cube.Utils.ByteImage;

public class VCode {

	protected Shell shell;
	static QQInterface qi;
	private Text text;
	private static byte[] ii;
	interface QQInterface{
		public void CodeOK(String Code);
		public void CodeFail();
	}
	public VCode(){}
	public VCode(byte[] codeicon,QQInterface Iqq){
		if(Iqq==null){
			System.exit(0);
		}
		qi=Iqq;
		ii=codeicon;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public void ShowCode() {
		
		try {
			VCode window = new VCode();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(display);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Display display) {
		shell = new Shell(display,SWT.CLOSE);
		shell.setSize(284, 156);
		shell.setText("\u8BF7\u8F93\u5165\u9A8C\u8BC1\u7801");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(70, 10, 139, 44);
		label.setImage(ByteImage.byte2Image(ii));
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(54, 60, 171, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				final String i=text.getText();
				Thread t=new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						qi.CodeOK(i);
						
					}
					
				});
				t.start();
				shell.close();
			}
		});
		button.setBounds(23, 89, 80, 27);
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Thread t=new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						qi.CodeFail();
						
					}
					
				});
				t.start();
				shell.close();
			}
		});
		button_1.setBounds(172, 89, 80, 27);
		button_1.setText("\u53D6\u6D88");

	}
}
