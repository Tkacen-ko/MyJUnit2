package junit;

import junit.Annotations.*;

public class MyFavoriteTest {

    @TkachBeforeAll
    public void beforeAllMethod(){
        System.out.println("\n!BeforeAll Work!\n");
    }

    @TkachBefore
    public void beforeMethod(){
        System.out.println("-!Before Work!-");
    }

    @TkachAfter
    public void afterMethod(){
        System.out.println("-@!After Work!@-\n");
    }

    @TkachAfterAll
    public void afterAllMethod(){
        System.out.println("-!!!After All Work!!!-");
    }

    @TkachAfterAll
    public void displayNameMethod(){}

    @TkachTest
    public void test1(){}

    @TkachTest
    public void test2(){
        throw new RuntimeException();
    }

    @TkachTest
    public void test3(){}

    @TkachDisplayName(testName = "Тест4")
    @TkachTest
    public void test4(){}

    @TkachTest
    public void test5(){
        throw new RuntimeException();
    }

    public void noTestMethod(){}
}
