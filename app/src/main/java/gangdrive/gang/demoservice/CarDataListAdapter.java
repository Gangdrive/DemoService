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

public class CarDataListAdapter extends RecyclerView.Adapter<CarDataListAdapter.MyViewHolder> {
    private Context context;
    private List<CarData> carDataList;
    private HandleCarDataClick clickListener;

    public CarDataListAdapter(Context context, HandleCarDataClick clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setCarData(List<CarData> carDataList) {
        this.carDataList = carDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarDataListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarDataListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvCarDataName.setText(this.carDataList.get(position).carDataName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.itemClick(carDataList.get(position));
            }
        });

        holder.editCarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.editItem(carDataList.get(position));
            }
        });

        holder.removeCarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.removeItem(carDataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (carDataList == null || carDataList.size() == 0) {
            return 0;
        } else {
            return carDataList.size();
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCarDataName;
        ImageView removeCarData;
        ImageView editCarData;

        public MyViewHolder(View view) {
            super(view);
            tvCarDataName = view.findViewById(R.id.tvCarDataName);
            removeCarData = view.findViewById(R.id.removeCarData);
            editCarData = view.findViewById(R.id.editCarData);
        }
    }

    public interface HandleCarDataClick {
        void itemClick(CarData carData);

        void removeItem(CarData carData);

        void editItem(CarData carData);
    }

}
