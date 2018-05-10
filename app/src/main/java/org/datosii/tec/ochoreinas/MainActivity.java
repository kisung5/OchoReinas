package org.datosii.tec.ochoreinas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    boolean runnning = false;
    Button button;
    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button1);
        mainView = (MainView) findViewById(R.id.mainview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!runnning) {
                    start();
                    button.setText("Stop");
                    runnning = true;
                } else if (runnning) {
                    runnning = false;
                    stop();
                    button.setText("Start");
                }
            }
        });
    }

    private void start () {
        mainView.setRun(true);
    }

    private void stop() {
        mainView.setRun(false);
    }
}
