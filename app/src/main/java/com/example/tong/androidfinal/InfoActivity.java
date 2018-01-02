package com.example.tong.androidfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.bean.student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by tong on 2017/12/28.
 */

public class InfoActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int ID_SUCCESS = 1;
    private TextView studentidTv,nameTv,genderTv,vcodeTv,roomTv,buildingTv,locationTv,gradeTv;
    private Button button2;
    private String gender,stuidForIntent,location;

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg1){
            switch ((msg1.what)){
                case ID_SUCCESS:
                    Log.d("student","handler");
                    init((student) msg1.obj);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_id);

        button2 = (Button) findViewById(R.id.enter);
        button2.setOnClickListener(this);

        Intent intent = this.getIntent();
        String stuid = intent.getStringExtra("stuid");
        String page = intent.getStringExtra("page");
        if(page.equals("select")){
            button2.setVisibility(View.INVISIBLE);
        }
        getStuInfo(stuid);
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.enter){
            if(location.equals("大兴")){
                Log.d("student","turn to select");
                Intent i = new Intent(this,ChooseActivity.class);
                i.putExtra("stuid",stuidForIntent);
                i.putExtra("gender",gender);
                startActivity(i);
            }else{
                Toast.makeText(InfoActivity.this, "不需要选择宿舍", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void init(student student){
        Log.d("student","init");
        studentidTv = (TextView) findViewById(R.id.studentid);
        nameTv = (TextView) findViewById(R.id.name);
        genderTv = (TextView) findViewById(R.id.gender);
        vcodeTv = (TextView) findViewById(R.id.vcode);
        roomTv = (TextView) findViewById(R.id.room);
        buildingTv = (TextView) findViewById(R.id.building);
        locationTv = (TextView) findViewById(R.id.location);
        gradeTv = (TextView) findViewById(R.id.grade);

        gender = student.getGender();
        stuidForIntent = student.getStudentid();
        Log.d("student",student.getName());
        studentidTv.setText("学号："+student.getStudentid());
        nameTv.setText("姓名："+student.getName());
        genderTv.setText("性别："+ student.getGender());
        vcodeTv.setText("校验码："+student.getVcode());
        roomTv.setText("宿舍号："+student.getRoom());
        buildingTv.setText("宿舍楼号："+student.getBuilding());
        locationTv.setText("校区："+student.getLocation());
        gradeTv.setText("年级："+student.getGrade());
    }
    private void getStuInfo(String stuid){
        final String address = "https://api.mysspku.com/index.php/V1/MobileCourse/getDetail?stuid="+stuid;
        Log.d("student",address);

        new Thread(new Runnable() {
            public void run() {
                HttpURLConnection con = null;
                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers(){
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {

                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {

                    }
                }};
                student student = null;
                try{
                    HttpsURLConnection.setDefaultHostnameVerifier(new NullHostnameVerifier());
                    SSLContext sc = SSLContext.getInstance("TLS");
                    sc.init(null, trustAllCerts, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                    URL url = new URL(address);

                    con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(5000);
                    con.setRequestMethod("GET");
                    con.connect();

                    System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));//设置编码,否则中文乱码
                    StringBuilder response = new StringBuilder();
                    String str;
                    while((str=reader.readLine())!=null){
                        response.append(str);
                    }
                    String responseStr = response.toString();
                    student = jsonInfo(responseStr);
                    if(student != null){
                        Log.d("student",student.toString());
                    }
                    Message msg1 = new Message();
                    msg1.what = ID_SUCCESS;
                    msg1.obj = student;
                    mHandler.sendMessage(msg1);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(con != null){
                        con.disconnect();
                    }
                }
            }
        }).start();
    }
    public student jsonInfo(String jsondata){
        student student = new student();
        String res = null;
        try{
            JSONObject json1 = new JSONObject(jsondata);
            res = json1.getString("data");
            Log.d("student","res"+res);

            JSONObject json = json1.getJSONObject("data");
            String id = json.getString("studentid");
            student.setStudentid(id);
            student.setName(json.getString("name"));
            student.setGender(json.getString("gender"));
            student.setGrade(json.getString("grade"));
            student.setLocation(json.getString("location"));
            location = json.getString("location");
            student.setVcode(json.getString("vcode"));
            if(json.has("room")){
                student.setRoom(json.getString("room"));
            }else{
                student.setRoom("暂未选择宿舍");
            }
            if(json.has("building")){
                student.setBuilding(json.getString("building"));
            }else{
                student.setBuilding("暂未选择宿舍楼");
            }

            student.setRoom(json.getString("room"));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return student;
    }
}
