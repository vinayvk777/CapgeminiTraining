package groupone;
import groupone.*;
import   org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import   org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
public class  AppTest
{
 int  a=200;int b=30;
  
 @BeforeEach
 public  void init()
 {
	 System.out.println("Runs before every test case");
	  a=200; b=30;
 }
@Test
public  void test() {
	    
	   Assertions.assertTrue(Greater.m1(a, b));
	   System.out.println("hiiiii");
}
@Test
public  void test1() {
	    
	   Assertions.assertTrue(Greater.even(70));
	   System.out.println("helloooooo");
}
@AfterEach
public  void destroy()
{
	 System.out.println("Runs after every test case");

}

}