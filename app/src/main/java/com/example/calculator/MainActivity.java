package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.R;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView inputtxt, outputtxt;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnc, btn00,
            btneq, btndot, btnbracket, btndel, btnplus, btnminus, btndiv, btnmul;
    double lastnumber = 0;
    double firstnumber = 0;
    String data = null;
    boolean bracketcheck = false;
    boolean dot = true;
    boolean operator = false;
    String status = null;
    boolean delete = true;
    boolean equalControl=true;
    String history, currentrsult;
    DecimalFormat myformator = new DecimalFormat("#####.#####");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        outputtxt = findViewById(R.id.textView1);
        inputtxt = findViewById(R.id.editText);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btn00 = findViewById(R.id.btn00);
        btnc = findViewById(R.id.btncancel);
        btneq = findViewById(R.id.btneq);
        btndot = findViewById(R.id.btndot);
        btnbracket = findViewById(R.id.btnb);
        btndel = findViewById(R.id.btndel);
        btnplus = findViewById(R.id.btnadd);
        btnminus = findViewById(R.id.btnsub);
        btndiv = findViewById(R.id.btndiv);
        btnmul = findViewById(R.id.btnmul);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });
        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("00");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                numberClick("9");
            }
        });
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = outputtxt.getText().toString();
                currentrsult = inputtxt.getText().toString();
                outputtxt.setText(history + currentrsult + "+");
                if (operator) {
                    if (status == "multiplication") {
                        multiply();
                    } else if (status == "division") {
                        div();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        plus();
                    }
                }
                status = "sum";
                operator = false;
                data = null;

            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = outputtxt.getText().toString();
                currentrsult = inputtxt.getText().toString();
                outputtxt.setText(history + currentrsult + "-");
                if (operator) {
                    if (status == "multiplication") {
                        multiply();
                    } else if (status == "division") {
                        div();
                    } else if (status == "sum") {
                        plus();
                    } else {
                        minus();
                    }
                }
                status = "subtraction";
                operator = false;
                data = null;
            }
        });
        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = outputtxt.getText().toString();
                currentrsult = inputtxt.getText().toString();
                outputtxt.setText(history + currentrsult + " x ");
                if (operator) {
                    if (status == "sum") {
                        plus();
                    } else if (status == "division") {
                        div();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        multiply();
                    }
                }
                status = "multiplication";
                operator = false;
                data = null;
            }
        });
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = outputtxt.getText().toString();
                currentrsult = inputtxt.getText().toString();
                outputtxt.setText(history + currentrsult + "/");
                if (operator) {
                    if (status == "multiplication") {
                        multiply();
                    } else if (status == "sum") {
                        plus();
                    } else if (status == "subtraction") {
                        minus();
                    } else {
                        div();
                    }
                }
                status = "division";
                operator = false;
                data = null;
            }
        });


        btndot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dot) {
                    if (data == null) {
                        data = "0.";
                    } else {
                        data = data + ".";
                    }
                }
                inputtxt.setText(data);
                dot = false;

            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (delete) {
                    inputtxt.setText("0");
                } else {
                    data = data.substring(0, data.length() - 1);
                    if (data.length() == 0) {
                        btndel.setClickable(false);
                    }
                    inputtxt.setText(data);

                }
            }
        });

        btnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data = null;
                status = null;
                inputtxt.setText("0");
                outputtxt.setText("");
                firstnumber = 0;
                lastnumber = 0;
                dot = true;
                delete = true;
            }
        });
       btneq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (operator) {
                    if (status == "sum") {
                        plus();
                    } else if (status == "subtraction") {
                        minus();
                    } else if (status == "multiplication") {
                        multiply();
                    } else if (status == "division") {
                        div();
                    } else {
                        firstnumber = Double.parseDouble(inputtxt.getText().toString());


                    }

                }
                operator = false;
                equalControl=true;
            }
        });
    }
    public void numberClick(String view) {
        if (data == null) {
            data = view;
        }
        else if (equalControl){
            firstnumber = 0;
        lastnumber = 0;
        data = view;
       }
        else
        {
            data = data + view;
        }
        inputtxt.setText(data);

        operator = true;
        equalControl=false;
    }

    public void plus() {
        lastnumber = Double.parseDouble(inputtxt.getText().toString());
        firstnumber = firstnumber + lastnumber;
        inputtxt.setText(myformator.format(firstnumber));
        dot = true;
    }

    public void minus() {
        if (firstnumber == 0) {
            firstnumber = Double.parseDouble(inputtxt.getText().toString());
        } else {
            lastnumber = Double.parseDouble(inputtxt.getText().toString());
            firstnumber = firstnumber - lastnumber;
        }
        inputtxt.setText(myformator.format(firstnumber));
        dot = true;
    }

    public void multiply() {
        if (firstnumber == 0) {
            firstnumber = 1;
            lastnumber = Double.parseDouble(inputtxt.getText().toString());
            firstnumber = firstnumber * lastnumber;

        } else {
            lastnumber = Double.parseDouble(inputtxt.getText().toString());
            firstnumber = firstnumber * lastnumber;
        }
        inputtxt.setText(myformator.format(firstnumber));
         dot = true;
    }

    public void div() {
        if (firstnumber == 0) {
            lastnumber = Double.parseDouble(inputtxt.getText().toString());
            firstnumber = firstnumber / 1;
        } else {
            lastnumber = Double.parseDouble(inputtxt.getText().toString());
            firstnumber = firstnumber / lastnumber;
        }
        inputtxt.setText(myformator.format(firstnumber));
        dot = true;

    }
}