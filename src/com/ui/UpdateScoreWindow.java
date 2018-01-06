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
import com.bean.ScoreBean;
import com.db.ScoreDao;

public class UpdateScoreWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField editSid;
	private JTextField editCourse;
	private JTextField editScore;
	private JTextField editTime;
	
	private JButton btnAdd;
	//private JButton btnClose;
	private Container container;
	//
	private ScoreBean score;
	
	public UpdateScoreWindow(ScoreBean score){
		this.score = score;
		initUI();
		initData();
		initEvent();
	}
	
	
	private void initUI(){
		container = getContentPane();// ������ת��Ϊ����
		container.setLayout(null);
		//
		addJLabel(0, "ѧ��");
		addJLabel(1, "�γ���");
		addJLabel(2, "�ɼ�");
		addJLabel(3, "ѧ��");
		//
		editSid=createJTextField(0);
		editCourse= createJTextField(1);
		editScore= createJTextField(2);
		editTime= createJTextField(3);
		container.add(editSid);
		container.add(editCourse);
		container.add(editScore);
		container.add(editTime);
		//
		btnAdd = new JButton("����");
		btnAdd.setBounds(90, 250, 104, 30);
		container.add(btnAdd);
		//
		/*btnClose = new JButton("�ر�");
		btnClose.setBounds(200, 250, 104, 30);
		container.add(btnClose);*/
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("����Ա��������ѧ���ɼ���Ϣ����");
		this.setBounds(350, 300, 400, 350);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		editSid.setText(score.getSid());
		editSid.setEditable(false);
		editCourse.setText(score.getCourse());
		editScore.setText(score.getScore());
		editTime.setText(score.getTime());
	}
	
	private void initEvent(){
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sid = editSid.getText().toString();
				String course = editCourse.getText().toString();
				String score = editScore.getText().toString();
				String time = editTime.getText().toString();
				if("".equals(sid) || "".equals(course) || "".equals(score) 
						|| "".equals(time)){
					JOptionPane.showMessageDialog(null, "�����Ϣ������!!!");
					return;
				}
				if(new ScoreDao().updateScore(new ScoreBean(sid,course,score,time))){
					JOptionPane.showMessageDialog(null, "������Ϣ�ɹ�!!!");
				}else{
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�ϵͳ��������!");
				}
			}
		});
		

		// ����ر�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				new StudentScoreWindow(score.getSid(), PopedomDao.POPEDOM_ADMIN).setVisible(true);
			}
		});
	}
	

	
}
