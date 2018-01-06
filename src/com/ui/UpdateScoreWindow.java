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
		container = getContentPane();// 将窗体转换为容器
		container.setLayout(null);
		//
		addJLabel(0, "学号");
		addJLabel(1, "课程名");
		addJLabel(2, "成绩");
		addJLabel(3, "学期");
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
		btnAdd = new JButton("更新");
		btnAdd.setBounds(90, 250, 104, 30);
		container.add(btnAdd);
		//
		/*btnClose = new JButton("关闭");
		btnClose.setBounds(200, 250, 104, 30);
		container.add(btnClose);*/
		//
		this.setResizable(false);// 禁止调整界面的大小
		this.setTitle("管理员——更新学生成绩信息界面");
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
					JOptionPane.showMessageDialog(null, "请把信息填完整!!!");
					return;
				}
				if(new ScoreDao().updateScore(new ScoreBean(sid,course,score,time))){
					JOptionPane.showMessageDialog(null, "更新信息成功!!!");
				}else{
					JOptionPane.showMessageDialog(null, "更新失败，系统出现问题!");
				}
			}
		});
		

		// 窗体关闭
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				new StudentScoreWindow(score.getSid(), PopedomDao.POPEDOM_ADMIN).setVisible(true);
			}
		});
	}
	

	
}
