public class LinkList {
  //data members of the class (set to private)
  private Node head; //= null;
  
  public LinkList(){
    head = null;
  }
  
  /* return 1 if stack is empty */
  public int isEmpty(){
    if(head == null){
      return 1;
    }
    else{
      return 0;
    }
  }
  
  /* add the data to the top of the stack */
  public void push(Token t){
    Node tmp = new Node(t);
    //tmp.t = t;
    tmp.next = head;
    head = tmp;
  }
  
  /* return the data value on the top of the stack */
  public Token top(){
    if(head != null){
      return head.tok;
    }
    else{
      return null;
    }
  }
  
  /* remove the data value from the top of the stack */
  public void pop(){
    if(head != null){
      head = head.next;
      //System.out.print("\tPOP SUCCESS\n");
    }
    else{
      System.out.print("\tSTACK IS EMPTY\n");
    }
  }
}


