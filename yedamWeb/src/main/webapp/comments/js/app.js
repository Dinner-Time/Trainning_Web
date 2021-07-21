// app.js
// 1. ajax와 servlet을 활용

// 'use strict'를 선언하면 브라우저 콘솔에서 error를 더욱 정확하게 잡아낸다.
'use strict'


// 브라우저가 load되었을때 (페이지를 새로 열거나 새로고침 했을때)
window.onload = function (event) {
    // #commentList에 이미 저장된 게시글을 불러온다.
    loadCommentList();
}

// ************************** 페이지 로드 **************************

// 목록조회
function loadCommentList() {
    // 1. 데이터베이스에 저장되어있는 게시글을 불러오는 메서드.
    // 2. ajax를 이용, servlet페이지에 JSON, xml형태로 저장된 텍스트들을 읽어온다.

    // ajax를 이용하기 위한 객체 생성
    let xhtp = new XMLHttpRequest();

    // servlet페이지를 열고(?)
    xhtp.open("get", "../CommentsServ?cmd=selectAll");

    // servlet페이지와 통신(?)
    xhtp.send();
    xhtp.onreadystatechange = loadCommentResult;
}

// 조회결과
function loadCommentResult() {
    // 1. servlet페이지의 데이터들을 불러와서
    // 2. #commentList에 작성한다.
    // 3. 여기서 this의 의미는(???)

    // 아래 조건식은 페이지 연결상태가 정상일때를 의미한다
    if (this.readyState == 4 && this.status == 200) {
        let xmp = new DOMParser(); // xml로 저장된 데이터를 사용하기 위한 객체 생성
        let xmlDoc = xmp.parseFromString(this.responseText, 'text/xml'); // 데이터를 string타입으로 변환
        let code = xmlDoc.getElementsByTagName('code')[0].innerHTML; // java에서 데이터 처리가 제대로 되었는지 확인하기 위한 변수
        let listDiv = document.getElementById('commentList'); // 데이터를 추가할 부모div

        if (code == 'success') { // 데이터 처리가 성공적

            // 추가할 데이터를 처리가능한 형태로 변경
            // JSON은 eval 사용 불가능
            let commentList = eval(xmlDoc.getElementsByTagName('data')[0].innerHTML);

            // commentList는 object를 담은 *Array*이다.
            console.log(commentList); // 데이터 확인(주석 가능)

            for (let row of commentList) {
                // makeCommentView(comment) => 데이터를 읽어와서 div태그를 생성
                listDiv.appendChild(makeCommentView(row)); // 데이터를 하나씩 추가
            }
        } else {
            alert("data Error!! Check on ~DAO.java")
        }
    }
}

// ************************** 페이지 로드 **************************


// ************************** 게시글 추가 **************************

// 등록 버튼 클릭 시
function addComment() {
    // 1. input에 아무 것도 없을 시 
    // 2. 경고창을 띄우고 기능 하지 않도록 함
    // 3. 입력받은 내용을 바탕으로 parameter를 작성하여
    // 4. servlet과 통신(?)
    // 5. 이후 servlet페이지에 작성된 데이터를 통해 페이지에 내용 작성

    // ***** 1, 2 ********
    let name = addForm.name.value; // 이름에 입력된 input 값
    if (name == "") {
        alert('please...');
        addForm.name.focus();
        return;
    }
    let content = addForm.content.value; // 내용에 입력된 input 값
    if (content == "") {
        alert('please...');
        addForm.content.focus();
        return;
    }

    // ***** 3 *****
    let param = "&name=" + name + "&content=" + content;
    console.log(param); // 작성된 parameter확인 (주석가능)

    // ***** 4 *****
    let xhtp = new XMLHttpRequest();
    xhtp.open("get", "../CommentsServ?cmd=insert" + param);
    xhtp.send();

    // ***** 5 *****
    xhtp.onreadystatechange = addResult;
}

