package sg.edu.nus.iss.ft08.phoenix.radioprogram;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.phoenix.ControlFactory;
import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseActivity;
import sg.edu.nus.iss.ft08.phoenix.R;
import sg.edu.nus.iss.ft08.phoenix.model.RadioProgram;

public class RadioProgramsActivity extends PhoenixBaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_radio_programs);
    initCommonUi();
  }

  @Override
  protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    ControlFactory.getRadioProgramsController().onDisplay(this);
  }

  public void showRadioPrograms(List<RadioProgram> radioPrograms) {
    View v = findViewById(R.id.drawer_layout);
    RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_radioprograms);

    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    RadioProgramsRecyclerViewAdapter adapter = new RadioProgramsRecyclerViewAdapter(radioPrograms);
    recyclerView.setAdapter(adapter);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.radio_programs, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_create_new) {
      ControlFactory.getRadioProgramDetailController().startCreateUseCase();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

}
