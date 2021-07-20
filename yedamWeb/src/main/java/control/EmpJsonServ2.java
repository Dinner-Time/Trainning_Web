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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import common.EmpDAO;
import common.Employee;

@WebServlet("/EmpJsonServ2")
public class EmpJsonServ2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmpJsonServ2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// { 'data' : [ {}, {}, {}, ...] } <- empl_demo
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		EmpDAO dao = new EmpDAO();
		List<Employee> emp = dao.getEmpList();
		JsonArray oAry = new JsonArray();
		JsonObject obj = new JsonObject();
		int i = 1;
		for(Employee row : emp) {
			JsonObject iObj = new JsonObject();
			
			iObj.addProperty("id", i++);
			iObj.addProperty("EmployeeId", row.getEmployeeId());
			iObj.addProperty("FirstName", row.getFirstName());
			iObj.addProperty("LastName", row.getLastName());
			iObj.addProperty("Email", row.getEmail());
			iObj.addProperty("HireDate", row.getHireDate());
			iObj.addProperty("Salary", row.getSalary());
			
			oAry.add(iObj);
		}
		
		obj.add("data", oAry);

		out.print(gson.toJson(obj));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
