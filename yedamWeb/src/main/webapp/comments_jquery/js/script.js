// app.js
// 1. jQuery 활용

// 'use strict'를 선언하면 브라우저 콘솔에서 error를 더욱 정확하게 잡아낸다.
'use strict'


$(document).ready(function () {
    loadCommentList();
})

// ************************** 페이지 로드 **************************

// 목록조회
function loadCommentList() {
    $.ajax({
        url: "../CommentsServ",
        data: {
            cmd: 'selectAll'
        },
        success: loadCommentResult,
        error: function () {
            alert("error");
        }
    });
}

// 조회결과
function loadCommentResult(result) {
    const xmp = new DOMParser();
    const xmlDoc = xmp.parseFromString(result, 'text/xml');
    const code = $(xmlDoc).find('code').html();

    if (code == 'success') {
        const commentList = eval($(xmlDoc).find('data').html());
        const listDiv = $('#commentList');
        for (let row of commentList) {
            listDiv.append(makeCommentView(row));
        }
    }
}

// ************************** 페이지 로드 **************************


// ************************** 게시글 추가 **************************

// 등록 버튼 클릭 시
function addComment() {
    const name = addForm.name.value;
    if (name == "") {
        alert('please...');
        addForm.name.focus();
        return;
    }
    const content = addForm.content.value;
    if (content == "") {
        alert('please...');
        addForm.content.focus();
        return;
    }

    $.ajax({
        url: '../CommentsServ',
        data: {
            cmd: "insert",
            name: name,
            content: content
        },
        success: addResult,
        error : function(){
            alert('error');
        }
    });
}

// 등록 콜백 함수
function addResult(result) {

    const xmp = new DOMParser();
    const xmlDoc = xmp.parseFromString(result, 'text/xml');
    const code = $(xmlDoc).find('code').html();
    
    if (code == 'success') {
        const listDiv = $('#commentList');
        let comment = JSON.parse($(xmlDoc).find('data').html());
        console.log(makeCommentView(comment));
        listDiv.append(makeCommentView(comment));
        addForm.name.value = "";
        addForm.content.value = "";
        textEffect(); // 텍스트 효과
    }
}

// ************************** 게시글 추가 **************************


// ************************** 게시글 수정 **************************

// 수정 화면
function viewUpdateForm(commentId) {
    const commentDiv = $("#"+commentId);
    const updateFormDiv = $('#commentUpdate').css('display', 'block');

    commentDiv.after(updateFormDiv);

    let comment = commentDiv.data('obj'); 
    updateForm.id.value = comment.id;
    updateForm.name.value = comment.name;
    updateForm.content.value = comment.content;
    textEffect(); // 텍스트 효과
  
}

// 변경 버튼 클릭시 
function updateComment() {
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
    let id = updateForm.id.value; 

    $.ajax({
        url : '../CommentsServ',
        data :{
            cmd : 'update',
            id : id,
            name : name,
            content : content
        },
        success : updateResult
    });
}

// 변경 콜백 함수
function updateResult(result) {
        const code = $(result).find('code').html();
        console.log(code)

        if (code == 'success') {
            $('#commentUpdate').css('display', 'none');

            const comment = JSON.parse($(result).find('data').html());
            const commentDiv = makeCommentView(comment);
            
            $('#'+comment.id).html($(commentDiv).html());

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
    $.ajax({
        url : '../CommentsServ',
        data : {
            cmd : 'delete',
            id : commentId
        },
        success : function(){
            $('#'+commentId).remove();
        } 
    });

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

    let div = $('<div />');
    $(div).attr({
        id : comment.id,
    });
    $(div).data('obj', comment);
    $(div).html(commentView(comment));
    return $(div);
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