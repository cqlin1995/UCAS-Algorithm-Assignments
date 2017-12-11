/* Variables */
var x1 >= 0;
var x2 >= 0;
var x3 >= 0;

/* Object function */
maximize z: x1 + 14*x2 + 6*x3;

/* Constrains */
s.t. con1: x1 + x2 + x3 <= 4;
s.t. con2: x1  <= 2;
s.t. con3: x3  <= 3;
s.t. con4: 3*x2 + x3  <= 6;

end;