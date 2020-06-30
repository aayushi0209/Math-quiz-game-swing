//math project-Quiz
//set classpath=C:\Users\Aayushi Gupta\Desktop\prgs\mathquiz1\mysql-connector-java-8.0.17.jar;.;%classpath%

// This is the file used to link all the cards.

// imports*****************************************************************************
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

// Constructor
class SDemo16 extends JFrame implements ActionListener{
 
 // Extra Pages(Cards)
  FPage p1;//first page
  MPage m1;//main page
  Show1 s1; //table (performers)

  // Layout used
  CardLayout card;//card object
  Container cn;
  
  //Username 
  String unm;

	// Constructor
  SDemo16(){
	card=new CardLayout();
	cn=getContentPane();
     setLayout(card); //card layout set
                            
     p1=new FPage(this);
     add(p1); 
     m1=new MPage(this);
     add(m1);
     s1=new Show1(this);
     add(s1);

	}//end of constructor

  // Events**********************************
   public void actionPerformed(ActionEvent e){
  
   //register yourself   	
   if(e.getSource()==p1.b1){
       String unm= JOptionPane.showInputDialog("ENTER USERNAME:-");//enter name to reg
       try{
           Class.forName("com.mysql.jdbc.Driver");
           String db_uname="root";
           String db_upass="root";
           String db_url="jdbc:mysql://localhost:3306/math?useSSL=false";
           Connection con=DriverManager.getConnection(db_url,db_uname,db_upass);
           Statement st=con.createStatement();
           String q="insert into reg(UNAME)values('"+unm+"')";
           st.executeUpdate(q);
           con.close();
           JOptionPane.showMessageDialog(p1.b1,"REGISTERED SUCCESSFULLY!!");
        } 
        catch(Exception e1){
        	System.out.println(e1);
        }
    }

    //back button show
     if(e.getSource()==s1.b2){
       card.first(cn);
     }

     //back button MPAGE
     if(e.getSource()==m1.back){
       card.first(cn);
     }
    
   //ScoreBoard
   	if(e.getSource()==p1.b2){
   	card.last(cn);
    }


	// start Play
    if(e.getSource()==p1.b3){
   	    unm= JOptionPane.showInputDialog("ENTER USERNAME:-");
       if(unm!=""){
            try{
           Class.forName("com.mysql.jdbc.Driver");
           String db_url="jdbc:mysql://localhost:3306/math?allowPublicKeyRetrieval=true  ";
           String db_uname="root";
           String db_upass="root";
         
           Connection con =DriverManager.getConnection(db_url,db_uname,db_upass); 
           Statement st=con.createStatement();
           String q="select *from reg ";
           ResultSet rs=st.executeQuery(q);
           int flag =0;
    
          while(rs.next()){
             if(rs.getString(1).equals(unm)){
                  flag=1;
                 card.next(cn);
                  // break;
              }
          }
          if(flag==0){
             JOptionPane.showMessageDialog(p1.b3,"INVALID USERNAME,REGISTER FIRST!!!","ALERT",JOptionPane.WARNING_MESSAGE);
          }
         con.close();
       }
        catch(Exception e2){
               System.out.println(e2);
       }
       }

        }//end of if  
   }//END OF FUN(action listener)

	
  // Main Method 
  public static void main(String[] args) {
    
		SDemo16 f=new SDemo16();
		f.setVisible(true);
    f.setResizable(false);
    Toolkit t=Toolkit.getDefaultToolkit();
    Image i=t.getImage("icon2.png");
    f.setIconImage(i);
		f.setLocation(150,0);
		f.setSize(800,700);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

	}
}