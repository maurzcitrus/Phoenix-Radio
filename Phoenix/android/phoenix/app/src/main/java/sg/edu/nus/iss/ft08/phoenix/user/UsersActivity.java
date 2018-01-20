package sg.edu.nus.iss.ft08.phoenix.user;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.User;

/**
 * The User Activity Class which extends the PhoenixBaseActivity
 * Initialises and Loads the RecylerList View
 * @see sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity
 *
 * @author MuraliKrishnaB
 */
public class UsersActivity extends PhoenixBaseActivity {

  /**
   * Used to Iinitalise the Activity and the set the Content View For Display
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_users);

    initCommonUi();
  }

  /**
   * Calls the Display Method in the User Controller.
   *
   * @param savedInstanceState
   */
  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ControlFactory.getUsersController().onDisplay(this);
  }


  /**
   * Display the List of Users in RecyclerView.
   *
   * @param users
   */
  public void showUsers(List<User> users){

      View v = findViewById(R.id.drawer_layout);
      RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_users);

      recyclerView.setLayoutManager(new LinearLayoutManager(this));

      UsersRecycleViewAdapter adapter = new UsersRecycleViewAdapter(users);
      recyclerView.setAdapter(adapter);

  }

  /**
   * Loads the Menu TO Create and Delete the User
   *
   * @param menu
   * @return The menu Button is loaded
   *
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.users, menu);
    return true;
  }

  /**
   * Initiates the UserController StarCreateUseCase.
   *
   * @param item
   * @return boolean if the controller is initialised in the Control Factory.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_create_new) {
        ControlFactory.getUserDetailController().startCreateUseCase();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

}
