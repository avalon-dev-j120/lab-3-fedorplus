package own;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Dictionary;

public class ColorPicker extends JFrame implements ChangeListener {

    private JPanel color;
    private JLabel labelRed;
    private JLabel labelGreen;
    private JLabel labelBlue;
    private JPanel labelSet;
    private JSlider redSlide;
    private JSlider greenSlide;
    private JSlider blueSlide;

    public ColorPicker() {

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setTitle("ColorPicker");
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridBag);

        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 3.0;
        gbc.weighty = 3.0;

        color = new JPanel();
        color.setBackground(new Color(125, 125, 125));

        //init for panel 
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gridBag.setConstraints(color, gbc);
        // подсказка
        color.setToolTipText(CurColor());
        add(color);

        //init for titles
        labelRed = new JLabel("Red:");
        labelGreen = new JLabel("Green:");
        labelBlue = new JLabel("Blue:");

        labelSet = new JPanel();
        GridLayout labelSetLayout = new GridLayout(3, 1);
        labelSet.setLayout(labelSetLayout);
        labelSet.add(labelRed);
        labelSet.add(labelGreen);
        labelSet.add(labelBlue);

        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 3;
        gbc.gridy = 1;
        gridBag.setConstraints(labelSet, gbc);
        add(labelSet);
        // init for slider
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        redSlide = addSlider("hell", gridBag, gbc, 4, 1);
        greenSlide = addSlider("hell", gridBag, gbc, 4, 2);
        blueSlide = addSlider("hell", gridBag, gbc, 4, 3);

        setMinimumSize(new Dimension(550, 300));
        setSize(800, 360);
        setVisible(true);

    }

    public JSlider addSlider(String name, GridBagLayout gridbag, GridBagConstraints gbc, Integer x, Integer y) {
        JSlider panel = new JSlider(0, 255, 125);
        panel.setMajorTickSpacing(15);
        panel.setPaintLabels(true);
        panel.setPaintTicks(true);
        panel.addChangeListener(this);

        Dictionary dictionary = panel.getLabelTable();
        int i = 15;
        while (i < 255) {
            dictionary.remove(i);
            i = i + 15;
        }

        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.gridx = x;
        gbc.gridy = y;
        gridbag.setConstraints(panel, gbc);
        add(panel);
        return panel;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        if (ce.getSource() == redSlide || ce.getSource() == greenSlide || ce.getSource() == blueSlide) {
            color.setBackground(new Color(redSlide.getValue(), greenSlide.getValue(), blueSlide.getValue()));
            color.setToolTipText(CurColor());
            setClipboard(CurColor());
        }
    }

    public String CurColor() {
        Color curCol = color.getBackground();
        //return Integer.toHexString(curCol.getRGB());
        return "#" + String.format("%x", curCol.getRGB()).substring(2);

    }

    public static void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

}
