    package com.example.glsvn.doviz;

    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;
    import android.widget.Toast;

    import com.example.glsvn.doviz.model.dovizObject;
    import com.example.glsvn.doviz.model.moneyApiInterface;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class SplashActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            apiControl();
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();

        }

        public void apiControl()
        {
            moneyApiInterface myapiInterface = Api.getRetrofitInstance().create(moneyApiInterface.class);

            Call<dovizObject> call = myapiInterface.getDoviz();

            call.enqueue(new Callback<dovizObject>()
            {
                @Override
                public void onResponse(Call<dovizObject> call, Response<dovizObject> response) {
                    dovizObject rates = response.body();
                    if(!rates.getSuccess())
                        Toast.makeText(getBaseContext(),"Apiye erişim yok!",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getBaseContext(),"Apiye erişim var!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<dovizObject> call, Throwable t) {
                    Log.d("Error response", t.getMessage());
                }
            });
        }

    }