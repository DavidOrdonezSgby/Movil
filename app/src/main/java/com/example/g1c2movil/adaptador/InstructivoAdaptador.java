package com.example.g1c2movil.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g1c2movil.R;
import com.example.g1c2movil.retrofit.model.Anexo;

import java.util.List;

public class InstructivoAdaptador extends RecyclerView.Adapter<InstructivoAdaptador.ViewHolder> {

    private List<Anexo> anexoList;

    public InstructivoAdaptador(List<Anexo> data) {
        this.anexoList = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_instructivo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Anexo anexo = anexoList.get(position);

        holder.nombre.setText(anexo.getNombre_t());
        holder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnItemClickListener.imageViewOnClick(v, holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return anexoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        ImageView btnDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.textView24);
            btnDownload = itemView.findViewById(R.id.img_download1);
        }
    }

    //  listener
    private static onItemClickListener setOnItemClickListener;

    public interface onItemClickListener {
        void imageViewOnClick(View v, int position);
    }

}
