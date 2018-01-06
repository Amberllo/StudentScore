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
		Container container = getContentPane();// ������ת��Ϊ����
		container.setLayout(null);
		//
		btnAddScore = new JButton("�ɼ�¼��");
		btnAddScore.setBounds(50, 20, 300, 60);
		btnQueryScore = new JButton("�ɼ���ѯ");
		btnQueryScore.setBounds(50, 100, 300, 60);
		btnStudentInfo = new JButton("ѧ����Ϣ����");
		btnStudentInfo.setBounds(50, 180, 300, 60);
		//
		container.add(btnAddScore);
		container.add(btnQueryScore);
		container.add(btnStudentInfo);
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("����Ա������");
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
		// ����ر�
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
