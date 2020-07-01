//math project
//Main Page
// imports**********************************************************************************
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;

// ****************************************************************************************


class MPage extends JPanel implements ActionListener,ItemListener{

    // Variables**********************
    JComboBox jb1,jb2;//for selectng difficlty level and operation 
    JLabel lt1,lt2;//for label title ie. LEVEL AND MATH FN
    JLabel lt3,lt4,lt5;
    JButton b1,b2,b3,b4;//for start,restart,stop and instruction
    JButton back;
    JButton[] opt=new JButton[4];//for option buttons
    JButton name;
    //for backgrund image
    Image im;
    ImageIcon i;
    
    JLabel nm,cr,incr,acc;//for name,correctans ,inncorrectans and accuracy
    JLabel nm1,dif,acc1,tm;
    JLabel Num1,Num2,Ans,Opr;
    static float count=0, correct=0,incorrect=0,accuracy=0;
    Integer ans;
    String unm="Aayushi";

    MPage(SDemo16 s){
        // Layout
        setLayout(null);
        // background image
        i=new ImageIcon("study.jpg");
        im=i.getImage();
    
        Font f=new Font("Algerian",Font.BOLD,20);
        Font f2=new Font("",Font.BOLD,10);
        
        //SELECT LEVEL(EASY,AVERAGE ,DIFFICULT)

        lt1= new JLabel("SELECT LEVEL");
        lt1.setBounds(100,10,150,20);
        lt1.setForeground(Color.white);
        lt1.setFont(f);
        add(lt1);

        jb1=new JComboBox();
        jb1.setBounds(90,40,120,20);
        jb1.addItem("EASY");
        jb1.addItem("AVERAGE");
        jb1.addItem("DIFFICULT");
        add(jb1);

        jb1.addItemListener(this);

        //CHOOSE MATH FUNCTION(ADDITION(+),SUBSTRACTION(-),MULTIPLY(*),DIVIDE(/))
        lt2=new JLabel("CHOOSE MATH FUNCTION");
        lt2.setBounds(510,10,260,20);
        lt2.setForeground(Color.white);
        lt2.setFont(f);
        add(lt2);

        jb2=new JComboBox();
        jb2.setBounds(550,40,150,20);
        jb2.addItem("ADDITION(+)");
        jb2.addItem("SUBSTRACTION(-)");
        jb2.addItem("MULTIPLY(*)");
        jb2.addItem("DIVIDE(/)");
        add(jb2);
        jb2.addItemListener(this);

        Font f1=new Font("",Font.BOLD,13);
      
        //START BUTTON
        b1=new JButton("START");
        b1.setBounds(50,90,100,30);
        b1.setBackground(Color.BLUE);
        b1.setFont(f1);
        add(b1);
        b1.addActionListener(this);

        //RESTART BUTTON
        b2=new JButton("RESTART");
        b2.setBounds(270,90,200,30);
        b2.setBackground(Color.PINK);
        b2.setFont(f1);
        add(b2);
        b2.addActionListener(this);

        //INSTRUCTION BUTTON
        b4=new JButton("INSTRUCTIONS");
        b4.setBounds(600,90,150,30);
        b4.setBackground(Color.PINK);
        b4.setFont(f1);
        add(b4);
        b4.addActionListener(this);

        //OPTIONS opt[0-3]
      
        //OPTION A
        opt[0]=new JButton("A");
        opt[0].setBounds(50,350,100,30);
        opt[0].setBackground(Color.PINK);
        opt[0].setFont(f1);
        add(opt[0]);
        opt[0].addActionListener(this);
        
        
        //OPTION B
        opt[1]=new JButton("B");
        opt[1].setBounds(250,350,100,30);
        opt[1].setBackground(Color.PINK);
        opt[1].setFont(f1);
        add(opt[1]);
        opt[1].addActionListener(this);
       
        //OPTION C
        opt[2]=new JButton("C");
        opt[2].setBounds(450,350,100,30);
        opt[2].setBackground(Color.PINK);
        opt[2].setFont(f1);
        add(opt[2]);
        opt[2].addActionListener(this);
        
        //OPTION D
        opt[3]=new JButton("D");
        opt[3].setBounds(650,350,120,30);
        opt[3].setBackground(Color.PINK);
        opt[3].setFont(f1);
        add(opt[3]);
        opt[3].addActionListener(this);

        

        lt3=new JLabel("-LOOK FOR HIGH SCORE-");
        lt3.setBounds(240,390,300,50);
        lt3.setFont(f);
        add(lt3);

        lt4=new JLabel("PLAYER SCORE-");
        lt4.setBounds(50,430,200,20);
        lt4.setFont(f);
        add(lt4);
        
        
     
        nm=new JLabel("Name:");
        nm.setBounds(50,450,100,20);
        nm.setFont(f);
        add(nm);

        name=new JButton("Player");
        name.setBounds(150,450,100,20);
        name.setFont(f);
        add(name);
        name.addActionListener(this);

        cr=new JLabel("Correct:0");
        cr.setBounds(50,480,200,20);
        cr.setFont(f);
        add(cr);

        incr=new JLabel("Incorrect:0");
        incr.setBounds(50,510,200,20);
        incr.setFont(f);
        add(incr);
        
        acc=new JLabel("Accuracy:0%");
        acc.setBounds(50,540,200,20);
        acc.setFont(f);
        add(acc);      
        acc.setVisible(true);
       
       Font f3=new Font("",Font.BOLD,20);

        Num1=new JLabel("Num1");
        Num1.setBounds(360,150,50,50);
        Num1.setFont(f3);
        add(Num1);


        Opr=new JLabel("Opr");
        Opr.setBounds(300,210,50,50);
        Opr.setFont(f3);
        add(Opr);


        Num2=new JLabel("Num2");
        Num2.setBounds(360,210,50,50);
        Num2.setFont(f3);
        add(Num2);


        Ans=new JLabel("???????");
        Ans.setBounds(300,270,200,40);
        Ans.setFont(f3);
        add(Ans);

        Font f4=new Font("",Font.BOLD,20);

        back=new JButton("BACK");
        back.setBounds(10,600,100,30);
        back.setFont(f4);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.PINK);
        add(back);
        back.addActionListener(s);

    }
    public void actionPerformed(ActionEvent e){
    
         if(e.getSource()==name){
               String n= JOptionPane.showInputDialog("ENTER USERNAME:-");       
                if(n!=""){
                unm=n;
                }
                name.setText(unm);
         }


        String s1=(String)jb1.getSelectedItem();
        String s2=(String)jb2.getSelectedItem();
        
        //for option 0
       if(e.getSource()==opt[0]){
            JButton b1=(JButton)e.getSource();
            String getAns =b1.getText();
            String Ans=""+ans;
            if(getAns.equals(Ans)){
                correct=correct+1;
                cr.setText("correct: "+correct);
            }
            else{
                 incorrect=incorrect+1;
                 incr.setText("incorrect: "+incorrect);
            }
            accuracy=((float)(correct/count))*100;
            acc.setText("Accuracy: "+accuracy);
            opt[0].setText("A");
            opt[1].setText("B");
            opt[2].setText("c");
            opt[3].setText("D");
            Num1.setText("");
            Num2.setText("");
            start();
       }

        //for option 1
        if(e.getSource()==opt[1]){  
            JButton b1=(JButton)e.getSource();
            String getAns =b1.getText();
            String Ans=""+ans;
            if(getAns.equals(Ans)){
               
                correct=correct+1;
                cr.setText("correct: "+correct);
            }
            else{
                 incorrect+=1;
                 incr.setText("incorrect:"+incorrect);
            }
            accuracy=((float)(correct/count))*100;

            acc.setText("Accuracy "+accuracy);
            opt[0].setText("A");
            opt[1].setText("B");
            opt[2].setText("c");
            opt[3].setText("D");
            Num1.setText("");
            Num2.setText("");
            start();

     
       }

        //for option 2
       if(e.getSource()==opt[2]){
            JButton b1=(JButton)e.getSource();
            String getAns =b1.getText();
            String Ans=""+ans;
            if(getAns.equals(Ans)){
                
                correct=correct+1;
                cr.setText("correct: "+correct);
            }
            else{
                 incorrect+=1;
                 incr.setText("incorrect:"+incorrect);
            }
            accuracy=((float)(correct/count))*100;
            acc.setText("Accuracy-"+accuracy);
            opt[0].setText("A");
            opt[1].setText("B");
            opt[2].setText("c");
            opt[3].setText("D");
            Num1.setText("");
            Num2.setText("");
            start();

        }

        //for option 3

       if(e.getSource()==opt[3]){
            JButton b1=(JButton)e.getSource();
            String getAns =b1.getText();
            String Ans=""+ans;
            if(getAns.equals(Ans)){
                
                correct=correct+1;
                cr.setText("correct: "+correct);
            }
            else{
                 incorrect+=1;
                 incr.setText("incorrect:"+incorrect);
            }
            accuracy=((float)(correct/count))*100;
            acc.setText("Accuracy-"+accuracy);
            opt[0].setText("A");
            opt[1].setText("B");
            opt[2].setText("c");
            opt[3].setText("D");
            Num1.setText("");
            Num2.setText("");
            start();


       }

        // clicked on start**********************************************************
        if(e.getSource()==b1){
        
           start();
        }


        // clicked on restart************************************************************
        if(e.getSource()==b2){
            restart();

        }   
        // clicked instructions
        if(e.getSource()==b4){
                 JOptionPane.showMessageDialog(Ans,"INSTRUCTIONS**\n 1.There are 10 Questions. \n 2.You can take your own time to solve Questions.\n 3.You can check your activity in ScoreBoard Section. \n\n ENJOY!!");

        }

    }

    //to put background image
    public void paintComponent(Graphics g){
        g.drawImage(im,0,0,800,700,this);
    }

    public void itemStateChanged(ItemEvent e){
        String s1=(String)jb1.getSelectedItem();
        String s2=(String)jb2.getSelectedItem();
       if(s1.equals("")){
        JOptionPane.showMessageDialog(jb1,"Select the Item!!");
       }

       if(s2.equals("")){
        JOptionPane.showMessageDialog(jb2,"Select the Item!!");
       }
    }

        //SET DATA****************************************************************** 
        void set_value(int num1,int num2,int ans ,String sign){
           
            opt[0].setText("");
            opt[1].setText("");
            opt[2].setText("");
            opt[3].setText("");
            Num1.setText(""+num1);
            Num2.setText(""+num2);
            Opr.setText(sign);
            int optno = (int)Math.round(Math.random() *3);
            opt[optno].setText(""+ans);

            for(int i=0;i<=3;i++){
                if(opt[i].getText()==""){
                    int value=(int)Math.round(Math.random() *30);
                    opt[i].setText(""+value); 
                }
            }
            count++;
        }

        // end of set_value**********************************************************


    void easy(){
        String s1=(String)jb1.getSelectedItem();
        String s2=(String)jb2.getSelectedItem();

         int num1 = (int)Math.round(Math.random() *10);
                int num2 = (int)Math.round(Math.random() *10);

                    if(s2=="ADDITION(+)"){
                         ans=num1+num2;
                        set_value(num1,num2,ans,"+");
                    }
                   else if(s2=="SUBSTRACTION(-)"){
                         ans=num1-num2;
                        set_value(num1,num2,ans,"-");

                    }
                   else if(s2=="MULTIPLY(*)"){
                         ans=num1*num2;
                        set_value(num1,num2,ans,"*");
                        
                    }
                    else if(s2=="DIVIDE(/)"){
                         ans=num1/num2;
                        set_value(num1,num2,ans,"/");
                        
                    }

    }
    void avgerage(){
        String s1=(String)jb1.getSelectedItem();
        String s2=(String)jb2.getSelectedItem();

         int num1 = (int)Math.round(Math.random() *20);
                     int num2 = (int)Math.round(Math.random() *20);
                      if(s2=="ADDITION(+)"){
                         ans=num1+num2;
                        set_value(num1,num2,ans,"+");
                    }
                   else if(s2=="SUBSTRACTION(-)"){
                         ans=num1-num2;
                        set_value(num1,num2,ans,"-");

                    }
                   else if(s2=="MULTIPLY(*)"){
                         ans=num1*num2;
                        set_value(num1,num2,ans,"*");
                        
                    }
                    else if(s2=="DIVIDE(/)"){
                         ans=num1/num2;
                        set_value(num1,num2,ans,"/");
                        
                    }
    }

     void difficult(){
        String s1=(String)jb1.getSelectedItem();
        String s2=(String)jb2.getSelectedItem();

         int num1 = (int)Math.round(Math.random() *50);
                     int num2 = (int)Math.round(Math.random() *50);

                     if(s2=="ADDITION(+)"){
                         ans=num1+num2;
                        set_value(num1,num2,ans,"+");
                    }
                   else if(s2=="SUBSTRACTION(-)"){
                         ans=num1-num2;
                        set_value(num1,num2,ans,"-");

                    }
                   else if(s2=="MULTIPLY(*)"){
                         ans=num1*num2;
                        set_value(num1,num2,ans,"*");
                        
                    }
                    else if(s2=="DIVIDE(/)"){
                         ans=num1/num2;
                        set_value(num1,num2,ans,"/");
                        
                    }

     }
     void start(){
        String s1=(String)jb1.getSelectedItem();
        String s2=(String)jb2.getSelectedItem();

        if(count<10){

                 if(s1=="EASY"){

                    easy();
                }
                else if(s1=="AVERAGE"){

                    avgerage();

                }
                else if(s1=="DIFFICULT"){

                    difficult();
                }
        }
       else{
            Num1.setText("Num1");
            Num2.setText("Num2");
            Opr.setText("opr");
             opt[0].setText("A");
            opt[1].setText("B");
            opt[2].setText("c");
            opt[3].setText("D");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String db_url="jdbc:mysql://localhost:3306/math?allowPublicKeyRetrieval=true ";
                String db_uname="root";
                String db_upass="root";
                Connection con=DriverManager.getConnection(db_url,db_uname,db_upass);   
                Statement st=con.createStatement();
                int c=(int)count;
                String q="insert into data(UNM,CORRECT,TOTAL,ACCURACY,TIME,DATE)values('"+unm+"','"+((int)correct)+"','"+((int)c)+"','"+((int)accuracy)+"','"+java.time.LocalTime.now()+"','"+java.time.LocalDate.now()+"')";
                st.executeUpdate(q);
                con.close(); 
            }
            catch(Exception e){
                    System.out.println(e);
            }
            
            
            JOptionPane.showMessageDialog(Ans,"WellDone!!\n I hope u loved it!!");
       }
    }
    
    void restart(){
            count=0;
            correct=0;
            cr.setText("correct: "+correct);
            incorrect=0;
            incr.setText("incorrect:"+incorrect);
            opt[0].setText("A");
            opt[1].setText("B");
            opt[2].setText("c");
            opt[3].setText("D");
            Num1.setText("Num1");
            Num2.setText("Num2");
            Opr.setText("opr");
            nm.setText("Name:");
            acc.setText("Accuracy-0");
           
            start();
    }    
}  
