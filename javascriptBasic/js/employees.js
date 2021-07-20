let json = `[{"id":1,"first_name":"Merrily","last_name":"Diperaus","email":"mdiperaus0@berkeley.edu","gender":"Bigender"},
{"id":2,"first_name":"Leone","last_name":"Tramel","email":"ltramel1@pen.io","gender":"Polygender"},
{"id":3,"first_name":"Sharlene","last_name":"Simcock","email":"ssimcock2@blogspot.com","gender":"Polygender"},
{"id":4,"first_name":"Yardley","last_name":"Macellar","email":"ymacellar3@army.mil","gender":"Genderqueer"},
{"id":5,"first_name":"Irvine","last_name":"Coarser","email":"icoarser4@altervista.org","gender":"Male"},
{"id":6,"first_name":"Vivi","last_name":"Runnicles","email":"vrunnicles5@blogtalkradio.com","gender":"Male"},
{"id":7,"first_name":"Ophelie","last_name":"Oddey","email":"ooddey6@ow.ly","gender":"Agender"},
{"id":8,"first_name":"Star","last_name":"Bakes","email":"sbakes7@webs.com","gender":"Male"},
{"id":9,"first_name":"Cos","last_name":"Mogg","email":"cmogg8@yale.edu","gender":"Male"},
{"id":10,"first_name":"Silva","last_name":"Dawkins","email":"sdawkins9@google.com.hk","gender":"Agender"}]`;


let obj = JSON.parse(json);

createTable(obj);

function createTable(arr) {
    let tableTag = document.createElement("table");

    for (let row of arr) {
        let trTag = document.createElement("tr");

        for (let field in row) {
            let tdTag = document.createElement('td');
            tdTag.innerHTML = row[field];
            tdTag.style.border = "1px solid black";
            trTag.appendChild(tdTag);
        }

        tableTag.appendChild(trTag);
    }

    document.getElementById('show').appendChild(tableTag);
}