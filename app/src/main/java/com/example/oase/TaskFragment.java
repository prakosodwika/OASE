package com.example.oase;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskFragment extends Fragment {

    ListView listView;
    ArrayList<Task> arrayList = new ArrayList<>();
    ListTaskAdapter listTaskAdapter;

    private static String JSON_URL = "https://api.npoint.io/3bd60b20ab036d79363f";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_task,container, false);

//        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTask);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setAdapter(new ListDashboardAdapter(nameMatkul));

        listView = view.findViewById(R.id.listViewTask);
        arrayList = new ArrayList<>();
        extractTask();

//        arrayList.add(new Task("Kecerdasan Buatan", "Assignment", "23.59"));
//        arrayList.add(new Task("Interaksi Manusia dan Komputer", "Quiz", "11.59"));
//        arrayList.add(new Task("Pemrograman Citra Digital", "Absen", "08.59"));
//        arrayList.add(new Task( "Elektronika", "Assignment", "23.59"));
//        arrayList.add(new Task("Data Mining", "Assignment", "23.59"));
//        arrayList.add(new Task( "Basis Data Lanjut", "Absen", "08.59"));
//        arrayList.add(new Task("Rangkaian Listrik II + Lab", "Quiz", "20.59"));
//        arrayList.add(new Task( "Pengantar Skripsi (Kelas TK)", "Absen", "10.59"));
//        arrayList.add(new Task("Kalkulus", "Absen", "11.59"));
//        arrayList.add(new Task("Matematika Teknik", "Absen", "16.59"));
//
//        listTaskAdapter = new ListTaskAdapter(this.getContext(), 0 , arrayList);
//        listView.setAdapter(listTaskAdapter);

        return view;
    }

    private void extractTask(){
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject taskObject = response.getJSONObject(i);

                        Task task = new Task();
                        task.setMatkulTask(taskObject.getString("name").toString());
                        task.setTypeTask(taskObject.getString("task").toString());
                        task.setDeadlineTask(taskObject.getString("deadline").toString());
                        arrayList.add(task);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                listTaskAdapter = new ListTaskAdapter(getLayoutInflater().getContext(), 0,  arrayList);
                listView.setAdapter(listTaskAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }
}