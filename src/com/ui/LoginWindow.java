package com.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.db.PopedomDao;
import com.db.UserDao;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField edt_username;
	private JPasswordField edt_password;
	private JButton btn_login;
	private JButton btn_register;
	
	public LoginWindow(){
		init();
		initEvent();
	}
	private void init(){
		Container container = getContentPane();// ������ת��Ϊ����
		container.setLayout(null);
		JLabel labelName = new JLabel("�û�����");
		labelName.setBounds(100, 160, 110, 21);
		container.add(labelName);
	    edt_username = new JTextField();
		edt_username.setBounds(158, 160, 110, 21);
		edt_username.setOpaque(false);
		edt_username.setColumns(10);
		edt_username.setOpaque(true);

		JLabel labelPassword = new JLabel("���룺");
		labelPassword.setBounds(100, 200, 110, 21);
		container.add(labelPassword);
		edt_password = new JPasswordField();
		edt_password.setForeground(Color.BLACK);
		edt_password.setEchoChar('*');
		edt_password.setOpaque(true);//���ò�͸��
		edt_password.setBounds(158, 200, 110, 21);
		edt_password.setBackground(Color.WHITE);
		//
		btn_login = new JButton("��¼");
		btn_login.setBounds(90, 250, 104, 30);
		//
		btn_register = new JButton("ע��");
		btn_register.setBounds(200, 250, 104, 30);
		//
		container.add(edt_username);
		container.add(edt_password);
		container.add(btn_login);
		container.add(btn_register);
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("ѧ���ɼ�����ϵͳ");
		this.setBounds(350, 300, 400, 350);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initEvent(){
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String username = edt_username.getText().toString();
				String password = edt_password.getText().toString();
				if(null == username || "".equals(username)){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					return;
				}
				if(null == password || "".equals(password)){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					return;
				}
				login(username, password);
			}
		});
		
	}

	
	private void login(String username, String password){
		int flag = new UserDao().login(username, password);
		switch (flag) {
		case -1:
			JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ��û������ڻ��������!");
			break;
		case PopedomDao.POPEDOM_STUDNET:
			setVisible(false);
			new StudentScoreWindow(username, PopedomDao.POPEDOM_STUDNET).setVisible(true);
			break;
		case PopedomDao.POPEDOM_ADMIN:
			setVisible(false);
			new MainWindow().setVisible(true);
			break;

		default:
			break;
		}
	}
	public static void main(String[] args) {
		new LoginWindow();
	}
}
