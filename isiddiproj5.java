import java.io.*;
import java.util.*;

public class isiddiproj5{
  static int debugMode = 0;
  public static void main (String[] args)  {
    if(args.length > 0 && args[0].equals("-d")){
      System.out.println("\nDEBUG MODE ON BABY\n");
      debugMode = 1;
    }
    Token inputToken;
    TokenReader tr = new TokenReader();
    
    System.out.print ("Starting Expression Evaluation Program\n\n");
    System.out.print ("Enter Expression: ");
    
    inputToken = tr.getNextToken ();
    
    while (inputToken.equalsType(TokenType.QUIT) == false){
      /* check first Token on Line of input */
      if(inputToken.equalsType(TokenType.HELP)){
        printCommands();
        tr.clearToEoln();
      }
      else if(inputToken.equalsType(TokenType.ERROR)){
        System.out.print ("Invalid Input - For a list of valid commands, type ?\n");
        tr.clearToEoln();
      }
      else if(inputToken.equalsType(TokenType.EOLN)){
        System.out.print ("Blank Line - Do Nothing\n");
      }
      else{
        processExpression(inputToken, tr);
      } 
      
      System.out.print ("\nEnter Expression: ");
      inputToken = tr.getNextToken ();
    }
    
    System.out.print ("Quitting Program\n");
    return;
  }
  
