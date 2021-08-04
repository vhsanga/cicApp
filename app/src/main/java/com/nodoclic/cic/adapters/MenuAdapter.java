package com.nodoclic.cic.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nodoclic.cic.R;
import com.nodoclic.cic.clases.RsMenu;

import java.util.List;

public class MenuAdapter  extends RecyclerView.Adapter<MenuAdapter.ViewHolder>  {
    private List<RsMenu> localDataSet;


    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public MenuAdapter(List<RsMenu> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_menu, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final RsMenu menuItem = localDataSet.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.nombre.setText(menuItem.getNombre());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView nombre;
        RsMenu objMenu;

        public ViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.txvItemMenu);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });


        }
    }

}
