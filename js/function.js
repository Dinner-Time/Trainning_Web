// function.js

function sum(v1, v2) {
    return v1 + v2;
}

function plus() {
    const var1 = document.getElementById('num1')
    const var2 = document.getElementById('num2');
    document.getElementById('result').value = sum(parseInt(var1.value), parseInt(var2.value));
}


var fnc = function (v1, v2) {
    return v1 + v2;
}

console.log(fnc(1, 2, 3));

