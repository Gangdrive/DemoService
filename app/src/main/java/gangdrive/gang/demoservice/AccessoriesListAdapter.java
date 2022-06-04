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

import gangdrive.gang.demoservice.db.Accessories;

public class AccessoriesListAdapter extends RecyclerView.Adapter<AccessoriesListAdapter.MyViewHolder> {
    private Context context;
    private List<Accessories> accessoriesList;
    private HandleAccessoriesClick clickListener;

    public AccessoriesListAdapter(Context context, HandleAccessoriesClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setAccessories(List<Accessories> accessoriesList) {
        this.accessoriesList = accessoriesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccessoriesListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccessoriesListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvAccessoriesName.setText(this.accessoriesList.get(position).accessoriesName);

        holder.editAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(accessoriesList.get(position));
            }
        });

        holder.removeAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(accessoriesList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (accessoriesList == null || accessoriesList.size() == 0) {
            return 0;
        } else {
            return accessoriesList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAccessoriesName;
        ImageView removeAccessories;
        ImageView editAccessories;

        public MyViewHolder(View view) {
            super(view);
            tvAccessoriesName = view.findViewById(R.id.tvCarDataName);
            removeAccessories = view.findViewById(R.id.removeCarData);
            editAccessories = view.findViewById(R.id.editCarData);
        }
    }

    public interface HandleAccessoriesClick {
     //   void itemClick(Accessories accessories);

        void removeItem(Accessories accessories);

        void editItem(Accessories accessories);
    }

}
