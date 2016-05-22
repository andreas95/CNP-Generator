import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
public class GUI {
private JFrame frame;
private JPanel panel;
private JLabel lab1,lab2,lab3,lab4;
private JComboBox cb1,cb2;
private JTextField field;
private JButton generate;
 public GUI(){
	frame=new JFrame();
	frame.setTitle("CNP GENERATOR V1.0");
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	panel=new JPanel();
	panel.setLayout(null);
	panel.setBackground(Color.BLACK);
	frame.add(panel);
	lab1=new JLabel("Selectati datele");
	lab1.setBounds(150, 14, 146, 14);
	lab1.setForeground(Color.green);
	lab1.setFont(new Font("Tahoma", Font.BOLD, 15));
	lab2=new JLabel("Sexul");
	lab2.setBounds(15,40,40, 14);
	lab2.setForeground(Color.green);
	lab2.setFont(new Font("Tahoma", Font.BOLD, 12));
	lab3=new JLabel("Data nasterii");
	lab3.setBounds(105, 40, 100, 14);
	lab3.setFont(new Font("Tahoma",Font.BOLD,12));
	lab3.setForeground(Color.green);
	lab4=new JLabel("Judetul");
	lab4.setBounds(270,40,100,14);
	lab4.setFont(new Font("Tahoma",Font.BOLD,12));
	lab4.setForeground(Color.green);
	panel.add(lab1);
	panel.add(lab2);
	panel.add(lab3);
	panel.add(lab4);
	cb1=new JComboBox();
	cb1.setModel(new DefaultComboBoxModel(new String[] {"-","M","F"} ));
	cb1.setBounds(15,60,40,20);
	panel.add(cb1);
	cb2=new JComboBox();
	String judet[]={"-","Alba","Arad","Arges","Bacau","Bihor","Bistrita-Nasaud","Botosani",
			"Brasov","Braila","Buzau","Caras-Severin","Cluj","Constanta","Covasna",
			"Dambovita","Dolj","Galati","Gorj","Harghita","Hundedoara","Ialomita",
			"Iasi","Ilfov","Maramures","Mehedinti","Mures","Neamt","Olt","Prahova",
			"Satu mare","Salaj","Sibiu","Suceava","Teleorman","Timis","Tulcea","Vaslui",
			"Valcea","Vrancea","Bucuresti","Bucuresti S1","Bucuresti S2",
			"Bucuresti S3","Bucuresti S4","Bucuresti S5",
			"Bucuresti S6","Calarasi","Giurgiu"};
	cb2.setModel(new DefaultComboBoxModel(judet));
	cb2.setBounds(250,60,115,20);
	panel.add(cb2);
	JDateChooser data=new JDateChooser();
	data.setDateFormatString("dd/MM/yyyy");
	data.setBounds(100,60,100,20);
	panel.add(data);
    field=new JTextField();
    field.setHorizontalAlignment(JTextField.CENTER);
    field.setFont(new Font("Tahoma", Font.BOLD, 14));
    field.setBounds(130, 160, 170, 40);
    field.setEditable(false);
    field.setVisible(false);
    panel.add(field);
	generate=new JButton("Genereaza");
	generate.setBounds(170, 105, 100, 25);
	generate.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			String cnp[]=new String[13];
			if (cb1.getSelectedIndex()==0)
				JOptionPane.showMessageDialog(null,"Selectati sexul!","ERROR",JOptionPane.ERROR_MESSAGE);
			else
				cnp[0]=Integer.toString(cb1.getSelectedIndex());
			if (data.getDate()==null)
				JOptionPane.showMessageDialog(null,"Introduceti data nasterii!","ERROR",JOptionPane.ERROR_MESSAGE);
			else
			{
	        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	        String date = fmt.format(data.getDate());
	        int j=5;
	        int k=0;
	        for (int i=0;i<date.length();i++)
	        	if (date.charAt(i)!='/')
	        	{
	        		cnp[j]=Character.toString(date.charAt(i));
	        		j++;
	        	}
	        	else
	        	{
	        		k++;
	        		if (k==2) i=7;
	        		j=j-4;
	        	}}
	        if (cb2.getSelectedIndex()==0)
				JOptionPane.showMessageDialog(null,"Selectati judetul!","ERROR",JOptionPane.ERROR_MESSAGE);
			else
	        {
				cnp[7]=Integer.toString(cb2.getSelectedIndex()/10);
				cnp[8]=Integer.toString(cb2.getSelectedIndex()%10);
	        }
	   		Random r=new Random();
	   		cnp[9]=Integer.toString(r.nextInt(10));
	   		cnp[10]=Integer.toString(r.nextInt(10));
	   		cnp[11]=Integer.toString(r.nextInt(10));
	   		int sum=0;
	   		int[] c={2,7,9,1,4,6,3,5,8,2,7,9};
	   		for (int i=0;i<12;i++)
	   			sum+=c[i]*(Integer.parseInt(cnp[i]));
	   			sum%=11;
	   			if (sum==10)
	   			cnp[12]=Integer.toString(1);
	   			else
	   				cnp[12]=Integer.toString(sum);	
	   			String test="";
	   			for (String str: cnp)
	   				test+=str;
	   		   field.setText(test);
		       field.setVisible(true);
		}	
	});
	panel.add(generate);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
}
}
