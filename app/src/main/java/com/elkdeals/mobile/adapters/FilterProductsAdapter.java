package com.elkdeals.mobile.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elkdeals.mobile.R;
import com.elkdeals.mobile.api.models.filter_model.get_filters.FilterDetails;
import com.elkdeals.mobile.api.models.filter_model.get_filters.FilterOption;
import com.elkdeals.mobile.api.models.filter_model.get_filters.FilterValue;
import com.elkdeals.mobile.api.models.filter_model.post_filters.FiltersAttributes;

import java.util.List;


/**
 * FilterProductsAdapter is the adapter class of RecyclerView holding List of Filters in FilterDialog
 **/

public class FilterProductsAdapter extends RecyclerView.Adapter<FilterProductsAdapter.MyViewHolder>{

    Context context;
    List<FilterDetails> filtersList;
    List<FiltersAttributes> filtersAttributesList;
    FilterProductAttributesAdapter filterProductAttributesAdapter;


    public FilterProductsAdapter(Context context, List<FilterDetails> filtersList, List<FiltersAttributes> filtersAttributesList) {
        this.context = context;
        this.filtersList = filtersList;
        this.filtersAttributesList = filtersAttributesList;
    }



    //********** Called to Inflate a Layout from XML and then return the Holder *********//

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate the custom layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_filters, parent, false);

        return new MyViewHolder(itemView);
    }



    //********** Called by RecyclerView to display the Data at the specified Position *********//

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // Get the data model based on Position
        FilterDetails filterDetails = filtersList.get(position);

        FilterOption filterOption = filterDetails.getOption();
        List<FilterValue> filterValues = filterDetails.getValues();

        holder.filter_name.setText(filterOption.getName());


        // Adapter for Attribute Values
        filterProductAttributesAdapter = new FilterProductAttributesAdapter(context, filterValues, filterOption.getName(), filtersAttributesList);
        holder.filter_attributes_recycler.setLayoutManager(new LinearLayoutManager(context));

        // Set adapter to AttributeValues RecyclerView
        holder.filter_attributes_recycler.setAdapter(filterProductAttributesAdapter);
    }



    //********** Returns the total number of items in the data set *********//

    @Override
    public int getItemCount() {
        return filtersList.size();
    }



    /********** Custom ViewHolder provides a direct reference to each of the Views within a Data_Item *********/

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView filter_name;
        public RecyclerView filter_attributes_recycler;


        public MyViewHolder(final View itemView) {
            super(itemView);
            filter_name = itemView.findViewById(R.id.filter_name);
            filter_attributes_recycler = itemView.findViewById(R.id.filter_attributes_recycler);
        }
    }
}

