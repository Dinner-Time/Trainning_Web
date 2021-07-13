// variable.js

var age = 10;
console.log(age);

age = 20;
console.log(age);

const PI = 3.1415;

{
    let age = 30;
    console.log(age);
}

console.log(age);

let v1 = 'hello'; // string
v1 = 100.45; // number
v1 = true; // boolean
v1 = null; // object
v1 = [1, 2, 3]; // object
v1 = {
    name: 'hong',
    age: 10,
} // object
v1 = function (a, b) {
    return a + b;
} // function
console.log(typeof v1);

console.log(v1(10, 20));

v1 = 10 > 20;
v1 = 0; // 0, null, false => false

if (v1) {
    console.log('true');
} else {
    console.log('false');
}