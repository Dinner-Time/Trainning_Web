/**
 * ajax.js
 */
'use strict'

let xhtp = new XMLHttpRequest();

createTable();

function createTable() {
	xhtp.open("get", "EmpListServlet");
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			let data = JSON.parse(xhtp.responseText);
			let table = document.createElement("table");
			table.setAttribute('border', '1');

			tableHeader(data, table);
			tableBody(data, table);

			document.body.appendChild(table);

		}
	}
}

function tableBody(data, table) {
	for (let row of data) {
		let tr = document.createElement("tr");
		tr.setAttribute('id', row.employeeId);
		tr.onclick = rowClick;
		for (let i in row) {
			let td = document.createElement("td");
			td.innerHTML = row[i];
			tr.appendChild(td);
		}
		table.appendChild(tr);
	}
}

function rowClick() {
	const eid = this.getAttribute('id');
	xhtp.open("get", "EmployeeServlet?eid=" + eid);
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			let data = JSON.parse(xhtp.responseText);

			if (data.employeeId == eid) {
				document.querySelector('input[name="eid"]').value = data.employeeId;
				document.querySelector('input[name="first_name"]').value = data.firstName;
				document.querySelector('input[name="last_name"]').value = data.lastName;
				document.querySelector('input[name="email"]').value = data.email;
				document.querySelector('input[name="hire_date"]').value = data.hireDate.substring(0, 10);
			}

		}
	}
	/*xhtp.open("get", "EmpListServlet");
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			let data = JSON.parse(xhtp.responseText);
			for (let row of data) {
				if (row.employeeId == eid) {
					document.querySelector('input[name="eid"]').value = row.employeeId;
					document.querySelector('input[name="first_name"]').value = row.firstName;
					document.querySelector('input[name="last_name"]').value = row.lastName;
					document.querySelector('input[name="email"]').value = row.email;
					document.querySelector('input[name="hire_date"]').value = row.hireDate.substring(0, 10);
				}
			}

		}
	}*/
}

function tableHeader(data, table) {
	let title = document.createElement('caption');
	title.innerHTML = "사원테이블";
	table.appendChild(title);
	let row = data[0];
	let tr = document.createElement("tr");
	for (let i in row) {
		let th = document.createElement("th");
		th.innerHTML = i;
		tr.appendChild(th);
	}
	table.appendChild(tr);
}

function frm_submit() {
	const id = document.querySelector('input[name="eid"]').value;
	const fn = document.querySelector('input[name="first_name"]').value;
	const ln = document.querySelector('input[name="last_name"]').value;
	const em = document.querySelector('input[name="email"]').value;
	const hr = document.querySelector('input[name="hire_date"]').value;
	const param = 'eid=' + id + '&first_name=' + fn + '&last_name=' + ln + '&email=' + em + '&hire_date=' + hr;

	xhtp.open("get", "RegisterServlet?" + param);
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			document.querySelector('table').remove();
			createTable();
		}
	}
}

function frm_delete() {
	let eid = document.querySelector('input[name="eid"]').value;
	console.log(eid);
	xhtp.open("get", "DeletServlet?eid=" + eid);
	xhtp.send();
	xhtp.onreadystatechange = function() {
		if (xhtp.readyState == 4 && xhtp.status == 200) {
			document.querySelector('table').remove();
			createTable();
		}
	}
}



