package com.aziz.novi.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.aziz.novi.R;
import com.aziz.novi.model.ModelChapter;

import java.util.List;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    private List<ModelChapter> items;
    private Context mContext;

    public ChapterAdapter(Context context, List<ModelChapter> items) {
        this.mContext = context;
        this.items = items;
    }

    @Override
    public ChapterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trailer, parent, false);
        return new ChapterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChapterAdapter.ViewHolder holder, int position) {
        final ModelChapter data = items.get(position);

        holder.btnTrailer.setText(data.getChapter());
        holder.btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wsjti.id/novi/public/fchapter/" + data.getId()));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public Button btnTrailer;

        public ViewHolder(View itemView) {
            super(itemView);
            btnTrailer = itemView.findViewById(R.id.btnTrailer);
        }
    }

}
