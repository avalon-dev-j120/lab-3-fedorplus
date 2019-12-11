package own;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

public class AnotherCalc extends JFrame {
    private JButton window;
    private String firstArg;
    private String secondArg;
    private String savedAction;    
    private String inputCmd;
    private String inputData;
    private Double tempResult = Double.parseDouble("0");
   // private String[] actList = new String[]{"+","-","*","/"};
   // private List list = Arrays.asList(actList);
    public AnotherCalc() {
// init
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setTitle("Калькулятор");
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridBag);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        ActionListener ins = new InsListener();        
        ActionListener act = new ActListener();
//экран
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 4;
        window = new JButton("0");
        window.setEnabled(false);
        window.setBorderPainted(false);
        window.setHorizontalTextPosition(SwingConstants.RIGHT);
        gridBag.setConstraints(window, gbc);
        add(window);

        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
// первая строка кнопок
        makebutton("7", gridBag, gbc,0, 1, ins);  
        makebutton("8", gridBag, gbc,1, 1, ins );
        makebutton("9", gridBag, gbc,2, 1, ins); 
        makebutton("+", gridBag, gbc,3, 1, ins);        
// вторая строка кнопок
        makebutton("4", gridBag, gbc ,0, 2, ins);    
        makebutton("5", gridBag, gbc,1, 2, ins);
        makebutton("6", gridBag, gbc,2, 2, ins);  
        makebutton("-", gridBag, gbc,3, 2, ins);
// треться строка кнопок
        makebutton("1", gridBag, gbc,0, 3, ins);    
        makebutton("2", gridBag, gbc,1, 3, ins);
        makebutton("3", gridBag, gbc,2, 3, ins); 
        makebutton("*", gridBag, gbc,3, 3, ins);          
// четвертая строка кнопок
        makebutton("CE", gridBag, gbc,0, 4, ins);    
        makebutton("0", gridBag, gbc,1, 4, ins);
        makebutton(".", gridBag, gbc,2, 4, ins); 
        makebutton("/", gridBag, gbc,3, 4, ins);
 //последняя строка - равно
        gbc.gridwidth = 4;
        gbc.weightx = 0.0;
        makebutton("=", gridBag, gbc, 0, 5, act);

        setSize(300, 300);
   }
        private void makebutton(String name, GridBagLayout gridbag, GridBagConstraints gbc, Integer x, Integer y, ActionListener list) {
        Button button = new Button(name);
        button.addActionListener(list);
        gbc.gridx = x;
        gbc.gridy = y;
        gridbag.setConstraints(button, gbc);
        add(button);

    }
private String tempArg; // предполагаем что она будет пустой
    private class InsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            inputData = event.getActionCommand();
            tempArg = tempArg + inputData;
            window.setText(window.getText() + inputData);
//            if (firstArg ==null){
//                firstArg = inputData;
//                inputData = null;
//            }
//            else if (secondArg == null){
//                secondArg = inputData;
//                inputData = null;
//            }
        }
    }
    
    private class ActListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            inputCmd = event.getActionCommand();
            //очистили буферное значение
            if (tempArg != null)inputData = tempArg;            
            // теперь нужно прикрыть кейс когда не то выбрали Todo
            //if (actList.)
            
            
//            //if (input.equals("="))window.setText(tempResult.toString());
//            //можно попробовать черех одну перемнную
//            if(inputCmd.equals("+") || inputCmd.equals("-")){
//            if (savedAction == null){savedAction = inputCmd;
//            inputCmd = null;
//            }
//            }
            
                }   
    }
    
    public void Calculate() {
        // разбираемся со значениями
        if (firstArg == null){
            firstArg = inputData;
            inputData = null;
        }
        else if (secondArg ==null){
            secondArg = inputData;            
            inputData = null;
            // если мы знаме что пока только 2 числа и умножение или деление - выполняем сразу
            if (inputCmd.equals("*")) {
                tempResult = Double.parseDouble(firstArg) * Double.parseDouble(secondArg);
                firstArg = tempResult.toString();
                secondArg = null;
            }
            if (inputCmd.equals("/")) {
                tempResult = Double.parseDouble(firstArg) / Double.parseDouble(secondArg);
                firstArg = tempResult.toString();
                secondArg = null;
            }
            // если мызнаем что пока только 2 числа и сложение или вычитание - оставляем в сохраненках
            if (inputCmd.equals("+") || inputCmd.equals("-")){
                savedAction = inputCmd;
                inputCmd = null;
            }
        }
        // а вот это уже означает что мы дошли до второго действия т.к первое было не с макс приоритетом
        //else
       
        
    
}

}
