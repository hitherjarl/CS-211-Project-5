/*DESCRIPTION:

*/

public class Node{
  Token tok;
  Node next;
  
  public Node(Token t){
    tok = t;
    next = null;
  }
}
//  /* initializing */
//  private Token t;
//  private Node next = null;
//  
//  //constructor
//  public void Node(Token tok){
//    this.t = tok;
//  }
//  
//  public void setNext(Node next){
//    this.next = next;
//  }
//  
//  public Node getNextNode(){
//    return next;
//  }
//  
//  public void setElement(Token t){
//    this.t = t;
//  }
//  
//  public Token getElement(){
//    Token element = this.t;
//    return element;
//  }