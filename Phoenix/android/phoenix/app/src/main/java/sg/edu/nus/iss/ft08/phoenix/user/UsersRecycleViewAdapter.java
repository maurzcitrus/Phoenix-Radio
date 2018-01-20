package sg.edu.nus.iss.ft08.phoenix.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.User;


/**
 * Inherits the Android Base RecyclerVIew Class and handles record list user interface
 * @see android.support.v7.widget.RecyclerView.Adapter
 * @author MuraliKrishnaB
 */
public class UsersRecycleViewAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    private final List<User> users;

    /**
     * Constructor to Initialise the User entity.
     * @param users
     */
    public UsersRecycleViewAdapter(List<User> users){
        if(users!=null){
            this.users=users;
        }
       else {
            this.users= new ArrayList<>();
        }
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_holder_user, parent, false);
        return new UsersViewHolder(view);
    }

    /**
     * Set the User attributs to be diaplyed on the Screen.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final UsersViewHolder holder, int position) {
        User current = users.get(position);

        holder.user = current;

        holder.txtUserID.setText(current.getUserId());
        holder.txtUserName.setText(current.getFullName());
        holder.txtPrgCount.setText(Integer.toString(current.getAssignedProgramSlot()));

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ControlFactory.getUserDetailController().startEditUseCase(holder.user);
            }
        });
    }

    /**
     * Number of rows that needs to be populated on the screen.
     *
     * @return Number of rows to be created.
     */
    @Override
    public int getItemCount() {
        return this.users.size();
    }


}

