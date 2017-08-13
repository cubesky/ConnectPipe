package cube.AutoControl;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class AutoControlHelper {
  
	Robot robot;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	public AutoControlHelper() throws AWTException{

			robot = new Robot();

	}
	
	public void pressKey(int keyvalue) {
		robot.keyPress(keyvalue);
		robot.keyRelease(keyvalue);
	}
	
	public void pressKeyWithShift(int keyvalue) {
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(keyvalue);
		robot.keyRelease(keyvalue);
		robot.keyRelease(KeyEvent.VK_SHIFT);
	}
	public void pressKeyWithCtrl(int keyvalue) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(keyvalue);
		robot.keyRelease(keyvalue);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	public void closeApplication() {
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_F4);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_F4);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
	}
	/**************************************************************** 
	   * @param s the surname of the snapshot file 
	   * @param format the format of the  image file, 
	   * it can be "jpg" or "png" 
	   * 本构造支持JPG和PNG文件的存储 
	****************************************************************/
	public void ScreenCapture(String s,String format){
	    String fileName = s; 
	    try 
	    { 
	        //拷贝屏幕到一个BufferedImage对象screenshot 
	        BufferedImage screenshot = robot.createScreenCapture(new 
	            Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));  
	        //根据文件前缀变量和文件格式变量，自动生成文件名 
	        String name = fileName; 
	        File f = new File(name);  
	        //将screenshot对象写入图像文件 
	        ImageIO.write(screenshot, format, f); 
	    } 
	    catch (Exception ex) { 
	      System.out.println(ex); 
	    } 
	
	}
	public void robotdelay(int ms){
		robot.delay(ms);
	}
	public void MouseMove(int x, int y) {
	        robot.mouseMove(x, y);
	        

	}
	public void MouseLClick(){
		robot.mousePress(InputEvent.BUTTON1_MASK);
	    robot.delay(10);
	    robot.mouseRelease(InputEvent.BUTTON1_MASK);
	    robot.delay(10);
	}
    public void MouseRClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.delay(10);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
        robot.delay(10);
    }
    public Color getColor(int x,int y){
		return robot.getPixelColor(x, y);
    }
    public Robot getRowRobot(){
    	return robot;
    }
}
