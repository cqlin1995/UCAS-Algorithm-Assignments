/* Variables */
var x1 >= 0;
var x2 >= 0;
var x3 >= 0;
var x4 >= 0;
var x5 >= 0;
var x6 >= 0;

/* Object function */
minimize z: -7*x1 + 7*x2 - 2*x3 - x4 - 6*x5;

/* Constrains */
s.t. con1: 3*x1 - x2 + x3 - 2*x4 = -3;
s.t. con2: 2*x1 + x2 + x4 + x5 = 4;
s.t. con3: -1*x1 + 3*x2 - 3*x4 + x6 = 12;
end;

