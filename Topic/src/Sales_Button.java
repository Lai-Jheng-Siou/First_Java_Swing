import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sales_Button implements ActionListener {
	private JPanel panelSell = null;
	private JLabel lbcompany,contectP,number,number1,guiNumber,companyMail,address,sumMoney,payWay,lbl_ID = null;
	private static JTextField txcompany,txcontectP,txNumber,txNumber1,txGUInumber,txMail,txAddress,txSum,tx_ID = null;
	private JButton AddButton,DelButton,confirm,save = null;
	private DefaultTableModel model = null;
	private Vector rows = null;
	private Vector<String> columnNames = null;
	private JTable SalesInfo = null;
	private JScrollPane scroll = null;
	private static JComboBox<String> comboBox = null;
	int Count_ID = 0; //數訂單編號
	int product_count = 1;  //數產品編號
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-M-d");
	SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy/M/d");	
	SimpleDateFormat fmt5 = new SimpleDateFormat("YYYYMMdd");
	
	JPanel salesButton(){
		
		panelSell = new JPanel();  //銷售介面開始
					
		lbcompany = new JLabel("公司名稱");    //公司資料開始
		lbcompany.setBounds(35,10,100,30);
		lbcompany.setFont(f1);
		contectP = new JLabel("連絡人姓名");
		contectP.setBounds(15,45,100,30);
		contectP.setFont(f1);
		number = new JLabel("公司電話");
		number.setBounds(370,10,100,30);
		number.setFont(f1);
		number1 = new JLabel("連絡人電話");
		number1.setBounds(350,45,100,30);
		number1.setFont(f1);
		guiNumber = new JLabel("公司統一編號");
		guiNumber.setBounds(670,10,150,30);
		guiNumber.setFont(f1);
		companyMail = new JLabel("公司信箱");
		companyMail.setBounds(700,45,100,30);
		companyMail.setFont(f1);
		address = new JLabel("公司地址");
		address.setBounds(35,80,100,30);
		address.setFont(f1);
		sumMoney = new JLabel("總金額");
		sumMoney.setBounds(75,430,100,30);
		sumMoney.setFont(f1);
		payWay = new JLabel("付款方式");
		payWay.setBounds(60,480,100,30);
		payWay.setFont(f1);
		
		txcompany = new JTextField();            
		txcompany.setBounds(115,12,150,30);
		txcompany.setFont(f1);
		txcompany.setForeground(Color.red);
		txcompany.addActionListener(this);
		
		txcontectP = new JTextField();
		txcontectP.setBounds(115,47,150,30);
		txcontectP.setFont(f1);
		txcontectP.setForeground(Color.red);
		txcontectP.addActionListener(this);
		
		txNumber = new JTextField();
		txNumber.setBounds(455,12,150,30);
		txNumber.setFont(f1);
		txNumber.setForeground(Color.red);
		txNumber.addActionListener(this);
		
		txNumber1 = new JTextField();
		txNumber1.setBounds(455,47,150,30);
		txNumber1.setFont(f1);
		txNumber1.setForeground(Color.red);
		txNumber1.addActionListener(this);
		
		txGUInumber = new JTextField();
		txGUInumber.setBounds(785,12,150,30);
		txGUInumber.setFont(f1);
		txGUInumber.setForeground(Color.red);
		txGUInumber.addActionListener(this);
		
		txMail = new JTextField();
		txMail.setBounds(785,47,150,30);  
		txMail.setFont(f1);
		txMail.setForeground(Color.red);
		txMail.addActionListener(this);
		
		txAddress = new JTextField();
		txAddress.setBounds(115,82,345,30);
		txAddress.setFont(f1);
		txAddress.setForeground(Color.red);
		txAddress.addActionListener(this);   //公司資料結束
		
		txSum = new JTextField();
		txSum.setBounds(150,430,150,30);
		txSum.setEnabled(false);
		txSum.setFont(f1);
		
		lbl_ID = new JLabel("訂單編號");
		lbl_ID.setBounds(20,120,80,20);
		lbl_ID.setFont(f1);
		tx_ID = new JTextField();
		tx_ID.setBounds(100,120,150,20);
		tx_ID.setEnabled(false);
		tx_ID.setFont(f1);
		
		//設置不重複訂單編號
		String ID_folder = String.format("src/Info/CountNumber/%s.txt", fmt5.format(new java.util.Date()).toString());				
		String count = String.format("%s%d", fmt5.format(new java.util.Date()).toString(),Count_ID);
		File file = new File(ID_folder);
			
		if(file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ID_folder)));
				int rr = Integer.parseInt(br.readLine());
				int RR=rr+1;
				String ID_count = String.format("%s%d",fmt5.format(new java.util.Date()).toString(),RR);
				tx_ID.setText(ID_count);
				br.close();
				BufferedWriter bw = new BufferedWriter(new FileWriter(ID_folder));
				String string = Integer.toString(RR);
				bw.write(string);
				bw.flush();
				bw.close();
			}catch(Exception exception) {}	
		 }else {
			try {
				file.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(ID_folder));
				bw.write(Integer.toString(Count_ID));
				bw.flush();
				bw.close();
				tx_ID.setText(count);				
			}catch(Exception E) {} 
		 }
		 Count_ID++;
		//訂單編號結束
				
		String[] PayWay = {"待確認","現金","信用卡","轉帳","支票"};
		comboBox = new JComboBox<String>(PayWay);
		comboBox.setFont(f1);
		comboBox.setBounds(150,480,100,30);
		
		save = new JButton("儲存");
		save.setBounds(830,480,80,30);
		save.setFont(f1);
		save.addActionListener(this);
		AddButton = new JButton("新增");
		AddButton.setBounds(20,390,80,25);
		AddButton.setFont(f1);
		AddButton.addActionListener(this);		
		DelButton = new JButton("刪除");
		DelButton.setBounds(101,390,80,25);
		DelButton.setFont(f1);
		DelButton.addActionListener(this);
		confirm = new JButton("確認");
		confirm.setBounds(180,390,79,25);
		confirm.setFont(f1);
		confirm.addActionListener(this);
		
		columnNames = new Vector<String>();
		columnNames.add("編號");columnNames.add("類別");columnNames.add("名稱");columnNames.add("單價");
		columnNames.add("數量");columnNames.add("金額");columnNames.add("折扣");columnNames.add("備註");
		
		SalesInfo = new JTable(model);
		model = (DefaultTableModel) SalesInfo.getModel();
			
		SalesInfo.setCellSelectionEnabled(true);
		SalesInfo.setRowSelectionAllowed(true);
		SalesInfo.setColumnSelectionAllowed(true);
		
		rows = ((DefaultTableModel)SalesInfo.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
		model.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中

		
		Setting_addProduct PI= new Setting_addProduct();
		JComboBox<String> pi = PI.product_index();
		SalesInfo.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(pi));
		SalesInfo.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(PI.INDEX()));
		
		scroll = new JScrollPane(SalesInfo);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		scroll.setBounds(20,140,900,235);
				
		panelSell.add(lbcompany);panelSell.add(contectP);panelSell.add(number);panelSell.add(number1);panelSell.add(guiNumber);	panelSell.add(companyMail);panelSell.add(address);panelSell.add(sumMoney);panelSell.add(payWay);panelSell.add(lbl_ID);
		panelSell.add(txcompany);panelSell.add(txcontectP);panelSell.add(txNumber);panelSell.add(txNumber1);panelSell.add(txGUInumber);panelSell.add(txMail);panelSell.add(txAddress);panelSell.add(txSum);panelSell.add(tx_ID);
		panelSell.add(AddButton);panelSell.add(DelButton);panelSell.add(confirm);panelSell.add(save);
		panelSell.add(comboBox);
		panelSell.add(scroll);  
		
		panelSell.setLayout(null);
		return panelSell;
		
	}
	public void actionPerformed(ActionEvent e) {
			int CountRow = model.getRowCount();
		  if(e.getSource()==AddButton) {
			  if(CountRow==0) {
				  model.addRow(new Vector<Object>());
				  model.setValueAt(product_count, CountRow, 0);
			  }else {
				  model.addRow(new Vector<Object>());
				  int last_row_ID = (int) model.getValueAt(CountRow-1, 0);
				  model.setValueAt(last_row_ID+1, CountRow, 0);
			  }
			    
		  }else if(e.getSource()==DelButton) {
			  int RowID=SalesInfo.getRowCount();
		      if (RowID > 0) {
		    	  model.removeRow(RowID-1);
		      }else {
		    	  JOptionPane.showMessageDialog(null, "已經空囉~");
		      }
		  }
		  if(e.getSource()==confirm) {
				int i = model.getRowCount();	   //取得JTable列數
			  	int Sum=0;
			  try {
				  for(int x=0;x<i;x++) {
					  String price = (String) model.getValueAt(x,3);
					  String amount = (String) model.getValueAt(x,4);
					  float a = Float.parseFloat(price);
					  float b = Float.parseFloat(amount);
					  float c = a*b;
					  String d = String.format("%.1f", c);
					  Sum+=c;
					  model.setValueAt(d, x, 5);	  
				  }			 
				  String sum = String.valueOf(Sum);
				  txSum.setText(sum+"元");
			  }catch(Exception error) {
				  JOptionPane.showMessageDialog(null, "單價及數量都必須輸入");
			  }
		  }
		  if(e.getSource()==save) {
			  int i = model.getRowCount();	   //取得JTable列數
			  int j = model.getColumnCount();  //取得JTable行數	
			  try {  //庫存迴圈
				  String csv = "src/Info/Stock/Stock.csv";
				  
				  ArrayList<String[]> list = new ArrayList<String[]>();				
				  CSVReader reader = new CSVReader(new FileReader(csv));
				  list = (ArrayList<String[]>) reader.readAll();
				  reader.close();
				  
				  for(int x=0;x<i;x++) {					  
					  for(int row=0;row<list.size();row++) {					  
						  if(list.get(row)[0].equals(model.getValueAt(x, 1)) && list.get(row)[1].equals(model.getValueAt(x, 2))) {
							  int price = Integer.parseInt((String)list.get(row)[2]);
					          int amount = Integer.parseInt((String)list.get(row)[3]);
					          int newPrice = Integer.parseInt(model.getValueAt(x, 3).toString());
					          int newAmount = Integer.parseInt(model.getValueAt(x, 4).toString());
					          int totalAmount = amount - newAmount;
					          list.get(row)[3]= String.valueOf(totalAmount);
					          if(price!=newPrice) {
					        	  list.get(row)[2]= String.valueOf(newPrice);
					          }					          
						  }
					  }					  
				  }
				  CSVWriter writer = new CSVWriter(new FileWriter(csv));			  				 
				  writer.writeAll(list);
				  writer.flush();
				  writer.close();
			  }catch(Exception ERROR) {
				  System.out.println(ERROR.getMessage());
			  }
			  		  
			  try{
				  String folder = String.format("src/Info/Sell/%s",fmt1.format(new java.util.Date()).toString());				  
					File file =new File(folder);    
					if  (!file .exists()  && !file .isDirectory()){  //建立日期資料夾 
					    file .mkdir();    
					} 				  
				  String csv = String.format("src/Info/Sell/%s/%s.csv",fmt1.format(new java.util.Date()).toString(),txcompany.getText());
				  File FileName = new File(csv);
				  if(FileName.exists()) {      //用內建讀寫方法
					  	BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
			            String writeTime = String.format("時間:%s", fmt2.format(new java.util.Date()).toString());
			            bw.write("訂單編號,"+tx_ID.getText()+",null"+",null"+",null"+",null"+",null"+",null"+",null"+"\n");
			            for(int x=0;x<i;x++) {
							  for(int y=0;y<j;y++) {
								  bw.write(model.getValueAt(x, y)+",");
							  }
							bw.write(writeTime+",");
							bw.newLine();
			            }
			            bw.write("付款方式,"+comboBox.getItemAt(comboBox.getSelectedIndex())+",null"+",null"+",null"+",null"+",null"+",null"+",null"+"\n");
			            bw.write("總金額,"+txSum.getText()+",null"+",null"+",null"+",null"+",null"+",null"+",null"+"\n");
			            bw.flush();
			            bw.close();
			            JOptionPane.showMessageDialog(null, "儲存成功");
			            
				  }else{         
					  Connection c = null;  //連接資料庫
					  Statement stmt = null;					  
					  try {						  
						  Class.forName("org.sqlite.JDBC");
						  c = DriverManager.getConnection("jdbc:sqlite:Inventory_system.db");
					      c.setAutoCommit(false);
					      stmt = c.createStatement();
					      ResultSet rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
					      
					      int countCompany = 0; //用來判斷是否有同公司的名稱 如果有則+1 下面判斷式不執行
					      while ( rs.next() ) {
					         String  name = rs.getString("公司名稱");
					         if(txcompany.getText().equals(name)) {
					        	 countCompany += 1;
					         }
					      }
					      if(countCompany==0) {
					    	  String sql = "INSERT INTO COMPANY (公司名稱,公司電話,公司信箱,聯絡人姓名,聯絡人電話,統一編號,公司地址,顧客or廠商)"+
					    		  		String.format("VALUES('%s','%s','%s','%s','%s','%s','%s','%s');",txcompany.getText(),txNumber.getText(),txMail.getText(),
					    		  				txcontectP.getText(),txNumber1.getText(),txGUInumber.getText(),txAddress.getText(),"顧客");;
					      stmt.executeUpdate(sql);
					      }
					      countCompany = 0; //把數字變回0
					      rs.close();
					      stmt.close();
					      c.commit();
					      c.close();
					  }catch(Exception E) {
						  System.out.println(E.getMessage());
					  }  //連接資料庫		
					  
			          BufferedWriter br = new BufferedWriter(new FileWriter(csv));
			          String writeTime = String.format("時間:%s", fmt2.format(new java.util.Date()).toString());
			          br.write("訂單編號,"+tx_ID.getText()+",null"+",null"+",null"+",null"+",null"+",null"+",null"+"\n");
			          for(int x=0;x<i;x++) {
							for(int y=0;y<j;y++) {
								br.write(model.getValueAt(x, y)+",");
							}						
							br.write(comboBox.getItemAt(comboBox.getSelectedIndex())+",");
							br.write(writeTime+",");
							br.newLine();
			          }
			          br.write("總金額,"+txSum.getText()+",null"+",null"+",null"+",null"+",null"+",null"+",null"+",null"+"\n");
			          br.flush();
			          br.close();
			          JOptionPane.showMessageDialog(null, "儲存成功");
				  }	            
		        }catch (IOException a) {
		        	JOptionPane.showMessageDialog(null, "呵呵...儲存失敗了!");
		        }  
		  }    
	}
}
