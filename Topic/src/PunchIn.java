import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.awt.Graphics;
import java.io.*;

public class PunchIn extends JFrame implements ActionListener{
	private static JFrame frame = null;
	private static JPanel panel = null;
	private static JLabel Acc,Pasd = null;  //�b�� �K�X����
	private static JTextField acc = null;  //�b����
	private static JPasswordField pasd = null;  //�K�X��
	private static JButton log,exit = null;  //���d ���}���s
	private static JTextField time;  //�ʺA�ɶ�
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");	
	SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-M-d");
	Date date = new Date();
	SimpleDateFormat dt = 
		      new SimpleDateFormat ("yyyy.MM.dd E H:mm:ss ");
	
	public PunchIn(){
		frame = new JFrame("���d�@�~");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		
		panel = new ImagePanel();  //�]�m�ʺA��
		panel.setBounds(200,0,200,200);
		
		Acc = new JLabel("�b��");
		Acc.setBounds(25,50,75,30);
		Pasd = new JLabel("�K�X");
		Pasd.setBounds(25,100,75,30);
		acc = new JTextField();
		acc.setBounds(75,50,125,30);
		pasd = new JPasswordField();
		pasd.setBounds(75,100,125,30);
		log = new JButton("���d");
		log.setBounds(25,150,75,30);
		exit = new JButton("���}");
		exit.setBounds(125,150,75,30);
		log.addActionListener(this);
		exit.addActionListener(this);
		
		time = new JTextField();       //�ʺA�ɶ�
		time.setBounds(0,0,120,30);
		time.addActionListener(new TimeActionListener());
		time.setEditable(false);
		time.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));  //�]�w�z����
		
		frame.add(panel);
		frame.add(Acc);frame.add(Pasd);
		frame.add(acc);
		frame.add(pasd);
		frame.add(log);frame.add(exit);	
		frame.add(time);  //�ʺA�ɶ�
		frame.setSize(400,230);
		frame.setLocationRelativeTo(null);
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
		ImageIcon icon = new ImageIcon("src/GIF/17.gif");
		g.drawImage(icon.getImage(), 0, 0, 200, 200, this);
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
		HashMap<String,String> map = new HashMap<>();
		HashMap<String,String> punch = new HashMap<>();
		try {  //�N�b���K�X�]�m�� Key Value
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/Info/Password.csv"), "utf-8"));
			String line="";
			String[] arrs=null;
			while ((line=br.readLine())!=null) {
				arrs=line.split(",");
				map.put(arrs[1], arrs[2]);
				punch.put(arrs[0], arrs[1]);
				}
			 br.close();
			 }catch(Exception a) {
				 System.out.println(a.getMessage());
			 }
		try {  //�إߤ����Ƨ�
			String folder = String.format("src/Info/PunchIn/%s",fmt1.format(new java.util.Date()).toString() );
			File file = new File(folder);
			if  (!file .exists()  && !file .isDirectory()){  //�إߤ����Ƨ� 
			    file .mkdir();    
			} 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(e.getSource()==exit) {
			new Login();
			PunchIn.frame.dispose(); //����������
		}else if(e.getSource()==log){
			String pun = String.format("src/Info/PunchIn/%s/PunchIn.csv",fmt1.format(new java.util.Date()).toString() ); //�إ�csv�ɮ׸��|
			File FileName = new File(pun);
			if(map.containsKey(acc.getText()) && map.get(acc.getText()).equals(String.valueOf(pasd.getPassword()))) {
				if(FileName.exists()) {
					try {
						BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pun,true),"utf-8"));
						String a = null ;
						String b = acc.getText();
						for(String i:punch.keySet()) {
							if (punch.get(i).equals(acc.getText())) {
								a = i;
							}	
						}
						String c = fmt.format(new java.util.Date()).toString();
						br.write(a+",");br.write(b+",");br.write(c+",\n");
						br.flush();
						br.close();
						JOptionPane.showMessageDialog(null,"���d���\\n"+dt.format(date));
						new Login();
						PunchIn.frame.dispose(); //����������
					}catch(Exception a){
						System.out.println(a.getMessage());
					}
				}else {
					try {
						BufferedWriter br = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pun),"utf-8"));
						br.write("User Name"+",");br.write("Account"+",");br.write("PunchIn time"+"\n");
						String a = null ;
						String b = acc.getText();
						for(String i:punch.keySet()) {
							if (punch.get(i).equals(acc.getText())) {
								a = i;
							}	
						}
						String c = fmt.format(new java.util.Date()).toString();
						br.write(a+",");br.write(b+",");br.write(c+",\n");
						br.flush();
						br.close();
						JOptionPane.showMessageDialog(null,"���d���\\n"+dt.format(date));
						new Login();
						PunchIn.frame.dispose(); //����������
					}catch(Exception a){
						System.out.println(a.getMessage());
					}
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "�b���αK�X���~! �Э��s��J!");
				acc.setText("");
				pasd.setText("");
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
}
