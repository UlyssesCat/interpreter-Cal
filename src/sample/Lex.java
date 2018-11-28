package sample;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lex {

    public static ArrayList<token> lexAnalysis(String input){
        //Scanner scanner = new Scanner(System.in);
        //String s= scanner.nextLine();
        char[] arr = input.toCharArray();
        ArrayList<String> arrayList = new ArrayList<String>();
        int left=0;
        int right=0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i<arr.length; i++)
        {
            if(i==0&&(arr[i]!='('&&arr[i]!='-'&&(!isNumeric(String.valueOf(arr[i]))))){
                System.out.println("开头应为数字或左括号"+",position: "+i+"\n");
                Controller.er+=("开头应为数字或左括号"+",position: "+i);
                //System.exit(-1);
            }
            if(arr[i]=='.'||'0'<=arr[i]&&arr[i]<='9')
            {
                if(arr[i]=='.')
                {
                    if(!isNumeric(String.valueOf(arr[i-1])))
                    {
                        Controller.er+=("表达式格式错误"+"position:"+i+"\n");
                        System.out.println("表达式格式错误"+"position:"+i);
                        //System.exit(-1);
                    }
                    if(i==arr.length-1||!isNumeric(String.valueOf(arr[i+1])))
                    {
                        Controller.er+=("表达式格式错误"+"position:"+i+"\n");
                        System.out.println("表达式格式错误"+"position:"+i);
                        //System.exit(-1);
                    }

                }
                sb.append(arr[i]);

                if(i==arr.length-1) arrayList.add(sb.toString());
            }
            else if(arr[i]=='+'||arr[i]=='-'||arr[i]=='*'||arr[i]=='/'||arr[i]=='('||arr[i]==')'||arr[i]==';')
            {
                if(sb.length()!=0)
                    arrayList.add(sb.toString());
                sb.delete(0,sb.length());
                if(arr[i]=='+')
                {
                    if(i==arr.length-1)
                    {
                        Controller.er+=("缺少操作数 "+",position: "+(i)+"\n");
                        System.out.println("缺少操作数 "+",position: "+(i));
                        //System.exit(-1);
                    }
                    if((!isNumeric(String.valueOf(arr[i+1])))&&(arr[i+1]!='('))
                    {
                        Controller.er+=("操作符后应为数字或表达式 "+",position: "+(i+1)+"\n");
                        System.out.println("操作符后应为数字或表达式 "+",position: "+(i+1));
                        //System.exit(-1);
                    }

                    arrayList.add(arr[i]+"");
                }
                if(arr[i]=='-')
                {
                    if(i==arr.length-1)
                    {
                        Controller.er+=("缺少操作数 "+",position: "+(i)+"\n");
                        System.out.println("缺少操作数 "+",position: "+(i));
                        //System.exit(-1);
                    }
                    if((!isNumeric(String.valueOf(arr[i+1])))&&(arr[i+1]!='('))
                    {
                        Controller.er+=("操作符后应为数字或表达式"+",position: "+(i+1)+"\n");
                        System.out.println("操作符后应为数字或表达式"+",position: "+(i+1));
                        //System.exit(-1);
                    }
                    if(i==0||arr[i-1]=='(')
                    {
                        arrayList.add("0");
                    }
                    arrayList.add(arr[i]+"");
                }
                if(arr[i]=='*')
                {
                    if(i==arr.length-1)
                    {
                        Controller.er+=("缺少操作数 "+",position: "+(i)+"\n");
                        System.out.println("缺少操作数 "+",position: "+(i));
                        //System.exit(-1);
                    }
                    if((!isNumeric(String.valueOf(arr[i+1])))&&(arr[i+1]!='('))
                    {
                        Controller.er+=("操作符后应为数字或表达式"+",position: "+(i+1)+"\n");
                        System.out.println("操作符后应为数字或表达式"+",position: "+(i+1));
                        //System.exit(-1);
                    }

                    arrayList.add(arr[i]+"");
                }
                if(arr[i]=='/')
                {
                    if(i==arr.length-1)
                    {
                        Controller.er+=("缺少操作数 "+",position: "+(i)+"\n");
                        System.out.println("缺少操作数 "+",position: "+(i));
                        //System.exit(-1);
                    }

                    if((!isNumeric(String.valueOf(arr[i+1])))&&(arr[i+1]!='('))
                    {
                        Controller.er+=("操作符后应为数字或表达式"+",position: "+(i+1)+"\n");
                        System.out.println("操作符后应为数字或表达式"+",position: "+(i+1));
                        //System.exit(-1);
                    }
                    arrayList.add(arr[i]+"");
                }
                if(arr[i]=='(')
                {
                    left++;
                    arrayList.add(arr[i]+"");
                }
                if(arr[i]==')')
                {
                    right++;
                    arrayList.add(arr[i]+"");
                }
                if(arr[i]==';')
                {
                    arrayList.add(arr[i]+"");
                }
            }
            else
            {
                Controller.er+=("表达式含有非法字符"+",position: "+i+"\n");
                System.out.println("表达式含有非法字符"+",position: "+i);
                //System.exit(0);
            }
        }

        if(left!=right)
        {
            Controller.er+=("括号不匹配\n");
            System.out.println("括号不匹配");
            //System.exit(0);
        }
        ArrayList<token> arrayList1 = new ArrayList<token>();
        for(int i=0;i<arrayList.size();i++)
        {
            String tmp = arrayList.get(i);
            token t = new token(tmp);
            arrayList1.add(t);
        }


        return arrayList1;

    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
    public static boolean isOperator(String str){
        switch (str)
        {
            case "+":
            case "-":
            case "*":
            case "/":
                return true;
            default:
                return false;
        }
    }
    public static int priority(String str){
        switch (str)
        {
            case "#":
                return -1;
            case "(":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }

}
