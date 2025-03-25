lexer grammar LexicalAnalyzer;

// Case-sensitive Keywords
SELF: 'self';
SELF_TYPE	: 'SELF_TYPE' ;

//case-insensitive Keywords
CLASS : [Cc][Ll][Aa][Ss][Ss];
INHERITS : [Ii][Nn][Hh][Ee][Rr][Ii][Tt][Ss];
IF : [Ii][Ff];
THEN : [Tt][Hh][Ee][Nn];
ELSE : [Ee][Ll][Ss][Ee];
FI : [Ff][Ii];
WHILE : [Ww][Hh][Ii][Ll][Ee];
LOOP : [Ll][Oo][Oo][Pp];
POOL : [Pp][Oo][Oo][Ll];
LET : [Ll][Ee][Tt];
IN : [Ii][Nn];
CASE : [Cc][Aa][Ss][Ee];
ESAC : [Ee][Ss][Aa][Cc];
OF : [Oo][Ff];
NEW : [Nn][Ee][Ww];
ISVOID : [Ii][Ss][Vv][Oo][Ii][Dd];
NOT : [Nn][Oo][Tt];

// Identifiers & Types
TYPE_IDENTIFIER : [A-Z][a-zA-Z0-9_]* |  SELF_TYPE ;
OBJECT_IDENTIFIER : [a-z][a-zA-Z0-9_]* | SELF;
INT : [0-9]+;
STRING: '"' (('\\'|'\t'|'\n'|'\f'|'\b') | ~('\\'|'\t'|'\n'|'\f'|'\b'|'"'))*'"';
// true and false must start with lowwer case chars
TRUE : [t] [Rr] [Uu] [Ee];
FALSE :  [f] [Aa] [Ll] [Ss] [Ee];

// Symbols & Operators
SEMICOLON: ';';
COLON: ':';
COMMA: ',';
DOT: '.';
AT: '@';
ASSIGN: '<-';
CASEASSIGN: '=>';
LBRACE: '{';
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
EQUAL: '=';
LT: '<';
LTEQ: '<=';
TILDE: '~';

// Comments
SINGLECOMMENT: '--' ~[\r\n]* -> skip;
MULTICOMMENT : '(*' (MULTICOMMENT | .)*? '*)' -> skip;

// Whitespace
WS: [ \n\t\r]+ -> skip;
