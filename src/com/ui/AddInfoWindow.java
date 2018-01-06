package com.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.db.PopedomDao;
import com.bean.InfoBean;
import com.bean.UserBean;
import com.db.InfoDao;
import com.db.UserDao;

public class AddInfoWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField editSid;
	private JTextField editName;
	private JTextField editSex;
	private JTextField editDept;
	private JTextField editMajor;
	private JTextField editClazz;
	
	private JButton btnAdd;
	private Container container;
	
	public AddInfoWindow(){
		initUI();
		initEvent();
	}
	
	
	private void initUI(){
		container = getContentPane();// ������ת��Ϊ����
		container.setLayout(null);
		//
		addJLabel(0, "ѧ��");
		addJLabel(1, "����");
		addJLabel(2, "�Ա�");
		addJLabel(3, "ϵ��");
		addJLabel(4, "רҵ");
		addJLabel(5, "�༶");
		//
		editSid=createJTextField(0);
		editName= createJTextField(1);
		editSex= createJTextField(2);
		editDept= createJTextField(3);
		editMajor= createJTextField(4);
		editClazz= createJTextField(5);
		container.add(editSid);
		container.add(editName);
		container.add(editSex);
		container.add(editDept);
		container.add(editMajor);
		container.add(editClazz);
		//
		btnAdd = new JButton("���");
		btnAdd.setBounds(90, 250, 104, 30);
		container.add(btnAdd);
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("����Ա�������ѧ����Ϣ����");
		this.setBounds(350, 300, 400, 350);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void addJLabel(int index,String labelName){
		JLabel jLabel = new JLabel(labelName);
		jLabel.setBounds(100, 20+index*30, 110, 21);
		container.add(jLabel);
	}

	private JTextField createJTextField(int index){
		JTextField jTextField = new JTextField();
		jTextField.setBounds(158, 20+index*30, 110, 21);
		jTextField.setOpaque(false);
		return jTextField;
	}
	
	private void initEvent(){
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sid = editSid.getText().toString();
				String name = editName.getText().toString();
				String sex = editSex.getText().toString();
				String dept = editDept.getText().toString();
				String major = editMajor.getText().toString();
				String clazz = editClazz.getText().toString();
				if("".equals(sid) || "".equals(name) || "".equals(sex) 
						|| "".equals(dept) || "".equals(major) || "".equals(clazz)){
					JOptionPane.showMessageDialog(null, "�����Ϣ������!!!");
					return;
				}
				if(new InfoDao().addInfo(new InfoBean(sid, name, sex, dept, major, clazz))){
					//���һ��ѧ�����ʹ���һ����¼�˺�
					new UserDao().addUser(new UserBean(sid, sid, PopedomDao.POPEDOM_STUDNET));
					JOptionPane.showMessageDialog(null, "���ѧ����Ϣ�ɹ�!!!");
				}else{
					JOptionPane.showMessageDialog(null, "���ʧ�ܣ���ѧ���Ѿ������!!!");
				}
			}
		});
		

		// ����ر�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				new StudentInfoWindow().setVisible(true);
			}
		});
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddInfoWindow();
	}

}
