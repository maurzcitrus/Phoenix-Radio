package sg.edu.nus.iss.ft08.phoenix.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.DashboardItem;

public class DashboardItemViewHolder extends RecyclerView.ViewHolder {
  public final View view;
  public final ImageView icon;
  public final TextView txtName;
  public final TextView txtCount;
  public DashboardItem item;

  public DashboardItemViewHolder(View itemView){
    super(itemView);

    view = itemView;
    txtName = (TextView) itemView.findViewById(R.id.dashboard_item_name);
    txtCount = (TextView) itemView.findViewById(R.id.dashboard_item_count);
    icon = (ImageView) itemView.findViewById(R.id.dashboard_item_icon);
  }
}
