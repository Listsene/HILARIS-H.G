package com.example.samsung.hilaris;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Detail_HeartRateGraph extends Graph {

    private final int HR_STATES = 3; //Rest, Stimulus, Recovery

    int[] intHR_RATE = new int[HR_STATES];
    TextView[] textViews_HR_RATE = new TextView[HR_STATES];
    String[]strSubjectStates = {"Rest", "Stim", "Recv"}; //HR_SUBJECT_STATES  Rest, Stimulus, Recovery


    JSON json;
    String get_mb_id;
    String get_GUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__heart_rate_graph);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        get_mb_id = intent.getExtras().getString("mb_id");
        get_GUID = intent.getExtras().getString("GUID");

        // Later Have to change the below code into for loop for simplicity of code
        textViews_HR_RATE[0] = (TextView) findViewById(R.id.textView_Before_HeartRate);
        textViews_HR_RATE[1] = (TextView) findViewById(R.id.textView_During_HeartRate);
        textViews_HR_RATE[2] = (TextView) findViewById(R.id.textView_After_HeartRate);


//Setting Graph's X axis

        try {
            JSONObject profile = new JSONObject(intent.getStringExtra("SelectedProfile"));
            json = new JSON(profile);
            for(int states = 0; states < HR_STATES; states++)
            {
                intHR_RATE[states] = json.getHeartRate(strSubjectStates[states]);
                textViews_HR_RATE[states].setText(""+intHR_RATE[states]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//Getting Data


//Setting graph UI of graph_HeartRate
                    GraphView graph_HeartRate = (GraphView) findViewById(R.id.graph_HeartRate);
                    StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph_HeartRate);
                    staticLabelsFormatter1.setHorizontalLabels(strSubjectStates);
                    graph_HeartRate.setTitle("Heart Rate");
                    graph_HeartRate.getLegendRenderer().setVisible(true);
                    graph_HeartRate.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
                    graph_HeartRate.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
                    graph_HeartRate.addSeries(addLineSeriesData(intHR_RATE, "BLUE"));


    }
    /**
     * Action Bar에 메뉴를 생성
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.logout:
                Intent intent = new Intent(Detail_HeartRateGraph.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
