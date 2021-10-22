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
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 30);  //字體2
	private static JTextField time;  //動態時間
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	
	public Sales() {
		frame = new JFrame("乖巧系統");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);

		panel = new JPanel();
		panel.setBackground(Color.black);

		sell = new JButton("銷售");
		sell.setFont(f1);
		sell.addActionListener(this);

		refund = new JButton("修改");
		refund.setFont(f1);
		refund.addActionListener(this);

		Previous_page = new JButton("POS主頁");
		Previous_page.setFont(f1);
		Previous_page.addActionListener(this);	
				
		panel.add(sell);
		panel.add(refund);panel.add(Previous_page);
		panel.setLayout(new GridLayout(0,1));
		
		panelNull = new JPanel();  //空的
		panelNull1 = new JPanel(); //空的
		
		panel_time = new JPanel();
		time = new JTextField();       //動態時間
		time.setBounds(10,0,120,30);
		time.addActionListener(new TimeActionListener());
		time.setEditable(false);
		time.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));  //設定透明框
		panel_time.setLayout(new FlowLayout(FlowLayout.LEFT));

		split_time = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panel_time.add(time);
		split_time.setTopComponent(panel_time);
		split_time.setBottomComponent(panelNull);
		split_time.setEnabled(false);
		split_time.setDividerSize(0);	
			
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);  //左右分割
		split.setRightComponent(split_time); //必須放一個空的JPanel 擋住預設按鈕
		frame.getContentPane().add(split);

		splitLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT);  //垂直分割
		splitLeft.setTopComponent(panel);
		splitLeft.setBottomComponent(panelNull1);
		split.setLeftComponent(splitLeft);
		split.setEnabled(false); //讓分隔線不能移動	
		split.setDividerLocation(150);
		splitLeft.setDividerLocation(150);
		splitLeft.setDividerSize(0);
		
		//frame.setLayout(null);  不能有 不然split畫面出不來		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));		
	}
	
	class TimeActionListener implements ActionListener{  //動態時間
		public TimeActionListener() {
			javax.swing.Timer T = new javax.swing.Timer(1000,this);
			T.start();
		}public void actionPerformed(ActionEvent e){
			time.setText(fmt.format(new java.util.Date()).toString());
		}
	}   //動態時間
			
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==Previous_page) {
			new Main_page();
			Sales.frame.dispose();
			
		}else if(e.getSource()==sell) {		
			split_time.setBottomComponent(new Sales_TabbedPane().tabbedPane());
			split.setDividerLocation(150);  //必須兩個都設置  另半邊畫面會強制null
		
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
		               "確定要結束程式嗎?",
		               "確認訊息",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
		    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
		    }  
		  }

//	public static void main(String[] args) {
//		new Sales();
//	}
}
