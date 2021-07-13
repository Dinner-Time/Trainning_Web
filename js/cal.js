// calendar.js

function calCalendar() {

    reload(); // 달력 초기화

    // 입력받은 년도 와 월을 저장
    let userYear = document.getElementById('year').value; 
    let userMonth = document.getElementById('month').value;

    // 날짜를 새기위한 객체 생성
    let today = new Date(userYear, userMonth, 0);
    let date = today.getDate();
    // 시작 일을 새기위한 객체 생성
    let firstDay = new Date(userYear, userMonth - 1, 1);

    // 요일
    const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'];

    // 달력 제목 
    let calTitle = document.createElement('h1');
    calTitle.innerHTML = `${userYear}년 ${userMonth}월`;

    // 테이블 생성
    let table = document.createElement('table');

    // 요일 작성
    let weekDays = document.createElement('tr');
    for (let day of days) {
        let th = document.createElement('th');
        th.innerHTML = day;

        if (day == "Sun") {
            th.style.color = 'red';
        }
        if (day == "Sat") {
            th.style.color = 'blue';
        }
        th.style.border = "1px solid black";

        weekDays.appendChild(th);
    }
    table.appendChild(weekDays);

    // 날짜 작성
    let num = 1 - firstDay.getDay();
    for (let i = 0; i < 5; i++) {
        let tr = document.createElement('tr');

        for (let j = 0; j < 7; j++) {
            let td = document.createElement('td');

            td.style.border = "1px solid black";
            td.style.width = "50px";
            td.style.height = "50px";
            td.style.textAlign = 'center';
            if (j == 0) {
                td.style.backgroundColor = 'red';
            }
            if (j == 6) {
                td.style.backgroundColor = 'blue';
            }

            if (num <= date && num > 0) {
                td.innerHTML = num;
            }
            num++;

            tr.appendChild(td);
        }
        table.appendChild(tr);
    }

    document.querySelector("div").appendChild(calTitle);
    document.querySelector("div").append(table); 
}

// 달력 초기화 함수
function reload(){
    // table의 부모태그인 div태그를 삭제한 이후
    document.querySelector('div').remove();
    // 새로운 div태그를 생성
    let content = document.createElement("div");
    // body에 추가
    document.body.appendChild(content);
}
