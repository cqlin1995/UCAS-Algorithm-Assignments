/* 设有5架飞机，其时间窗口分别是[8:00-9:00] ,[9:30-10:00], [11:00-11:30], [12:00-12:30], [13:00-14:00]  */

/* Variables */
var gap >= 0;
var x1 >= 0;
var x2 >= 0;
var x3 >= 0;
var x4 >= 0;
var x5 >= 0;

/* Object function */
maximize z: gap;

/* Constrains */
s.t. con1: gap - x2 + x1 <= 0;
s.t. con2: gap - x3 + x2 <= 0;
s.t. con3: gap - x4 + x3 <= 0;
s.t. con4: gap - x5 + x4 <= 0;
s.t. con5: x1 <= 9;
s.t. con6: x2 <= 10;
s.t. con7: x3 <= 11.5;
s.t. con8: x4 <= 12.5;
s.t. con9: x5 <= 14;
s.t. con10: -x1 <= -8;
s.t. con11: -x2 <= -9.5;
s.t. con12: -x3 <= -11;
s.t. con13: -x4 <= -12;
s.t. con14: -x5 <= -13;
end;

