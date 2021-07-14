'use strict'

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


// 테이블 생성
function createTable(arr) {
    let tableTag = document.createElement("table"); // table태그 생성

    createHeader2(tableTag); // header 생성

    // content 생성
    for (let row of arr) {
        let trTag = document.createElement("tr"); // tr태그 생성
        trTag.setAttribute('id', row.id);

        mouseEvent(trTag); // 강조 표시

        // td태그를 생성하여 tr태그에 추가하는 반복문
        for (let field in row) {
            let tdTag = document.createElement('td'); // td태그 생성
            tdTag.innerHTML = row[field]; // td태그에 내용 추가
            tdTag.style.border = "1px solid black"; // style
            trTag.appendChild(tdTag); // tr태그에 td태그 추가
        }

        createBtn(trTag, tableTag); // 삭제 버튼 생성
    }
    document.getElementById('show').appendChild(tableTag); // #show에 테이블 추가
}

// function createHeader(arr, table) {
//     let row = arr[0];
//     let trTag = document.createElement("tr");
//     for (let field in row) {
//         let tdTag = document.createElement('td');
//         tdTag.innerHTML = field;
//         tdStyle(tdTag);
//         trTag.appendChild(tdTag);
//     }
//     table.appendChild(trTag);
// }

// 테이블의 header 생성
function createHeader2(table) {
    let row = ['id', 'first_name', 'last_name', 'email', 'gender', '삭제']; // header에 들어갈 내용 배열화
    let trTag = document.createElement("tr");
    for (let field of row) {
        let tdTag = document.createElement('td');
        tdTag.innerHTML = field;
        tdStyle(tdTag); // style
        trTag.appendChild(tdTag);
    }
    table.appendChild(trTag);
}

// td 스타일 
function tdStyle(tdTag) {
    tdTag.style.border = "1px solid black";
    tdTag.style.textAlign = "center";
    tdTag.style.fontSize = "2rem";
    tdTag.style.fontWeight = "bold";
    tdTag.style.padding = ".1em .5em";
}

// 마우스이벤트
function mouseEvent(tag) {

    // 강조 효과
    tag.onmouseover = function () {
        this.style.backgroundColor = "yellow";
    }
    tag.onmouseout = function () {
        this.style.backgroundColor = "#fff";
    }

    clickEvent(tag);
}

// 행 클릭 이벤트
function clickEvent(tag){
    tag.onclick = function(){
        let input = document.getElementsByTagName('input');
        for(let i = 0; i<input.length; i++){
            input[i].setAttribute('value', tag.childNodes[i].innerHTML);
        }
    }
}

// 삭제 버튼 생성 
function createBtn(trTag, tableTag) {
    let tdTag = document.createElement('td');
    let button = document.createElement('button');
    button.innerHTML = "Del";
    // 클릭 이벤트
    button.onclick = function (evt) {
        // 이벤트가 전파 되는 것을 차단
        //  -> 해당 이벤트만 실행한다.
        evt.stopPropagation();
        // 실제 데이터 삭제
        let id = this.parentNode.parentNode.childNodes[0].innerHTML;
        for (let i = 0; i < obj.length; i++) {
            if (obj[i]['id'] == id) {
                obj.splice(i, 1);
                break;
            }
        }
        // 테이블 데이터 삭제
        this.parentNode.parentNode.remove();
    }

    tdTag.style.border = "1px solid black";
    tdTag.appendChild(button);
    trTag.appendChild(tdTag);

    tableTag.appendChild(trTag);
}

// 행 추가
function addRow() {
    let id = document.getElementById('eid').value;
    let fname = document.getElementById('first_name').value;
    let lname = document.getElementById('last_name').value;
    let email = document.getElementById('e-mail').value;
    let gender = document.getElementById('gender').value;
    let tableTag = document.getElementsByTagName('table')[0];

    let infoArr = [id, fname, lname, email, gender];

    let trTag = document.createElement('tr');
    mouseEvent(trTag)

    for (let i of infoArr) {
        let tdTag = document.createElement('td');
        tdTag.innerHTML = i;
        tdTag.style.border = "1px solid black"; // style
        trTag.appendChild(tdTag);
    }

    tableTag.appendChild(trTag);

    createBtn(trTag, tableTag);
}

function modRow(){
    let inputs = document.getElementsByTagName('input');
    let findTr = document.getElementById(inputs[0].value);
    
    for(let i = 1; i<inputs.length; i++){
        findTr.childNodes[i].textContent = inputs[i].value;
    }

}