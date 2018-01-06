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

public class AddScoreWindow extends JFrame{
	
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
	private String sid;
	//
	public final static int FROM_MAIN_WINDOW = 1;//�ӹ���Ա��������ת����
	public final static int FROM_STUDENT_SCORE_WINDOW = 2;//��ѧ���ɼ�������ת����
	//
	private int fromWidow; 
	
	public AddScoreWindow(){
		fromWidow = FROM_MAIN_WINDOW;
		initUI();
		initEvent();
	}
	
	public AddScoreWindow(String sid){
		fromWidow = FROM_STUDENT_SCORE_WINDOW;
		this.sid = sid;
		initUI();
		initEvent();
		editSid.setText(sid);//��ѧ���ɼ�������ת�����Ļ����Ѵ�������ѧ����ʾ��������Ϊ���ɱ༭��
		editSid.setEditable(false);
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
		btnAdd = new JButton("���");
		btnAdd.setBounds(90, 250, 104, 30);
		container.add(btnAdd);
		//
		/*btnClose = new JButton("�ر�");
		btnClose.setBounds(200, 250, 104, 30);
		container.add(btnClose);*/
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("����Ա�������ѧ���ɼ�����");
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
				String course = editCourse.getText().toString();
				String score = editScore.getText().toString();
				String time = editTime.getText().toString();
				if("".equals(sid) || "".equals(course) || "".equals(score) 
						|| "".equals(time)){
					JOptionPane.showMessageDialog(null, "�����Ϣ������!!!");
					return;
				}
				if(new ScoreDao().addSocre(new ScoreBean(sid,course,score,time))){
					JOptionPane.showMessageDialog(null, "��ӳɼ��ɹ�!!!");
				}else{
					JOptionPane.showMessageDialog(null, "���ʧ�ܣ�ϵͳ��������!");
				}
			}
		});
		

		// ����ر�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				switch (fromWidow) {
				case FROM_MAIN_WINDOW:
					setVisible(false);
					new MainWindow().setVisible(true);
					break;
				case FROM_STUDENT_SCORE_WINDOW:
					setVisible(false);
					new StudentScoreWindow(sid, PopedomDao.POPEDOM_ADMIN).setVisible(true);
					break;
				default:
					break;
				}

			}
		});
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AddScoreWindow("201730110117");
	}

}
