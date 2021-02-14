package com.fetch_rewards.fetchrewardsassessment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fetch_rewards.fetchrewardsassessment.R;
import com.fetch_rewards.fetchrewardsassessment.models.Item;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ImageViewHolder> {

    Context mContext;
    List<Item> itemList;

    public ItemAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.textId.setText(Integer.toString(item.getListId()));
        holder.textName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView textId, textName;

        public ImageViewHolder(View itemView) {
            super(itemView);

            textId = itemView.findViewById(R.id.text_id);
            textName = itemView.findViewById(R.id.text_name);
        }
    }
}