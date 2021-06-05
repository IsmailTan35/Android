package io.emqx.mqtt;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class MapsRecyclerViewAdapter  extends RecyclerView.Adapter<MapsRecyclerViewAdapter.ViewHolder> {
    private final List<Maps> mValues;

    public MapsRecyclerViewAdapter(List<Maps> items) {
        mValues = items;
    }

    @Override
    public MapsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MapsRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MapsRecyclerViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Message mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
