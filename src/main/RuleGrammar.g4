grammar RuleGrammar;

/* A number: can be an integer value, or a decimal value */
NUMBER: DIGIT+;
fragment DIGIT : '0'..'9';

ID : LETTER (LETTER | NUMBER | '_')*;

fragment LETTER : LOWERCASE | UPPERCASE;
fragment UPPERCASE : 'A'..'Z';
fragment LOWERCASE : 'a'..'z';


WS : [ \n\t\r] -> channel(HIDDEN);

program:    ID '.' EOF;