//Fixed Capacity Stack of Strings
 
import java.util.Scanner;
public class noder
{
 
 private String[] a;  //Stack Entries
 private int N; //Size
 
//Constructor
 
public noder(int cap)
{
 a = new String[cap];
}
 
public boolean isEmpty()
{
 return N==0;
}
 
public int size()
{
 return N;
}
 
public void push (String item)
{
 a[N++] = item;
 /*
 a[N++] is equivalent to
 a[N] = item
 N++  
 //If you are unclear about how that works check operators post in Java
 */
}
 
public String pop ()
{
 return a[--N];
}
 
//Test Client
public static void main(String[] args)
{
 noder s = new noder(100);
 Scanner in = new Scanner(System.in);
 while (in.hasNext())
 {
  String item = in.next();
  
   s.push(item);
  
 System.out.println("(" +s.size() + "left on stack )");
 if (item == 'q'){
   break;}
 
 System.out.println(s.pop() + " ");

 
}
 while (!s.isEmpty()){
   System.out.println(s.pop() + " ");
 }
}
}