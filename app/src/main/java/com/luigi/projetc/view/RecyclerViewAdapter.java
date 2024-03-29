package com.luigi.projetc.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luigi.projetc.R;
import com.luigi.projetc.database.entities.AlimentoEntity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<AlimentoEntity> alimentos = new ArrayList<>();
    private RecyclerViewOnClickListener<Integer> recyclerViewOnClickListener;

    public void setAlimentos(List<AlimentoEntity> alimentos){
        this.alimentos = alimentos;
        notifyDataSetChanged();
    }

    public void setOnClickItem(RecyclerViewOnClickListener<Integer> recyclerViewOnClickListener){
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Adicionando nome dos alimentos no item da lista
        holder.getTextView().setText(alimentos.get(position).getNome());

        //Adicionando evento de clique em cada item da lista de alimentos
        holder.textView.setOnClickListener(v -> {
            recyclerViewOnClickListener.onClick(alimentos.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return alimentos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_view_alimento_rv);
        }

        public TextView getTextView() {
            return textView;
        }
    }

}
