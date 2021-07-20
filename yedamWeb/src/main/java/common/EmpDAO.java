package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public static Connection getConnect() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void insertEmp(Employee emp) {
		conn = EmpDAO.getConnect();
		String sql = "insert into empl_demo(employee_id, first_name, last_name, email, hire_date, job_id) values(?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, "IT_PROG");
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateEmp(Employee emp) {
		conn = EmpDAO.getConnect();
		String sql = "update empl_demo set first_name = ?, last_name = ?, email = ?, hire_date = ?"
				+ "where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setInt(5, emp.getEmployeeId());
			if(psmt.executeUpdate() == 0) {
				insertEmp(emp);
			}
				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Employee selectEmp(String employeeId) {
		conn = EmpDAO.getConnect();
		Employee emp = new Employee();
		try {
			psmt = conn.prepareStatement("select * from empl_demo where employee_id = ?");
			psmt.setString(1, employeeId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return emp;
	}
	
	public Employee deleteEmp(String employeeId) {
		conn = EmpDAO.getConnect();
		Employee emp = new Employee();
		try {
			psmt = conn.prepareStatement("delete from empl_demo where employee_id = ?");
			psmt.setString(1, employeeId);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return emp;
	}
	
	public List<Employee> getEmpList(){
		conn = EmpDAO.getConnect();
		List<Employee> empList = new ArrayList<Employee>();
		try {
			psmt = conn.prepareStatement("select * from empl_demo order by employee_id");
			rs = psmt.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				empList.add(emp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empList;
	}

}
