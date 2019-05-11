package json.novin.com.json;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import json.novin.com.json.Model.Celebrity;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<Celebrity> celebrities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        adapter = new Adapter(MainActivity.this,celebrities);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);

        new getJson(MainActivity.this).execute();


    }
    public class getJson extends AsyncTask<Void,Void,String>{
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Context context;

        public getJson(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("در حال دریافت اطلاعات");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String link="http://www.novindevelopers.ir/celebrity.php";
            return HttpJsonParser.getJson(link);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s != null)
            {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("celebrity");
                    for (int i = 0 ; i<jsonArray.length();i++)
                    {
                        JSONObject object =jsonArray.getJSONObject(i);
                        String name = object.getString("name");
                        String email = object.getString("email");
                        String image = object.getString("image");

                        celebrities.add(new Celebrity(name,email,image));
                    }
                    adapter.notifyDataSetChanged();


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
