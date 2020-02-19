package groupone;
import java.util.Scanner;

public class Greater {
	public static boolean m1(int a, int b)
	{
		if(a>b)
		{
			return true;
		}
		else
		{
			return false;
			
		}
	}
		public static  boolean even(int i)
		{
			 if(i%2==0)
			 {
				 return true;
				 
				 
			 }
			 else
			 {
				  return false;
			 }
		}
	
 public static void main(String[] args) {
	 int a,b;
	Scanner sc=new Scanner(System.in);
	a=sc.nextInt();
	b=sc.nextInt();
	System.out.println(m1(a,b));
	 }

}
