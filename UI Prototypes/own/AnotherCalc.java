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
    boolean start = true;
    private String tempArg = ""; // предполагаем что она будет пустой
    private String curAct = null;
    private boolean finish = false;

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
        makebutton("7", gridBag, gbc, 0, 1, ins);
        makebutton("8", gridBag, gbc, 1, 1, ins);
        makebutton("9", gridBag, gbc, 2, 1, ins);
        makebutton("+", gridBag, gbc, 3, 1, act);
// вторая строка кнопок
        makebutton("4", gridBag, gbc, 0, 2, ins);
        makebutton("5", gridBag, gbc, 1, 2, ins);
        makebutton("6", gridBag, gbc, 2, 2, ins);
        makebutton("-", gridBag, gbc, 3, 2, act);
// треться строка кнопок
        makebutton("1", gridBag, gbc, 0, 3, ins);
        makebutton("2", gridBag, gbc, 1, 3, ins);
        makebutton("3", gridBag, gbc, 2, 3, ins);
        makebutton("*", gridBag, gbc, 3, 3, act);
// четвертая строка кнопок
        makebutton("CE", gridBag, gbc, 0, 4, act);
        makebutton("0", gridBag, gbc, 1, 4, ins);
        makebutton(".", gridBag, gbc, 2, 4, ins);
        makebutton("/", gridBag, gbc, 3, 4, act);
        //последняя строка - равно
        gbc.gridwidth = 4;
        gbc.weightx = 0.0;
        makebutton("=", gridBag, gbc, 0, 5, act);

        //setSize(250, 300);
        
        setPreferredSize(new Dimension(250, 300));
        setMinimumSize(new Dimension(180, 240));
        setMaximumSize(new Dimension(300, 360));
        
       
        
    }
     // lets try
        public void paint(Graphics g) {
                Dimension d = getSize();
                Dimension m = getMaximumSize();
                boolean resize = d.width > m.width || d.height > m.height;
                d.width = Math.min(m.width, d.width);
                d.height = Math.min(m.height, d.height);
                if (resize) {
                    Point p = getLocation();
                    setVisible(false);
                    setSize(d);
                    setLocation(p);
                    setVisible(true);
                }
                super.paint(g);
            }

    private void makebutton(String name, GridBagLayout gridbag, GridBagConstraints gbc, Integer x, Integer y, ActionListener list) {
        Button button = new Button(name);
        button.addActionListener(list);
        gbc.gridx = x;
        gbc.gridy = y;
        gridbag.setConstraints(button, gbc);
        add(button);

    }

    private class InsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            inputData = event.getActionCommand();
            tempArg = tempArg + inputData;
            if (start) {
                window.setText(inputData);
                start = false;
            } else {
                window.setText(window.getText() + inputData);
            }
        }
    }

    private class ActListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            inputCmd = event.getActionCommand();
            //очистили буферное значение
            if (inputCmd.equals("CE")) {
                clear();
                window.setText(tempResult.toString());
            } else if (tempArg == "" && inputCmd.equals("-")) {
                tempArg = "-";
                if (start) {
                    window.setText(tempArg);
                    start = false;
                } else {
                    window.setText(window.getText() + tempArg);
                };
            } else {
                if (inputCmd.equals("=")) {
                    finish = true;
                }
                window.setText(window.getText() + inputCmd);
                if (tempArg != "") {
                    inputData = tempArg;
                    tempArg = "";
                }
                if (savedAction == null) {
                    savedAction = inputCmd;
                    inputCmd = null;
                }
                Calculate();
                if (finish) {
                    window.setText(tempResult.toString());
                    clear();
                }
            }
        }
    }

    public void Calculate() {
        // разбираемся со значениями
        if (firstArg == null) {
            firstArg = inputData;
            inputData = null;
            if (finish) {
                tempResult = Double.parseDouble("0");
            }
        } else if (secondArg == null) {
            secondArg = inputData;
            inputData = null;
            if (inputCmd.equals("=")) {
                execCmd(firstArg, secondArg, savedAction);
            }
            // если мы знаме что пока только 2 числа и умножение или деление - выполняем сразу
            if (isPrior(savedAction)) {
                execCmd(firstArg, secondArg, savedAction);
                firstArg = tempResult.toString();
                secondArg = null;
                savedAction = inputCmd;
            } // если мызнаем что пока только 2 числа и сложение или вычитание - оставляем в сохраненках
            else if (!isPrior(inputCmd)) {
                execCmd(firstArg, secondArg, inputCmd);
                firstArg = tempResult.toString();
                secondArg = null;
                savedAction = inputCmd;
            } //          
            // если в сохраненке +- а текущее ействике */ тогда запоминаем это 
            else {
                curAct = inputCmd;
            }
        } else {
            //выполняем сохраненное выражение / * и сохраняем его как 2 переменную
            execCmd(secondArg, inputData, curAct);
            secondArg = tempResult.toString();
            //если текущее действие /* то сохранем его 
            if (isPrior(inputCmd)) {
                curAct = inputCmd;
            } //если нет то начинаем счтитать и зануляем
            else {
                execCmd(firstArg, secondArg, savedAction);
                firstArg = tempResult.toString();
                secondArg = null;
                curAct = null;
                savedAction = inputCmd;
            }

        }

    }

    public void clear() {
        firstArg = null;
        secondArg = null;
        savedAction = null;
        inputCmd = null;
        inputData = null;
        tempResult = Double.parseDouble("0");
        start = true;
        tempArg = "";
        curAct = null;
        finish = false;

    }

    public void execCmd(String x, String y, String cmd) {
        Double dx = Double.parseDouble(x);
        Double dy = Double.parseDouble(y);
        if (cmd.equals("/")) {
            tempResult = dx / dy;
        }
        if (cmd.equals("*")) {
            tempResult = dx * dy;
        }
        if (cmd.equals("+")) {
            tempResult = dx + dy;
        }
        if (cmd.equals("-")) {
            tempResult = dx - dy;
        }
    }

    public boolean isPrior(String cmd) {
        if (cmd.equals("*") || cmd.equals("/")) {
            return true;
        } else {
            return false;
        }
    }

}
