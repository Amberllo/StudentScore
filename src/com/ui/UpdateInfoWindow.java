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

import com.bean.InfoBean;
import com.db.InfoDao;

public class UpdateInfoWindow extends JFrame{
	
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
	
	private JButton btnUpdate;
	//private JButton btnClose;
	private Container container;
	private InfoBean info;
	
	public UpdateInfoWindow(InfoBean info){
		this.info = info;
		initUI();
		initData();
		initEvent();
	}
	
	
	private void initUI(){
		container = getContentPane();// 将窗体转换为容器
		container.setLayout(null);
		//
		addJLabel(0, "学号");
		addJLabel(1, "姓名");
		addJLabel(2, "性别");
		addJLabel(3, "系别");
		addJLabel(4, "专业");
		addJLabel(5, "班级");
		//
		editSid=createJTextField(0);
		editSid.setEditable(false);//不可编辑
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
		btnUpdate = new JButton("更新");
		btnUpdate.setBounds(90, 250, 104, 30);
		container.add(btnUpdate);
		//
		/*btnClose = new JButton("关闭");
		btnClose.setBounds(200, 250, 104, 30);
		container.add(btnClose);*/
		//
		this.setResizable(false);// 禁止调整界面的大小
		this.setTitle("管理员——更新学生信息界面");
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
	
	private void initData(){
		editSid.setText(info.getSid());
		editName.setText(info.getName());
		editSex.setText(info.getSex());
		editDept.setText(info.getDept());
		editMajor.setText(info.getMajor());
		editClazz.setText(info.getClazz());
	}
	
	private void initEvent(){
		btnUpdate.addActionListener(new ActionListener() {
			
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
					JOptionPane.showMessageDialog(null, "请把信息填完整!!!");
					return;
				}
				if(new InfoDao().updateInfo(new InfoBean(sid, name, sex, dept, major, clazz))){
					JOptionPane.showMessageDialog(null, "更新学生信息成功!!!");
				}else{
					JOptionPane.showMessageDialog(null, "更新失败!!!");
				}
			}
		});
		

		// 窗体关闭
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
		//new UpdateInfoWindow();
	}

}