// 등록 콜백 함수
function addResult() {
    // 1. 연결 상태가 정상일 경우
    // 2. servlet페이지에 저장된 xml형태의 데이터를 문자열로 변경한 이후
    // 3. 저장된 데이터중 code가 success일 때
    // 4. 저장된 데이터 중 data의 내용(JSON형태)을 Object로 변경한 후
    // 5. 'commentList'태그에 추가 하고 input값을 초기화 한다.

    // ***** 1 *****
    if (this.readyState == 4 && this.status == 200) {

        // ***** 2 *****
        let xmp = new DOMParser();
        let xmlDoc = xmp.parseFromString(this.responseText, 'text/xml');
        let code = xmlDoc.getElementsByTagName('code')[0].innerHTML; // 저장된 데이터 중 code
        let listDiv = document.getElementById('commentList'); // 'commentList'태그

        // ***** 3 *****
        if (code = 'success') {

            // ***** 4 *****
            let comment = JSON.parse(xmlDoc.getElementsByTagName('data').item(0).innerHTML);
            console.log(comment);

            // ***** 5 *****
            listDiv.appendChild(makeCommentView(comment));
            addForm.name.value = "";
            addForm.content.value = "";
            textEffect(); // 텍스트 효과
        } else if (code = 'error') {
            alert("error");
        }
    }
}

// ************************** 게시글 추가 **************************


// ************************** 게시글 수정 **************************

// 수정 화면
function viewUpdateForm(commentId) {
    // 1. 수정 Form을 보이게 하고
    // 2. 수정 하려는 글 아래에 Form을 추가
    // 2-1. appendChild를 할 경우 HTML내용을 변경 할 시 updateForm이 사라진다
    //      따라서 appendChild가 아닌 after를 한다.
    // 3. 수정 하려는 글의 데이터를 읽어와서 input에 저장

    let commentDiv = document.getElementById(commentId); // 수정 하려는 글의 id값
    let updateFormDiv = document.getElementById('commentUpdate'); // 수정 form

    // ***** 1 *****
    updateFormDiv.style.display = "block";

    // ***** 2 *****
    // commentDiv.appendChild(updateFormDiv);

    // ***** 2-1 *****
    commentDiv.after(updateFormDiv);

    // ***** 3 *****
    let comment = commentDiv.comment; // (????)
    console.log(updateForm.id);
    updateForm.id.value = comment.id;
    updateForm.name.value = comment.name;
    updateForm.content.value = comment.content;
    textEffect(); // 텍스트 효과
}

// 변경 버튼 클릭시 
function updateComment() {
    // parameter에 id가 추가 된 것을 제외 하고는 addComment()와 같다.
    // ctl + f 로 addComment() 확인
    let name = updateForm.name.value;
    if (name == "") {
        alert('please...');
        updateForm.name.focus();
        return;
    }
    let content = updateForm.content.value;
    if (content == "") {
        alert('please...');
        updateForm.content.focus();
        return;
    }
    let id = updateForm.id.value; // id
    let param = "&id=" + id + "&name=" + name + "&content=" + content;
    console.log(param); // parameter확인 (주석 가능)
    let xhtp = new XMLHttpRequest();
    xhtp.open("get", "../CommentsServ?cmd=update" + param);
    xhtp.send();
    xhtp.onreadystatechange = updateResult;
}

