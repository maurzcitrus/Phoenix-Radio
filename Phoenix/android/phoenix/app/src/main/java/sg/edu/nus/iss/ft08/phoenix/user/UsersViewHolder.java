package sg.edu.nus.iss.ft08.phoenix.user;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * Handles individual record list item user interface and its corresponding events
 *
 * @author MuraliKrishnaB
 */
public class UsersViewHolder extends RecyclerView.ViewHolder {

    public final View view;
    public final TextView txtUserName;
    public final TextView txtUserID;
    public final TextView txtPrgCount;
    public User user;

    /**
     * Initialise the UserId and UserName Text Field.
     * @param itemView
     */
    public UsersViewHolder(View itemView){
        super(itemView);

        view = itemView;
        txtUserID = (TextView) itemView.findViewById(R.id.user_ID);
        txtUserName = (TextView) itemView.findViewById(R.id.user_name);
        txtPrgCount = (TextView) itemView.findViewById(R.id.programslot_count_user);
    }
}
