package edu.nytd.xww.gui.implement;

import edu.nytd.xww.controller.implement.EventController;
import edu.nytd.xww.gui.MainGui;
import edu.nytd.xww.message.Message;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yanyu
 * 这是聊天框的 BorderLayout型 框架
 */
public class BorderFrame extends JFrame implements MainGui {
    /**
     * 默认宽 800像素   */
    private static final int DEFAULT_WIDTH = 800;

    /**
     * 默认高 600像素
     */
    private static final int DEFAULT_HEIGHT = 600;

    /**
     * 总布局
     */
    private final JPanel jPanel = new JPanel(new BorderLayout());

    /**
     * IP地址
     */
    private static JTextField address;

    /**
     * 接收框
     */
    private static JTextArea textArea;

    /**
     * 复选框
     */
    private static JComboBox<String> comboBox;

    /**
     * 无参数构造方法
     */
    public BorderFrame(){
        // 要在队列中设置属性
        EventQueue.invokeLater(()->{
            // 设置窗口默认大小
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            // 设置标题
            setTitle(" 聊 天 窗  see code in GitHub@gott1999 or Gitee@xiang_wei_wei");
            // 设置默认关闭选项
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // 内容初始化
            init();
            // 允许修改窗体大小
            setResizable(true);
            // 最后设置可见
            setVisible(true);
        });
    }

    /**
     * 初始化各个组件
     */
    private void init(){
        initNorth();
        initEast();
        initSouth();
        initWest();
        initCenter();
        this.add(jPanel);
    }


    /**
     * 初始化北部
     */
    private void initNorth(){
        var temp = new JPanel(new FlowLayout());
        // 提示信息
        var label = new JLabel("对方的 “IP:端口号”");
        temp.add(label);
        // IP输入框
        address = new JTextField("192.168.31.4:8888",10);
        temp.add(address);

        jPanel.add(temp,BorderLayout.NORTH);
    }

    /**
     * 初始化南部
     */
    private void initSouth(){
        // 嵌套的流式布局
        var temp = new JPanel(new FlowLayout(FlowLayout.LEFT));


        // 输入框
        var inputArea = new JTextArea(8,60);
        inputArea.setLineWrap(true);
        temp.add(inputArea);

        // 设置按钮
        var button = new JButton("发送信息");
        button.addActionListener(event -> {
            String url = address.getText();
            String text = inputArea.getText();
            Message message = new Message(url,text);

            // 发送检查
            if ((url == null || "".equals(url) || text == null || "".equals(text))){
                textArea.append("输入内容不要为空！\n");
            }else if (EventController.sendMessage(message)){
                inputArea.setText("");
                textArea.append("你对 "+ url +" 说:" + text +"\n");
            }else {
                textArea.append("发生错误了\n");
            }
        });
        temp.add(button);

        // 添加到总面板
        jPanel.add(temp,BorderLayout.SOUTH);
    }

    /**
     * 初始化东部
     */
    private void initEast(){
        var temp = new JPanel();
        comboBox = new JComboBox<>();
        comboBox.addItem("明文发送");
        temp.add(comboBox);
        jPanel.add(temp,BorderLayout.EAST);
    }

    /**
     * 初始化西部
     */
    private void initWest(){

    }

    /**
     * 初始化中部
     */
    private void initCenter(){
        textArea = new JTextArea("欢迎来到聊天窗~\n");
        textArea.setEditable(false);
        jPanel.add(textArea);
    }


    /**
     * 更新文本区
     */
    public static void update(Message message){
        textArea.append(message.getUrl() + "对你说:" + message.getText() +"\n");
    }

}
