grammar Parser;

options {
  tokenVocab = LexicalAnalyzer;
}

prog  : classDefine+ EOF# start;

classDefine : CLASS TYPE_IDENTIFIER (INHERITS TYPE_IDENTIFIER)?
              LBRACE classBody RBRACE SEMICOLON # classdef;

classBody: (attr | method)*;

attr: OBJECT_IDENTIFIER COLON TYPE_IDENTIFIER (ASSIGN expr)? SEMICOLON;

method: OBJECT_IDENTIFIER LPAREN (parm (COMMA parm)*)? RPAREN COLON TYPE_IDENTIFIER
        LBRACE expr RBRACE SEMICOLON;

parm: OBJECT_IDENTIFIER COLON TYPE_IDENTIFIER;

methodCall: OBJECT_IDENTIFIER LPAREN (expr (COMMA expr)*)? RPAREN;

expr: OBJECT_IDENTIFIER ASSIGN expr                         # assign
    | expr (AT TYPE_IDENTIFIER)? DOT methodCall             # longcall
    | methodCall                                            # shortcall
    | IF expr THEN expr ELSE expr FI                        # if
    | WHILE expr LOOP expr POOL                             # while
    | LBRACE (expr SEMICOLON)+ RBRACE                       # block
    | LET OBJECT_IDENTIFIER COLON TYPE_IDENTIFIER
    (ASSIGN expr)? (COMMA OBJECT_IDENTIFIER COLON TYPE_IDENTIFIER
    (ASSIGN expr)? )* IN expr                               # let
    | CASE expr OF (OBJECT_IDENTIFIER COLON
    TYPE_IDENTIFIER CASEASSIGN expr SEMICOLON)+ ESAC        # case
    | NEW TYPE_IDENTIFIER                                   # newobject
    | ISVOID expr                                           # void
    | expr MUL expr                                         # mul
    | expr DIV expr                                         # div
    | expr ADD expr                                         # add
    | expr SUB expr                                         # sub
    | expr LT expr                                          # lt
    | expr LTEQ expr                                        # lteq
    | expr EQUAL expr                                       # eq
    | NOT expr                                              # not
    | TILDE expr                                            # invert
    | LPAREN expr RPAREN                                    # parexpr
    | STRING                                                # string
    | (TRUE| FALSE)                                         # bool
    | INT                                                   # num
    | OBJECT_IDENTIFIER                                     # object
    ;







//// Case-sensitive Keywords
//SELF: 'self';
//SELF_TYPE	: 'SELF_TYPE' ;
//
////case-insensitive Keywords
//CLASS : [Cc][Ll][Aa][Ss][Ss];
//INHERITS : [Ii][Nn][Hh][Ee][Rr][Ii][Tt][Ss];
//IF : [Ii][Ff];
//THEN : [Tt][Hh][Ee][Nn];
//ELSE : [Ee][Ll][Ss][Ee];
//FI : [Ff][Ii];
//WHILE : [Ww][Hh][Ii][Ll][Ee];
//LOOP : [Ll][Oo][Oo][Pp];
//POOL : [Pp][Oo][Oo][Ll];
//LET : [Ll][Ee][Tt];
//IN : [Ii][Nn];
//CASE : [Cc][Aa][Ss][Ee];
//ESAC : [Ee][Ss][Aa][Cc];
//OF : [Oo][Ff];
//NEW : [Nn][Ee][Ww];
//ISVOID : [Ii][Ss][Vv][Oo][Ii][Dd];
//NOT : [Nn][Oo][Tt];
//
//// Identifiers & Types
//TYPE_IDENTIFIER : [A-Z][a-zA-Z0-9_]* |  SELF_TYPE ;
//OBJECT_IDENTIFIER : [a-z][a-zA-Z0-9_]* | SELF;
//INT : [0-9]+;
//STRING: '"' (('\\'|'\t'|'\n'|'\f'|'\b') | ~('\\'|'\t'|'\n'|'\f'|'\b'|'"'))*'"';
//// true and false must start with lowwer case chars
//TRUE : [t] [Rr] [Uu] [Ee];
//FALSE :  [f] [Aa] [Ll] [Ss] [Ee];
//
//// Symbols & Operators
//SEMICOLON: ';';
//COLON: ':';
//COMMA: ',';
//DOT: '.';
//AT: '@';
//ASSIGN: '<-';
//CASEASSIGN: '=>';
//LBRACE: '{';
//RBRACE: '}';
//LPAREN: '(';
//RPAREN: ')';
//ADD: '+';
//SUB: '-';
//MUL: '*';
//DIV: '/';
//EQUAL: '=';
//LT: '<';
//LTEQ: '<=';
//TILDE: '~';
//
//// Comments
//SINGLECOMMENT: '--' ~[\r\n]* -> skip;
//MULTICOMMENT : '(*' (MULTICOMMENT | .)*? '*)' -> skip;
//// Whitespace
//WS: [ \n\t\r]+ -> skip;
