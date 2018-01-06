package com.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.MyJTable;
import com.bean.ScoreBean;
import com.db.ScoreDao;

public class QueryScoreWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private JTextField editSid;
	private JButton btnSid;
	private JTextField editCourse;
	private JButton btnCourse;
	private JButton btnAll;
	
	private JTable table;
	private DefaultTableModel defaultTableModel;
	
	public QueryScoreWindow() {
		initUI();
		allScore();
		initEvent();
	}
	
	private void initUI(){
		container = getContentPane();// 将窗体转换为容器
		container.setLayout(null);
		//
		editSid = new JTextField();
		editSid.setBounds(20, 10, 200, 40);
		btnSid = new JButton("按学号查找");
		btnSid.setBounds(240, 10, 100, 40);
		btnAll = new JButton("全部");
		btnAll.setBounds(360, 10, 100, 40);
		container.add(editSid);
		container.add(btnSid);
		container.add(btnAll);
		//
		editCourse = new JTextField();
		editCourse.setBounds(20, 70, 200, 40);
		btnCourse = new JButton("按课程查找");
		btnCourse.setBounds(240, 70, 100, 40);
		container.add(editCourse);
		container.add(btnCourse);
		//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 120, 560, 300);
		table =  new MyJTable();
		table.setRowHeight(25);
		defaultTableModel = (DefaultTableModel)table.getModel();
		defaultTableModel.setRowCount(0);
		defaultTableModel.setColumnIdentifiers(new Object[]{"学号","课程","成绩","学期"});
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultTableModel);
		scrollPane.setViewportView(table);
		//
		container.add(scrollPane);
		//
		this.setResizable(false);// 禁止调整界面的大小
		this.setTitle("管理员-成绩查询界面");
		this.setBounds(300, 100, 600, 460);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initEvent(){
		btnSid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sid = editSid.getText().toString();
				if(null==sid ||"".equals(sid)){
					JOptionPane.showMessageDialog(null, "要搜索的学号不能为空！");
					return;
				}
				queryScoreBySid(sid);
			}
		});
		btnAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				allScore();
			}
		});
		btnCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String course = editCourse.getText().toString();
				if(null==course ||"".equals(course)){
					JOptionPane.showMessageDialog(null, "要搜索的课程名不能为空！");
					return;
				}
				queryScoreByCourse(course);
			}
		});
		// 窗体关闭
				this.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						setVisible(false);
						new MainWindow().setVisible(true);
					}
				});
	}
	
	private void allScore(){
		refreshTable(new ScoreDao().getScoreList());
	}
	//
	private void queryScoreBySid(String sid){
		refreshTable(new ScoreDao().getScoreListBySid(sid));
	}
	//
	private void queryScoreByCourse(String course){
		refreshTable(new ScoreDao().getScoreListByCourse(course));
	}
	//刷新界面
	private void refreshTable(ArrayList<ScoreBean> scoreList){
		defaultTableModel.getDataVector().clear();//清除数据
		//
		for(ScoreBean score : scoreList){
			defaultTableModel.addRow(new Object[]{score.getSid(),score.getCourse(),score.getScore(),score.getTime()});
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new QueryScoreWindow().setVisible(true);
	}

}
