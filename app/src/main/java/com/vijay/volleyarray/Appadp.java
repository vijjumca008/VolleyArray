package com.vijay.volleyarray;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vijay.volleyarray.databinding.DatasourceBinding;

import java.util.ArrayList;

public class Appadp extends RecyclerView.Adapter<Appadp.MyViewHolder> {
    ArrayList<String> adptarray;
    ArrayList<String> adpiarray;
    ArrayList<String> adpuarray;
    Context context;
    DatasourceBinding datasourceBinding;
    public Appadp(MainActivity mainActivity, ArrayList<String> tarray, ArrayList<String> iarray, ArrayList<String> uarray) {
        adpiarray=iarray;
        adptarray=tarray;
        adpuarray=uarray;
        context=mainActivity;
    }

    @NonNull
    @Override
    public Appadp.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        datasourceBinding= DataBindingUtil.inflate(inflater,R.layout.datasource,null,false);

        return new MyViewHolder(datasourceBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull Appadp.MyViewHolder holder, int position) {

        holder.datasourceBinding.idg.setText(adpiarray.get(position));
        holder.datasourceBinding.title.setText(adptarray.get(position));
        holder.datasourceBinding.xyzurl.setText(adpuarray.get(position));

    }

    @Override
    public int getItemCount() {
        return adpiarray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        DatasourceBinding datasourceBinding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            datasourceBinding=DataBindingUtil.getBinding(itemView);
        }
    }
}
