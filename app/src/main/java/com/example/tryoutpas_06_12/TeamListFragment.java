package com.example.tryoutpas_06_12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListFragment extends Fragment {

    private static final String ARG_LIGA = "NamaLiga";
    private String liga;

    private ProgressBar pbLoading;
    private RecyclerView recyclerView;

    public static TeamListFragment newInstance(String liga) {
        TeamListFragment fragment = new TeamListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LIGA, liga);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            liga = getArguments().getString(ARG_LIGA);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        pbLoading = view.findViewById(R.id.pbLoading);
        recyclerView = view.findViewById(R.id.rvRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadTeams();

        return view;
    }

    private void loadTeams() {
        if (liga == null || liga.isEmpty()) {
            Toast.makeText(getContext(), "Liga tidak ditemukan", Toast.LENGTH_SHORT).show();
            return;
        }

        pbLoading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<TeamResponse> call = apiService.getTeams(liga);

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeamResponse> call, @NonNull Response<TeamResponse> response) {
                pbLoading.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Team> teams = response.body().getTeams();
                    TeamAdapter adapter = new TeamAdapter(teams);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "Gagal mengambil data tim", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TeamResponse> call, @NonNull Throwable t) {
                pbLoading.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}