import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Report_punchIn implements ActionListener{
	private JPanel panel,panel2 = null;
	private JLabel lb = null;
	private JButton btn = null;
	private JComboBox<Object> year,month,day = null;
	private JTable table = null;
	private JScrollPane scroll = null;
	private JSplitPane split = null;	
	
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	public JSplitPane report_punchin() {	
		panel = new JPanel();
		
		lb = new JLabel("打卡紀錄:yyyy-MM-dd   開始時間:");

		String[] years = new String[980];
		for(int i=0;i<980;i++) {
			years[i] = i+2020+"";
		}
		year = new JComboBox<Object>(years);

		String[] months = new String[12];
		for(int i=0;i<12;i++) {
			months[i] = i+1+"";
		}
		month = new JComboBox<Object>(months);
		
		String[] days = new String[31];
		for(int i=0;i<31;i++) {
			days[i] = i+1+"";
		}
		day = new JComboBox<Object>(days);	
				
		btn = new JButton("導出");
		btn.addActionListener(this);
				
		panel.add(lb);panel.add(year);panel.add(month);panel.add(day);panel.add(btn);
		panel.setLayout(new FlowLayout());
		
		panel2 = new JPanel();
		table = new JTable();
		scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel2.setLayout(new BorderLayout());
		panel2.add(scroll);
		
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(panel);
		split.setBottomComponent(panel2);	
		split.setEnabled(false); //讓分隔線不能移動	
		split.setDividerLocation(50);
		split.setDividerSize(0);
		
		return split;
	}
	public void actionPerformed(ActionEvent e) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if(e.getSource()==btn) {
			model.setRowCount(0);
			
			String Cyear = ""+year.getItemAt(year.getSelectedIndex());     //抓出年份
			String Cmonth = ""+month.getItemAt(month.getSelectedIndex());  //抓出月份
			String Cday = ""+day.getItemAt(day.getSelectedIndex());        //抓出日
			String start = Cyear+"-"+Cmonth+"-"+Cday;
								
			String folderStart = String.format("src/Info/PunchIn/%s/PunchIn.csv",start);

			String[] data = new String[0];
			int count =0;  //數有幾行
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(folderStart), "utf-8"));
				String line="";
				String[] arrs = null;
				
				while ((line=br.readLine())!=null) {
					arrs=line.split(",");	
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[0];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[1];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[2];
					count++;
					}
				 br.close();
				 					
				 Vector<String> columnNames = new Vector<String>();
				 columnNames.add("使用者名稱");columnNames.add("帳號");columnNames.add("時間");
			
				 table.setCellSelectionEnabled(true);
				 table.setRowSelectionAllowed(true);
				 table.setColumnSelectionAllowed(true);
			
				 Vector rows = ((DefaultTableModel)table.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
				 model.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中
				 int countNumber=0;  
				 for(int i=0; i<count;i++) {
					 model.addRow(new Vector<Object>());
					 for(int j=0; j<3;j++) {
						table.setValueAt(data[countNumber], i, j);
						countNumber++;  //讓資料可以依順序讀出
					 }
				 }						

			if(table.getRowCount()>0) {
				panel2.add(scroll);
				split.setBottomComponent(panel2);
				split.setDividerLocation(50);	

			}			
			}catch(Exception a) {
				 JOptionPane.showMessageDialog(null,"當天沒有紀錄");
			 }
		}		
	}
}
