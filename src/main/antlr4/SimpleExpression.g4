grammar SimpleExpression;

SKIP  : 'skip';
READ  : 'read';
WRITE : 'write';

WHILE : 'while';
DO    : 'do';

IF   : 'if' ;
THEN : 'then';
ELSE : 'else';

TRUE  : 'true' ;
FALSE : 'false' ;

AND : '&&' ;
OR  : '||' ;

MULT  : '*' ;
DIV   : '/' ;
PLUS  : '+' ;
MINUS : '-' ;

GT : '>' ;
GE : '>=' ;
LT : '<' ;
LE : '<=' ;
EQ : '==' ;


ASSIGN : ':=' ;

DECIMAL : '-'?[0-9]+('.'[0-9]+)? ;

IDENTIFIER : [a-zA-Z_][a-zA-Z_0-9]* ;

SEMI : ';' ;

WS : [ \r\t\u000C\n]+ -> skip ;

program: statement EOF;

statement
    : if_statement
    | while_statement
    | skip_statement
    | assign_statement
    | write_statement
    | read_statement
    | statement SEMI statement;

if_statement: IF cond=condition THEN statement ELSE statement;
while_statement: WHILE condition DO statement;
skip_statement: SKIP;
assign_statement: IDENTIFIER ASSIGN expression;
write_statement: WRITE expression;
read_statement: READ expression;

condition : logical_expr ;
expression
    : logical_entity
    | arithmetic_expr;

logical_expr
 : logical_expr logicOperator=logic_operator logical_expr # LogicalExpressionOperator
 | comparison_expr               # ComparisonExpression
 | logical_entity                # LogicalEntity
 ;

comparison_expr
    : comparison_operand comp_operator comparison_operand # ComparisonExpressionWithOperator
    ;

comparison_operand : arithmetic_expr
                   ;

logic_operator : AND
               | OR;

comp_operator : GT
              | GE
              | LT
              | LE
              | EQ
              ;

arithm_operator: MULT
               | DIV
               | PLUS
               | MINUS;

arithmetic_expr
 : arithmetic_expr arithmeticOperator=arithm_operator arithmetic_expr  # ArithmeticExpressionOperator
 | MINUS arithmetic_expr                            # ArithmeticExpressionNegation
 | numeric_entity                                   # ArithmeticExpressionNumericEntity
 ;

logical_entity : (TRUE | FALSE) # LogicalConst
               | IDENTIFIER     # LogicalVariable
               ;

numeric_entity : DECIMAL              # NumericConst
               | IDENTIFIER           # NumericVariable
               ;