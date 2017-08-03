grammar RuleGrammar;

NUMBER: DIGIT+('.'DIGIT+)?;
fragment DIGIT : '0'..'9';

ID : LETTER (LETTER | DIGIT | '_')*;

fragment LETTER : LOWERCASE | UPPERCASE;
fragment UPPERCASE : 'A'..'Z';
fragment LOWERCASE : 'a'..'z';


WS : [ \n\t\r] -> channel(HIDDEN);

program:    memory? r_rules EOF;

memory:     (predicate '.')+;
predicate:  ID;

r_rules:    (r_rule '.')+;
r_rule:     predicates? '->' ('+' predicate | '!' predicate)? ('-' predicate)* action alist goal;
predicates: predicate  (',' predicate )*;

goal:       NUMBER;
alist:      '(' alistentry (',' alistentry)* ')';
alistentry: NUMBER '-' NUMBER ':' NUMBER;
action:     ID;