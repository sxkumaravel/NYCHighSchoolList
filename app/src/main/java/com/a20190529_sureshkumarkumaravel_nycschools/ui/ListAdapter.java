package com.a20190529_sureshkumarkumaravel_nycschools.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.a20190529_sureshkumarkumaravel_nycschools.databinding.ListViewItemBinding;
import com.a20190529_sureshkumarkumaravel_nycschools.model.HighSchool;

import java.util.List;

/**
 * Adapter to handle display of high school lists.
 * Created on 2019-05-29.
 *
 * @author kumars
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private final List<HighSchool> mHighSchoolList;
    private final Listener mListener;

    public ListAdapter(@NonNull List<HighSchool> mHighSchoolList, @Nullable Listener listener) {
        this.mHighSchoolList = mHighSchoolList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListViewItemBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HighSchool highSchool = mHighSchoolList.get(position);
        holder.bind(highSchool);
        holder.itemView.setTag(highSchool);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onHighSchoolSelected((HighSchool) v.getTag());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHighSchoolList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ListViewItemBinding mItemBinding;

        ViewHolder(@NonNull ListViewItemBinding itemView) {
            super(itemView.getRoot());
            mItemBinding = itemView;
        }

        void bind(@NonNull HighSchool highSchool) {
            mItemBinding.setHighSchool(highSchool);
            mItemBinding.executePendingBindings();
        }
    }

    /**
     * Interface to be implemented by the caller to get the adapter events.
     */
    public interface Listener {
        /**
         * Method to invoke when a school is selected to view further details.
         *
         * @param highSchool {@link HighSchool} selected.
         */
        void onHighSchoolSelected(HighSchool highSchool);
    }
}
