package gangdrive.gang.demoservice;


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

import gangdrive.gang.demoservice.db.CarData;
import gangdrive.gang.demoservice.db.Tototo;

public class ToListAdapter extends RecyclerView.Adapter<ToListAdapter.MyViewHolder> {
    private Context context;
    private List<Tototo> toList;
    private HandleToClick clickListener;

    public ToListAdapter(Context context, HandleToClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setTo(List<Tototo> toList) {
        this.toList = toList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ToListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvToName.setText(this.toList.get(position).toName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(toList.get(position));
            }
        });

        holder.editTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(toList.get(position));
            }
        });

        holder.removeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(toList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (toList == null || toList.size() == 0) {
            return 0;
        } else {
            return toList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvToName;
        ImageView removeTo;
        ImageView editTo;

        public MyViewHolder(View view) {
            super(view);
            tvToName = view.findViewById(R.id.tvCarDataName);
            removeTo = view.findViewById(R.id.removeCarData);
            editTo = view.findViewById(R.id.editCarData);
        }
    }

    public interface HandleToClick {
        void itemClick(Tototo to);

        void removeItem(Tototo to);

        void editItem(Tototo to);
    }

}
