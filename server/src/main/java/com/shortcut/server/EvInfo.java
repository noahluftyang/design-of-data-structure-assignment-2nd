package com.shortcut.server;

import java.io.BufferedReader;
import java.util.Calendar;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EvInfo {

  static final String csvFileDir = "data/elevator/";
  static String cvsSplitBy = ",";
  static final int floorCount = 15;
  static int[][][] floorEvCrowdData;
  static int[][][] floorEvCrowdDataDelta;
  static int[][][] floorEvWaitTime;
  static int lineCnt = 0;

  static public class WaitTime {
    public int min;
    public int max;
    public int avg;

    public WaitTime(int min, int max, int avg) {
      this.min = min;
      this.max = max;
      this.avg = avg;
    }

    public String ToString() {
      return "WaitTime Class- min: " + min + " max: " + max + " avg: " + avg;
    }
  }

  private static String GetDayOfWeek(int value) {
    String day = "";
    switch (value) {
    case 1:
      day = "sun";
      break;
    case 2:
      day = "mon";
      break;
    case 3:
      day = "tue";
      break;
    case 4:
      day = "wed";
      break;
    case 5:
      day = "thu";
      break;
    case 6:
      day = "fri";
      break;
    case 7:
      day = "sat";
      break;
    }
    return day;
  }

  static int todayWeekDay = -1;

  public static void Init() {
    if (todayWeekDay == Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
      return;
    todayWeekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    System.out.print("일간 데이터 로딩중...");

    floorEvCrowdData = new int[3][floorCount + 1][36001];
    floorEvCrowdDataDelta = new int[3][floorCount + 1][36001];
    floorEvWaitTime = new int[3][floorCount + 1][36001];
    String path = EvInfo.class.getResource("").getPath();
    String todayWeekDayS = GetDayOfWeek(todayWeekDay);
    InitEv(0, path + csvFileDir + todayWeekDayS + "_a.csv");
    InitEv(1, path + csvFileDir + todayWeekDayS + "_b.csv");
    InitEv(2, path + csvFileDir + todayWeekDayS + "_c.csv");
    System.out.print(todayWeekDayS + " 데이터 로딩 완료...\n");

    floorEvCrowdData = null;
    floorEvCrowdDataDelta = null;
  }

  static void InitEv(int evno, String filename) {
    BufferedReader br = null;
    String line = "";
    lineCnt = 0;
    try {
      br = new BufferedReader(new FileReader(filename));
      br.readLine();// drop first index line
      while ((line = br.readLine()) != null) {

        // use comma as separator
        String[] line_split = line.split(cvsSplitBy);
        for (int i = 1; i < floorCount + 1; i++) {

          try {
            floorEvCrowdData[evno][i][lineCnt] = Integer.parseInt(line_split[i]);
            // System.out.print(floorEvCrowdData[evno][i][lineCnt]);
          } catch (NumberFormatException e) {
            floorEvCrowdData[evno][i][lineCnt] = 0;
            System.out.print(floorEvCrowdData[0][i][lineCnt] + " wtf ");
          }

        }
        // System.out.println();
        lineCnt++;

      }

      // System.out.println(lineCnt);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    for (int i = 1; i < lineCnt; i++) {
      for (int j = 0; j < floorCount + 1; j++) {
        floorEvCrowdDataDelta[evno][j][i] = floorEvCrowdData[evno][j][i] - floorEvCrowdData[evno][j][i - 1];
        // System.out.print(floorEvCrowdDataDelta[evno][j][i] + " " );
      }
      for (int j = 0; j < floorCount + 1; j++) {
        floorEvWaitTime[evno][j][i] = floorEvCrowdData[evno][j][i] == 0 ? 0
            : (floorEvCrowdDataDelta[evno][j][i] < -4) ? 0 : (floorEvWaitTime[evno][j][i - 1] + 1);
      }
    }
  }

  static int GetWaitTimeData(int timeStamp, int floor, int evno) {
    if (timeStamp < 0)
      timeStamp += 3600;
    return floorEvWaitTime[evno][floor + 1][timeStamp];
  }

  static boolean IsNonexistentTime() {
    Calendar cal = Calendar.getInstance();
    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
      return true;
    if (cal.get(Calendar.HOUR_OF_DAY) < 8 || cal.get(Calendar.HOUR_OF_DAY) > 18)
      return true;
    return false;
  }

  public static WaitTime GetMoveTime(int evNo, int floorstart, int floorend) {
    Init();
    WaitTime wt = GetWaitTime(evNo, floorstart);
    int floordiff = floorstart - floorend;
    if (floordiff < 0)
      floordiff = -floordiff;
    wt.min += 10 * floordiff;
    wt.max += 10 * floordiff;
    wt.avg += 10 * floordiff;
    // System.out.print(wt.ToString() + "\n");
    return wt;
  }

  public static WaitTime GetWaitTime(int evNo, int floor) {
    evNo += 6;// 층 보정
    if (IsNonexistentTime())
      return new WaitTime(0, 6, 7);

    else {
      int now = Calendar.MINUTE * 60 + Calendar.SECOND;
      int min = 9999;
      int max = -9999;
      float sum = 0;
      for (int i = 0; i < 60; i++) {
        int data = GetWaitTimeData(now - i, floor, evNo);
        if (data < min)
          min = data;
        if (data > max)
          max = data;
        sum += data;
      }
      // System.out.print(new WaitTime(min, max ,(int)(sum/60)).ToString());
      return new WaitTime(min, max, (int) (sum / 60));

    }
  }

  /*
   * public static void main(String[] args) { EvInfo.Init();
   * EvInfo.GetWaitTime(0,0) System.out.print(.ToString()); }
   */
}