  public static void processExpression (Token inputToken, TokenReader tr){
    /**********************************************/
    /* Declare both stack head pointers here      */
    LinkList operatorStack = new LinkList();
    LinkList valueStack = new LinkList();
    
    
    /* loop while the current token is not the EndOfLine token */
    while (inputToken.equalsType(TokenType.EOLN) == false){
      /* The expression contain a VALUE */
      if (inputToken.equalsType(TokenType.VALUE)){
        /* make this a debugMode statement */
        if(debugMode == 1){  
          System.out.print ("Val: " + inputToken.getValue() + ", "); 
        }
        /* push the value into the valueStack */
        valueStack.push(inputToken);
      }
      
      /* The expression contains an OPERATOR */
      if(inputToken.equalsType(TokenType.OPERATOR)){
        /* make this a debugMode statement */
        if(debugMode == 1){
          System.out.print ("OP: " + inputToken.getOperator() + ", ");
        }
        /* check if current operator is an open parenthesis */
        if(inputToken.getOperator() == '('){
          //push the open parenthesis onto the operatorStack
          operatorStack.push(inputToken);
        }
        
        /* check if the current operator is + or - */
        if(inputToken.getOperator() == '+' || inputToken.getOperator() == '-'){
          //loop while operatorStack is not empty and top of operatorStack is +, -, *, or /
          while(operatorStack.isEmpty() != 1 && (operatorStack.top().getOperator() == '+' ||
                                                 operatorStack.top().getOperator() == '-' ||
                                                 operatorStack.top().getOperator() == '*' ||
                                                 operatorStack.top().getOperator() == '/')){
            
            Token op = operatorStack.top();
            if(op.getOperator() != '+' && op.getOperator() != '-' &&
               op.getOperator() != '*' && op.getOperator() != '/'){
              System.out.println("ERROR - Top of operatorStack is not +, -, *, or /\n");
              return;
            }
            operatorStack.pop();
            int v2 = valueStack.top().getValue();
            valueStack.pop();
            int v1 = valueStack.top().getValue();
            valueStack.pop();
            
            Token v3 = new Token();
            int num = 0;
            //check for add
            if(op.getOperator() == '+'){
              num = v1 + v2;
            }
            //check for subtraction
            if(op.getOperator() == '-'){
              num = v1 - v2;
            }  
            //check for multiplication
            if(op.getOperator() == '*'){
              num = v1 * v2;
            }    
            //check for division
            if(op.getOperator() == '/'){
              //check for divide by 0
              if(v2 == 0){
                System.out.println("\n*ERROR* - Cannot divide by 0\n");
                tr.clearToEoln();
                return;
              }
              else{
                num = v1 / v2;
              }
            }
            v3.setToValue( num );
            valueStack.push(v3);
            
          }
          //push the current operator on the operatorStack
          operatorStack.push(inputToken);
        }
        
        /* check if current operator ir * or / */
        if(inputToken.getOperator() == '*' || inputToken.getOperator() == '/'){
          //loop while operatorStack is not empty and top of operatorStack is * or /
          while(operatorStack.isEmpty() != 1 && (operatorStack.top().getOperator() == '*' ||
                                                 operatorStack.top().getOperator() == '/')){
           
            Token op = operatorStack.top();
            if(op.getOperator() != '+' && op.getOperator() != '-' &&
               op.getOperator() != '*' && op.getOperator() != '/'){
              System.out.println("ERROR - Top of operatorStack is not +, -, *, or /\n");
              return;
            }
            operatorStack.pop();
            int v2 = valueStack.top().getValue();
            valueStack.pop();
            int v1 = valueStack.top().getValue();
            valueStack.pop();
            
            Token v3 = new Token();
            int num = 0;
            //check for add
            if(op.getOperator() == '+'){
              num = v1 + v2;
            }
            //check for subtraction
            if(op.getOperator() == '-'){
              num = v1 - v2;
            }  
            //check for multiplication
            if(op.getOperator() == '*'){
              num = v1 * v2;
            }    
            //check for division
            if(op.getOperator() == '/'){
              //check for divide by 0
              if(v2 == 0){
                System.out.println("\n*ERROR* - Cannot divide by 0\n");
                tr.clearToEoln();
                return;
              }
              else{
                num = v1 / v2;
              }
            }
            v3.setToValue( num );
            valueStack.push(v3);
          }
          //push the current operator on the operatorStack
          operatorStack.push(inputToken);
        }
        
        /* check if current operator is a closing parenthese */
        if(inputToken.getOperator() == ')'){
          //loop while operatorStack is not empty and top of operatorStack is not an open parenthesis
          while(operatorStack.isEmpty() != 1 &&  operatorStack.top().getOperator() != '('){
            
            Token op = operatorStack.top();
            if(op.getOperator() != '+' && op.getOperator() != '-' &&
               op.getOperator() != '*' && op.getOperator() != '/'){
              System.out.println("*ERROR* - Top of operatorStack is not +, -, *, or /\n");
              return;
            }
            operatorStack.pop();
            int v2 = valueStack.top().getValue();
            valueStack.pop();
            int v1 = valueStack.top().getValue();
            valueStack.pop();
            
            Token v3 = new Token();
            int num = 0;
            //check for add
            if(op.getOperator() == '+'){
              num = v1 + v2;
            }
            //check for subtraction
            if(op.getOperator() == '-'){
              num = v1 - v2;
            }  
            //check for multiplication
            if(op.getOperator() == '*'){
              num = v1 * v2;
            }    
            //check for division
            if(op.getOperator() == '/'){
              //check for divide by 0
              if(v2 == 0){
                System.out.println("\n*ERROR* - Cannot divide by 0\n");
                tr.clearToEoln();
                return;
              }
              else{
                num = v1 / v2;
              }
            }
            v3.setToValue( num );
            valueStack.push(v3);
            
          }
          //check if operatorStack is empty
          if(operatorStack.isEmpty() == 1){
            //print error message
            System.out.println("*ERROR* - operatorStack is empty\n");
          }
          //else
          else{
            //pop open parenthesis from the operatorStack TODO
            operatorStack.pop();
          }
        }
      }
      
      /* get next token from input */
      inputToken = tr.getNextToken ();
    } 
    
    /* The expression has reached its end */
    
    //THIRD STEP: once the EndOfLine token is encountered:
    //loop while the operatorStack is not empty
    while(operatorStack.isEmpty() != 1){
      if(operatorStack.isEmpty() == 1){
        break;
      }
     
      Token op = operatorStack.top();
      if(op.getOperator() != '+' && op.getOperator() != '-' &&
         op.getOperator() != '*' && op.getOperator() != '/'){
        System.out.println("*ERROR* - Top of operatorStack is not +, -, *, or /\n");
        return;
      }
      operatorStack.pop();
      int v2 = valueStack.top().getValue();
      valueStack.pop();
      int v1 = valueStack.top().getValue();
      valueStack.pop();
      
      Token v3 = new Token();
      int num = 0;
      //check for add
      if(op.getOperator() == '+'){
        num = v1 + v2;
      }
      //check for subtraction
      if(op.getOperator() == '-'){
        num = v1 - v2;
      }  
      //check for multiplication
      if(op.getOperator() == '*'){
        num = v1 * v2;
      }    
      //check for division
      if(op.getOperator() == '/'){
        //check for divide by 0
        if(v2 == 0){
          System.out.println("\n*ERROR* - Cannot divide by 0\n");
          tr.clearToEoln();
          return;
        }
        else{
          num = v1 / v2;
        }
      }
      v3.setToValue( num );
      valueStack.push(v3);
    }
    if(valueStack.isEmpty() == 1){
      System.out.println("*ERROR* - valueStack is empty!!\n");
    }
    else{
      //print out the top of the valueStack at the result of the expression
      System.out.println("Top of valueStack: " + valueStack.top().getValue() + "\n");
    }
    //popping the valueStack should make it empty
    valueStack.pop();
    //print error if not empty
    if(valueStack.isEmpty() == 0){
      System.out.println("*ERROR* - valueStack not empty!!\n");
    }
    System.out.print ("\n");
  }
  
  
  //Node for the linked list
  public class Node{
  Token tok;
  Node next;
  
  public Node(Token t){
    tok = t;
    next = null;
  }
}
  
