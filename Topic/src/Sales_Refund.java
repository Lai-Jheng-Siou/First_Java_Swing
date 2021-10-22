import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Sales_Refund implements ActionListener{
	
	private JPanel panel,panel2,panel3 = null;
	private JLabel lb,lb1,lb2 = null;
	private JButton btn,btn1,btn2,btn3,btn4 = null;
	private JComboBox<Object> year,month,day = null;
	private JSplitPane split = null;
	private JScrollPane scroll,scroll1 = null;
	private JTable table,table1 = null;
	private String start;
	private String file;
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  16);  //字體1
	SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-M-d");
	
	public JSplitPane sales_refund() {
		panel = new JPanel();
		
		lb = new JLabel("          退貨時間:yyyy-MM-dd   開始時間:");
		lb.setFont(f1);
		
		String[] years = new String[980];
		for(int i=0;i<980;i++) {
			years[i] = i+2020+"";
		}
		year = new JComboBox<Object>(years);
		year.setFont(f1);
		
		String[] months = new String[12];
		for(int i=0;i<12;i++) {
			months[i] = i+1+"";
		}
		month = new JComboBox<Object>(months);
		month.setFont(f1);
		
		String[] days = new String[31];
		for(int i=0;i<31;i++) {
			days[i] = i+1+"";
		}
		day = new JComboBox<Object>(days);	
		day.setFont(f1);
				
		btn = new JButton("選擇日期");
		btn.addActionListener(this);
		btn.setFont(f1);
			
		panel.add(lb);panel.add(year);panel.add(month);panel.add(day);panel.add(btn);	
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panel2 = new JPanel();
		table = new JTable();
		scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
					
		table1 = new JTable();
		scroll1 = new JScrollPane(table1);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
		
		panel3 = new JPanel();		
		btn1 = new JButton("導出");
		btn1.setFont(f1);
		btn1.addActionListener(this);
		lb1 = new JLabel("=======>");
		lb1.setFont(f1);
		btn2 = new JButton("刪除");
		btn2.setFont(f1);
		btn2.addActionListener(this);
		btn3 = new JButton("確認");
		btn3.setFont(f1);
		btn3.addActionListener(this);
		lb2 = new JLabel("=======>");
		lb2.setFont(f1);
		btn4 = new JButton("修改");
		btn4.setFont(f1);
		btn4.addActionListener(this);
		panel3.add(btn1);panel3.add(lb1);panel3.add(btn2);panel3.add(btn3);panel3.add(lb2);panel3.add(btn4);
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.Y_AXIS));
				
		panel2.add(scroll);panel2.add(panel3);panel2.add(scroll1);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
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
			start = Cyear+"-"+Cmonth+"-"+Cday;    //整合日期		
			
			try {
				String fileLoc = String.format("src/Info/Sell/%s",start);  //檔案路徑
				File file = new File(fileLoc);
				String[] fileName = file.list();
				
					Vector<String> columnNames = new Vector<String>();
					columnNames.add("公司名稱");
					
					table.setCellSelectionEnabled(true);
					table.setRowSelectionAllowed(true);
					table.setColumnSelectionAllowed(true);
					
					Vector rows = ((DefaultTableModel)table.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
					model.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中
					int countNumber=0;  
					for(int i=0; i<fileName.length;i++) {
						model.addRow(new Vector<Object>());
						for(int j=0; j<1;j++) {
							table.setValueAt(fileName[countNumber], i, j);
							countNumber++;  //讓資料可以依順序讀出
						}
					}											
					if(table.getRowCount()>0) {
						panel2.add(scroll);panel2.add(panel3);panel2.add(scroll1);
						split.setBottomComponent(panel2);
						split.setDividerLocation(50);			
				}
			}catch(Exception E) {
				JOptionPane.showMessageDialog(null, "沒有所選擇日期!");
			}						
		}
		DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
		
		if(e.getSource()==btn1) {
			
			model1.setRowCount(0);
			int chooseRow = table.getSelectedRow();  //取得選擇的行數
			
			String[] data = new String[0];
			
			try {
				int count=0;  //數有幾行 
				String choose = (String) table.getValueAt(chooseRow, 0);   //取得選中的值
				file = String.format("src/Info/Sell/%s/%s",start,choose);    //用格式化選取檔案

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
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
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[3];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[4];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[5];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[6];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[7];
					data = Arrays.copyOf(data, data.length+1);
					data[data.length-1]=arrs[8];
					count++;
					}
				 br.close();
				 					
				 Vector<String> columnNames = new Vector<String>();
				 columnNames.add("編號");columnNames.add("類別");columnNames.add("名稱");columnNames.add("單價");
				 columnNames.add("數量");columnNames.add("金額");columnNames.add("折扣");columnNames.add("備註");columnNames.add("日期");
				 
				 table1.setCellSelectionEnabled(true);
				 table1.setRowSelectionAllowed(true);
				 table1.setColumnSelectionAllowed(true);
			
				 Vector rows = ((DefaultTableModel)table1.getModel()).getDataVector(); //先取出模型中的信息，再加如新的行，就是说rows代表整个数据
				 model1.setDataVector(rows, columnNames);//将新的整个数据和标题放如MyDataModel模型中
				 int countNumber=0;  
				 for(int i=0; i<count;i++) {
					 model1.addRow(new Vector<Object>());
					 for(int j=0; j<9;j++) {
						table1.setValueAt(data[countNumber], i, j);
						countNumber++;  //讓資料可以依順序讀出
					 }
				 }
				 panel2.add(scroll);panel2.add(panel3);panel2.add(scroll1);
				 split.setBottomComponent(panel2);
				 split.setDividerLocation(50);
			}catch(Exception A) {
				System.out.println(A.getMessage());
				JOptionPane.showMessageDialog(null, "要選擇東西唷");
			}			
		}
		if(e.getSource()==btn2) {
			try {
				int z = JOptionPane.showConfirmDialog(null,"確認要刪除嗎？", "確認", JOptionPane.YES_NO_OPTION);
				if(z==JOptionPane.YES_OPTION) {
					int i = table1.getSelectedRow();  //抓取選擇行
					model1.removeRow(i);  //刪除此行
				}
			}catch(Exception E) {
				JOptionPane.showMessageDialog(null, "未選擇東西");
			}	 
		 }
		if(e.getSource()==btn3) {		
			try {
				int countRow = model1.getRowCount();
				  for(int x=0;x<countRow;x++) {
					  if(model1.getValueAt(x,2).equals("null") && model1.getValueAt(x,3).equals("null")) {	
						  
					  }else {
						  String price = (String) model1.getValueAt(x,2);
						  String amount = (String) model1.getValueAt(x,3);
						  float a = Float.parseFloat(price);
						  float b = Float.parseFloat(amount);
						  float c = a*b;
						  String d = String.format("%.1f", c);
						  model1.setValueAt(d, x, 4);	
					  }  
				  }			 
			  }catch(Exception error) {
				  JOptionPane.showMessageDialog(null, "單價及數量都必須輸入");
			  }
		 }
		if(e.getSource()==btn4) {
			 try {
				 int countRow = table1.getRowCount();
				 BufferedWriter bw= new BufferedWriter(new FileWriter(file));
				 for (int i=0; i<countRow;i++) {
					 for(int j=0;j<9;j++) {
						 bw.write(model1.getValueAt(i, j)+",");
					 }
					 bw.newLine();
				 }
				 bw.flush();
				 bw.close();
				 JOptionPane.showMessageDialog(null, "修改成功");
			 }catch(Exception E) {
				 JOptionPane.showMessageDialog(null, "儲存失敗");
			 }			 
		 }
	}
}
