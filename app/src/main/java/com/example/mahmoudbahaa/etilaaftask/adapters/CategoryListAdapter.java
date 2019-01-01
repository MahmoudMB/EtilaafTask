package com.example.mahmoudbahaa.etilaaftask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mahmoudbahaa.etilaaftask.R;
import com.example.mahmoudbahaa.etilaaftask.models.Category;

import java.util.List;

/**
 * Created by MahmoudBahaa on 30/12/2018.
 */

public class CategoryListAdapter   extends RecyclerView.Adapter<CategoryListAdapter.CategoryListHolder> {

    private final Context context;
    private List<Category> categories;
    private int currentSelected = 0;


    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public CategoryListAdapter(List<Category> categories, Context context,ListItemClickListener listener) {
        this.categories = categories;
        this.context = context;
        mOnClickListener = listener;
    }


    @Override
    public CategoryListHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category_list, parent, false);
        return new CategoryListHolder(v);
    }


    @Override
    public void onBindViewHolder(final CategoryListHolder holder, final int position) {

       /* if(photos.get(position).isClose())
            holder.name.setText(photos.get(position).getName());
        */

        holder.name.setText(categories.get(position).getName());


     if (categories.get(position).getSelected())
     {
         /*
         ViewGroup.LayoutParams params = holder.line.getLayoutParams();
         params.height = 10;
         holder.line.setLayoutParams(params);*/
         holder.line.setVisibility(View.VISIBLE);
     }

     else {
         holder.line.setVisibility(View.GONE);


     }


       holder.item.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if (currentSelected != position)
        {

            categories.get(currentSelected).setSelected(false);
            categories.get(position).setSelected(true);

            notifyDataSetChanged();

            currentSelected = position;

            Log.v("cliked","Clikedc");
            mOnClickListener.onListItemClick(position);

        }
    }
});



    }

    @Override
    public int getItemCount() {

        return categories.size();
    }

    public class CategoryListHolder extends RecyclerView.ViewHolder {
       RelativeLayout item;
        TextView name;
        View line;



        public CategoryListHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.CategoryList_name);
            line = itemView.findViewById(R.id.CategoryList_line);
            item = itemView.findViewById(R.id.CategoryList_item);
        }



    }

}