  /**************************************************************/
  /*                                                            */
  /*  The Code below this point should NOT need to be modified  */
  /*  for this program.   If you feel you must modify the code  */
  /*  below this point, you are probably trying to solve a      */
  /*  more difficult problem that you are being asked to solve  */
  /*                                                            */
  /**************************************************************/
  public static void printCommands(){
    System.out.print ("The commands for this program are:\n\n");
    System.out.print ("q - to quit the program\n");
    System.out.print ("? - to list the accepted commands\n");
    System.out.print ("or any infix mathematical expression using operators of (), *, /, +, -\n");
  }
  
}

class TokenReader{
  private BufferedReader br;
  private String inline;
  private boolean needline;
  private int pos;
  
  // initialize the TokenReader class to read from Standard Input
  public TokenReader(){
    // set to read from Standard Input
    br = new BufferedReader (new InputStreamReader (System.in));
    inline = "";
    pos = 0;
    needline = true;
  }
  
  // Force the next getNextToken to read in a line of input
  public void clearToEoln(){
    needline = true;
  }
  
  // Return the next Token from the input line
  public Token getNextToken(){
    // get a new line of input from user
    if (needline){
      try{
        inline = br.readLine();
      }
      catch (IOException ioe){
        System.out.println ("Error in reading");
        return new Token (TokenType.EOF);
      }
      if (inline == null){ // End of File Encoutered - Should never be the case when reading 
        //   from standard input: System.in
        return new Token (TokenType.EOF);
      }
      
      inline = inline + " ";    // add a space at end to help deal with digit calculation
      needline = false;
      pos = 0;
    }
    
    // skip over any white space characters in the beginning of the input
    while ( pos < inline.length() && 
           Character.isWhitespace(inline.charAt(pos))){
      pos++;
    }
    
    // check for the end of the current line of input
    if (pos >= inline.length()){  // at end of line
      needline = true;
      return new Token (TokenType.EOLN);
    }
    
    // Get the next character from the input line
    char ch = inline.charAt(pos); 
    pos++;
    
    // check if 'q' or 'Q' was entered ==> QUIT Token
    if ( 'q' == ch || 'Q' == ch ){
      return new Token (TokenType.QUIT);
    }
    
    // check if "?" was entered ==> HELP Token
    if ( '?' == ch ){
      return new Token (TokenType.HELP);
    }
    
    // check for Operator values of: + - * / ( )  ==> OPERATOR Token
    if ( ('+' == ch) || ('-' == ch) || ('*' == ch) ||
        ('/' == ch) || ('(' == ch) || (')' == ch) ){
      Token t = new Token();
      t.setToOperator( ch );
      return t;
    }
    
    // check for a number  ==> VALUE Token
    if (Character.isDigit(ch)){
      int number = Character.digit(ch, 10);
      ch = inline.charAt(pos); pos++;
      while (Character.isDigit(ch)){
        number = number * 10 + Character.digit(ch, 10);
        ch = inline.charAt(pos); pos++;
      }
      pos--; // since number calcuation check one character after the last digit
      Token t = new Token();
      t.setToValue( number );
      return t; 
    }
    
    // Input in not valid if code get to this part ==> ERROR Token
    return new Token (TokenType.ERROR);
  }
}

// Enumarated Type specifying all of the Tokens
enum TokenType{
  ERROR, OPERATOR, VALUE, EOLN, QUIT, HELP, EOF
}

// Class to hold the Token information
class Token{
  private TokenType type;
  private char      op;       // using '$' as undefined/error
  private int       val;      // using -999 as undefined/error
  
  // Default to initialize to the ERROR TokenType
  public Token(){
    type = TokenType.ERROR;
    op = '$'; 
    val = -999;
  }
  
  // Initialize to a specific TokenType
  public Token (TokenType t){
    type = t;
    op = '$'; 
    val = -999;
  }
  
  // Set to a specific TokenType
  public void setToType(TokenType t){
    type = t;
    op = '$'; 
    val = -999;
  }
  
  // Set to a OPERATOR TokenType with specific operator value
  public void setToOperator(char c){
    type = TokenType.OPERATOR;
    op = c; 
    val = -999;
  }
  
  // Set to a VALUE TokenType with a specific numeric value
  public void setToValue(int v){
    type = TokenType.VALUE;
    op = '$'; 
    val = v;
  }
  
  // return true if the Current Token is of the given TokenType
  public boolean equalsType(TokenType t){
    if (type == t)
      return true;
    else
      return false;
  }
  
  // return true if the Current Token is of the OPERATOR TokenType
  //     and contains the given operator character
  public boolean equalsOperator(char c){
    if (type == TokenType.OPERATOR && op == c)
      return true;
    else
      return false;
  }
  
  // Return the Operator for the current Token
  //   verify the current Token is of the OPERATOR TokenType
  public char getOperator (){
    if (type == TokenType.OPERATOR)
      return op;
    else
      return '$';   // using $ to indicate an error value
  }
  
  // Return the Value for the current Token
  //   verify the current token is of the value TokenType
  public int getValue(){
    if (type == TokenType.VALUE)
      return val;
    else
      return -999;  // using -999 to indicate an error value
  }
}