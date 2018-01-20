package sg.edu.nus.iss.ft08.phoenix.schedule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.ProgramSlot;

/**
 * The UserSchedulesActivity Class which extends the PhoenixBaseActivity
 * Initialises and Loads the RecyclerList View
 *
 * @author Chandramouli
 * @see sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity
 */
public class UserSchedulesActivity extends PhoenixBaseActivity {

  private RecyclerView recyclerView;
  private SchedulesRecyclerViewAdapter adapter;

  /**
   * Used to Initialise the Activity and the set the Content View For Display
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_schedules);
    initCommonUi();
  }

  /**
   * Calls the DisplayTargetUser Method in the Schedule Controller.
   *
   * @param savedInstanceState
   */

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getSchedulesController().onDisplayTargetUser(this);
  }


  /**
   * Display the List of Schedules for respective user in RecyclerView.
   *
   * @param slots
   */
  public void showSchedules(List<ProgramSlot> slots) {
    View v = findViewById(R.id.drawer_layout);
    recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_schedules);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new SchedulesRecyclerViewAdapter(slots);
    recyclerView.setAdapter(adapter);
  }
}
