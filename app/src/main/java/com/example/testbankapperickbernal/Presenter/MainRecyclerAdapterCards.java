package com.example.testbankapperickbernal.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testbankapperickbernal.Models.CardsModel;
import com.example.testbankapperickbernal.R;

import java.util.List;

public class MainRecyclerAdapterCards  extends RecyclerView.Adapter<MainRecyclerAdapterCards.ViewHolder>

{
    private List<CardsModel> cardsModelList;
    Context context;


    public MainRecyclerAdapterCards(List<CardsModel> cardsModelList, Context context)
    {
        this.cardsModelList = cardsModelList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cards, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        String name = cardsModelList.get(position).getName();
        String account = cardsModelList.get(position).getCardNumber();
        String status = cardsModelList.get(position).getEstado();
        int balance = cardsModelList.get(position).getBalance();
        String titular = cardsModelList.get(position).getTipo();
        holder.txtAccountName.setText(name);
        holder.txtAccountNumber.setText(account);
        holder.txtCardStatus.setText(status);
        holder.txtBalance.setText(String.valueOf(balance));
        holder.txtTitular.setText(titular);
    }

    @Override
    public int getItemCount()
    {
        return cardsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtCardStatus;
        private TextView txtAccountName;
        private TextView txtAccountNumber;
        private TextView txtBalance;
        private TextView txtTitular;

        public ViewHolder(View v)
        {
            super(v);
            context = v.getContext();
            txtCardStatus = v.findViewById(R.id.txtCardStatus);
            txtAccountName = v.findViewById(R.id.txtAccountName);
            txtAccountNumber = v.findViewById(R.id.txtAccountNumber);
            txtBalance = v.findViewById(R.id.txtBalance);
            txtTitular = v.findViewById(R.id.txtTitular);
        }
    }
}
