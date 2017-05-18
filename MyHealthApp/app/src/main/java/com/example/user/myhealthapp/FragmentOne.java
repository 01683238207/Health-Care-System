package com.example.user.myhealthapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentOne extends Fragment {
    LinearLayout linearLayout;
    EditText editText;
    Button btnDb1;
    View v;
    OnClickInterface onClickInterface;
    int pos, subpos;
    OptainQuestions questions;
    float weight = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        btnDb1 = (Button) v.findViewById(R.id.btnDb1);
        linearLayout = (LinearLayout) v.findViewById(R.id.subqus);
        editText = (EditText) v.findViewById(R.id.subQusAns);
        final MyDataBaseHelper myDb = new MyDataBaseHelper(getContext());
        questions = new OptainQuestions(getContext());
        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.group);
        ((TextView) v.findViewById(R.id.question)).setText(OptainQuestions.getQuestion(pos));
        editText.setHint(OptainQuestions.getHints(pos));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.but1:
                        ((TextView) v.findViewById(R.id.subQus)).setText(OptainQuestions.subgetQuestion(pos, subpos));
                        linearLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.but2:
                        ((TextView) v.findViewById(R.id.subQus)).setText("");
                        linearLayout.setVisibility(View.INVISIBLE);
                        weight = 0;
                        break;
                }
            }
        });
        btnDb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                long inserted = myDb.insertData(s, pos - 1);
                if (myDb.getCountOfRow() > pos) {
                    if (weight == -1)
                        switch (pos) {
                            case 1:
                                if (Integer.parseInt(s)>=1 && Integer.parseInt(s)<=2) {
                                    weight =1;
                                } else if (Integer.parseInt(s)>=3 && Integer.parseInt(s)<=5) {
                                    weight =2;
                                } else if (Integer.parseInt(s)>=6) {
                                    weight =3;
                                }
                                break;
                            case 2:
                                String data[] = s.split("-");
                                if (Integer.parseInt(data[0])<120 && Integer.parseInt(data[1])<80) {
                                    weight =0;
                                } else if ((Integer.parseInt(data[0])>=120 && Integer.parseInt(data[0])<=139) && (Integer.parseInt(data[1])>=80 && Integer.parseInt(data[1])<=89)) {
                                    weight =2;
                                }else if ((Integer.parseInt(data[0])>=140 && Integer.parseInt(data[0])<=159) && (Integer.parseInt(data[1])>=90 && Integer.parseInt(data[1])<=99)) {
                                    weight =2.5f;
                                }
                                else if (Integer.parseInt(data[0])>=160 && Integer.parseInt(data[1])>=100) {
                                    weight =3;
                                }
                                else {
                                    weight =3.5f;
                                }
                                break;
                            case 3:
                                String sugarData[] = s.split("-");
                                if ((Integer.parseInt(sugarData[0]) >= 70 && Integer.parseInt(sugarData[0]) <= 99) && Integer.parseInt(sugarData[1])<140) {
                                    weight = 0;
                                } else if ((Integer.parseInt(sugarData[0]) >= 80 && Integer.parseInt(sugarData[0]) <= 130) && Integer.parseInt(sugarData[1])<180) {
                                    weight = 1;
                                } else if ((Integer.parseInt(sugarData[0]) >= 160 && Integer.parseInt(sugarData[0]) <= 240) && Integer.parseInt(sugarData[1])<300) {
                                    weight = 2;
                                } else {
                                    weight = 3;
                                }
                                break;
                            case 4:
                                if (Float.parseFloat(s)>=97.5 && Float.parseFloat(s)<99.5)
                                    weight = 0;
                                else if (Float.parseFloat(s) < 95) {
                                    weight = 1.5f;
                                } else if (Float.parseFloat(s) >=99.5 && Float.parseFloat(s) <= 104) {
                                    weight = 2;
                                } else {
                                    weight = 3;
                                }
                                break;
                            case 5:
                                if (Integer.parseInt(s) >= 60 && Integer.parseInt(s) <= 100)
                                    weight = 0;
                                else if (Integer.parseInt(s) < 60) {
                                    weight = 1;
                                } else if (Integer.parseInt(s) > 100) {
                                    weight = 2;
                                }
                                break;
                        }
                    if (onClickInterface != null)
                        onClickInterface.clicked(weight);
                } else {
                    onClickInterface.doneOperation();
                }


            }
        });
        return v;
    }

    void setInterface(OnClickInterface onClickInterface) {
        this.onClickInterface = onClickInterface;
    }

    void setPositionAndSubPosition(int pos, int subpos) {
        this.pos = pos;
        this.subpos = subpos;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    interface OnClickInterface {
        void clicked(float weight);

        void doneOperation();
    }

}
