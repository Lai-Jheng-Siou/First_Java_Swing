import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JComboBox;
import au.com.bytecode.opencsv.CSVReader;

public class Product_Index {
	JComboBox<String> product;
	public JComboBox<String> product_index() {
		product = new JComboBox<>();
		product.addItem("����");
		product.addItem("�[�u���~��");
		product.addItem("������");
		product.addItem("���f��");
		product.addItem("�s����");
		product.addItem("�����");
		product.addItem("������");
		
		return product;
	}
	public JComboBox<String> INDEX(Object object) {
		JComboBox<String> index = new JComboBox<>();
		String csv = "src/Info/Stock/Stock.csv";
		  try {
			  ArrayList<String[]> list = new ArrayList<String[]>();				
			  CSVReader reader = new CSVReader(new FileReader(csv));
			  list = (ArrayList<String[]>) reader.readAll();
			  reader.close();			  
			  for(int row=0;row<list.size();row++) {					  
				  if(list.get(row)[1].equals(object)) {
					  index.addItem(list.get(row)[2]);
				  }				  
			  }
		  }catch(Exception E) {
			  System.out.println(E.getMessage());
		  }		
		  return index;
	}
}
