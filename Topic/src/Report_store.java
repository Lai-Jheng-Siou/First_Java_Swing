import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import au.com.bytecode.opencsv.CSVReader;

public class Report_store implements ActionListener{
	private JPanel panel,panel1,panel2,panelNull = null;
	private JTable stock_table,table = null;
	private JScrollPane scroll,scroll1 = null;
	private JButton btn1,btn2 = null;
	private JSplitPane split = null;
	private DefaultTableModel dtm;

	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	public JSplitPane report_store() {
		
		panel = new JPanel();    
		stock_table = new JTable();  //設置庫存表格
		scroll = new JScrollPane(stock_table);  //設置滑動軸 將表格加進去
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		panel.add(scroll);
		panel.setLayout(new BorderLayout());
				
		panel1 = new JPanel();
		dtm=new DefaultTableModel();
		table = new JTable(dtm);
		scroll1 = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		panel1.add(scroll1);
		panel1.setLayout(new BorderLayout());
		
		panel2 = new JPanel();
		btn1 = new JButton("庫存");
		btn1.setFont(f1);
		btn1.addActionListener(this);
		btn2 = new JButton("顧客/廠商");
		btn2.setFont(f1);
		btn2.addActionListener(this);
		
		panel2.add(btn1);panel2.add(btn2);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNull = new JPanel();
		
		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(panel2);
		split.setBottomComponent(panelNull);	
		split.setEnabled(false); //讓分隔線不能移動	
		split.setDividerLocation(50);
		split.setDividerSize(1);
		return split;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			DefaultTableModel model = (DefaultTableModel) stock_table.getModel();
			
			String stock_path = "src/Info/Stock/Stock.csv";	
			try {
				model.setRowCount(0); //每次都把Row歸零 才不會重複增加row
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("分類");columnNames.add("名稱");columnNames.add("單價");columnNames.add("數量");							
				
				stock_table.setCellSelectionEnabled(true);
				stock_table.setRowSelectionAllowed(true);
				stock_table.setColumnSelectionAllowed(true);
				Vector rows = ((DefaultTableModel)stock_table.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
				model.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中
				ArrayList<String[]> list = new ArrayList<String[]>();
				
				CSVReader reader = new CSVReader(new FileReader(stock_path));
				list = (ArrayList<String[]>) reader.readAll();
				for(int i=0; i<list.size();i++) {
					 model.addRow(new Vector<Object>());
					 for(int j=0; j<4;j++) {
						 stock_table.setValueAt(list.get(i)[j], i, j);							
					 }
				 }			
			}catch(Exception E) {
				System.out.println(E.getMessage());
			}					
			panel.add(scroll);
			split.setBottomComponent(panel);
			split.setDividerLocation(50);
		}
		if(e.getSource()==btn2) {
			Connection c = null;
			Statement stmt = null;
			
			try {		//連接資料庫  不同方法設定JTable 的	DefaultTableModel
				dtm.setRowCount(0);
				String[] columnNames={"公司名稱","公司電話","公司信箱","聯絡人姓名","聯絡人電話","統一編號","公司地址","顧客or廠商"};
				dtm.setColumnIdentifiers(columnNames);
				TableRowSorter<DefaultTableModel> sorter=new TableRowSorter<DefaultTableModel>(dtm);
				table.setRowSorter(sorter);
								
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Inventory_system.db");
				c.setAutoCommit(false);
			    stmt = c.createStatement();
			    ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
			    while(rs.next()) {
			    	dtm.addRow(new Object[]{
			    	          rs.getObject("公司名稱"),
			    	          rs.getObject("公司電話"),
			    	          rs.getObject("公司信箱"),          
			    	          rs.getObject("聯絡人姓名"),          
			    	          rs.getObject("聯絡人電話"),
			    	          rs.getObject("統一編號"),
			    	          rs.getObject("公司地址"),
			    	          rs.getObject("顧客or廠商")
			    	          });
			    }
			    rs.close();
			    panel1.add(scroll1);
				split.setBottomComponent(panel1);
				split.setDividerLocation(50);
			    
			}catch(Exception E) {
				System.out.println(E.getMessage());
			}
		}
	}
}
