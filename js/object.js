// object.js

let s1 = {
    sno: 1001,
    sname: 'park',
    sscore: 90,
}
let field = 'sscore';

console.log(s1.sno);
console.log(s1['sname']);
console.log(s1[field]);

// 
for (let field in s1) {
    console.log(field + ', ' + s1[field]);
}

// for of => 배열일때

let numbers = [10, 32, 55, 27, 99];

for (let i = 0; i < numbers.length; i++) {
    console.log(numbers[i]);
}
for (let i of numbers) {
    console.log(i);
}

let s2 = {
    sno: 1002,
    sname: 'kim',
    sscore: 80,
}

let s3 = {
    sno: 1003,
    sname: 'choi',
    sscore: 44,
}

let students = [s1, s2, s3];

for(let student of students){
    for(let field in student){
        console.log(field + ' => ' + student[field]);
    }
    console.log('---------------------');
}

