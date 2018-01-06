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
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.MyJTable;
import com.db.PopedomDao;
import com.bean.InfoBean;
import com.db.InfoDao;

public class StudentInfoWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	//
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnScanScore;//查看成绩
	
	
	
	public StudentInfoWindow(){
		initUI();
		initData();
		initEvent();
	}

	private void initUI(){
		container = getContentPane();// 将窗体转换为容器
		container.setLayout(null);
		
		//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 560, 250);
		table =  new MyJTable();
		table.setRowHeight(25);
		defaultTableModel = (DefaultTableModel)table.getModel();
		defaultTableModel.setRowCount(0);
		defaultTableModel.setColumnIdentifiers(new Object[]{"学号","姓名","性别","系别","专业","班级"});
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(defaultTableModel);
		scrollPane.setViewportView(table);
		//
		container.add(scrollPane);
		//
		btnAdd = new JButton();
		btnAdd.setText("添加");
		btnAdd.setBounds(20, 290, 80, 40);
		container.add(btnAdd);
		//
		btnUpdate = new JButton();
		btnUpdate.setText("修改");
		btnUpdate.setBounds(110, 290, 80, 40);
		container.add(btnUpdate);
		//
		btnDelete = new JButton();
		btnDelete.setText("删除");
		btnDelete.setBounds(200, 290, 80, 40);
		container.add(btnDelete);
		//
		btnScanScore = new JButton();
		btnScanScore.setText("查看该学生的成绩");
		btnScanScore.setBounds(290, 290, 200, 40);
		container.add(btnScanScore);
		//
		this.setResizable(false);// 禁止调整界面的大小
		this.setTitle("管理员-学生信息管理界面");
		this.setBounds(300, 100, 600, 400);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void initData(){
		defaultTableModel.getDataVector().clear();//清除数据
		//
		ArrayList<InfoBean> infoList = new InfoDao().getInfoList();
		//
		for(InfoBean info : infoList){
			defaultTableModel.addRow(new Object[]{info.getSid(),info.getName(),info.getSex(),info.getDept(),info.getMajor(),info.getClazz()});
		}
	}
	
	private void initEvent(){
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				new AddInfoWindow().setVisible(true);
				
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() >=0){
					String sid= (String)table.getValueAt(table.getSelectedRow(), 0);
					String name = (String)table.getValueAt(table.getSelectedRow(), 1);
					String sex= (String)table.getValueAt(table.getSelectedRow(), 2);
					String dept = (String)table.getValueAt(table.getSelectedRow(), 3);
					String major= (String)table.getValueAt(table.getSelectedRow(), 4);
					String clazz = (String)table.getValueAt(table.getSelectedRow(), 5);
					//
					setVisible(false);
					new UpdateInfoWindow(new InfoBean(sid, name, sex, dept, major, clazz)).setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(null, "请先选择要更新的学生！！");	
				}
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() >=0){
					String sidString= (String)table.getValueAt(table.getSelectedRow(), 0);
					//
					int operation = JOptionPane.showConfirmDialog(null,"确定删除学号为:"+sidString+"的学生", "删除学生信息",
							JOptionPane.YES_NO_OPTION);
					if (operation == JOptionPane.YES_OPTION) {
						// 删除学生信息
						deleteInfo(sidString);
					}
				}else{
					JOptionPane.showMessageDialog(null, "请先选择要删除的学生！！");	
				}
			}
		});
		btnScanScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() >=0){
					String sid= (String)table.getValueAt(table.getSelectedRow(), 0);
					setVisible(false);
					new StudentScoreWindow(sid, PopedomDao.POPEDOM_ADMIN).setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "请先选择要查看的学生！！");	
				}
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
	
	private void deleteInfo(String sid){
		if (new InfoDao().deleteInfo(sid)) {
			JOptionPane.showMessageDialog(null, "删除学生信息成功!!!");
		}else{
			JOptionPane.showMessageDialog(null, "删除失败，该学生信息不存在!!!");
		}
		initData();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StudentInfoWindow().setVisible(true);
	}

}
