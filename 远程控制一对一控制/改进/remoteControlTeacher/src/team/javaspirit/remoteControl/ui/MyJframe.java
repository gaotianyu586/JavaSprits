package team.javaspirit.remoteControl.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * 
 * <p>
 * Title: MyJframe
 * </p>
 * <p>
 * Description:展示学生截图
 * </p>
 * 
 * @author 何慧霞
 * @date 2018年11月26日
 */
public class MyJframe extends JFrame {

	private static final long serialVersionUID = 1L;//java对象序列化
	private static JLabel jLabel = new JLabel();// 创建一个显示框
	public static JLabel getjLabel() {
		return jLabel;
	}

	public MyJframe(ObjectOutputStream o) {
		super("控制台");
		
		// 获取屏幕的边界
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		// 获取底部任务栏高度
		int taskBarHeight = screenInsets.bottom;
		// 获取屏幕的大小
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		double ratio = screenDimension.getWidth() / screenDimension.getHeight();
		setSize((int)(screenDimension.getWidth()-taskBarHeight*ratio),(int)(screenDimension.getHeight()-taskBarHeight)); //设置打开窗口为屏幕大小
		
		
		setLocation(0, 0);
		//setSize((int) screenDimension.getWidth(), (int) screenDimension.getHeight());
		setVisible(true); // 可见
		setAlwaysOnTop(true);
		setResizable(false);//不可改变jframe的大小
		setDefaultCloseOperation(3); // 关闭方式
		add(jLabel);
		try { // 显示方式
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			//
		}
	

	}

	
	

	/**
	 * 
	 * <p>
	 * Title: setImgLabel
	 * </p>
	 * <p>
	 * Description:等比例缩放
	 * </p>
	 * 
	 * @param icon
	 * @throws IOException
	 */
	public void setImgLabel(BufferedImage icon) throws IOException {
		int width = jLabel.getWidth();  
		int height = jLabel.getHeight();
		BufferedImage Image = resize(icon, width, height);
		ImageIcon newImage=  new ImageIcon(Image);
		jLabel.setIcon(newImage);//把图片添加到jlabel上
		
	}
	/**
	 * 
	 * <p>Title: resize</p>
	 * <p>Description:重置图片的大小 </p>
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) throws IOException {
		
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		// 开启文字抗锯齿
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		// 绘制当前可用的指定图像的指定区域，动态地缩放图像使其符合目标绘制表面的指定区域。
		// 透明像素不影响该处已存在的像素
		g.drawImage(img, 0, 0, newW, newH, null);
		// 销毁程序中指定的图形界面资源
		g.dispose();  
		return dimg;
	}
	

}
