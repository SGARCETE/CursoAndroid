package com.example.sgarcete.ejercicio.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sgarcete.ejercicio.R;
import com.example.sgarcete.ejercicio.activities.activities.MainActivity;
import com.example.sgarcete.ejercicio.activities.model.Article;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sgarcete on 12/27/16.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<Article> items = new ArrayList<Article>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Article article = this.items.get(position);
        holder.title.setText(article.getTitle());
        holder.precio.setText("$" + article.getPrecio() + "");

        Picasso.with(holder.view.getContext()) //Siempre recibe un contexto
                .load(article.getThumbnail())
                .into(holder.imagen);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                //Le pasamos los datos a la vista principal
                intent.putExtra("id", article.getIdServer());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void setItems(List<Article> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView title;
        TextView precio;
        ImageView imagen;

        public ViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            precio = (TextView) itemView.findViewById(R.id.precio);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
        }
    }
}
