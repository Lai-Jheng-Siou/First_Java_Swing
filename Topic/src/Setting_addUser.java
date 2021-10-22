import java.awt.event.*;
import java.io.*;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;

public class Setting_addUser implements ActionListener {
	private JPanel panel = null;
	private JLabel user,Acc,Pasd,ComfirmPasd = null;
	private JTextField name,acc,pasd,comfirm_Pasd = null;
	private JButton sign_in = null;
	
	public JPanel setting_addUser() {
		panel = new JPanel();
		
		user = new JLabel("1.使用者名稱");
		user.setBounds(50,15,100,30);
		Acc = new JLabel("2.帳號");
		Acc.setBounds(75,65,50,30);
		Pasd = new JLabel("3.密碼");
		Pasd.setBounds(75,115,50,30);
		ComfirmPasd = new JLabel("4.確認密碼");
		ComfirmPasd.setBounds(60,165,60,30);
		
		name = new HintTextField("必填");
		name.setBounds(135,15,100,30);
		name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		acc = new HintTextField("必填");
		acc.setBounds(135,65,100,30);
		pasd = new HintTextField("必填");
		pasd.setBounds(135,115,100,30);
		comfirm_Pasd = new HintTextField("必填");
		comfirm_Pasd.setBounds(135,165,100,30);
		
		sign_in = new JButton("註冊");
		sign_in.setBounds(100,220,75,30);
		sign_in.addActionListener(this);
		
		panel.add(user);panel.add(Acc);panel.add(Pasd);panel.add(ComfirmPasd);
		panel.add(name);panel.add(acc);panel.add(pasd);panel.add(comfirm_Pasd);
		panel.add(sign_in);	
		panel.setLayout(null);
		return panel;
	}
	public void actionPerformed(ActionEvent e) { 

		if(e.getSource()==sign_in) {
			if(name.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "名字為必填");
			}else if(acc.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "帳號為必填");
			}else if(pasd.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "密碼為必填");
			}else if(comfirm_Pasd.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "確認密碼為必填");
			}else if(!comfirm_Pasd.getText().equals(pasd.getText())){
				JOptionPane.showMessageDialog(null, "密碼及確認密碼必須一樣");
			}else if(pasd.getText().equals(comfirm_Pasd.getText())) {  //資料都填寫完畢 密碼及確認密碼為一樣時 進入此選擇
					HashSet<String> mapAcc = new HashSet<>();  //創建一個Set放帳號
					try {	//抓取已儲存的帳號				
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/Info/Password.csv"), "utf-8"));
						String line="";
						String[] arrs=null;
						while ((line=br.readLine())!=null) {
							arrs=line.split(",");
							mapAcc.add(arrs[1]);
							}
						 br.close();						 
					}catch(Exception a) {
						System.out.println(a.getMessage());
					}	
					boolean x=true;    //設定一個布林直 當帳號存在時 變成false 讓while不要進行
					
					for(String i:mapAcc) {  //用迴圈判斷帳號是否已存在
						if(acc.getText().equals(i)) {
							JOptionPane.showMessageDialog(null,"帳號已存在");
							x=false;	
							break;
						}
					}
					while(x) {
						try {
							BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/Info/Password.csv", true),"utf-8"));
							bw.write(name.getText()+",");bw.write(acc.getText()+",");bw.write(pasd.getText()+",\n");
							bw.flush();
							bw.close();
							JOptionPane.showMessageDialog(null, "註冊成功");
							name.setText("");acc.setText("");pasd.setText("");comfirm_Pasd.setText("");
							break;
						}catch(Exception a){
							System.out.println(a.getMessage());
						}
					}							
			}														
		}
	}
	
	class HintTextField extends JTextField implements FocusListener {

		  private final String hint;
		  private boolean showingHint;

	public HintTextField(final String hint) {
		    super(hint);
		    this.hint = hint;
		    this.showingHint = true;
		    super.addFocusListener(this);
		  }
	public void focusGained(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText("");
		      showingHint = false;
		    }
		  }
	public void focusLost(FocusEvent e) {
		    if(this.getText().isEmpty()) {
		      super.setText(hint);
		      showingHint = true;
		    }
		  }
	public String getText() {
		    return showingHint ? "" : super.getText();
		  }
	}
}
