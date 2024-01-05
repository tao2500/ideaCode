import java.io.*;
import java.util.Scanner;

/**
 * ClassName:main
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Date:2023/6/4 15:14
 * @Author:2500594037@qq.com
 */
public class main {
    public static void main(String[] args) {
        System.out.println("LIUJINTAO / Class 3 / 202010098231");
        Scanner sc = new Scanner(System.in);
        System.out.print( "Please enter a file name : " );
        String fileName = sc.next();
//        ./text/TEST1.txt
//        ./text/TEST2.txt
//        ./text/TEST3.txt
        analysis ana = new analysis();
        ana.analysis(fileName);
    }
}
