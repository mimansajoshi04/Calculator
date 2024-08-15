package com.example.calculatore;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private double firstNum = 0 ;
    private double secondNum = 0 ;
    private String operate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onTextViewClick(View view){
        if(view instanceof TextView){
            TextView text_view = (TextView) view;
            String text = text_view.getText().toString();
            TextView textScreenView = findViewById(R.id.screenView);

            if(textScreenView.getText().toString().equals("inf")){
                textScreenView.setText("");
                return;
            }

            String screenText = textScreenView.getText().toString() + text;
            textScreenView.setText(screenText);
        }
    }

    public void onTextViewOperationClick(View view){
        if(view instanceof TextView){
            TextView text_view = (TextView) view;
            String operation = text_view.getText().toString();

            TextView textScreenView = findViewById(R.id.screenView);
            String  text = textScreenView.getText().toString();

            TextView expression = findViewById(R.id.expressionView);



            if(text.equals("") || text.equals("inf") ){
                textScreenView.setText("");
                expression.setText("");
                this.secondNum = this.firstNum = 0;
                this.operate = "";
                return;
            }

            switch(operation){
                case "AC" :
                    textScreenView.setText("");
                    expression.setText("");
                    break;

                case "DEL":
                    int len = text.length();
                    textScreenView.setText(text.substring(0,len-1));
                    break;

                case "%":
                    double num = Double.parseDouble(text);
                    textScreenView.setText(Double.toString(num/100));
                    break;

                case "+/-":
                    double val = Double.parseDouble(text);
                    textScreenView.setText(Double.toString(val*-1));
                    break;

                case "=":
                    switch(this.operate){
                        case "+":
                            this.secondNum = Double.parseDouble(text);
                            expression.setText(this.firstNum + this.operate + this.secondNum) ;
                            textScreenView.setText(Double.toString(this.firstNum + this.secondNum));
                            this.secondNum = this.firstNum = 0;
                            this.operate = "";
                            break;

                        case "-":
                            this.secondNum = Double.parseDouble(text);
                            expression.setText(this.firstNum + this.operate + this.secondNum );
                            textScreenView.setText(Double.toString(this.firstNum - this.secondNum));
                            this.secondNum = this.firstNum = 0;
                            this.operate = "";
                            break;

                        case "*":
                            this.secondNum = Double.parseDouble(text);
                            expression.setText(this.firstNum + this.operate + this.secondNum );
                            textScreenView.setText(Double.toString(this.firstNum * this.secondNum));
                            this.secondNum = this.firstNum = 0;
                            this.operate = "";
                            break;


                        case "/":

                            this.secondNum = Double.parseDouble(text);
                            if(secondNum == 0){
                                textScreenView.setText("inf");
                            }else {
                                textScreenView.setText(Double.toString(this.firstNum / this.secondNum));
                            }
                            expression.setText(this.firstNum + this.operate + this.secondNum);

                            this.secondNum = this.firstNum = 0;
                            this.operate = "";
                            break;

                        default:
                            textScreenView.setText("");
                            this.secondNum = this.firstNum = 0;
                            this.operate = "";
                    }

                    break;


                default:
                    this.firstNum = Double.parseDouble(text);
                    textScreenView.setText("");
                    expression.setText(text + operation);
                    this.operate = operation;
                    break;
            }
        }
    }
}