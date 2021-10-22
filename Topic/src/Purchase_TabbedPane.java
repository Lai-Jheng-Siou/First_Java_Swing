import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Purchase_TabbedPane implements ActionListener,ChangeListener {
	private JPanel panel = null;
	private JLabel title = null;
	private static JTabbedPane tp = null;
	private JButton ADD,DEL,UP,LEFT,RIGHT = null;
	private JSplitPane split = null;
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 30);  //字體2
    int tabID=1;
    
  public JSplitPane tabbedPane() {
	  panel=new JPanel();
	  
	  title = new JLabel("進貨單");
	  title.setFont(f2);
	  panel.add(title);
   
	  ADD=new JButton("新增頁籤");
	  ADD.setSize(99,30);
	  ADD.addActionListener(this);
	  panel.add(ADD);
    
	  DEL=new JButton("刪除頁籤");
	  DEL.addActionListener(this);
	  panel.add(DEL);
    
	  UP=new JButton("頁籤位置 上");
	  UP.addActionListener(this);
	  panel.add(UP);
    
	  LEFT=new JButton("頁籤位置 左");
	  LEFT.addActionListener(this);
	  panel.add(LEFT);
    
	  RIGHT=new JButton("頁籤位置 右");
	  RIGHT.addActionListener(this);
	  panel.add(RIGHT);
	  panel.setLayout(new FlowLayout());
	  
	  tp=new JTabbedPane();  
	  tp.add("頁籤0",new Purchase_Button().purchaseButton());
	  tp.addChangeListener(this);
	  	  
	  split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	  split.setTopComponent(panel);
	  split.setBottomComponent(tp);
	  split.setEnabled(false); //讓分隔線不能移動	
	  split.setDividerLocation(50);
	  split.setDividerSize(0);
	    
	  return split;
    }
  
      public void stateChanged(ChangeEvent e) {
      }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource()==UP) {
      tp.setTabPlacement(JTabbedPane.TOP);
      }
    if (e.getSource()==LEFT) {
      tp.setTabPlacement(JTabbedPane.LEFT);
      }
    if (e.getSource()==RIGHT) {
      tp.setTabPlacement(JTabbedPane.RIGHT);
      }
    if (e.getSource()==ADD) {
    	String title="頁籤 " + tabID;
        tp.addTab(title,new Purchase_Button().purchaseButton());
        ++tabID;
      }
    if (e.getSource()==DEL) {
    	if(tp.getTabCount()==0) {
    		JOptionPane.showMessageDialog(null, "空的你刪屁");
    	}else if(tp.getTabCount()==1) {
    		tp.remove(tp.getSelectedIndex());
    		String title="頁籤 " + tabID;
    		tp.addTab(title,new Purchase_Button().purchaseButton());
    		++tabID;
    		//JOptionPane.showMessageDialog(null, "無法刪除最後一個頁籤");
    	}else {
    		tp.remove(tp.getSelectedIndex());
    	}
      }
   }
}
