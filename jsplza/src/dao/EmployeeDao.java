package dao;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
	//��
	public List<employee> selall() throws SQLException;
	//��
	public int addem(employee e) throws SQLException;;
	//��
	public int upem(employee e) throws SQLException;;
	//ɾ
	public int delem(employee e) throws SQLException;;
}
