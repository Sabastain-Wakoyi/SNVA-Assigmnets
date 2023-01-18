function push(arr1, arr2){
    arr1.push(...arr2);
    return arr1
}

var arr1 = [1, 2];
var arr2 = [3,4];
console.log(push(arr1, arr2));


