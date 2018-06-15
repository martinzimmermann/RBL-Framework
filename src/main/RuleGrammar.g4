grammar RuleGrammar;

NUMBER: DIGIT+('.'DIGIT+)?;
fragment DIGIT : '0'..'9';

ID : LETTER (LETTER | DIGIT | '_')*;

fragment LETTER : LOWERCASE | UPPERCASE;
fragment UPPERCASE : 'A'..'Z';
fragment LOWERCASE : 'a'..'z';

LT: '<';
LTE: '<=';

WS : [ \n\t\r] -> channel(HIDDEN);

program:    memory? r_rules EOF;

memory:     (predicate '.')+;
predicate:  ID;

r_rules:    (r_rule '.')+;
r_rule:     preconditions? '->' ('+' WorldAddtion=predicate | '#' Goal=predicate)? worldDeletions action alist? rule_goal? damping?;
preconditions: predicate  (',' predicate )*;
worldDeletions: (('-' predicate)*);
rule_goal:  NUMBER;
damping: '['Damping=NUMBER? ',' Aging=NUMBER? ',' agingTarget? ']';
agingTarget: MaxAging=NUMBER #AgingNoBound
     | '|' MaxAging=NUMBER #AgingLowerBound
     |  MaxAging=NUMBER '|' #AgingUpperBound
     ;
alist:      '('(( alistentry (',' alistentry)* (',' expr)?) | expr )')';
alistentry: NUMBER (LT|LTE) 'a' (LT|LTE) NUMBER ':' expr;
action:     ID('.'ID)*;

expr: sign value #UnarySignExpr
    | LHS=expr mulop RHS=expr #MulopExpr
    | LHS=expr sign RHS=expr #SignExpr
    | value #ValueExpr
    ;

sign: '+' | '-';
mulop: '*' | '/';

value: 'a' #VarValue
     | NUMBER #NumValue
     | '(' expr ')' #BraceValue
     ;