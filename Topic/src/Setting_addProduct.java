import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import au.com.bytecode.opencsv.CSVReader;
import org.jdesktop.swingx.autocomplete.*;  //讓JComboBox有建議功能
import javax.swing.*;

public class Setting_addProduct implements ActionListener {
	
	private JPanel panel = null;
	private JLabel lb1,lb2 = null;
	private JTextField tx1 = null;
	private JButton btn1 = null;
	
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  16);
	Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD,  20);

	public JPanel addproduct() {
		panel = new JPanel();
		lb1 = new JLabel("新增產品");
		lb1.setFont(f2);
		lb1.setBounds(110,25,100,30);
		panel.add(lb1);
		
		lb2 = new JLabel("1.產品名稱:");
		lb2.setFont(f1);
		lb2.setBounds(30,110,120,30);
		panel.add(lb2);

		tx1 = new HintTextField("必填");
		tx1.setBounds(130,110,150,30);
		panel.add(tx1);
		
		btn1 = new JButton("儲存");
		btn1.setFont(f1);
		btn1.setBounds(110,200,80,30);
		btn1.addActionListener(this);
		panel.add(btn1);
		
		panel.setLayout(null);
		return panel;
		
	}
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource()==btn1) {
			if(tx1.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "要輸入東西哦~");
			}else {
				String csv = "src/Info/Product.csv";
				String newName = (String)tx1.getText();
				try {
					BufferedWriter br = new BufferedWriter(new FileWriter(csv,true));
					br.write(newName+",\n");
					br.flush();
					br.close();
					JOptionPane.showMessageDialog(null, "新增成功");
					tx1.setText("");
				}catch(Exception E) {
					System.out.println(E.getMessage());
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
	
	public JComboBox<String> product_index() {
		JComboBox<String> product = new JComboBox<>();
		String csv = "src/Info/Product.csv";		
		try {
			  ArrayList<String[]> list = new ArrayList<String[]>();				
			  CSVReader reader = new CSVReader(new FileReader(csv));
			  list = (ArrayList<String[]>) reader.readAll();
			  reader.close();			  
			  for(int row=0;row<list.size();row++) {					  				
				  product.addItem(list.get(row)[0]);				  				  
			  }
		  }catch(Exception E) {
			  System.out.println(E.getMessage());
		  }	

		return product;
	}
	public JComboBox<String> INDEX() {
		JComboBox<String> index = new JComboBox<>();
		String csv = "src/Info/Stock/Stock.csv";
		  try {
			  ArrayList<String[]> list = new ArrayList<String[]>();				
			  CSVReader reader = new CSVReader(new FileReader(csv));
			  list = (ArrayList<String[]>) reader.readAll();
			  reader.close();			  
			  for(int row=0;row<list.size();row++) {					  				
					  index.addItem(list.get(row)[1]);				  				  
			  }
		  }catch(Exception E) {
			  System.out.println(E.getMessage());
		  }		
		  AutoCompleteDecorator.decorate(index);
		  return index;
	}
}
