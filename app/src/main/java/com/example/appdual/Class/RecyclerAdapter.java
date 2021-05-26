package com.example.appdual.Class;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdual.Fragments.FInfoPeli;
import com.example.appdual.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Film> data;
    Context context;

    public RecyclerAdapter(Context ct, List<Film> ListaPeli){
        this.context = ct;
        this.data = ListaPeli;
        Log.i("testNom", "-- " + data.size());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.myText.setText(data.get(position));
        holder.bindData(data.get(position));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                FInfoPeli finfopeli = new FInfoPeli();

                Film peli = data.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable("peli", peli);

                finfopeli.setArguments(bundle);


                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.PantallaFragment, finfopeli);
                transaction.addToBackStack(null);
                transaction.commit();
              //  intento.putExtra("peli", peli);

               // context.startActivity(intento);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView PortadaPeli;
        TextView myText;
        TextView myRating;
        TextView myCountVote;

        ConstraintLayout mainLayout;

        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            myText = itemView.findViewById(R.id.NomsTextView);
            PortadaPeli = itemView.findViewById(R.id.PortadaView);
            myCountVote = itemView.findViewById(R.id.countText);
            myRating = itemView.findViewById(R.id.RatingText);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        public void bindData(Film film) {

            myText.setText((film.getNombrepeli()));
            myCountVote.setText((film.getVoteCount()));
            myRating.setText((film.getRating()));

            String urlImg = "https://image.tmdb.org/t/p/original/" + film.getPoster_path();
            Picasso.get().load(urlImg).into(PortadaPeli);
        }
    }
}
