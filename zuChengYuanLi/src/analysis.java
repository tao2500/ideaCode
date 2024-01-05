import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * ClassName:test
 * Package:PACKAGE_NAME
 * Description:
 *
 * @Date:2023/6/4 15:16
 * @Author:2500594037@qq.com
 */
public class analysis {
    public void analysis(String path) {
        String text = readTxtFile(path);
        // 错误消息队列
        Queue<String> queue = new LinkedList<>();
        // 优先级运算栈
        Stack<String> stack = new Stack<>();
        // 已经遍历字符个数
        int count = 0;
        // 记录当前是遍历的第几行
        int row = 1;
        while (count < text.length()) {
            char s = text.charAt(count);
            // 除去表达式中的空格
            if (s == ' '){
                count++;
            } else if (s == '\n') {
                row++;
                count++;
            } else if (IsNum(s)) {
                StringBuilder str = new StringBuilder();
                str.append(text.charAt(count++));
                while(count < text.length() && (IsNum(text.charAt(count)) || text.charAt(count) == '.')) {
                    str.append(text.charAt(count++));
                }
                String num = str.toString();
                // 判断是否是小数
                if (num.contains(".")){
                    System.out.println("(8," + num + "," + num + ",double)");
                }else {
                    System.out.println("(5," + num + "," + num + ",int)");
                }
            } else if (IsJiefu(s)) {
                if (IsSuanshuyunsuanfu(s)) {
                    if (IsSuanshuyunsuanfu(text.charAt(count - 1)) || text.charAt(count - 1) == '\n') {
                        String err;
                        if (text.charAt(count - 1) == '\n') {
                            err = "ERROR" + queue.size() + ": 第" + row + "行 首字符是非法字符" + s;
                        }else {
                            err = "ERROR" + queue.size() + ": 第" + row + "行" + text.charAt(count - 1) + "后面存在非法字符" + s;
                        }
                        count++;
                        queue.offer(err);
                        continue;
                    }
                    switch (s) {
                        case '+' :
                            System.out.println("(1," + s + ",NULL,NULL)");
                            break;
                        case '-' :
                            System.out.println("(2," + s + ",NULL,NULL)");
                            break;
                        case '*' :
                            System.out.println("(3," + s + ",NULL,NULL)");
                            break;
                        case '/' :
                            System.out.println("(4," + s + ",NULL,NULL)");
                    }
                }else {
                    switch (s) {
                        case '(' :
                            String err = count == 0 ? ": 第" + row + "行 首字符是非法字符" + s : ": 第" + row + "行" + text.charAt(count - 1) + "后面存在非法字符" + s;
                            if (count > 0 && text.charAt(count - 1) == ')'){
                                count++;
                                queue.offer(err);
                                continue;
                            }
                            stack.push(err);
                            System.out.println("(6," + s + ",NULL,NULL)");
                            break;
                        case ')' :
                            if (stack.size() == 0 || text.charAt(count - 1) == '('){
                                err = "ERROR" + queue.size() + ": 第" + row + "行" + text.charAt(count - 1) + "后面存在非法字符" + s;
                                count++;
                                queue.offer(err);
                                continue;
                            }
                            stack.pop();
                            System.out.println("(7," + s + ",NULL,NULL)");
                    }
                }
                count++;
            } else {
                // ERROR
                String err = "ERROR" + queue.size() + ": 第" + row + "行" + text.charAt(count - 1) + "后面存在非法字符" + s;
                count++;
                queue.offer(err);
            }

        }
        while(!stack.empty()) {
            queue.offer("ERROR" + queue.size() + stack.pop());
        }
        if (queue.size() > 0) System.out.println("ERROR:");
        while (queue.size() > 0) {
            System.out.println(queue.poll());
        }
    }

    //界符表
    public static Set<Character> JiefuTable = new HashSet<>();
    static {
        Character[] Jiefu = {
                '(', ')', '+', '-', '*', '/'
        };
        JiefuTable.addAll(Arrays.asList(Jiefu));
    }
    //判断是否是数字
    public boolean IsNum(char x) { return x >= '0' && x <= '9'; }
    //判断是否是界符
    public boolean IsJiefu(char x)
    {
        return JiefuTable.contains(x);
    }
    //判断是否是算数运算符
    public static boolean IsSuanshuyunsuanfu(char x)
    {
        return x == '+' || x == '-' || x == '*' || x == '/';
    }

    public String readTxtFile(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt;
                // 是否初次运行
                int state = 0;
                while ((lineTxt = br.readLine()) != null) {
                    // 如果不是首行则添加换行符
                    if (state != 0) content += "\n";
                    content += lineTxt;
                    state ++;
                }
                br.close();
            } else {
                System.out.println("文件不存在!");
                return null;
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
            return null;
        }
        return content;
    }
}
