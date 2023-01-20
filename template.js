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