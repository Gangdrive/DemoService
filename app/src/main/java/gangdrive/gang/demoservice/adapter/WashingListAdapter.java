package gangdrive.gang.demoservice.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gangdrive.gang.demoservice.R;
import gangdrive.gang.demoservice.db.Washing;

public class WashingListAdapter extends RecyclerView.Adapter<WashingListAdapter.MyViewHolder> {
    private Context context;
    private List<Washing> washingList;
    private HandleWashingClick clickListener;

    public WashingListAdapter(Context context, HandleWashingClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setWashing(List<Washing> washingList) {
        this.washingList = washingList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WashingListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WashingListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvWashingPrice.setText(this.washingList.get(position).washingPrice);

        holder.editWashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(washingList.get(position));
            }
        });

        holder.removeWashing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(washingList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (washingList == null || washingList.size() == 0) {
            return 0;
        } else {
            return washingList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvWashingPrice;
        ImageView removeWashing;
        ImageView editWashing;

        public MyViewHolder(View view) {
            super(view);
            tvWashingPrice = view.findViewById(R.id.tvCarDataName);
            removeWashing = view.findViewById(R.id.removeCarData);
            editWashing = view.findViewById(R.id.editCarData);
        }
    }

    public interface HandleWashingClick {

        void removeItem(Washing washing);

        void editItem(Washing washing);
    }

}
