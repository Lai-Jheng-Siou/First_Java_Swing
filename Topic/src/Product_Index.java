import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JComboBox;
import au.com.bytecode.opencsv.CSVReader;

public class Product_Index {
	JComboBox<String> product;
	public JComboBox<String> product_index() {
		product = new JComboBox<>();
		product.addItem("肉類");
		product.addItem("加工食品類");
		product.addItem("蔬菜類");
		product.addItem("乾貨類");
		product.addItem("酒水類");
		product.addItem("醬料類");
		product.addItem("器具類");
		
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
