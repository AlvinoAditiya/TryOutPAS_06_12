package com.example.tryoutpas_06_12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DashboardFragment extends Fragment {
    private Button btnPremiere, btnLaLiga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnPremiere = view.findViewById(R.id.btnPremiere);
        btnLaLiga = view.findViewById(R.id.btnLaLiga);

        btnPremiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeamListFragment("English Premier League");
            }
        });

        btnLaLiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeamListFragment("Spanish La Liga");
            }
        });

        return view;
    }

    private void openTeamListFragment(String liga) {
        TeamListFragment fragment = new TeamListFragment();
        Bundle args = new Bundle();
        args.putString("NamaLiga", liga);
        fragment.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null); // agar bisa tekan back balik ke Dashboard
        transaction.commit();
    }
}