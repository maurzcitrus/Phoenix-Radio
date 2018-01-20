package sg.edu.nus.iss.ft08.phoenix.model;

import android.graphics.drawable.Drawable;

import sg.edu.nus.iss.ft08.phoenix.PhoenixBaseController;

public class DashboardItem {
  private Drawable icon;
  private String name;
  private int count;
  private PhoenixBaseController controller;

  public DashboardItem(PhoenixBaseController controller) {
    this.controller = controller;
  }

  public Drawable getIcon() {
    return icon;
  }

  public void setIcon(Drawable icon){
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public int getCount() {
    return count;
  }

  public void startUseCase() {
    controller.startUseCase();
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
