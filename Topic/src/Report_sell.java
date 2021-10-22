import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import au.com.bytecode.opencsv.CSVReader;

public class Report_sell implements ActionListener{
	private JPanel panel,panel2 = null;
	private JLabel lb = null;
	private JButton btn = null;
	private JComboBox<Object> year,month,day = null;
	private JTable table = null;
	private JScrollPane scroll = null;
	private JSplitPane split = null;	
	
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	
	public JSplitPane report_sell() {
		panel = new JPanel();
		
		lb = new JLabel("銷售紀錄:yyyy-MM-dd   開始時間:");

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
								
			String folderStart = String.format("src/Info/Sell/%s",start);
			File file = new File(folderStart);
			
			try {
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("編號");columnNames.add("分類");columnNames.add("名稱");columnNames.add("單價");columnNames.add("數量");
				columnNames.add("金額");columnNames.add("折扣");columnNames.add("備註");columnNames.add("付款方式");
			
				table.setCellSelectionEnabled(true);
				table.setRowSelectionAllowed(true);
				table.setColumnSelectionAllowed(true);
			
				Vector rows = ((DefaultTableModel)table.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
				model.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中
				ArrayList<String[]> list = new ArrayList<String[]>();
				int countRow = 0;
				for(File filelist:file.listFiles()) {
						
					if(!filelist.isDirectory()) {
						String csv = String.format("src/Info/Sell/%s/%s", start,filelist.getName());
						CSVReader reader = new CSVReader(new FileReader(csv));
						list = (ArrayList<String[]>) reader.readAll();							
					}
					for(int i=0; i<list.size();i++) {
						 model.addRow(new Vector<Object>());
						 for(int j=0; j<9;j++) {
							table.setValueAt(list.get(i)[j], countRow, j);							
						 }
						 countRow+=1;
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
