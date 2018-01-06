package com;

import javax.swing.JTable;

/**
 * 内容单元不可编辑
 * @author jdallen
 *
 */
public class MyJTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
