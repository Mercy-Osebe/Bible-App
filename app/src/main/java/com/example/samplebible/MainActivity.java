package com.example.samplebible;

import android.os.Bundle;

import com.example.samplebible.networking.BibleService;
import com.example.samplebible.pojos.Bible;
import com.example.samplebible.pojos.BibleResponse;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.samplebible.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public static final String TAG = "MainActivity";
    ArrayList<String> booksArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.scripture.api.bible")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BibleService service = retrofit.create(BibleService.class);

        Call<BibleResponse>  listBibles =  service.listBibles();

        listBibles.enqueue(new Callback<BibleResponse>() {
            @Override
            public void onResponse(Call<BibleResponse> call, Response<BibleResponse> response) {
                if(response.isSuccessful())
                {
                    BibleResponse bibleResponse = response.body();

                    for(Bible bible: bibleResponse.getData())
                    {
                        booksArray.add(bible.getName());
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this,
                            R.layout.activity_listview, booksArray);

                    binding.listViewId.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BibleResponse> call, Throwable t) {
                Log.d(TAG, t.getMessage());

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}