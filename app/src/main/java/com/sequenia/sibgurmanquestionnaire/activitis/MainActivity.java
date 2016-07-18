package com.sequenia.sibgurmanquestionnaire.activitis;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.icu.text.SelectFormat;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sequenia.sibgurmanquestionnaire.R;
import com.sequenia.sibgurmanquestionnaire.adapters.QestionAdapter;
import com.sequenia.sibgurmanquestionnaire.controllers.QuestionnaireController;
import com.sequenia.sibgurmanquestionnaire.fragments.SelectedFragment;
import com.sequenia.sibgurmanquestionnaire.fragments.TypeFreeFragment;
import com.sequenia.sibgurmanquestionnaire.fragments.TypeRatingFragment;
import com.sequenia.sibgurmanquestionnaire.fragments.TypeTranscriptFragment;
import com.sequenia.sibgurmanquestionnaire.helpers.DialogsHelper;
import com.sequenia.sibgurmanquestionnaire.helpers.PermissionsChecker;
import com.sequenia.sibgurmanquestionnaire.helpers.QuestionHelper;
import com.sequenia.sibgurmanquestionnaire.helpers.SchemaJSONHelper;
import com.sequenia.sibgurmanquestionnaire.models.Answerd;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeFree;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.AnswerdTypeTranslate;
import com.sequenia.sibgurmanquestionnaire.models.Question;
import com.sequenia.sibgurmanquestionnaire.models.Sample;
import com.sequenia.sibgurmanquestionnaire.models.TypeFree;
import com.sequenia.sibgurmanquestionnaire.models.TypeRaing;
import com.sequenia.sibgurmanquestionnaire.models.TypeSelect;
import com.sequenia.sibgurmanquestionnaire.models.TypeTranslate;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    QuestionnaireController questionnaireController;
    ListView listView;
    Button backward;
    Button forward;
    Button complete;
    TextView name_question;
    DrawerLayout drawerLayout;
    TextView numberQuest;
    ProgressBar progressQuestions;


    ArrayList<Sample>answerdSample;
    ArrayList<Sample>samples;
    ArrayList<TypeFree>typeFrees;
    ArrayList<TypeRaing>typeRaings;
    ArrayList<TypeTranslate>typeTranslates;
    ArrayList<TypeSelect>typeSelects;
    ArrayList<AnswerdTypeRaing>answerdTypeRaings;
    ArrayList<AnswerdTypeFree>answerdTypeFrees;
    ArrayList<AnswerdTypeTranslate>answerdTypeTranslates;
    ArrayList<AnswerdTypeSelect>answerdTypeSelects;
    ArrayList<Answerd>answerds;
    TypeRatingFragment fragment;
    TypeFreeFragment fragmentFree;
    TypeTranscriptFragment fragmentTranscript;
    SelectedFragment selectedFragment;

    private int idQuestion;


    private boolean permissionsAllShown = false; // Если уже показали, то не показывать
    private boolean permissionsOverlayShown = false; // Если уже показали, то не показывать
    private PermissionsChecker checker;
    private ArrayList<Question> questions;
    private String[] PERMISSIONS ={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checker = new PermissionsChecker(getApplicationContext());
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if (!Settings.canDrawOverlays(this)) {
                boolean check = checker.permissionsDenied(PERMISSIONS);
                if (check) {
                    if (!permissionsOverlayShown) {
                        permissionsOverlayShown = true;
                        PermissionsActivity.startActivityForResult(this, DialogsHelper.REQUEST_PERMISSIONS, PERMISSIONS);
                    }
                }
            }
        }



        backward=(Button) findViewById(R.id.backward);
        forward=(Button)findViewById(R.id.forward);
        complete = (Button)findViewById(R.id.complete);

        backward.setOnClickListener(this);
        forward.setOnClickListener(this);
        complete.setOnClickListener(this);

        name_question = (TextView)findViewById(R.id.name_question);
        drawerLayout =(DrawerLayout)findViewById(R.id.draw_layout);
        listView = (ListView)findViewById(R.id.right_drawer);
        numberQuest = (TextView)findViewById(R.id.number_quest);
        progressQuestions = (ProgressBar)findViewById(R.id.progress_questions);


        questionnaireController= new QuestionnaireController(this);
        questionnaireController.loadQuestionnaire();
        questions=questionnaireController.getQuestions();
        samples = questionnaireController.getSamples();
        typeFrees=questionnaireController.getTypeFrees();
        typeRaings=questionnaireController.getTypeRaings();
        typeTranslates = questionnaireController.getTypeTranslates();
        typeSelects=questionnaireController.getTypeSelects();

        answerdSample = questionnaireController.getAnswerSample();
        answerdTypeRaings=questionnaireController.getAnswerdTypeRaings();
        answerdTypeFrees=questionnaireController.getAnswerdTypeFrees();
        answerdTypeTranslates=questionnaireController.getAnswerdTypeTranslates();
        answerdTypeSelects=questionnaireController.getAnswerdTypeSelects();
        answerds=questionnaireController.getAnswerds();



        int countQuestion=0;
        int countRequer=0;
        for (Question question:questions){
            if (!question.isAnswered()){
                countQuestion++;
            }
            if (!question.isRequired()){
                countRequer++;
            }
        }
        if (countRequer>0){
            complete.setEnabled(false);
        }

        complete.setText(getString(R.string.complete,countQuestion));
        numberQuest.setText(idQuestion+1+"/"+questions.size());

        progressQuestions.setMax(questions.size());
        progressQuestions.setProgress(idQuestion+1);
        selectFragment(questions.get(0));

        setupAdapter();


    }




    private void setupAdapter(){
        QestionAdapter adapter = new QestionAdapter(this,getLayoutInflater(),questions);
        listView.setAdapter(adapter);
    }
    private void selectFragment(Question question){
        fTrans = getFragmentManager().beginTransaction();
        if (fragment!=null){
            fTrans.remove(fragment);
        }
        if (fragmentFree!=null){
            fTrans.remove(fragmentFree);
        }
        if (fragmentTranscript!=null){
            fTrans.remove(fragmentTranscript);
        }
        if (selectedFragment!=null){
            fTrans.remove(selectedFragment);
        }

        switch (question.getType()){
            case QuestionHelper.TYPE_RATING:
                for(TypeRaing typeRaing: typeRaings){
                    if (typeRaing.getId()==question.getId()){
                        fragment= TypeRatingFragment.newInstance(question,samples,typeRaing);
                        fTrans.add(R.id.contantFragment,fragment);
                        fTrans.commit();
                    }
                }

                break;
            case QuestionHelper.TYPE_FREE:
                for (TypeFree typeFree: typeFrees){
                    if (typeFree.getId()==question.getId()){
                        fragmentFree= TypeFreeFragment.newInstance(question,samples,typeFree);
                        fTrans.add(R.id.contantFragment,fragmentFree);
                        fTrans.commit();
                    }
                }

                break;
            case QuestionHelper.TYPE_TRANSCRIPT:
                for(TypeTranslate typeTranslate:typeTranslates){
                    if(typeTranslate.getId()==question.getId()){
                        fragmentTranscript = TypeTranscriptFragment.newInstance(question,samples,typeTranslate);
                        fTrans.add(R.id.contantFragment,fragmentTranscript);
                        fTrans.commit();
                    }
                }

                break;
            case QuestionHelper.TYPE_SELECTED:
                for(TypeSelect typeSelect:typeSelects){
                    if (typeSelect.getId()==question.getId()){
                        selectedFragment = SelectedFragment.newInstance(question,samples);
                        fTrans.add(R.id.contantFragment,selectedFragment);
                        fTrans.commit();
                    }
                }

                //fTrans.remove(fragmentFree);


                break;
        }



    }


    private Question forwardQuestion(){
        if (idQuestion<questions.size()-1){
            idQuestion++;
            numberQuest.setText(idQuestion+1+"/"+questions.size());
            progressQuestions.setProgress(idQuestion+1);
            return questions.get(idQuestion);
        }
        return questions.get(idQuestion);
    }
    private Question backwardQuestion(){
        if (idQuestion>0){
            idQuestion--;
            numberQuest.setText(idQuestion+1+"/"+questions.size());
            progressQuestions.setProgress(idQuestion+1);
            return questions.get(idQuestion);
        }
        return questions.get(idQuestion);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backward:
                Question question =backwardQuestion();
                selectFragment(question);
                break;
            case R.id.forward:
                Question question1=forwardQuestion();
                selectFragment(question1);
                break;
            case R.id.complete:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigate_questions:
                if (drawerLayout.isDrawerOpen(GravityCompat.END))
                    drawerLayout.closeDrawer(GravityCompat.END);
                else
                    drawerLayout.openDrawer(GravityCompat.END);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
