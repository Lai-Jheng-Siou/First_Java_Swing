import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JComboBox;
import java.awt.event.*;
import java.awt.*;

public class Setting implements ActionListener{
	private static JFrame frame = null;
	private JPanel panel = null;
	private JList<String> list = null;
	private JButton exit = null;
	private JSplitPane split = null;
	private static JPanel panelNull = null;
	Font  f1  = new Font(Font.SANS_SERIF,  Font.BOLD,  16);
	
	Setting(){
		frame = new JFrame("�ĥ��t��");
		Image icon = Toolkit.getDefaultToolkit().getImage("src/Gif/22.jpg"); 
		frame.setIconImage(icon);
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		exit = new JButton("�W�@��");
		exit.addActionListener(this);
		panel.add(exit);

		String[] listItem = {"New�Τ�","Del�Τ�","�s�W���~"};
		list = new JList<>(listItem);
		list.setFont(f1);
		list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (arg0.getValueIsAdjusting()) {
                	JList<String> source = (JList<String>)arg0.getSource();
                    String selected = source.getSelectedValue().toString();
                    if(selected =="New�Τ�") {
                    	split.setRightComponent(new Setting_addUser().setting_addUser());
                    	split.setDividerLocation(70);
                    }else if(selected =="Del�Τ�") {
                    	JOptionPane.showMessageDialog(null, "�٨S���n�q�д���");
                    }else if(selected =="�s�W���~") {
                    	split.setRightComponent(new Setting_addProduct().addproduct());
                    	split.setDividerLocation(70);
                    }
                }
            }
        });		
		panel.add(list);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));

		panelNull = new JPanel();
		split = new JSplitPane();
		split.setDividerLocation(70);
		split.setDividerSize(7);  //���j�u�ʲ�
		frame.getContentPane().add(split,BorderLayout.CENTER);
		split.setLeftComponent(panel);	
		split.setRightComponent(panelNull); //������@�ӪŪ�JPanel �צ�w�]���s
		split.setEnabled(false); //�����j�u���ಾ��
		
		frame.setSize(400,300);
		frame.setLocationRelativeTo(null); //�q�{�������}��
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowHandler(frame));
		frame.setVisible(true);
	}
	public void actionPerformed (ActionEvent e) {
		if(e.getSource()==exit) {
			new Main_page();
			Setting.frame.dispose();
		}
	}
	class WindowHandler extends WindowAdapter {
		  JFrame frame;
		  public WindowHandler(JFrame frame) {this.frame=frame;}
		  public void windowClosing(WindowEvent e) {
		    int result=JOptionPane.showConfirmDialog(frame,
		               "�T�w�n�����{����?",
		               "�T�{�T��",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.WARNING_MESSAGE);
		    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
		    }  
		  }
}
