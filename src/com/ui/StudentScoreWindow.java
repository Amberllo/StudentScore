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
import com.db.PopedomDao;
import com.bean.ScoreBean;
import com.db.ScoreDao;

public class StudentScoreWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	//
	private JTextField editCourse;
	private JButton btnCourse;
	private JButton btnAll;
	//
	private JTable table;
	private DefaultTableModel defaultTableModel;
	//
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private String sid;
	private int popedom;//Ȩ��
	
	public StudentScoreWindow(String sid, int popedom){
		this.sid = sid;
		this.popedom = popedom;
		initUI();
		initData();
		initEvent();
		if(popedom != PopedomDao.POPEDOM_ADMIN){//������ǹ���Ա������ӣ����£��ƴ���ť���ɼ�
			btnAdd.setVisible(false);
			btnDelete.setVisible(false);
			btnUpdate.setVisible(false);
		}
	}
	
	private void initUI(){
		container = getContentPane();// ������ת��Ϊ����
		container.setLayout(null);
		//
		editCourse = new JTextField();
		editCourse.setBounds(20, 20, 200, 40);
		btnCourse = new JButton("���γ̲���");
		btnCourse.setBounds(240, 20, 100, 40);
		container.add(editCourse);
		container.add(btnCourse);
		btnAll = new JButton("ȫ��");
		btnAll.setBounds(360, 20, 100, 40);
		container.add(btnAll);
		
		//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 560, 250);
		table = new MyJTable();
		table.setRowHeight(25);
		defaultTableModel = (DefaultTableModel)table.getModel();
		defaultTableModel.setRowCount(0);
		defaultTableModel.setColumnIdentifiers(new Object[]{"ѧ��","�γ���","�ɼ�","����ʱ��"});
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultTableModel);
		scrollPane.setViewportView(table);
		//
		container.add(scrollPane);
		//
		btnAdd = new JButton();
		btnAdd.setText("���");
		btnAdd.setBounds(20, 330, 80, 40);
		container.add(btnAdd);
		//
		btnUpdate = new JButton();
		btnUpdate.setText("�޸�");
		btnUpdate.setBounds(110, 330, 80, 40);
		container.add(btnUpdate);
		//
		btnDelete = new JButton();
		btnDelete.setText("ɾ��");
		btnDelete.setBounds(200, 330, 80, 40);
		container.add(btnDelete);
		//
		this.setResizable(false);// ��ֹ��������Ĵ�С
		this.setTitle("ѧ��Ϊ��"+sid+"��ѧ���ĳɼ�����");
		this.setBounds(300, 100, 600, 430);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initData(){
		refreshTable(new ScoreDao().getScoreListBySid(sid));
	}
	
	//
	private void refreshTable(ArrayList<ScoreBean> scoreList){
		defaultTableModel.getDataVector().clear();//�������
		for(ScoreBean score : scoreList){
			defaultTableModel.addRow(new Object[]{score.getSid(),score.getCourse(),score.getScore(),score.getTime()});
		}
	}
	
	private void initEvent(){
		btnCourse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String course = editCourse.getText().toString();
				if(null==course ||"".equals(course)){
					JOptionPane.showMessageDialog(null, "Ҫ�����Ŀγ̲���Ϊ�գ�");
					return;
				}
				refreshTable(new ScoreDao().getScoreListBySidAndCourse(sid, course));
			}
		});
		btnAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				initData();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				new AddScoreWindow(sid).setVisible(true);
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() >=0){
					String sid= (String)table.getValueAt(table.getSelectedRow(), 0);
					String course = (String)table.getValueAt(table.getSelectedRow(), 1);
					String score= (String)table.getValueAt(table.getSelectedRow(), 2);
					String time = (String)table.getValueAt(table.getSelectedRow(), 3);
					//
					setVisible(false);
					new UpdateScoreWindow(new ScoreBean(sid, course, score, time)).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "����ѡ��Ҫ���µĳɼ���Ϣ����");	
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() >=0){
					String sid= (String)table.getValueAt(table.getSelectedRow(), 0);
					String course= (String)table.getValueAt(table.getSelectedRow(), 1);
					String score= (String)table.getValueAt(table.getSelectedRow(), 2);
					String time= (String)table.getValueAt(table.getSelectedRow(), 3);
					//
					int operation = JOptionPane.showConfirmDialog(null,"ȷ��ɾ���óɼ���", "ɾ���ɼ�",
							JOptionPane.YES_NO_OPTION);
					if (operation == JOptionPane.YES_OPTION) {
						// ɾ��ѧ����Ϣ
						deleteScore(new ScoreBean(sid, course, score, time));
					}
				}else{
					JOptionPane.showMessageDialog(null, "����ѡ��Ҫɾ���ĳɼ���Ϣ����");	
				}
			}
		});
		
		// ����ر�
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				switch (popedom) {
				case PopedomDao.POPEDOM_ADMIN:
					setVisible(false);
					new StudentInfoWindow().setVisible(true);
					break;
				default:
					setVisible(false);
					System.exit(1);
					break;
				}
			}
		});
	}
	
	private void deleteScore(ScoreBean score){
		if(new ScoreDao().deleteScore(score)){
			JOptionPane.showMessageDialog(null, "ɾ���ɼ���Ϣ�ɹ�!!!");
		}else{
			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ��п��ܸóɼ���Ϣ������!!!");
		}
		//ˢ�½���
		initData();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentScoreWindow("201430110117", PopedomDao.POPEDOM_ADMIN).setVisible(true);

	}

}
