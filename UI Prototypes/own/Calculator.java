package own;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;



public class Calculator extends JFrame{
    JButton window = new JButton("0");  
    JButton eq = new JButton("=");  
    
    
    public Calculator(){
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationByPlatform(true);
    setTitle("Калькулятор");
    setSize(600, 600);
    BorderLayout lao = new BorderLayout();
    lao.setVgap(40);
    setLayout(lao);
    window.setSize(new Dimension(150, 150));
    window.setEnabled(false);
    Font nw = new Font("TimesRoman", Font.BOLD, 36);
    window.setFont(nw);
    //window
    add(new JButton("hell"), BorderLayout.NORTH );
    add(window, BorderLayout.NORTH);
    GridLayout gr = new GridLayout(4,4);
    gr.setHgap(30);
    gr.setVgap(40);
    JPanel calButtoms = new JPanel(gr);
    
    //calButtoms.se
    calButtoms.add(new JButton("7"));
    calButtoms.add(new JButton("8"));
    calButtoms.add(new JButton("9"));
    calButtoms.add(new JButton("+"));
    calButtoms.add(new JButton("4"));
    calButtoms.add(new JButton("5"));
    calButtoms.add(new JButton("6"));
    calButtoms.add(new JButton("-"));
    calButtoms.add(new JButton("1"));
    calButtoms.add(new JButton("2"));
    calButtoms.add(new JButton("3"));
    calButtoms.add(new JButton("*"));
    calButtoms.add(new JButton("CE"));
    calButtoms.add(new JButton("0"));
    calButtoms.add(new JButton("."));
    calButtoms.add(new JButton("/"));
    add (calButtoms, BorderLayout.CENTER);
    
    add(eq, BorderLayout.SOUTH);
    
    
}
    
}
