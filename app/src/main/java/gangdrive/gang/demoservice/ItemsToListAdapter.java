package gangdrive.gang.demoservice;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gangdrive.gang.demoservice.db.ItemsTo;

public class ItemsToListAdapter extends RecyclerView.Adapter<ItemsToListAdapter.MyViewHolder> {
    private Context context;
    private List<ItemsTo> itemsToList;
    private HandleItemsToClick clickListener;

    public ItemsToListAdapter(Context context, HandleItemsToClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setTo(List<ItemsTo> itemsToList) {
        this.itemsToList = itemsToList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemsToListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsToListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvItemsToName.setText(this.itemsToList.get(position).itemNameTo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemToClick(itemsToList.get(position));
            }
        });

        holder.editTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editToItem(itemsToList.get(position));
            }
        });

        holder.removeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeToItem(itemsToList.get(position));
            }
        });

        if (this.itemsToList.get(position).completedTo) {
            holder.tvItemsToName.setPaintFlags(holder.tvItemsToName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvItemsToName.setPaintFlags(0);
        }
    }

    @Override
    public int getItemCount() {
        if (itemsToList == null || itemsToList.size() == 0)
            return 0;
         else
            return itemsToList.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemsToName;
        ImageView removeTo;
        ImageView editTo;

        public MyViewHolder(View view) {
            super(view);
            tvItemsToName = view.findViewById(R.id.tvCarDataName);
            removeTo = view.findViewById(R.id.removeCarData);
            editTo = view.findViewById(R.id.editCarData);
        }
    }

    public interface HandleItemsToClick {
        void itemToClick(ItemsTo item);

        void removeToItem(ItemsTo item);

        void editToItem(ItemsTo item);
    }

}
