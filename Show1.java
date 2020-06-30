// SCORECARD


// IMPORTS********************************************
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class Show1 extends JPanel implements ActionListener{

  // variables
  JButton b1,b2;
  String[] columnNames={"UNM","CORRECT","TOTAL","ACCURACY","TIME","DATE"};
  JTable tb;
 
 // Constructor
    Show1(SDemo16 s){

      // Setting Layout
	  	setLayout(null);

      // setting font
       Font f4=new Font("",Font.BOLD,20);

       // show data button
       b1=new JButton("Show Data");
       b1.setBounds(200,0,250,30);
       b1.setFont(f4);
       b1.setBackground(Color.BLACK);
       b1.setForeground(Color.PINK);
       add(b1);
       b1.addActionListener(this);

       // back button to front page
       b2=new JButton("BACK");
       b2.setBounds(10,600,100,30);
       b2.setFont(f4);
       b2.setBackground(Color.BLACK);
       b2.setForeground(Color.PINK);
       add(b2);
       b2.addActionListener(s);

       // show data in table
      tb=new JTable();
      tb.setBounds(20,50,550,550);
      add(tb);
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
        String r="select * from data";
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

}