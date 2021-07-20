package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.EmpDAO;
import common.Employee;

@WebServlet("/EmpListServlet")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EmpListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		Gson gson = new GsonBuilder().create();
		out.print(gson.toJson(list));
//		out.print("[");
//		int cnt = 1;
//		for(Employee emp : list) {
//			out.printf("{\"id\":%d, "
//					+ "\"first_name\":\"%s\", "
//					+ "\"last_name\":\"%s\", "
//					+ "\"email\":\"%s\", "
//					+ "\"hire_date\":\"%s\"}",
//					emp.getEmployeeId(), emp.getFirstName(), 
//					emp.getLastName(), emp.getEmail(), emp.getHireDate().substring(0, 10));
//			if (cnt++ != list.size()) {
//				out.println(",");
//			}
//		}
//		out.print("]");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
