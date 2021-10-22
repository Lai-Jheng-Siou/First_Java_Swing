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
	private static JLabel acc,pasd = null; //�b�� �K�X����	
	private static JTextField txt1 = null;  //�b����
	private static JPasswordField txt2 = null;  //�K�X��
	private static JButton log,exit,punchIn = null;  //�n�J ���} ���d���s
	
	private static JTextField time;  //�ʺA�ɶ�
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	
	public Login() {
		frame = new JFrame("�w�s�t��-�Х��n�J");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		
		panel = new ImagePanel();  //�]�m�ʺA��
		panel.setBounds(200,0,250,250);
		
		acc = new JLabel("�b��");
		acc.setBounds(25,50,75,30);
		pasd = new JLabel("�K�X");
		pasd.setBounds(25,100,75,30);
		txt1 = new JTextField();
		txt1.setBounds(75,50,125,30);
		txt2 = new JPasswordField();
		txt2.setBounds(75,100,125,30);
		log = new JButton("�n�J");
		log.setBounds(25,150,75,30);
		exit = new JButton("���}");
		exit.setBounds(125,150,75,30);
		log.addActionListener(this);
		exit.addActionListener(this);
		punchIn = new JButton("���d�@�~");
		punchIn.setBounds(25,190,175,30);
		punchIn.addActionListener(this);
		
		time = new JTextField();       //�ʺA�ɶ�
		time.setBounds(0,0,120,30);
		time.addActionListener(new TimeActionListener());
		time.setEditable(false);
		time.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));  //�]�w�z����
		
		frame.add(panel);
		frame.add(acc);frame.add(pasd);
		frame.add(txt1);
		frame.add(txt2);
		frame.add(log);frame.add(exit);frame.add(punchIn);	
		frame.add(time);  //�ʺA�ɶ�
		frame.setSize(475,275);
		//frame.getContentPane().setBackground(Color.cyan); //�]�m�e���I���C��
		frame.setLocationRelativeTo(null);  //�q�{�������}��
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));
	}
	class ImagePanel extends JPanel {  //�]�m�ʺA��
		public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon("src/GIF/2.gif");
		g.drawImage(icon.getImage(), 0, 0, 250, 250, this);
		}
	}  //�]�m�ʺA�Ϩ�o��
	
	class TimeActionListener implements ActionListener{  //�ʺA�ɶ�
		public TimeActionListener() {
			javax.swing.Timer T = new javax.swing.Timer(1000,this);
			T.start();
		}public void actionPerformed(ActionEvent e){
			time.setText(fmt.format(new java.util.Date()).toString());
		}
	}   //�ʺA�ɶ�
	
	public void actionPerformed(ActionEvent e){	
		HashMap<String,String> map = new HashMap<>();  //Ū�X�b���K�X �ó]�m�� Key Value 
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
			int i = JOptionPane.showConfirmDialog(null,"�T�{�n�h�X�ܡH", "�T�{", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION)
				System.exit(0);		
		}else if(e.getSource()==punchIn){
			new PunchIn();
			Login.frame.dispose(); //����������
		}else {
			if(map.containsKey(txt1.getText()) && map.get(txt1.getText()).equals(String.valueOf(txt2.getPassword()))) {				
				JOptionPane.showMessageDialog(null,"�n�J���\");
				new Main_page();
				Login.frame.dispose(); //����������
			}else {
				JOptionPane.showMessageDialog(null, "�b���αK�X���~! �Э��s��J!");
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
		               "�T�w�n�����{����?",
		               "�T�{�T��",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
		    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
		    }  
	}
	public static void main(String[] args) {
		new Login();
	}
}
