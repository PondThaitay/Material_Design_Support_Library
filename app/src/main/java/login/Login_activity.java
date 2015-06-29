package login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.cm_smarthome.materialdesignsupportlibrary.MainActivity;
import com.cm_smarthome.materialdesignsupportlibrary.R;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import menu.Sqlite;

/**
 * Created by AdminPond on 28/6/2558.
 */
public class Login_activity extends AppCompatActivity {

    Context context = this;
    Sqlite sqlite = new Sqlite(context);

    private LoginButton loginBtn;

    private UiLifecycleHelper uiHelper;

    private BootstrapButton btnStart;
    private Toolbar mToolbar;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        String State = getIntent().getExtras().getString("State");

        MyAsyncTaskCheckSessionFacebook sessionFacebook = new MyAsyncTaskCheckSessionFacebook();
        sessionFacebook.execute(State);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(mToolbar);

        btnStart = (BootstrapButton) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        uiHelper = new UiLifecycleHelper(this, statusCallback);
        uiHelper.onCreate(savedInstanceState);

        loginBtn = (LoginButton) findViewById(R.id.fb_login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
                    @Override
                    public void onUserInfoFetched(GraphUser user) {
                        if (user != null) {
                            MyAsyncTaskClickLoginFacebook loginFacebook = new MyAsyncTaskClickLoginFacebook();
                            loginFacebook.execute();
                        } else {
                            Log.e("Login", "No User");
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        finish();
        super.onBackPressed();
    }

    private Session.StatusCallback statusCallback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            if (state.isOpened()) {
                Log.d("FacebookSampleActivity", "Facebook session opened");
            } else if (state.isClosed()) {
                Log.d("FacebookSampleActivity", "Facebook session closed");
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        uiHelper.onSaveInstanceState(savedState);
    }

    public void checkSession() {
        Session.openActiveSession(Login_activity.this, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (session.isOpened()) {

                    // make request to the /me API
                    Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {

                        // callback after Graph API response with user object
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                System.out.println(user.getName());
                                System.out.println(user.getFirstName());
                                System.out.println(user.getLastName());
                                System.out.println(user.getLink());
                                System.out.println(user.getLocation());
                                System.out.println("facebook user id" + user.getId());
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    private class MyAsyncTaskClickLoginFacebook extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(2000);
                sqlite.UpdateDataFlagLogin("1", "1");
                String[] arrData = sqlite.SelectData("1");
                if (arrData[0].length() > 0) {
                    Log.e("Flag Logout1", arrData[1] + "+" + arrData[3]);
                }
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "Please wait...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }
    }

    private class MyAsyncTaskCheckSessionFacebook extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
                if (params[0].equals("out")) {
                    Session session = Session.getActiveSession();
                    session.closeAndClearTokenInformation();
                } else if (params[0].equals("check")) {
                    checkSession();
                } else {
                    Log.e("Else", "State");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(context, "", "Please wait...");
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
        }
    }
}