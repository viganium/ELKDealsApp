package com.elkdeals.mobile.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elkdeals.mobile.R;
import com.elkdeals.mobile.Utils.Utils;
import com.elkdeals.mobile.api.models.TimeLineModel;
import com.elkdeals.mobile.interfaces.ItemsHolder;
import com.elkdeals.mobile.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("StatementWithEmptyBody")
public class AuctionsAdapter extends RecyclerView.Adapter<AuctionsAdapter.ViewHolder> implements ItemsHolder {

    private OnItemClickListener onItemClickListener;
    @SuppressWarnings("FieldCanBeLocal")
    private Context context;
    private ArrayList<TimeLineModel> auctions;
    private OnItemClickListener onDetailsClickListener, onQuestionsClickListener, onPlayClickListener;

    private AuctionsAdapter() {
        auctions = new ArrayList<>();
    }

    public AuctionsAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        auctions = new ArrayList<>();
    }

    public void setAuctions(ArrayList<TimeLineModel> auctions) {
        this.auctions = auctions;
        notifyDataSetChanged();
    }

    public void setOnQuestionsClickListener(OnItemClickListener onQuestionsClickListener) {
        this.onQuestionsClickListener = onQuestionsClickListener;
    }

    public void setOnPlayClickListener(OnItemClickListener onPlayClickListener) {
        this.onPlayClickListener = onPlayClickListener;
    }

    public void addItems(Object auction) {
        if (auction instanceof TimeLineModel)
            this.auctions.add((TimeLineModel) auction);
        notifyItemInserted(auctions.size() - 1);
    }

    public void addItems(List auctions) {
        if (auctions != null && auctions.size() > 0 && auctions.get(0) instanceof TimeLineModel) {
            int pos = this.auctions.size();
            this.auctions.addAll(auctions);
            notifyItemRangeInserted(pos - 1, auctions.size());

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_auction, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        TimeLineModel auction = auctions.get(i);
        if (auction == null)
            return;
        viewHolder.title.setText(auctions.get(i).getName());
        viewHolder.description.setText(auctions.get(i).getDesc());
        if(TextUtils.isEmpty(viewHolder.description.getText()))
            viewHolder.description.setVisibility(View.GONE);
        Utils.loadImage(viewHolder.img, auction.getImage());
        if (onDetailsClickListener != null)
            viewHolder.details.setOnClickListener(v -> onDetailsClickListener.OnItemClick(auction));
        if (onQuestionsClickListener != null)
            viewHolder.question.setOnClickListener(v -> onQuestionsClickListener.OnItemClick(String.valueOf(auction.getId())));
        if (onPlayClickListener != null)
            viewHolder.play.setOnClickListener(v -> onPlayClickListener.OnItemClick(String.valueOf(auction.getVideo())));        if (onItemClickListener != null)
            viewHolder.view.setOnClickListener(v -> onItemClickListener.OnItemClick(auction));
    }

    @Override
    public int getItemCount() {
        return auctions == null ? 0 : auctions.size();
    }

    public void updateItemCounters() {

    }

    public void setOnDetailsClickListener(OnItemClickListener onDetailsClickListener) {
        this.onDetailsClickListener = onDetailsClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.details)
        public TextView details;

        @BindView(R.id.question)
        TextView question;
        @BindView(R.id.auction_title)
        TextView title;
        @BindView(R.id.auction_description)
        TextView description;
        @BindView(R.id.video)
        ImageView img;
        @BindView(R.id.play)
        ImageView play;
        @BindView(R.id.start_bidding)
        View view;
        /*@BindView(R.id.prod_img)
        ImageView image;
        @BindView(R.id.auc_start_date_label)
        TextView aucStartDateLabel;
        @BindView(R.id.note)
        TextView note;
        @BindView(R.id.card)
        CardView containerCard;
*/
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
