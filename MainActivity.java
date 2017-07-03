package com.example.zhuxiaolin.audiofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.puredata.android.io.PdAudio;
import org.puredata.android.utils.PdUiDispatcher;
import org.puredata.core.PdBase;
import org.puredata.core.utils.IoUtils;
import org.puredata.core.utils.PdDispatcher;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    //----------控件的定义-----------------／／
    private SeekBar seekBarAudio;
    private TextView textViewAudio;
    private ToggleButton toggleButton,btnCenter, btnNext,btnBox;
//    private Button btnCenter, btnNext,btnBox;
    private Button btnC, btnD, btnE,btnF,btnG,btnA,btnB;
    private Button btnCup, btnDup,btnFup,btnGup,btnAup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            initPd();
        } catch (IOException e) {
            e.printStackTrace();//
        }
        //----------通过控件的id实现，界面的控件与对象的绑定----------------／／
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        btnCenter = (ToggleButton) findViewById(R.id.btnCenter);
        btnNext = (ToggleButton) findViewById(R.id.btnNext);
        btnBox = (ToggleButton) findViewById(R.id.btnBox);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        btnE = (Button) findViewById(R.id.btnE);
        btnF = (Button) findViewById(R.id.btnF);
        btnG = (Button) findViewById(R.id.btnG);
        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnCup = (Button) findViewById(R.id.btnCup);
        btnDup = (Button) findViewById(R.id.btnDup);
        btnFup = (Button) findViewById(R.id.btnFup);
        btnGup = (Button) findViewById(R.id.btnGup);
        btnAup = (Button) findViewById(R.id.btnAup);
        seekBarAudio = (SeekBar) findViewById(R.id.seekBarAudio);
        textViewAudio = (TextView) findViewById(R.id.textViewAudio);

        //------------SeekBar改变时----------------//
        seekBarAudio.setOnSeekBarChangeListener(this);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    PdAudio.startAudio(MainActivity.this); //ToggleButton ON
                else
                    PdAudio.stopAudio();//ToggleButton OFF
            }
        });
        btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("trigger"); //点击中央C区，在当前八度
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("modu"); //点击高八度，前往下一个八度
            }
        });
        btnBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("box"); //点击八音盒C4
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("C"); //点击C，  播放 1
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("D"); //点击D，播放 2
            }
        });
        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("E"); //点击E，播放 3
            }
        });
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("F"); //点击F，播放 4
            }
        });
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("G"); //点击G，播放 5
            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("A"); //点击A，播放 6
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("B"); //点击B，播放 7
            }
        });
        btnCup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("Cup"); //点击Cup，播放 1#
            }
        });
        btnDup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("Dup"); //点击Dup，播放 2#
            }
        });
        btnFup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("Fup"); //点击Fup，播放 4#
            }
        });
        btnGup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("Gup"); //点击Gup，播放 5#
            }
        });
        btnAup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdBase.sendBang("Aup"); //点击Aup，播放 6#
            }
        });
    }
    private void initPd() throws IOException {
        int sampleRate = 44100;
        int outChans = 2;
        int ticks = 16;
        PdBase.openAudio(0, outChans, (int) sampleRate);
        PdBase.computeAudio(true);
        File dir = getFilesDir();
        IoUtils.extractZipResource(getResources().openRawResource(R.raw.a), dir, true);
        File patchFile = new File(dir, "aaa.pd");
        PdBase.openPatch(patchFile);
        PdAudio.initAudio(sampleRate, 0, outChans, ticks, true);
        PdDispatcher dispatcher = new PdUiDispatcher();
        PdBase.setReceiver(dispatcher);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekBarAudio)
        {
            PdBase.sendFloat("audio", progress / 20.0f);  //安卓范围0～100；pd范围0～5

            textViewAudio.setText("音量:" + Float.toString(progress / 20.0f));
        }
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
