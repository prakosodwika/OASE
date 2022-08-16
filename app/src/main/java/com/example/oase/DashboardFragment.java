package com.example.oase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

//    String[] nameMatkul = {"Pemrograman Aplikasi Mobile", "Big Data", "Interaksi Manusia dan Komputer", "Pemrograman Internet", "PSD", "Pemrograman Visual", "IOT", "OOP", "Etika Profesi", "PAM", "Big Data", "IMK", "Pemrograman Internet", "PSD", "Pemrograman Visual", "IOT", "OOP", "Etika Profesi", "PAM", "Big Data", "IMK", "Pemrograman Internet", "PSD", "Pemrograman Visual", "IOT", "OOP", "Etika Profesi"};

    RecyclerView recyclerView;
    List<Dashboard> dashboards;
    private static String JSON_URL = "https://api.npoint.io/229a2da34f41515d6736";
    ListDashboardAdapter listDashboardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);

// tidak mengunakan data json
//        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDashboard);
//        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager( 2,StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setAdapter(new ListDashboardAdapter(nameMatkul));

// mengunakan data json

        recyclerView = view.findViewById(R.id.recyclerViewDashboard);
        dashboards = new ArrayList<>();
        extractMatkul();

        return view;

    }

    private void extractMatkul() {
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject matkulObject = response.getJSONObject(i);

                        Dashboard matkul = new Dashboard();
                        matkul.setMatkul(matkulObject.getString("name").toString());
                        dashboards.add(matkul);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

//                recyclerView.setLayoutManager(new LinearLayoutManager(getLayoutInflater().getContext());
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                listDashboardAdapter = new ListDashboardAdapter(getLayoutInflater().getContext(), dashboards);
                recyclerView.setAdapter(listDashboardAdapter);

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