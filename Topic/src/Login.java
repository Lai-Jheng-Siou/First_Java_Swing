import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Login extends JFrame implements ActionListener{
	private static JFrame frame = null;
	private static JPanel panel = null;
	private static JLabel acc,pasd = null; //帳號 密碼標籤	
	private static JTextField txt1 = null;  //帳號框
	private static JPasswordField txt2 = null;  //密碼框
	private static JButton log,exit,punchIn = null;  //登入 離開 打卡按鈕
	
	private static JTextField time;  //動態時間
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	
	public Login() {
		frame = new JFrame("庫存系統-請先登入");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		
		panel = new ImagePanel();  //設置動態圖
		panel.setBounds(200,0,250,250);
		
		acc = new JLabel("帳號");
		acc.setBounds(25,50,75,30);
		pasd = new JLabel("密碼");
		pasd.setBounds(25,100,75,30);
		txt1 = new JTextField();
		txt1.setBounds(75,50,125,30);
		txt2 = new JPasswordField();
		txt2.setBounds(75,100,125,30);
		log = new JButton("登入");
		log.setBounds(25,150,75,30);
		exit = new JButton("離開");
		exit.setBounds(125,150,75,30);
		log.addActionListener(this);
		exit.addActionListener(this);
		punchIn = new JButton("打卡作業");
		punchIn.setBounds(25,190,175,30);
		punchIn.addActionListener(this);
		
		time = new JTextField();       //動態時間
		time.setBounds(0,0,120,30);
		time.addActionListener(new TimeActionListener());
		time.setEditable(false);
		time.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));  //設定透明框
		
		frame.add(panel);
		frame.add(acc);frame.add(pasd);
		frame.add(txt1);
		frame.add(txt2);
		frame.add(log);frame.add(exit);frame.add(punchIn);	
		frame.add(time);  //動態時間
		frame.setSize(475,275);
		//frame.getContentPane().setBackground(Color.cyan); //設置畫布背景顏色
		frame.setLocationRelativeTo(null);  //默認正中央開啟
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));
	}
	class ImagePanel extends JPanel {  //設置動態圖
		public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon("src/GIF/2.gif");
		g.drawImage(icon.getImage(), 0, 0, 250, 250, this);
		}
	}  //設置動態圖到這邊
	
	class TimeActionListener implements ActionListener{  //動態時間
		public TimeActionListener() {
			javax.swing.Timer T = new javax.swing.Timer(1000,this);
			T.start();
		}public void actionPerformed(ActionEvent e){
			time.setText(fmt.format(new java.util.Date()).toString());
		}
	}   //動態時間
	
	public void actionPerformed(ActionEvent e){	
		HashMap<String,String> map = new HashMap<>();  //讀出帳號密碼 並設置成 Key Value 
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/Info/Password.csv"), "utf-8"));
			String line="";
			String[] arrs=null;
			while ((line=br.readLine())!=null) {
				arrs=line.split(",");
				map.put(arrs[1], arrs[2]);
				}
			 br.close();
			 }catch(Exception a) {
				 System.out.println(a.getMessage());
			 }			
		if(e.getSource()==exit) {
			int i = JOptionPane.showConfirmDialog(null,"確認要退出嗎？", "確認", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION)
				System.exit(0);		
		}else if(e.getSource()==punchIn){
			new PunchIn();
			Login.frame.dispose(); //關閉本視窗
		}else {
			if(map.containsKey(txt1.getText()) && map.get(txt1.getText()).equals(String.valueOf(txt2.getPassword()))) {				
				JOptionPane.showMessageDialog(null,"登入成功");
				new Main_page();
				Login.frame.dispose(); //關閉本視窗
			}else {
				JOptionPane.showMessageDialog(null, "帳號或密碼錯誤! 請重新輸入!");
				txt1.setText("");
				txt2.setText("");
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
	public static void main(String[] args) {
		new Login();
	}
}
