import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main_page extends JFrame implements ActionListener{
	private static JFrame frame = null;
	private static JPanel panel,panel1 = null;
	private static JLabel name = null; //���q�W��
	private static JButton in,out,setting,squared,report,exit = null; 

	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //�r��1
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 35);  //�r��2
	
	Main_page(){
		frame = new JFrame("�ĥ��t��");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		//frame.getContentPane().setBackground(Color.white); //�I���C��	
		panel = new ImagePanel();  //�]�m�ʺA��
		panel.setBounds(438,0,100,100);
		panel1 = new ImagePanel1();  //�]�m�ʺA��
		panel1.setBounds(0,0,100,100);
		
		name = new JLabel("�ĥ��_�_�������q");
		name.setBounds(120,30,300,30);
		name.setFont(f2);
				
		out = new JButton("�P��");
		out.setBounds(50,100,290,100);
		out.setFont(f1);
		out.addActionListener(this);
		
		in = new JButton("�i�f");
		in.setBounds(50,225,290,100);
		in.setFont(f1);
		in.addActionListener(this);
		
		setting = new JButton("�]�w");
		setting.setBounds(350,350,140,100);
		setting.setFont(f1);
		setting.addActionListener(this);
		
		squared = new JButton("�M�b");
		squared.setBounds(350,225,140,100);
		squared.setFont(f1);
		squared.addActionListener(this);
		
		report = new JButton("����");
		report.setBounds(350,100,140,100);
		report.setFont(f1);
		report.addActionListener(this);
		
		exit = new JButton("���}/���s�n�J");
		exit.setBounds(50,350,290,100);
		exit.setFont(f1);
		exit.addActionListener(this);
				
		frame.add(panel);frame.add(panel1);
		frame.add(name);
		frame.add(in);frame.add(out);frame.add(setting);frame.add(squared);
		frame.add(report);frame.add(exit);
		frame.setSize(550,500);
		frame.setLocationRelativeTo(null);  //�q�{�������}��
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);  //�������i�վ�j�p
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));
	}
	class ImagePanel extends JPanel {  //�]�m�ʺA��
		public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon("src/GIF/1.gif");
		g.drawImage(icon.getImage(), 0, 0, 100, 100, this);
		}
	}  //�]�m�ʺA�Ϩ�o��
	class ImagePanel1 extends JPanel {  //�]�m�ʺA��
		public void paint(Graphics g) {
		super.paint(g);
		ImageIcon icon = new ImageIcon("src/GIF/18.gif");
		g.drawImage(icon.getImage(), 0, 0, 100, 100, this);
		}
	}  //�]�m�ʺA�Ϩ�o��
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==exit) {
			int i = JOptionPane.showConfirmDialog(null,"�h�X�Ы� YES\n���s�n�J�Ы�NO", "�T�{", JOptionPane.YES_NO_CANCEL_OPTION);
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
			int i = JOptionPane.showConfirmDialog(null,"�T�w�O�_�n�M�b", "�T�{", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "�M�b����");
				Main_page.frame.dispose();
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
