grammar H;
code: statement* EOF;

statement: assignment | containsCheck;

assignment: ID '=' ID '[' STRING ']' ';' ;

containsCheck: ID '.' 'contains' '(' STRING ')' ';' ;

ID: [a-zA-Z_][a-zA-Z_0-9]* ;

STRING: '\'' .*? '\'' ;

WS: [ \t\r\n]+ -> skip ;