//front  page

// imports*************************************************
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class FPage extends JPanel implements ActionListener{
    // variables
	ImageIcon i ;
    Image im;
    JLabel l;
    JButton b1,b2,b3;
    
    JTable tb;
    String[] columnNames={"UNM","CORRECT","TOTAL","ACCURACY","TIME","DATE"};
	
    //Constructor 
    FPage(SDemo16 s){
        // setting layout
		setLayout(null);

        // setting background image
		i=new ImageIcon("study.jpg");
  	     im=i.getImage();
    

    // setting font
     Font f=new Font("",Font.BOLD,20);
    

    l=new JLabel("WELCOME TO THE MATH QUIZ");
    l.setBounds(100,100,300,200);
    l.setFont(f);
    add(l);

	 b1=new JButton("REGISTRATION");
     b1.setBounds(150,380,200,50); 
     b1.setBackground(Color.BLACK);
     b1.setForeground(Color.PINK);
     b1.setFont(f);
     add(b1);
     b1.addActionListener(s);
     
     b2=new JButton("SCORE BOARD");
     b2.setBounds(380,380,200,50);
     b2.setBackground(Color.BLACK);
     b2.setForeground(Color.PINK);
     b2.setFont(f);
     add(b2);
     b2.addActionListener(s);
     
     b3=new JButton("PLAY");
     b3.setBounds(100,450,550,50);
     b3.setBackground(Color.BLACK);
     b3.setForeground(Color.PINK);
     b3.setFont(f);
     add(b3);
     b3.addActionListener(s);

    }


	public void actionPerformed(ActionEvent e){
		String s1="";
		String s2=""; 
		String s3="";
		String s4="";
		String s5="";
	    String s6="";

    try{
    	Class.forName("com.mysql.jdbc.Driver");
	    String db_url="jdbc:mysql://localhost:3306/math?allowPublicKeyRetrieval=true ";
	    String db_uname="root";
        String db_upass="root";
	    Connection con=DriverManager.getConnection(db_url,db_uname,db_upass);	
        Statement st =con.createStatement();
        String r="select *from  reg1";
        ResultSet rs=st.executeQuery(r);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tb.setModel(model);
        model.addRow(new Object[]{"UNM","CORRECT","TOTAL","ACCURACY","TIME","DATE"});
        while (rs.next()) {
                s1= rs.getString(1);
                s2 = rs.getString(2);
                s3 = rs.getString(3);
                s4 = rs.getString(4);
                s5 = rs.getString(5); 
                s6 = rs.getString(6);    
                model.addRow(new Object[]{s1,s2,s3,s4,s5,s6});
            }
            con.close();   
        }
    catch(Exception e2){
        	System.out.println(e2);
       }
   }
    public void paintComponent(Graphics g){
      g.drawImage(im,0,0,800,700,this);
	}
}