// event.js
// console.log(this);

function changeValue() {
    // 대문자로 변경
    let elem = document.getElementById('num3').value;
    document.getElementById('num3').value = elem.toUpperCase();
}

let obj = {
    name: 'hong',
    hobby: ['running', 'cooking', 'reading'],
    pet: [{
        dog: 'bowwow'
    }, {
        cat: 'meow'
    }, {
        horse: 'latte'
    }]
}

let fruits = ["Apple", "Orange", "Melon", "Mango"];

function addFruit(){
    let addVal = document.getElementById('add').value;
    fruits[fruits.length] = addVal;
    document.getElementById('add').value = null;
    removeElem();
    createElem();
}

function createElem(){
    let ulTag = document.createElement('ul');
    ulTag.setAttribute('id', 'fruit');

    for(let i of fruits){
        let liTag = document.createElement('li');
        liTag.innerHTML = i;
        ulTag.appendChild(liTag);
    }
    document.body.appendChild(ulTag);
}

function removeElem(){
    if(document.getElementById('fruit')){
        document.getElementById('fruit').remove();
    }
}
