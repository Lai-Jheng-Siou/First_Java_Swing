import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main_page extends JFrame implements ActionListener{
	private static JFrame frame = null;
	private static JPanel panel,panel1 = null;
	private static JLabel name = null; //公司名稱
	private static JButton in,out,setting,squared,report,exit = null; 

	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 35);  //字體2
	
	Main_page(){
		frame = new JFrame("乖巧系統");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		//frame.getContentPane().setBackground(Color.white); //背景顏色	
		panel = new ImagePanel();  //設置動態圖
		panel.setBounds(438,0,100,100);
		panel1 = new ImagePanel1();  //設置動態圖
		panel1.setBounds(0,0,100,100);
		
		name = new JLabel("乖巧寶寶有限公司");
		name.setBounds(120,30,300,30);
		name.setFont(f2);
				
		out = new JButton("銷售");
		out.setBounds(50,100,290,100);
		out.setFont(f1);
		out.addActionListener(this);
		
		in = new JButton("進貨");
		in.setBounds(50,225,290,100);
		in.setFont(f1);
		in.addActionListener(this);
		
		setting = new JButton("設定");
		setting.setBounds(350,350,140,100);
		setting.setFont(f1);
		setting.addActionListener(this);
		
		squared = new JButton("清帳");
		squared.setBounds(350,225,140,100);
		squared.setFont(f1);
		squared.addActionListener(this);
		
		report = new JButton("報表");
		report.setBounds(350,100,140,100);
		report.setFont(f1);
		report.addActionListener(this);
		
		exit = new JButton("離開/重新登入");
		exit.setBounds(50,350,290,100);
		exit.setFont(f1);
		exit.addActionListener(this);
				
		frame.add(panel);frame.add(panel1);
		frame.add(name);
		frame.add(in);frame.add(out);frame.add(setting);frame.add(squared);
		frame.add(report);frame.add(exit);
		frame.setSize(550,500);
		frame.setLocationRelativeTo(null);  //默認正中央開啟
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);  //視窗不可調整大小
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));
	}
	class ImagePanel extends JPanel {  //設置動態圖
		public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon("src/GIF/1.gif");
		g.drawImage(icon.getImage(), 0, 0, 100, 100, this);
		}
	}  //設置動態圖到這邊
	class ImagePanel1 extends JPanel {  //設置動態圖
		public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon("src/GIF/18.gif");
		g.drawImage(icon.getImage(), 0, 0, 100, 100, this);
		}
	}  //設置動態圖到這邊
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==exit) {
			int i = JOptionPane.showConfirmDialog(null,"退出請按 YES\n重新登入請按NO", "確認", JOptionPane.YES_NO_CANCEL_OPTION);
			if(i == JOptionPane.YES_OPTION)
				System.exit(0);
			else if(i==JOptionPane.NO_OPTION){
				new Login();
				Main_page.frame.dispose();
			}			
		}else if(e.getSource() == report) {
			new Report();
			Main_page.frame.dispose();
		}else if(e.getSource() == out) {
			new Sales();
			Main_page.frame.dispose();
		}else if(e.getSource() == in) {
			new Purchase();
			Main_page.frame.dispose();
		}else if(e.getSource()==setting) {
			new Setting();
			Main_page.frame.dispose();
		}else if(e.getSource()==squared) {
			int i = JOptionPane.showConfirmDialog(null,"確定是否要清帳", "確認", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "清帳完成");
				Main_page.frame.dispose();
			}		
		}
	}
	class WindowHandler extends WindowAdapter {
		  JFrame frame;
		  public WindowHandler(JFrame frame) {this.frame=frame;}
		  public void windowClosing(WindowEvent e) {
		    int result=JOptionPane.showConfirmDialog(frame,
		               "確定要結束程式嗎?",
		               "確認訊息",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
		    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
		  }  
	}	
}
