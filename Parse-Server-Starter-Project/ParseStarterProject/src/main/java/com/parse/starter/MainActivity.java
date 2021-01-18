/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

  Boolean signUpModeActive=true;
  TextView changeSignUpModeTextView;
  EditText password;

  public void showUserList() {
    Intent intent=new Intent(this,UserListActivity.class);
    startActivity(intent);
  }

  public void signUp(View view){

    EditText username=findViewById(R.id.usernameEditText);


    if(username.getText().toString().equals("")|| password.getText().toString().equals("")) {
      Toast.makeText(this,"An username and a pasword are required",Toast.LENGTH_SHORT).show();
    }else{
      if(signUpModeActive) {

        ParseUser user = new ParseUser();
        user.setUsername(username.getText().toString());
        user.setPassword(password.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
            if (e == null) {
              Log.i("Signup", "Successful");
              showUserList();
            } else {
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      }else {


        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
          @Override
          public void done(ParseUser user, ParseException e) {

            if(user!=null){
              Log.i("Signup","Login Successful");
              showUserList();

            }else{
              Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        });
      }
    }

  }
  



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setTitle("Instagram-ish");

    changeSignUpModeTextView=findViewById(R.id.changeSignupModeTextView);
    changeSignUpModeTextView.setOnClickListener(this);

    ConstraintLayout backgroundConstraintLayout=findViewById(R.id.backgroundConstraintLayout);
    ImageView logoImageView=findViewById(R.id.logoImageView);

    backgroundConstraintLayout.setOnClickListener(this);
    logoImageView.setOnClickListener(this);

    password=findViewById(R.id.passwordEditText);

    password.setOnKeyListener(this);

    if(ParseUser.getCurrentUser()!=null){
      showUserList();
    }


   /* ParseUser.logOut();

if(ParseUser.getCurrentUser()!=null){
  Log.i("currentUser","User logged in " +ParseUser.getCurrentUser().getUsername());
}else{
    Log.i("currentUser","User not logged in");
}*/


   /* ParseUser.logInInBackground("NeculaFlorin", "asdfg", new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException e) {
          if(user!=null){
            Log.i("Login","Successful");
          }else{
            Log.i("Login","Failed"+e.toString());
        }
      }
    });*/

   /* ParseUser user=new ParseUser();
    user.setUsername("FlorinNecula");
    user.setPassword("password");

    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if(e==null){
          Log.i("Sign Up","Successful");
        }else{
          Log.i("Sign Up","Failed");
        }
      }
    });*/

   /* ParseQuery<ParseObject> query=ParseQuery.getQuery("Score");

    query.whereGreaterThan("score",200);
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
          if(e==null && objects!=null) {
              for(ParseObject object:objects){
                object.put("score",object.getInt("score")+50);
                object.saveInBackground();
              }
          }
      }
    });*/

   /* ParseQuery<ParseObject> query=ParseQuery.getQuery("Score");
    query.whereEqualTo("username","tom");
    query.setLimit(1);
    
    query.findInBackground(new FindCallback<ParseObject>() {
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
          if( e== null) {
            Log.i("findInBackground","Retrieved "+objects.size() +" objects");
            if(objects.size()>0){
              for(ParseObject object:objects) {
                Log.i("findInBackgroundResult",object.getInt("score")+"");
              }
            }

          } else{
            
          }
      }
    });*/

  /*  ParseObject score=new ParseObject("Score");
    score.put("username","Florin");
    score.put("score",86);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {

        if(e==null){
          Log.i("SaveInBackground","Successful");
        }else{
          Log.i("SaveInBackground","Failed.Error: "+e.toString());
        }


      }
    });*/

    /*ParseQuery<ParseObject> query=ParseQuery.getQuery("Score");
    query.getInBackground("PH1I78GrdU", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null && object!=null) {
          object.put("score",200);
          object.saveInBackground();

          Log.i("ObjectValue",object.getString("username"));
          Log.i("ObjectValue",object.getInt("score")+"");
        }
      }
    });*/

   /* ParseObject tweet=new ParseObject("Tweet");

    tweet.put("username","tom");
    tweet.put("tweet","Some random comment");

    tweet.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e==null){
          Log.i("Tweet","Successful");
        }else{
          Log.i("Tweet","Failed");
        }
      }
    });*/

/*

    ParseQuery<ParseObject> query=ParseQuery.getQuery("Tweet");
    query.getInBackground("utuNQtmtnp", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if(e==null){
          Log.i("Tweet","Successful");
          object.put("tweet","Bye");
          object.saveInBackground();
        }else{
          Log.i("Tweet","Failed");
        }
      }
    });
*/


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  @Override
  public void onClick(View v) {
    if(v.getId()==R.id.changeSignupModeTextView){
      Button signupButton=findViewById(R.id.signupButton);
      if(signUpModeActive){
        signUpModeActive=false;
        signupButton.setText("LOGIN");
        changeSignUpModeTextView.setText("Or,Signup");
      }else{
        signUpModeActive=true;
        signupButton.setText("SIGN UP");
        changeSignUpModeTextView.setText("Or,Login");
      }
    } else if(v.getId()==R.id.backgroundConstraintLayout || v.getId()==R.id.logoImageView){
      InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
  }

  @Override
  public boolean onKey(View v, int keyCode, KeyEvent event) {

    if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
      signUp(v);
    return false;
  }
}