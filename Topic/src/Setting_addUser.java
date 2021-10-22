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
		
		user = new JLabel("1.�ϥΪ̦W��");
		user.setBounds(50,15,100,30);
		Acc = new JLabel("2.�b��");
		Acc.setBounds(75,65,50,30);
		Pasd = new JLabel("3.�K�X");
		Pasd.setBounds(75,115,50,30);
		ComfirmPasd = new JLabel("4.�T�{�K�X");
		ComfirmPasd.setBounds(60,165,60,30);
		
		name = new HintTextField("����");
		name.setBounds(135,15,100,30);
		name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		acc = new HintTextField("����");
		acc.setBounds(135,65,100,30);
		pasd = new HintTextField("����");
		pasd.setBounds(135,115,100,30);
		comfirm_Pasd = new HintTextField("����");
		comfirm_Pasd.setBounds(135,165,100,30);
		
		sign_in = new JButton("���U");
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
				JOptionPane.showMessageDialog(null, "�W�r������");
			}else if(acc.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�b��������");
			}else if(pasd.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�K�X������");
			}else if(comfirm_Pasd.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�T�{�K�X������");
			}else if(!comfirm_Pasd.getText().equals(pasd.getText())){
				JOptionPane.showMessageDialog(null, "�K�X�νT�{�K�X�����@��");
			}else if(pasd.getText().equals(comfirm_Pasd.getText())) {  //��Ƴ���g���� �K�X�νT�{�K�X���@�ˮ� �i�J�����
					HashSet<String> mapAcc = new HashSet<>();  //�Ыؤ@��Set��b��
					try {	//����w�x�s���b��				
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
					boolean x=true;    //�]�w�@�ӥ��L�� ��b���s�b�� �ܦ�false ��while���n�i��
					
					for(String i:mapAcc) {  //�ΰj��P�_�b���O�_�w�s�b
						if(acc.getText().equals(i)) {
							JOptionPane.showMessageDialog(null,"�b���w�s�b");
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
							JOptionPane.showMessageDialog(null, "���U���\");
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
