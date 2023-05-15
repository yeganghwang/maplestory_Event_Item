package control;

import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import UI.SearchUI;



public class SearchControl {
	private String name="";
	private String item="";
	private String table_name="";
	private String type = "";
	private String sql = "";
	private ResultSet response;
	
	
	
	public void getResult(SearchUI ui) { //조건에 따라 검색 후 테이블에 추가하는 메쏘드입니다.
		dbConnecter db = new dbConnecter();
		name = ui.nameTextbox.getText();
		item = ui.selectTextbox.getText();
		table_name = ui.table_name;
		type = getType(table_name);
		sql = "select character_table.name, world, " + type + " from " + table_name  + " "
				+ "left join character_table on character_table.name = "+ table_name +".name "
				+ "where character_table.name like '%"+ name +"%'"
				+ "and "+type+" like '%"+item+"%'"
				+ "order by world asc, name asc, "+type+" asc";
		
		
		DefaultTableModel model = (DefaultTableModel) ui.searchTable.getModel();
		model.setNumRows(0);
		
		try {
			response = db.executeQuery(sql); //SQL을 Execute합니다.
			if(!response.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "결과 없음", JOptionPane.WARNING_MESSAGE);
			}
			
			while(response.next()) { //값이 존재하여 DefaultTableModel에 값을 넣어줍니다.
				System.out.println(response.getString(1)+"\t"+response.getString(2)+'\t'+response.getString(3));
				model.addRow(new Object[] {response.getString(1), response.getString(2), response.getString(3)});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			response.close();
			if(response.isClosed())
				System.out.println("DB CLOSED");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteItem(SearchUI ui) {
		dbConnecter db = new dbConnecter();
		name = ui.searchTable.getValueAt(ui.searchTable.getSelectedRow(),0).toString();
		item = ui.searchTable.getValueAt(ui.searchTable.getSelectedRow(),2).toString();
		table_name = ui.table_name;
		type = getType(table_name);
		sql = "delete from "+table_name+" where name like '"+name+"' and "+type+" like '"+item+"';";
		String confirm = JOptionPane.showInputDialog(null, "정말로 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.\n삭제를 원하신다면 다음과 같이 입력하세요.\n"+name+"/"+item+"/삭제한다", "삭제", JOptionPane.WARNING_MESSAGE);
		if(confirm.equals(name+"/"+item+"/삭제한다")) {
			db.executeQuery(sql);
			((DefaultTableModel) ui.searchTable.getModel()).removeRow(ui.searchTable.getSelectedRow());
			JOptionPane.showMessageDialog(null, name+" 소유의 \""+item+"\" 삭제가 완료되었습니다.", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "삭제 문구가 정확하지 않아 취소되었습니다.", "삭제 취소", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(confirm);
	}
	
	
	private String getType(String table_name) {
		String type="";
		if(table_name.equals("chair_table"))
			type = "chair";
		else if(table_name.equals("riding_table"))
			type = "riding";
		else if(table_name.equals("damageskin_table"))
			type = "skin";
		else
			System.out.println("ERROR");
		return type;
	}
	
	
}
