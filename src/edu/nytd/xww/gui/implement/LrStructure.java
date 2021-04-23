package edu.nytd.xww.gui.implement;

import edu.nytd.xww.gui.MainGui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yanyu
 * @date 2021年4月22日
 */
public class LrStructure implements MainGui {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private JPanel panel;
    /**
     * 初始化方法
     */
    private void init(){
        panel = new JPanel(new BorderLayout());
    }


}
