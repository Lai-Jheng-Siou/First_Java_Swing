import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Report extends JFrame implements ActionListener{
	private static JFrame frame = null;
	private static JPanel panel,panelNull = null;
	private static JButton sell,stock,buy,punchin,Previous_page = null;
	private static JSplitPane split = null;
	
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //�r��1
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 30);  //�r��2
	
	public Report(){
		frame = new JFrame("�ĥ��t��");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		
		sell = new JButton("�P�����");
		sell.setFont(f1);
		sell.addActionListener(this);
		buy = new JButton("�i�f����");
		buy.setFont(f1);
		buy.addActionListener(this);
		stock = new JButton("�w�s����");
		stock.setFont(f1);
		stock.addActionListener(this);
		punchin = new JButton("���d����");
		punchin.setFont(f1);
		punchin.addActionListener(this);
		Previous_page = new JButton("POS�D��");
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
		//frame.setLocationRelativeTo(null);  //�q�{�������}��
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  //�����̤j��
		frame.setVisible(true);
		frame.setResizable(false);  //�������i�վ�j�p
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
		               "�T�w�n�����{����?",
		               "�T�{�T��",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
		    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
		    }  
		  }
//	public static void main(String[] args) {
//		new Report();
//	}
}
