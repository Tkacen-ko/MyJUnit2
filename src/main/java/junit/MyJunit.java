package junit;

import junit.Annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MyJunit {
    private final Object objTestClass;
    private int passedTests = 0;
    private int failedTests = 0;
    private List<Method> beforeAllMethods = new ArrayList<>();
    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();
    private List<Method> afterAllMethods = new ArrayList<>();
    private List<Method> displayNameMethods = new ArrayList<>();
    private List<Method> testMethods = new ArrayList<>();


    @SneakyThrows
    public void start() {
        fillAllMethodsGroup();

        for (Method method : beforeAllMethods){
            method.invoke(objTestClass);
        }
        for (Method method : testMethods) {
            if (method.isAnnotationPresent(TkachTest.class)) {
                if (beforeMethods.size()!= 0){
                    for (Method methodBefore : beforeMethods){
                        methodBefore.invoke(objTestClass);
                    }
                }
                if (displayNameMethods.size()!= 0 && method.isAnnotationPresent(TkachDisplayName.class)) {
                    for (Method methodDisplayName : displayNameMethods) {
                        System.out.println("Название теста на русском языке: " + methodDisplayName.getAnnotation(TkachDisplayName.class).testName());
                    }
                }
                try {
                    method.invoke(objTestClass);
                    System.out.println(method.getName() + ": PASSED!");
                    passedTests++;
                } catch (Exception e) {
                    System.out.println(method.getName() + ": FAILED!");
                    failedTests++;
                }
                if (afterMethods.size()!=0){
                    for (Method methodAfter :afterMethods){
                        methodAfter.invoke(objTestClass);
                    }
                }
            }
        }
        for (Method method : afterAllMethods){
            method.invoke(objTestClass);
        }
        printResults();
    }

    private void fillAllMethodsGroup(){
        for(Method method : objTestClass.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(TkachBeforeAll.class)){
                beforeAllMethods.add(method);
            }
            else if(method.isAnnotationPresent(TkachTest.class)){
                testMethods.add(method);
            }
            else if(method.isAnnotationPresent(TkachBefore.class)){
                beforeMethods.add(method);
            }
            else if(method.isAnnotationPresent(TkachAfter.class)){
                afterMethods.add(method);
            }
            else if(method.isAnnotationPresent(TkachAfterAll.class)){
                afterAllMethods.add(method);
            }

            if(method.isAnnotationPresent(TkachDisplayName.class)){
                displayNameMethods.add(method);
            }
        }
    }
    private void printResults() {
        System.out.println("\n=============================================");
        System.out.println("Количество упавших тестов: " + failedTests);
        System.out.println("Количество пройденных тестов: " + passedTests);
        System.out.println("=============================================");
    }
}
