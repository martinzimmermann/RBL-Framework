grammar RuleGrammar;

NUMBER: DIGIT+('.'DIGIT+)?;
fragment DIGIT : '0'..'9';

ID : LETTER (LETTER | DIGIT | '_')*;

fragment LETTER : LOWERCASE | UPPERCASE;
fragment UPPERCASE : 'A'..'Z';
fragment LOWERCASE : 'a'..'z';

LT: '<';
LTE: '<=';

LINEEND : DOT (NEWLINE | EOF);
fragment DOT   : '.';
fragment NEWLINE   : '\r' '\n' | '\n' | '\r';

WS : [ \n\t\r] -> channel(HIDDEN);

program:        ((fact |  r_rule) LINEEND)*;
fact:           predicate;
predicate:      ID;
r_rule:         preconditions? '->' ('#' Goal=predicate)? postconditions action;
preconditions:  predicate  (',' predicate )*;
postconditions: postcondition*;
postcondition:  (worldAddition | worldDeletion);
worldAddition:  '+' predicate;
worldDeletion:  '-' predicate;

action:         ID('.'ID)*;
