import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main_Board extends JFrame {
    private File file;
    private JTextField jtf;
    private JTextArea jTextArea;
    private JTextArea jta;
    private JButton jb2;
    private JButton jl1;
    String[] mounths = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
    private String selectMounth;

    public Main_Board() {
        initSetting();
    }


    private void initSetting() {
        this.setTitle("json 转换(文件名不能使英文！！ 文件名以xls结尾)");
        this.setBounds(100, 100, 800, 300);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setResizable(false);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();

        jl1 = new JButton("请选择xml文件所在地址");
        jl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择");
                file = jfc.getSelectedFile();
                //显示路径
                if (file.getAbsolutePath() != null) {
                    jtf.setText(file.getAbsolutePath());
                }
            }
        });
        jtf = new JTextField(30);
        jp1.add(jl1);
        jp1.add(jtf);
        this.add(jp1);

        // 选择框
        Choice choice = new Choice();
        for (int i = 0; i < mounths.length; i++) {
            choice.add(mounths[i]);
        }
        choice.select(0);
        choice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                selectMounth = (String) e.getItem();
            }
        });
        jb2 = new JButton("转换");
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file != null && file.getAbsolutePath() != null) {
                    //转换excel
                    //todo 启动线程 转换excel
                    MyThread myThread = new MyThread(file.getAbsolutePath(), selectMounth);
                    myThread.setOnListener(new MyThread.MyUpdateListenr() {
                        @Override
                        public void process(String res) {
                            jta.setText("");
                            jta.setText(res);
                        }
                    });
                    myThread.start();
                } else {
                    JOptionPane.showMessageDialog(jp1, "请选择需要转换的excel文件"
                            , "json转换", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        jta = new JTextArea(10, 30);
        jta.setEditable(false);
        jta.setLineWrap(true);
        jta.setSize(500, 200);
        jp2.add(choice);
        jp2.add(jb2);
        jp2.add(jta);
        this.add(jp2);

        JButton jb3 = new JButton("开始生成文件");
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = jta.getText();
                String path = jtf.getText();
                if (res.equals("") || res == null || path.equals("") || path == null) {
                    JOptionPane.showMessageDialog(jp1, "请正确执行"
                            , "json转换", JOptionPane.WARNING_MESSAGE);
                } else {
                    HandlerManager.saveFile(res,path);
                }

            }
        });
        this.add(jb3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


}
