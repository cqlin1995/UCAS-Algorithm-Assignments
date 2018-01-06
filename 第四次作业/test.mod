/* Variables */
var x1 >= 0;
var x2 >= 0;
var x3 >= 0;
var x4 >= 0;
var x5 >= 0;

/* Object function */
minimize z: 5*x1 + 35*x2 + 20*x3;

/* Constrains */
s.t. con1: x1 - x2 - x3 + x4 = -2;
s.t. con2: -1*x1 - 3*x2 + x5 = -3;

end;