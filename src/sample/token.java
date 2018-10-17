package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class token {
    public token(String value){
        this.s=value;
        foo(value);
    }
    String s;//token变量的值
    int i;//token的类型的种别码
    String name;//token类型的名称    如 s ""7777""   i 1   name "NUMBER"

    public void foo(String value) {
        switch (value) {
            case "+":
                this.i = 2;
                this.name = "PLUS";
                break;
            case "-":
                this.i = 3;
                this.name = "MINUS";
                break;
            case "*":
                this.i = 4;
                this.name = "MUL";
                break;
            case "/":
                this.i = 5;
                this.name = "DIV";
                break;
            case "(":
                this.i = 6;
                this.name = "LPARENT";
                break;
            case ")":
                this.i = 7;
                this.name = "RPARENT";
                break;
            case ";":
                this.i = 8;
                this.name = "SEMICOLON";
                break;
            default:
                this.i = 1;
                this.name = "NUMBER";
        }
    }

}
