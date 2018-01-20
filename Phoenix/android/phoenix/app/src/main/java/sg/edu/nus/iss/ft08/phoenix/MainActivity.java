package sg.edu.nus.iss.ft08.phoenix;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.home.DashboardRecyclerViewAdapter;
import sg.edu.nus.iss.ft08.phoenix.model.DashboardItem;
import sg.edu.nus.iss.ft08.phoenix.model.Summary;
import sg.edu.nus.iss.ft08.phoenix.radioprogram.RadioProgramsRecyclerViewAdapter;

public class MainActivity extends PhoenixBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initCommonUi();

  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getMainController().onDisplay(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private List<DashboardItem> getDashboardItems(Summary summary) {
    List<DashboardItem> items = new ArrayList<>();

    PhoenixBaseController myProfileController = ControlFactory.getMyProfileController();
    DashboardItem forMyProfile = new DashboardItem(myProfileController);
    forMyProfile.setName("My Profile");
    String uri = "@drawable/ic_menu_myprofile";
    int userIconId = getResources().getIdentifier(uri, null, getPackageName());
    Drawable userIcon = getResources().getDrawable(userIconId);
    forMyProfile.setIcon(userIcon);

    PhoenixBaseController usersController = ControlFactory.getUsersController();
    DashboardItem forUsers = new DashboardItem(usersController);
    forUsers.setName("Users");
    forUsers.setCount(summary.getTotalUsers());
    uri = "@drawable/ic_menu_users";
    userIconId = getResources().getIdentifier(uri, null, getPackageName());
    userIcon = getResources().getDrawable(userIconId);
    forUsers.setIcon(userIcon);

    PhoenixBaseController schedulesController = ControlFactory.getSchedulesController();
    DashboardItem forSchedules = new DashboardItem(schedulesController);
    forSchedules.setName("Schedules");
    forSchedules.setCount(summary.getTotalProgramSlots());
    uri = "@drawable/ic_menu_schedules";
    int scheduleIconId = getResources().getIdentifier(uri, null, getPackageName());
    Drawable scheduleIcon = getResources().getDrawable(scheduleIconId);
    forSchedules.setIcon(scheduleIcon);

    PhoenixBaseController programsController = ControlFactory.getRadioProgramsController();
    DashboardItem forPrograms = new DashboardItem(programsController);
    forPrograms.setName("Radio Programs");
    forPrograms.setCount(summary.getTotalRadioPrograms());
    uri = "@drawable/ic_menu_radio_programs";
    int programIconId = getResources().getIdentifier(uri, null, getPackageName());
    Drawable programIcon = getResources().getDrawable(programIconId);
    forPrograms.setIcon(programIcon);


    items.add(forMyProfile);

    items.add(forSchedules);

    if(currentUser.isAdmin() || currentUser.isManager()){
      items.add(forPrograms);
      items.add(forUsers);
    }

    return items;
  }

  public void showDashboardItems(Summary summary) {

    List<DashboardItem> items = getDashboardItems(summary);

    View v = findViewById(R.id.drawer_layout);
    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_dashboard_items);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    DashboardRecyclerViewAdapter adapter = new DashboardRecyclerViewAdapter(items);
    recyclerView.setAdapter(adapter);
  }
}