// 변경 콜백 함수
function updateResult() {
    // 1, 2, 3, 4 과정이 등록 콜백 함수와 같다
    // ctl + f로 addResult() 참고
    // 5. 'commentUpdate'이 안보이도록 처리한 이후
    // 6. comment의 id 값과 같은 div를 찾아서 
    //    그 div에 속해있는 **'commentUpdate'를 다른 위치로 이동한 후**(updateComment에서 appendChild를 한 경우에만) HTML을 새로 작성한다. 
    // 6-1. 또는 replaceChild를 활용한다 

    // servlet에서 html로 받아온 경우와 xml로 받아온 경우의 처리 과정이 다르다.
    // 주석처리 된 부분들은 html로 데이터를 받아왔을때의 처리 과정

    // ***** 1 *****
    if (this.readyState == 4 && this.status == 200) {

        // ***** 2 *****
        // let xmp = new DOMParser();
        // let xmlDoc = xmp.parseFromString(this.responseText, 'text/xml');
        let xmlDoc = this.responseXML;
        let code = xmlDoc.getElementsByTagName('code')[0].innerHTML;
        let listDiv = document.getElementById('commentList');

        // ***** 3 *****
        if (code = 'success') {

            // // ***** 4 *****
            // let comment = JSON.parse(xmlDoc.getElementsByTagName('data').item(0).innerHTML);
            let updateFormDiv = document.getElementById('commentUpdate');
            
            // // ***** 5, 6 *****
            updateFormDiv.style.display = "none";
            // listDiv.appendChild(updateFormDiv);
            // for (let i of listDiv.children) { 
            //     if (i.id == comment.id) {
            //         i.innerHTML = commentView(comment);
            //         break;
            //     }
            // }

            // ***** 6-1 *****
            let comment = JSON.parse(xmlDoc.getElementsByTagName('data').item(0).firstChild.nodeValue);
            let commentDiv = makeCommentView(comment);
            let oldCommentDiv = document.getElementById(comment.id);

            listDiv.replaceChild(commentDiv, oldCommentDiv);

        } else if (code = 'error') {
            alert("error");
        }
    }
}

// 취소 버튼
function cancelComment() {
    // 스타일만 변경
    document.getElementById('commentUpdate').style.display = "none";
}

// ************************** 게시글 수정 **************************


// ************************** 게시글 삭제 **************************

// 삭제 버튼
function confirmDeletion(commentId) {
    // commentId는 현재 글의 id값
    // 1. id값으로 parameter작성 후 servlet과 통신하고
    // 2. 현재 글 삭제

    // ***** 1 *****
    let xhtp = new XMLHttpRequest();
    xhtp.open("get", "../CommentsServ?cmd=delete&id=" + commentId);
    xhtp.send();

    // ***** 2 *****
    xhtp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById(commentId).remove();
        }
    }
}

// ************************** 게시글 삭제 **************************


// ************************** 기능 함수 **************************

// 텍스트 효과
function textEffect() {
    // css스타일을 도와주기 위한 기능 함수 (중요도 낮음)
    const inputText = document.querySelectorAll('input[type="text"]');
    const labelText = document.querySelectorAll('input[type="text"] ~ label');

    for (let i = 0; i < inputText.length; i++) {
        if (inputText[i].value != "") {
            labelText[i].classList.remove("active");
            labelText[i].classList.add("inactive");
        } else {
            labelText[i].classList.remove("inactive");
            labelText[i].classList.add("active");
        }
    }
}

// 데이터 한 건 조회
function makeCommentView(comment) {
    let div = document.createElement('div'); // div태그 생성
    div.setAttribute('id', comment.id); // id속성값을 읽어온 데이터의 id값으로 저장
    div.className = 'comment'; // class 부여(comment)
    div.comment = comment; // (????)
    div.innerHTML = commentView(comment); // 생성한 div태그의 html내용을 작성
    return div;
}

// 출력 내용 작성
function commentView(comment) {
    let str =
        '<div class="conWrapper">' +
        '<span class="writer">' + comment.name + '</span class="writer">' + // 작성자 이름
        '<span class="content">' + comment.content + '<span class="content">' + // 작성 내용
        '</div>' +
        '<div class="btnWrapper">' +
        '<input type="button" value="수정" onclick="viewUpdateForm(' +
        comment.id + ')">' +
        '<input type="button" value="삭제" onclick="confirmDeletion(' +
        comment.id + ')">' +
        '</div>'
    return str;
}