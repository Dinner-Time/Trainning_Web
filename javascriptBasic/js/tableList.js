// tableList.js

let data = [{
    name: "이름",
    eng: "영어",
    math: "수학",
}, {
    name: "홍길동",
    eng: "100",
    math: "90",
}, {
    name: "김민수",
    eng: "90",
    math: "99",
}];

createTable();


function createTable2(){
    let all = '<table>'
    for (let row of data) {
        all += '<tr>'
        for (let text in row) {
            console.log(row[text]);
            all += '<td>' + row[text] + '</td>'
        }
        all += '</tr>'
        console.log(all);
    }
    all += '</table>'
    document.write(all);
}

function createTable() {
    let tableTag = document.createElement("table");

    for (let row of data) {
        let trTag = document.createElement("tr");

        for (let field in row) {
            let tdTag = document.createElement('td');
            // let text = document.createTextNode(row[field]);
            // tdTag.appendChild(text);
            tdTag.innerHTML = row[field];
            trTag.appendChild(tdTag);
        }

        tableTag.appendChild(trTag);
    }

    document.getElementById('show').appendChild(tableTag);
}