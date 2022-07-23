package com.example.project4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project4.Retrofit.SearchItem;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
private final List<SearchItem> userDataList;
private final UserInterface userInterface;

public RecyclerViewAdapter(List<SearchItem> userDataList, UserInterface userInterface) {
        this.userDataList=userDataList;
    this.userInterface = userInterface;
}




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
}

    @Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Toast.makeText(RecyclerViewAdapter.this,"hi",Toast.LENGTH_SHORT).show();

        SearchItem userData = userDataList.get(position);
        holder.tvTitle.setText(userData.getTitle());
        holder.tvYear.setText(userData.getYear());
        holder.tvimdbId.setText(userData.getImdbID());
        holder.tvType.setText(userData.getType());
        Glide.with(holder.ivPoster.getContext()).load(userData.getPoster()).placeholder(R.drawable.ic_launcher_background).into(holder.ivPoster);
        //TO-DO
//        holder.root.setOnClickListener(view -> {
        holder.rootView.setOnClickListener(view -> userInterface.onUserClick(userData,position));

//        });
        //if((userData))



        }


@Override
public int getItemCount() {
        //tells the adapter size of recycler
        return userDataList.size();
        }

    public interface UserInterface{
        void onUserClick( SearchItem userData,int position);

    }


public static class ViewHolder extends RecyclerView.ViewHolder{
    private final TextView tvTitle;
    private final TextView tvYear;
    private final TextView tvimdbId;
    private final TextView tvType;
    private final View rootView ;
    private final ImageView ivPoster;



    public ViewHolder(View view) {
        super(view);

        tvTitle = view.findViewById(R.id.name);
        tvYear = view.findViewById(R.id.year);
        tvimdbId = view.findViewById(R.id.imdb);
        tvType = view.findViewById(R.id.type);
        ivPoster = view.findViewById(R.id.img1);
        //assigning view root
        rootView = view;
    }
}

}
