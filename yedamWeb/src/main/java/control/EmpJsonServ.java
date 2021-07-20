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

@WebServlet("/EmpJsonServ")
public class EmpJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EmpJsonServ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// { 'data' : [ [], [], [], ...] } <- empl_demo
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().create();
		EmpDAO dao = new EmpDAO();
		List<Employee> emp = dao.getEmpList();
		JsonArray oAry = new JsonArray();
		JsonObject obj = new JsonObject();
		
		for(Employee row : emp) {
			JsonArray iAry = new JsonArray();
			iAry.add(row.getEmployeeId());
			iAry.add(row.getFirstName());
			iAry.add(row.getLastName());
			iAry.add(row.getEmail());
			iAry.add(row.getHireDate());
			iAry.add(row.getSalary());
			
			oAry.add(iAry);
		}
		
		obj.add("data", oAry);

		out.print(gson.toJson(obj));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
