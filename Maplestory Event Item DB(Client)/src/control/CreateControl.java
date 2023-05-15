package control;

import java.sql.*;

import javax.swing.JOptionPane;

import UI.CreateUI;

public class CreateControl {
	
	private String name="";
	private String item="";
	private String table_name="";
	private String type = "";
	private String sql = "";
	
	public void inputData(CreateUI ui) {
		dbConnecter db = new dbConnecter();
		Connection conn = db.getConnection();
		System.out.println(conn);
		name = ui.nameTextbox.getText();
		item = ui.selectTextbox.getText();
		table_name = ui.table_name;
		type = getType(table_name);
		sql = "insert into "+table_name+" values('"+name+"', '"+item+"');";
		
		PreparedStatement pre;
		try {
		if(item.isBlank() || item.isEmpty())
			throw new Exception();
		pre = conn
				.prepareStatement("insert into "+table_name+" values(?,?)");
		pre.setString(1, name);
		pre.setString(2, item);
		pre.executeUpdate();
		JOptionPane.showMessageDialog(null, "등록이 완료되었습니다", "등록 완료", JOptionPane.INFORMATION_MESSAGE);
		
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "존재하지 않는 닉네임이거나 이미 등록된 "+ui.selectLabel.getText()+"입니다.\n새로운 닉네임 추가는 관리자에게 문의하십시오.", "등록 실패", JOptionPane.ERROR_MESSAGE);
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "뭔지 모를 오류", "오류", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"형 뭘 등록한다는 거야?", "???????????", JOptionPane.ERROR_MESSAGE);
		}
		finally {
			ui.nameTextbox.setText("");
			ui.selectTextbox.setText("");
		}
		closedb(conn);
	}
		
	
	public void closedb(Connection conn) {
		try {
			conn.close();
			if(conn.isClosed())
				System.out.println("DB CLOSED");
		}catch (SQLException e) {
			e.printStackTrace();
		}
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
