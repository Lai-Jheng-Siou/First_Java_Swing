import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

public class Sales extends JFrame implements ActionListener {
	private static JFrame frame = null;
	private static JPanel panel,panel_time = null;
	private static JButton sell,refund,Previous_page = null;
	private static JSplitPane split,splitLeft,split_time = null;	
	private static JPanel panelNull,panelNull1 = null;
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //�r��1
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 30);  //�r��2
	private static JTextField time;  //�ʺA�ɶ�
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	
	public Sales() {
		frame = new JFrame("�ĥ��t��");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);

		panel = new JPanel();
		panel.setBackground(Color.black);

		sell = new JButton("�P��");
		sell.setFont(f1);
		sell.addActionListener(this);

		refund = new JButton("�ק�");
		refund.setFont(f1);
		refund.addActionListener(this);

		Previous_page = new JButton("POS�D��");
		Previous_page.setFont(f1);
		Previous_page.addActionListener(this);	
				
		panel.add(sell);
		panel.add(refund);panel.add(Previous_page);
		panel.setLayout(new GridLayout(0,1));
		
		panelNull = new JPanel();  //�Ū�
		panelNull1 = new JPanel(); //�Ū�
		
		panel_time = new JPanel();
		time = new JTextField();       //�ʺA�ɶ�
		time.setBounds(10,0,120,30);
		time.addActionListener(new TimeActionListener());
		time.setEditable(false);
		time.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));  //�]�w�z����
		panel_time.setLayout(new FlowLayout(FlowLayout.LEFT));

		split_time = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel_time.add(time);
		split_time.setTopComponent(panel_time);
		split_time.setBottomComponent(panelNull);
		split_time.setEnabled(false);
		split_time.setDividerSize(0);	
			
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);  //���k����
		split.setRightComponent(split_time); //������@�ӪŪ�JPanel �צ�w�]���s
		frame.getContentPane().add(split);

		splitLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);  //��������
		splitLeft.setTopComponent(panel);
		splitLeft.setBottomComponent(panelNull1);
		split.setLeftComponent(splitLeft);
		split.setEnabled(false); //�����j�u���ಾ��	
		split.setDividerLocation(150);
		splitLeft.setDividerLocation(150);
		splitLeft.setDividerSize(0);
		
		//frame.setLayout(null);  ���঳ ���Msplit�e���X����		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));		
	}
	
	class TimeActionListener implements ActionListener{  //�ʺA�ɶ�
		public TimeActionListener() {
			javax.swing.Timer T = new javax.swing.Timer(1000,this);
			T.start();
		}public void actionPerformed(ActionEvent e){
			time.setText(fmt.format(new java.util.Date()).toString());
		}
	}   //�ʺA�ɶ�
			
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==Previous_page) {
			new Main_page();
			Sales.frame.dispose();
			
		}else if(e.getSource()==sell) {		
			split_time.setBottomComponent(new Sales_TabbedPane().tabbedPane());
			split.setDividerLocation(150);  //������ӳ��]�m  �t�b��e���|�j��null
		
		}else if(e.getSource()==refund) {
			split_time.setBottomComponent(new Sales_Refund().sales_refund());  
			split.setDividerLocation(150);
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

//	public static void main(String[] args) {
//		new Sales();
//	}
}
