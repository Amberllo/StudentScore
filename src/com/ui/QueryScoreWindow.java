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
		container = getContentPane();// ������ת��Ϊ����
		container.setLayout(null);
		//
		editSid = new JTextField();
		editSid.setBounds(20, 10, 200, 40);
		btnSid = new JButton("��ѧ�Ų���");
		btnSid.setBounds(240, 10, 100, 40);
		btnAll = new JButton("ȫ��");
		btnAll.setBounds(360, 10, 100, 40);
		container.add(editSid);
		container.add(btnSid);
		container.add(btnAll);
		//
		editCourse = new JTextField();
		editCourse.setBounds(20, 70, 200, 40);
		btnCourse = new JButton("���γ̲���");
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
		defaultTableModel.setColumnIdentifiers(new Object[]{"ѧ��","�γ�","�ɼ�","ѧ��"});
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultTableModel);
		scrollPane.setViewportView(table);
		//
		container.add(scrollPane);
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("����Ա-�ɼ���ѯ����");
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
					JOptionPane.showMessageDialog(null, "Ҫ������ѧ�Ų���Ϊ�գ�");
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
					JOptionPane.showMessageDialog(null, "Ҫ�����Ŀγ�������Ϊ�գ�");
					return;
				}
				queryScoreByCourse(course);
			}
		});
		// ����ر�
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
	//ˢ�½���
	private void refreshTable(ArrayList<ScoreBean> scoreList){
		defaultTableModel.getDataVector().clear();//�������
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
