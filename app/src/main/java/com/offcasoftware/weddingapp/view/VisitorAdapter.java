package com.offcasoftware.weddingapp.view;

import com.offcasoftware.weddingapp.R;
import com.offcasoftware.weddingapp.model.Visitor;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<Visitor> mData = new ArrayList<>();
    private View.OnLongClickListener mOnLongClickListener;
    private View.OnClickListener mOnClickLister;

    public VisitorAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public VisitorViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = mLayoutInflater.inflate(R.layout.row_visitor, parent, false);
        return new VisitorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VisitorViewHolder holder, final int position) {
        final Visitor visitor = mData.get(position);
        holder.mName.setText(visitor.getName());
        holder.mSurname.setText(visitor.getSurname());
        holder.mAdditionalPerson.setText(String.valueOf(visitor.getAdditionalPerson()));

        holder.itemView.setBackgroundColor(ContextCompat.getColor(
                holder.itemView.getContext(), visitor.getVisitorStatus().getColor()));

        holder.itemView.setTag(visitor);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                return mOnLongClickListener.onLongClick(v);
            }
        });
        holder.itemView.setOnClickListener(mOnClickLister);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    public void setData(List<Visitor> visitors) {
        mData.clear();
        mData.addAll(visitors);
        notifyDataSetChanged();
    }

    public void setOnClickLister(final View.OnClickListener onClickLister) {
        mOnClickLister = onClickLister;
    }

    public class VisitorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView mName;

        @BindView(R.id.surname)
        TextView mSurname;

        @BindView(R.id.additional_person)
        TextView mAdditionalPerson;

        public VisitorViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
