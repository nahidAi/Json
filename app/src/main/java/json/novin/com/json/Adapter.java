package json.novin.com.json;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import json.novin.com.json.Model.Celebrity;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder>{
    Context context;
    List<Celebrity>celebrities;

    public Adapter(Context context, List<Celebrity> selebrities) {
        this.context = context;
        this.celebrities = selebrities;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_selebrity,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Celebrity selebrity = celebrities.get(position);
        Picasso.with(context).load(selebrity.getImage()).into(holder.imageView);
        holder.txtName.setText(selebrity.getName());
        holder.txtEmail.setText(selebrity.getEmail());

    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtEmail;
        TextView txtName;
        public viewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCelebrity);
            txtEmail = itemView.findViewById(R.id.textEmail);
            txtName = itemView.findViewById(R.id.textName);
        }
    }
}
