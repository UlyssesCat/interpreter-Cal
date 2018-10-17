package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;

import static sample.Lex.lexAnalysis;

public class Controller {
    @FXML
    TextArea Text_input;
    @FXML
    TextField Text_output;
    @FXML
    private void btn_0_click(ActionEvent e){
        Text_input.appendText("0");
    }
    @FXML
    private void btn_1_click(ActionEvent e){
        Text_input.appendText("1");
    }
    @FXML
    private void btn_2_click(ActionEvent e){
        Text_input.appendText("2");
    }
    @FXML
    private void btn_3_click(ActionEvent e){
        Text_input.appendText("3");
    }
    @FXML
    private void btn_4_click(ActionEvent e){
        Text_input.appendText("4");
    }
    @FXML
    private void btn_5_click(ActionEvent e){
        Text_input.appendText("5");
    }
    @FXML
    private void btn_6_click(ActionEvent e){
        Text_input.appendText("6");
    }
    @FXML
    private void btn_7_click(ActionEvent e){
        Text_input.appendText("7");
    }
    @FXML
    private void btn_8_click(ActionEvent e){
        Text_input.appendText("8");
    }
    @FXML
    private void btn_9_click(ActionEvent e){
        Text_input.appendText("9");
    }
    @FXML
    private void left_click(ActionEvent e){
        Text_input.appendText("(");
    }
    @FXML
    private void right_click(ActionEvent e){
        Text_input.appendText(")");
    }
    @FXML
    private void plus_click(ActionEvent e){ Text_input.appendText("+"); }
    @FXML
    private void minus_click(ActionEvent e){
        Text_input.appendText("-");
    }
    @FXML
    private void mul_click(ActionEvent e){
        Text_input.appendText("*");
    }
    @FXML
    private void div_click(ActionEvent e){ Text_input.appendText("/");}//Text_input.getText();
    @FXML
    private void btn_dot_click(ActionEvent e){ Text_input.appendText("."); }
    @FXML
    private void btn_clear_click(ActionEvent e){ Text_input.clear(); }
    @FXML
    private void btn_delete_click(ActionEvent e){ Text_input.setText(Text_input.getText(0,Text_input.getLength()-1)); }
    @FXML
    private void btn_equals_click(ActionEvent e){ Text_output.setText(Interpreter(Text_input.getText())); }

    private String Interpreter(String input){
        ArrayList<token> tokenList = new ArrayList<>();
        tokenList = lexAnalysis(input);
        Grammar g = new Grammar(tokenList);
        String output = g.gramAnalysis();
        return output;
    }
}
