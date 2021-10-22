import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sales_TabbedPane implements ChangeListener,ActionListener {
	private JPanel panel = null;
	private JLabel title = null;
	private static JTabbedPane tp = null;
	private JButton ADD,DEL,UP,LEFT,RIGHT = null;
	private JSplitPane split = null;
	Font  f2  = new Font(Font.DIALOG,  Font.BOLD, 30);  //�r��2
	int tabID=1; 
	 
  public JSplitPane tabbedPane() {
	  	  	  
	  panel=new JPanel();
	  
	  title = new JLabel("�X�f��");
	  title.setFont(f2);
	  panel.add(title);
   
	  ADD=new JButton("�s�W����");
	  ADD.setSize(99,30);
	  ADD.addActionListener(this);
	  panel.add(ADD);
    
	  DEL=new JButton("�R������");
	  DEL.addActionListener(this);
	  panel.add(DEL);
    
	  UP=new JButton("���Ҧ�m �W");
	  UP.addActionListener(this);
	  panel.add(UP);
    
	  LEFT=new JButton("���Ҧ�m ��");
	  LEFT.addActionListener(this);
	  panel.add(LEFT);
    
	  RIGHT=new JButton("���Ҧ�m �k");
	  RIGHT.addActionListener(this);
	  panel.add(RIGHT);	  
	  panel.setLayout(new FlowLayout());
	  
	  tp=new JTabbedPane();
	  tp.add("����0",new Sales_Button().salesButton());
	  tp.setBounds(10,40,1000,600);
	  tp.addChangeListener(this);
	  panel.add(tp);
	  
	  split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	  split.setTopComponent(panel);
	  split.setBottomComponent(tp);
	  split.setEnabled(false); //�����j�u���ಾ��	
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
      String title="���� " + tabID;

      tp.addTab(title,new Sales_Button().salesButton());
      ++tabID;
      }
    if (e.getSource()==DEL) {
    	if(tp.getTabCount()==1) {
    		tp.remove(tp.getSelectedIndex());
    		String title="���� " + tabID;
    		tp.addTab(title,new Sales_Button().salesButton());
    		++tabID;
    	}else {
    		tp.remove(tp.getSelectedIndex());
    	}
      }
    }
  }
