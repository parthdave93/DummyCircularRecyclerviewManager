package kapil.circularlayoutmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter for recycler view.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<Model> list;

    RecyclerViewAdapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        position = position % list.size();
        holder.event.setText(list.get(position).getEvent());
    }

    @Override
    public int getItemCount() {
        if(list==null)
            return 0;
        return Integer.MAX_VALUE;//list.size();
    }

    /**
     * View Holder for recycler view.
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView event, timings;

        MyViewHolder(View itemView) {
            super(itemView);
            event = (TextView) itemView.findViewById(R.id.event);
        }
    }
}
