grammar RuleGrammar;

/* A number: can be an integer value, or a decimal value */
NUMBER: DIGIT+;
fragment DIGIT : '0'..'9';

ID : LETTER (LETTER | NUMBER | '_')*;

fragment LETTER : LOWERCASE | UPPERCASE;
fragment UPPERCASE : 'A'..'Z';
fragment LOWERCASE : 'a'..'z';


WS : [ \n\t\r] -> channel(HIDDEN);

program:    memory rules;

memory:     (predicate '.')*;
predicate:  ID;

rules:      predicates '->' ('+' predicate)? ('-' predicate)* action alist goal '.';
predicates: predicate  (',' predicate )*;

goal:       NUMBER;
alist:      '(' alistentry (',' alistentry)* ')';
alistentry: NUMBER '-' NUMBER ':' NUMBER;
action:     ID;