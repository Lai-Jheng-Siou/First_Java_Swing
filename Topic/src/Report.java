import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Report extends JFrame implements ActionListener{
	private static JFrame frame = null;
	private static JPanel panel,panelNull = null;
	private static JButton sell,stock,buy,punchin,Previous_page = null;
	private static JSplitPane split = null;
	
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 30);  //字體2
	
	public Report(){
		frame = new JFrame("乖巧系統");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		
		sell = new JButton("銷售報表");
		sell.setFont(f1);
		sell.addActionListener(this);
		buy = new JButton("進貨報表");
		buy.setFont(f1);
		buy.addActionListener(this);
		stock = new JButton("庫存報表");
		stock.setFont(f1);
		stock.addActionListener(this);
		punchin = new JButton("打卡紀錄");
		punchin.setFont(f1);
		punchin.addActionListener(this);
		Previous_page = new JButton("POS主頁");
		Previous_page.setFont(f1);
		Previous_page.addActionListener(this);		
		panel.add(sell);panel.add(buy);panel.add(stock);panel.add(punchin);panel.add(Previous_page);
		panel.setLayout(new FlowLayout());	
		panelNull = new JPanel();
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setDividerLocation(50);
		split.setTopComponent(panel);
		split.setBottomComponent(panelNull);
		split.setDividerSize(1);
		
		frame.getContentPane().add(split);
		//frame.setSize(800,350);
		//frame.setLocationRelativeTo(null);  //默認正中央開啟
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //視窗最大化
		frame.setVisible(true);
		frame.setResizable(false);  //視窗不可調整大小
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Previous_page) {
			new Main_page();
			Report.frame.dispose();
		}else if(e.getSource()==buy) {
			split.setBottomComponent(new Report_buy().report_buy());
			split.setDividerLocation(50);

		}else if(e.getSource()==sell) {
			split.setBottomComponent(new Report_sell().report_sell());
			split.setDividerLocation(50);

		}else if(e.getSource()==stock) {
			split.setBottomComponent(new Report_store().report_store());
			split.setDividerLocation(50);

		}else if(e.getSource()==punchin) {
			split.setBottomComponent(new Report_punchIn().report_punchin());
			split.setDividerLocation(50);
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
//		new Report();
//	}
}
