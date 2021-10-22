import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.sql.*;

public class Purchase_Button implements ActionListener{
	private JPanel panelPurchase = null;
	private JLabel lbcompany,contectP,number,number1,guiNumber,companyMail,address,sumMoney = null;
	private static JTextField txcompany,txcontectP,txNumber,txNumber1,txGUInumber,txMail,txAddress,txSum = null;
	private JButton AddButton,DelButton,confirm,save = null;
	private DefaultTableModel model = null;
	private Vector rows = null;
	private Vector<String> columnNames = null;
	private JTable BuyInfo = null;
	private JScrollPane scroll = null;
	int Count_ID = 0; //數訂單編號
	int product_count = 1;  //數產品編號
	
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  18);  //字體1
	SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-M-d");
	SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy/M/d");
	SimpleDateFormat fmt5 = new SimpleDateFormat("YYYYMMdd");

	JPanel purchaseButton(){
		
		panelPurchase = new JPanel();  //銷售介面開始
					
		lbcompany = new JLabel("公司名稱");    //公司資料開始
		lbcompany.setBounds(35,10,100,30);
		lbcompany.setFont(f1);
		contectP = new JLabel("業務姓名");
		contectP.setBounds(30,45,100,30);
		contectP.setFont(f1);
		number = new JLabel("公司電話");
		number.setBounds(370,10,100,30);
		number.setFont(f1);
		number1 = new JLabel("業務電話");
		number1.setBounds(370,45,100,30);
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
		sumMoney.setBounds(20,480,100,30);
		sumMoney.setFont(f1);
		
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
		txSum.setBounds(95,480,150,30);
		txSum.setEnabled(false);
		txSum.setFont(f1);

		save = new JButton("儲存");
		save.setBounds(845,500,80,30);
		save.setFont(f1);
		save.addActionListener(this);
		AddButton = new JButton("新增");
		AddButton.setBounds(20,430,79,25);
		AddButton.setFont(f1);
		AddButton.addActionListener(this);	
		DelButton = new JButton("刪除");
		DelButton.setBounds(100,430,79,25);
		DelButton.setFont(f1);
		DelButton.addActionListener(this);
		confirm = new JButton("確認");
		confirm.setBounds(180,430,79,25);
		confirm.setFont(f1);
		confirm.addActionListener(this);
		
		columnNames = new Vector<String>();
		columnNames.add("編號");columnNames.add("類別");columnNames.add("名稱");columnNames.add("單價");
		columnNames.add("數量");columnNames.add("金額");columnNames.add("折扣");columnNames.add("備註");
		
		BuyInfo = new JTable(model);
		model = (DefaultTableModel) BuyInfo.getModel();

		BuyInfo.setCellSelectionEnabled(true);
		BuyInfo.setRowSelectionAllowed(true);
		BuyInfo.setColumnSelectionAllowed(true);
		
		rows = ((DefaultTableModel)BuyInfo.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
		model.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中
		
		BuyInfo.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new Setting_addProduct().product_index())); //設計品項分類表單
		
		scroll = new JScrollPane(BuyInfo);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		scroll.setBounds(20,130,925,275);
	
		panelPurchase.add(lbcompany);panelPurchase.add(contectP);panelPurchase.add(number);panelPurchase.add(number1);panelPurchase.add(guiNumber);	panelPurchase.add(companyMail);panelPurchase.add(address);panelPurchase.add(sumMoney);
		panelPurchase.add(txcompany);panelPurchase.add(txcontectP);panelPurchase.add(txNumber);panelPurchase.add(txNumber1);panelPurchase.add(txGUInumber);panelPurchase.add(txMail);panelPurchase.add(txAddress);panelPurchase.add(txSum);
		panelPurchase.add(AddButton);panelPurchase.add(DelButton);panelPurchase.add(save);panelPurchase.add(confirm);
		panelPurchase.add(scroll);  
		panelPurchase.setLayout(null);

		return panelPurchase;
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
			  int RowID=BuyInfo.getRowCount();
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
				  JOptionPane.showMessageDialog(null, "單價及數量都必須輸入/要輸入數字唷~");
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
				  
				  int count=0;
				  for(int x=0;x<i;x++) {					  
					  for(int row=0;row<list.size();row++) {					  
						  if(list.get(row)[0].equals(model.getValueAt(x, 1)) && list.get(row)[1].equals(model.getValueAt(x, 2))) {
							  int price = Integer.parseInt((String)list.get(row)[2]);
					          int amount = Integer.parseInt((String)list.get(row)[3]);
					          int newPrice = Integer.parseInt(model.getValueAt(x, 3).toString());
					          int newAmount = Integer.parseInt(model.getValueAt(x, 4).toString());
					          int totalAmount = amount + newAmount;
					          list.get(row)[3]= String.valueOf(totalAmount);
					          if(price!=newPrice) {
					        	  list.get(row)[2]= String.valueOf(newPrice);
					          }					          
					          count+=1;
						  }
					  }
					  if(count==0) {
						  String[] a = new String[4];
						  for(int y=0;y<4;y++) {							  
							  a[y] = model.getValueAt(x, y+1).toString();
						  }							  
						  list.add(a);						  
					  }	
					  count=0;					  
				  }
				  CSVWriter writer = new CSVWriter(new FileWriter(csv));			  				 
				  writer.writeAll(list);
				  writer.flush();
				  writer.close();
			  }catch(Exception ERROR) {
				  System.out.println(ERROR.getMessage());
			  }
			  
			  try{  //Buy 迴圈
				  String folder = String.format("src/Info/Buy/%s",fmt1.format(new java.util.Date()).toString());				  
					File file =new File(folder);    
					if  (!file .exists()  && !file .isDirectory()){  //建立日期資料夾 
					    file .mkdir();    
					} 				  
				  String csv = String.format("src/Info/Buy/%s/%s.csv",fmt1.format(new java.util.Date()).toString(),txcompany.getText());
				  File FileName = new File(csv);
				  
				  if(FileName.exists()) {      //用內建讀寫方法
					  	BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
			            String writeTime = String.format("時間:%s", fmt2.format(new java.util.Date()).toString());
			            for(int x=0;x<i;x++) {
							  for(int y=0;y<j;y++) {
								  bw.write(model.getValueAt(x, y)+",");
							  }
							bw.write(writeTime+",");
							bw.newLine();
			            }
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
					    		  				txcontectP.getText(),txNumber1.getText(),txGUInumber.getText(),txAddress.getText(),"廠商");;
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
			          for(int x=0;x<i;x++) {
			        	  for(int y=0;y<j;y++) {
			        		  br.write(model.getValueAt(x, y)+",");		        		  
			        	  }
			        	  br.write(writeTime+",");
			        	  br.newLine();
			           }
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