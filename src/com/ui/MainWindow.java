package com.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	private JButton btnAddScore;
	private JButton btnQueryScore;
	private JButton btnStudentInfo;
	
	public MainWindow(){
		initUI();
		initEvent();
	}
	
	private void initUI(){
		Container container = getContentPane();// 将窗体转换为容器
		container.setLayout(null);
		//
		btnAddScore = new JButton("成绩录入");
		btnAddScore.setBounds(50, 20, 300, 60);
		btnQueryScore = new JButton("成绩查询");
		btnQueryScore.setBounds(50, 100, 300, 60);
		btnStudentInfo = new JButton("学生信息管理");
		btnStudentInfo.setBounds(50, 180, 300, 60);
		//
		container.add(btnAddScore);
		container.add(btnQueryScore);
		container.add(btnStudentInfo);
		//
		this.setResizable(false);// 禁止调整界面的大小
		this.setTitle("管理员主界面");
		this.setBounds(350, 300, 400, 350);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	private void initEvent(){
		btnAddScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				new AddScoreWindow().setVisible(true);
			}
		});
		btnQueryScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				new QueryScoreWindow().setVisible(true);
			}
		});
		btnStudentInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				new StudentInfoWindow().setVisible(true);
			}
		});
		// 窗体关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(1);
			}
		});
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow().setVisible(true);
	}

}
