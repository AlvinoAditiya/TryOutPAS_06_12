package com.example.tryoutpas_06_12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
public class TeamAdapter  extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder>{

    private List<Team> teamList;

    public TeamAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView tvTeam, tvStadium;
        ImageView imgLogo;

        public TeamViewHolder(View itemView) {
            super(itemView);
            tvTeam = itemView.findViewById(R.id.tvTeam);
            tvStadium = itemView.findViewById(R.id.tvStadium);
            imgLogo = itemView.findViewById(R.id.imgLogo);
        }
    }

    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.tvTeam.setText(team.getStrTeam());
        holder.tvStadium.setText(team.getStrStadium());

        Glide.with(holder.itemView.getContext())
                .load(team.getStrBadge())
                .into(holder.imgLogo);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
