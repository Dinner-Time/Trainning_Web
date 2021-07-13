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

function createTable(){
    let tableTag = document.createElement("table");
    
    for (let row of data) {
        let trTag = document.createElement("tr");
    
        for (let field in row) {
            let tdTag = document.createElement('td');
            let text = document.createTextNode(row[field]);
            tdTag.appendChild(text);
            trTag.appendChild(tdTag);
        }
    
        tableTag.appendChild(trTag);
    }
    
    document.getElementById('show').appendChild(tableTag);
}