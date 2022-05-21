package com.example.testbankapperickbernal.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testbankapperickbernal.Models.CardsModel;
import com.example.testbankapperickbernal.Models.MovementModel;
import com.example.testbankapperickbernal.R;

import java.util.List;

public class MainRecyclerAdapterMovements extends RecyclerView.Adapter<MainRecyclerAdapterMovements.ViewHolder>

{
    private List<MovementModel> movementModelList;
    Context context;


    public MainRecyclerAdapterMovements(List<MovementModel> movementModelList, Context context)
    {
        this.movementModelList = movementModelList;
        this.context = context;
    }

    @Override
    public MainRecyclerAdapterMovements.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movements, parent, false);
        MainRecyclerAdapterMovements.ViewHolder viewHolder = new MainRecyclerAdapterMovements.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapterMovements.ViewHolder holder, int position)
    {
        String description = movementModelList.get(position).getDescripcion();
        String fecha = movementModelList.get(position).getFecha();
        Double monto = movementModelList.get(position).getMonto();
        String tipo = movementModelList.get(position).getTipo();
        holder.txtDescription.setText(description);
        holder.txtFecha.setText(fecha);
        holder.txtMonto.setText(String.valueOf(monto));
        if(TextUtils.equals(tipo,"abono"))
        {
            holder.txtMonto.setTextColor(Color.GREEN);
        }
        else
        {
            holder.txtMonto.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount()
    {
        return movementModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtDescription;
        private TextView txtFecha;
        private TextView txtMonto;

        public ViewHolder(View v)
        {
            super(v);
            context = v.getContext();
            txtDescription = v.findViewById(R.id.txtMovementDescription);
            txtFecha = v.findViewById(R.id.txtMovementFecha);
            txtMonto = v.findViewById(R.id.txtMovementMonto);
        }
    }
}
