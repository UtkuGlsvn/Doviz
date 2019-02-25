    package com.example.glsvn.doviz;

    import android.content.Context;
    import android.content.Intent;
    import android.net.ConnectivityManager;
    import android.net.NetworkInfo;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.widget.Toast;

    public class SplashActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            internetControl();
        }

        void internetControl()
        {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if(isConnected==false)
                Toast.makeText(getBaseContext(),"Not connected internet!",Toast.LENGTH_SHORT).show();
            else
            {

                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }

        }
    }