/* Multiline support */
console.log(`This is the first line
this is the second line`);
/* Expression interpolation */
const MAX = 10;
let value = Math.random() * 20;
if (value <= MAX) {
    console.log(`${value} is less than or equal to ${MAX}`);
} else {
    console.log(`${value} is greater than ${MAX}`);
}

/* Tagged template strings */
function templateHandler(literals, ...values) {
    var temp = '';
    for (var i = 0; i < values.length; i++) {
        temp = temp + literals[i] + values[i];
    }
    return temp + literals[values.length];
}

let firstName = 'Jander', lastName = 'Klander';
// ${ can be any expression }
console.log(templateHandler`Hi ${firstName} ${lastName}!`); // String.raw`...`
// Hi Jander Klander!
console.log(templateHandler(['Hi ', ' ', '!'], firstName, lastName));
// Hi Jander Klander!




// Create a function called 'names2Table' which uses string templates to transform:
// [
//     { first: 'Jander', last: 'Klander' },
//     { first: 'Chiquito', last: 'de la Calzada' }
// ]



// SOLUTION:
var names2Table = names => `
<table> ${names.map(name => `
  <tr>
    <td> ${name.first} </td> <td> ${name.last} </td>
  </tr>
`)}</table>`.replace(',', '');

console.log(names2Table([
    {first: 'Jander', last: 'Klander'},
    {first: 'Chiquito', last: 'de la Calzada'}]
));




// Factory function 'Symbol' accepting an optional description.
var symbol1 = Symbol('description');
var symbol2 = Symbol('description');

console.log(symbol1.toString()); // __$187584550$10$__
console.log(symbol2.toString()); // __$163428317$11$__

// Symbols are unique.
console.log(symbol1 === symbol2); // false

// Symbols can be used as property names.
let obj = {};
let property = Symbol();
obj[property] = 'value';
obj['anotherProperty'] = 'another value';

console.log(obj[property]); // value

console.log(Object.getOwnPropertyNames(obj)); // Ignores symbol-valued keys
console.log(Object.getOwnPropertySymbols(obj)); // Ignores string-valued keys