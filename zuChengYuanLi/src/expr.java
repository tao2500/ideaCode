/**
 * ClassName:lexical
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Date:2023/6/3 14:16
 * @Author:2500594037@qq.com
 */

import java.util.Set;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class expr {
    //界符表(12)
    public static Set<Character> JiefuTable = new HashSet<>();
    //常数表
    public static Set<String> DigitBTable = new HashSet<>();


    static {
        Character[] Jiefu = {
                '(', ')', '+', '-', '*', '/'
        };
        JiefuTable.addAll(Arrays.asList(Jiefu));
    }

    //判断是否是：数字
    public static boolean IsNum(char x) { return x >= '0' && x <= '9'; }

    //判断是否是：界符
    public static boolean IsJiefu(char x)
    {
        return JiefuTable.contains(x);
    }

    //判断是否是 算数运算符：加减乘
    public static boolean IsSuanshuyunsuanfu(char x)
    {
        return x == '+' || x == '-' || x == '*' || x == '/';
    }

    public static void read_write_File () throws IOException {
        FileReader reader = new FileReader("./text/TEST1.txt");
        BufferedReader bReader = new BufferedReader(reader);


        String content= "";
        boolean flag = false;

        //readLine一行一行的读取
        while((content = bReader.readLine()) != null) {
            int count = 0;
            while (flag) {
                while (count < content.length()) {
                    if(content.charAt(count)=='*') {
                        count++;

                        if(count< content.length() && content.charAt(count)=='/') {
                            count++;
                            flag = false;
                        }
                    }
                    else count++;
                }
                break;
            }
            // 遍历每一个字符
            while (count < content.length()) {
                if (content.charAt(count) == ' ') {
                    count++;
                } else if(IsSuanshuyunsuanfu(content.charAt(count))) {
                    String str = "";
                    str += content.charAt(count++);
                    System.out.println(str);
                } else if(IsNum(content.charAt(count))) {
                    String str = "";
                    str += content.charAt(count++);
                    while(count < content.length() && IsNum(content.charAt(count))) {
                        str += content.charAt(count++);
                    }
                    DigitBTable.add(str);
                    System.out.println(str);
                } else if(content.charAt(count)=='/') {
                    String str = "";
                    str += content.charAt(count);
                    count++;
                    if(content.charAt(count)=='/') {
                        break;
                    }
                    if(content.charAt(count)=='*') {
                        count++;
                        flag = true;
                        while (count < content.length()) {
                            if(content.charAt(count)=='*') {
                                count++;

                                if(count< content.length() && content.charAt(count)=='/') {
                                    count++;
                                    flag = false;
                                }
                            }
                            else count++;
                        }
                    }
                }

                else {
                    String str = "";
                    str += content.charAt(count);
                    count++;
                    System.out.println(str);
                }
            }

        }
        reader.close();
        bReader.close();
    }

    public static void main(String[] args) throws IOException {
        read_write_File();
    }
}
