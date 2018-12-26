package com.shortcut.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

//����ȣ�� ���� ������ ���ȣ-2
//727ȣ�� ��� 25�࿡ �����ϹǷ� ��� ��ȣ�� 23
//������ ��� - ���� �Է�
//B4�� ��� -4
//186~188�� JSON ���� ��ȯ �ۼ� �ʿ�!!!

public class MainClass {
  public final static int INF = 10000;
  public final static int ELENUM = 3; // ���������� ����
  public final static int STAIRNUM = 4; // ��� ����
  public final static int MAX = 50; // �� ���� �ִ� ��� ����
  public final static int ONEFLOORSTAIRMOVETIME = 20; // �� �� ��� �̵� �ð�
  static String[] startFloorNodeName = new String[MAX]; // ����� ��� �̸�
  static String[] endFloorNodeName = new String[MAX]; // ������ ��� �̸�
  static int[][] startFloorLen = new int[MAX][MAX]; // ����� edge ����ġ ���� �迭
  static int[][] endFloorLen = new int[MAX][MAX]; // ������ edge ����ġ ���� �迭
  static int[][] elevatorExist = { { -1, 0, -1, -1, -1, -1, -1 }, { -1, 0, -1, -1, -1, -1, -1 },
      { -1, 0, -1, -1, -1, -1, 1 }, { 0, 1, 2, -1, -1, -1, 3 }, { 0, 1, 2, -1, -1, -1, 3 }, { 0, 1, 2, -1, -1, -1, 3 },
      { 0, 1, 2, 3, 4, 5, 6 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 },
      { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 },
      { 0, 1, 2, 3, 4, 5, -1 } };

  public static ResponseData getData(int startFloor, int endFloor, int startPlace, int endPlace) {
    int startFloorNodeNum, endFloorNodeNum;
    RecommendObject directMove = new RecommendObject();
    RecommendObject undirectMove = new RecommendObject();
    RecommendObject finalRecommend = new RecommendObject();
    RecommendObject temp = new RecommendObject();
    // Scanner scan = new Scanner(System.in);
    int[][] tempelevatorExist = new int[6][7];

    EvInfo.Init();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        tempelevatorExist[i][j] = elevatorExist[i][j];
      }
    }

    while (true) {
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
          elevatorExist[i][j] = tempelevatorExist[i][j];
        }
      }

      // System.out.print("�����: ");
      // int startFloor = scan.nextInt();
      startFloorNodeNum = readCSV(startFloor, true);
      // System.out.print("����� ����ȣ: ");
      // int startPlace = scan.nextInt();
      // System.out.print("������: ");
      // int endFloor = scan.nextInt();
      endFloorNodeNum = readCSV(endFloor, false);
      // System.out.print("������ ��� ��ȣ: ");
      // int endPlace = scan.nextInt();

      // CSV���� �Է� �� ��Ÿ �ִ��� üũ
      for (int i = 0; i < startFloorNodeNum; i++) {
        for (int j = 0; j < startFloorNodeNum; j++) {
          if (startFloorLen[i][j] != startFloorLen[j][i])
            System.out.println(i + " : " + j);
        }
      }

      System.out.println("<1�� ���� ���� �ʴ� ���>");
      directMove = calculateShortestTime(startFloor, endFloor, startPlace, endPlace, startFloorNodeNum,
          endFloorNodeNum);

      for (int i = 0; i < directMove.move.size(); i++)
        System.out.print(directMove.move.elementAt(i));
      System.out.println(" ��� ��õ");
      System.out.println("���� �ҿ� �ð�: " + directMove.requiredTime);
      for (int i = 0; i < directMove.path.size(); i++) {
        if (i == 0)
          System.out.print(directMove.path.elementAt(i));
        else
          System.out.print("->" + directMove.path.elementAt(i));
      }
      System.out.println("");
      System.out.println("------------------------");

      if (startFloor * endFloor < 0 && startFloor != 1 && endFloor != 1) {
        // if (Math.min(startFloor, endFloor) != -5 && Math.min(startFloor, endFloor) !=
        // -6) {
        if (Math.min(startFloor, endFloor) >= 1) {
          for (int i = 2; i < 6; i++) {
            elevatorExist[i][0] = -1;
            elevatorExist[i][1] = -1;
            elevatorExist[i][2] = -1;
          }

          startFloorNodeNum = readCSV(startFloor, true);
          endFloorNodeNum = readCSV(1, false);
          /*
           * for(int i=0; i<6; i++) { for(int j=0; j<7; j++) {
           * System.out.print(elevatorExist[i][j]); } System.out.println(""); }
           */
          System.out.println("<��������� 1������>");
          undirectMove = calculateShortestTime(startFloor, 1, startPlace, 6, startFloorNodeNum, endFloorNodeNum);

          for (int i = 0; i < undirectMove.move.size(); i++)
            System.out.print(undirectMove.move.elementAt(i));
          System.out.println(" ��� ��õ");
          System.out.println("���� �ҿ� �ð�: " + undirectMove.requiredTime);
          for (int i = 0; i < undirectMove.path.size(); i++) {
            if (i == 0)
              System.out.print(undirectMove.path.elementAt(i));
            else
              System.out.print("->" + undirectMove.path.elementAt(i));
          }
          System.out.println("");
          System.out.println("------------------------");

          startFloorNodeNum = readCSV(1, true);
          endFloorNodeNum = readCSV(endFloor, false);

          System.out.println("<1������ ����������>");
          temp = calculateShortestTime(1, endFloor, 6, endPlace, startFloorNodeNum, endFloorNodeNum);

          for (int i = 0; i < temp.move.size(); i++)
            System.out.print(temp.move.elementAt(i));
          System.out.println(" ��� ��õ");
          System.out.println("���� �ҿ� �ð�: " + temp.requiredTime);
          for (int i = 0; i < temp.path.size(); i++) {
            if (i == 0)
              System.out.print(temp.path.elementAt(i));
            else
              System.out.print("->" + temp.path.elementAt(i));
          }
          System.out.println("");
          System.out.println("------------------------");

          temp.path.remove(0);
          undirectMove.move.add(temp.move.elementAt(0));
          undirectMove.requiredTime += temp.requiredTime;
          for (int i = 0; i < temp.path.size(); i++)
            undirectMove.path.add(temp.path.elementAt(i));

          System.out.println("<1�� �����ϴ� ���>");
          String sstartFloor, sendFloor;
          for (int i = 0; i < undirectMove.move.size(); i++) {
            if (startFloor >= 1)
              sstartFloor = startFloor + "F";
            else
              sstartFloor = "B" + Math.abs(startFloor) + "F";
            if (endFloor >= 1)
              sendFloor = endFloor + "F";
            else
              sendFloor = "B" + Math.abs(endFloor) + "F";
            if (i == 0)
              System.out.print(undirectMove.move.elementAt(i) + "(" + sstartFloor + "~1F) ");
            else if (i == 1)
              System.out.print(undirectMove.move.elementAt(i) + "(1F~" + sendFloor + ")");
          }
          System.out.println(" ��� ��õ");
          System.out.println("���� �ҿ� �ð�: " + undirectMove.requiredTime);
          for (int i = 0; i < undirectMove.path.size(); i++) {
            if (i == 0)
              System.out.print(undirectMove.path.elementAt(i));
            else
              System.out.print("->" + undirectMove.path.elementAt(i));
          }
          System.out.println("");
          System.out.println("------------------------");
        }
      }

      if (directMove.requiredTime <= undirectMove.requiredTime)
        finalRecommend = directMove;
      else
        finalRecommend = undirectMove;

      System.out.println("<���� ��õ>");
      for (int i = 0; i < finalRecommend.move.size(); i++)
        System.out.print(finalRecommend.move.elementAt(i));
      System.out.println(" ��� ��õ");
      System.out.println("���� �ҿ� �ð�: " + finalRecommend.requiredTime);
      for (int i = 0; i < finalRecommend.path.size(); i++) {
        if (i == 0)
          System.out.print(finalRecommend.path.elementAt(i));
        else
          System.out.print("->" + finalRecommend.path.elementAt(i));
      }
      System.out.println("");

      /*
       * ��� �̵�����: finalRecommend.move(���� ���� ������ �Ѱ�, ���� �ϸ� �ΰ�)(Strin ��
       * 
       * Vector) �� �ҿ�ð�: finalRecommend.requiredTime(int��) �̵� ���:
       *  finalRecommend.path(String�� Vector)
       */

      return new ResponseData(finalRecommend.move, finalRecommend.requiredTime, finalRecommend.path);
    }
  }

  public static RecommendObject calculateShortestTime(int startFloor, int endFloor, int startPlace, int endPlace,
      int startFloorNodeNum, int endFloorNodeNum) {
    int[] startFloorTime = new int[MAX];
    int[] endFloorTime = new int[MAX];
    boolean[] startFloorVisited = new boolean[MAX];
    boolean[] endFloorVisited = new boolean[MAX];
    int[] startFloorPred = new int[MAX];
    int[] endFloorPred = new int[MAX];
    Vector<String> path = new Vector<String>();
    Vector<String> temp = new Vector<String>();

    for (int i = 0; i < startFloorNodeNum; i++) {
      startFloorVisited[i] = false;
      startFloorPred[i] = -1;
      for (int j = 0; j < startFloorNodeNum; j++) {
        if (startFloorLen[i][j] == 0)
          startFloorLen[i][j] = INF;
      }
    }

    for (int i = 0; i < endFloorNodeNum; i++) {
      endFloorVisited[i] = false;
      endFloorPred[i] = -1;
      for (int j = 0; j < endFloorNodeNum; j++) {
        if (endFloorLen[i][j] == 0)
          endFloorLen[i][j] = INF;
      }
    }

    int min, minPos;
    startFloorTime = startFloorLen[startPlace];
    startFloorVisited[startPlace] = true;
    startFloorTime[startPlace] = 0;
    for (int i = 0; i < startFloorNodeNum; i++) {
      if (startFloorTime[i] != INF)
        startFloorPred[i] = startPlace;
    }

    endFloorTime = endFloorLen[endPlace];
    endFloorVisited[endPlace] = true;
    endFloorTime[endPlace] = 0;
    for (int i = 0; i < endFloorNodeNum; i++) {
      if (endFloorTime[i] != INF)
        endFloorPred[i] = endPlace;
    }

    for (int counter = 0; counter < startFloorNodeNum - 1; counter++) {
      min = INF;
      minPos = -1;
      for (int i = 0; i < startFloorNodeNum; i++) {
        if (min > startFloorTime[i] && !startFloorVisited[i]) {
          min = startFloorTime[i];
          minPos = i;
        }
      }

      startFloorVisited[minPos] = true;

      for (int i = 0; i < startFloorNodeNum; i++) {
        if (!startFloorVisited[i]) {
          if (min + startFloorLen[minPos][i] < startFloorTime[i]) {
            startFloorTime[i] = min + startFloorLen[minPos][i];
            startFloorPred[i] = minPos;
          }
        }
      }
    }

    for (int counter = 0; counter < endFloorNodeNum - 1; counter++) {
      min = INF;
      minPos = -1;
      for (int i = 0; i < endFloorNodeNum; i++) {
        if (min > endFloorTime[i] && !endFloorVisited[i]) {
          min = endFloorTime[i];
          minPos = i;
        }
      }

      endFloorVisited[minPos] = true;

      for (int i = 0; i < endFloorNodeNum; i++) {
        if (!endFloorVisited[i]) {
          if (min + endFloorLen[minPos][i] < endFloorTime[i]) {
            endFloorTime[i] = min + endFloorLen[minPos][i];
            endFloorPred[i] = minPos;
          }
        }
      }
    }

    int j;
    int[] totalTime = new int[ELENUM + STAIRNUM];
    for (int i = 0; i < ELENUM + STAIRNUM; i++)
      totalTime[i] = INF;

    int tempstartFloor, tempendFloor;
    if (startFloor < 1)
      tempstartFloor = startFloor + 6;
    else
      tempstartFloor = startFloor + 5;
    if (endFloor < 1)
      tempendFloor = endFloor + 6;
    else
      tempendFloor = endFloor + 5;

    for (int i = 0; i < ELENUM + STAIRNUM; i++) {
      if (elevatorExist[tempstartFloor][i] != -1 && elevatorExist[tempendFloor][i] != -1) {
        // System.out.println(startFloorNodeName[elevatorExist[tempstartFloor][i]] + "
        // ���: ");

        // System.out.println("�����~"+
        // startFloorNodeName[elevatorExist[tempstartFloor][i]] + ": "+
        // startFloorTime[elevatorExist[tempstartFloor][i]]);
        // System.out.print(startFloorNodeName[elevatorExist[tempstartFloor][i]]);
        j = elevatorExist[tempstartFloor][i];
        do {
          j = startFloorPred[j];
          // System.out.print("<-"+startFloorNodeName[j]);
        } while (j != startPlace);
        // System.out.println("");

        // System.out.println(startFloorNodeName[elevatorExist[tempstartFloor][i]] + "
        // ���+�̵� �ð�: "+floorMoveTime(i, startFloor, endFloor));

        // System.out.println(endFloorNodeName[elevatorExist[tempendFloor][i]] +
        // "~������: "+ endFloorTime[elevatorExist[tempendFloor][i]]);
        // System.out.print(endFloorNodeName[elevatorExist[tempendFloor][i]]);
        j = elevatorExist[tempendFloor][i];
        do {
          j = endFloorPred[j];
          // System.out.print("->"+endFloorNodeName[j]);
        } while (j != endPlace);
        // System.out.println("");

        // totalTime[i] = startFloorTime[elevatorExist[tempstartFloor][i]] +
        // floorMoveTime(i, startFloor, endFloor)
        // + endFloorTime[elevatorExist[tempendFloor][i]];
        totalTime[i] = startFloorTime[elevatorExist[tempstartFloor][i]] + endFloorTime[elevatorExist[tempendFloor][i]];
        // totalTime[i]=totalTime[i]+floorMoveTime(i, startFloor, endFloor);
        totalTime[i] += totalTime[i] + 30;
        System.out.println(
            startFloorNodeName[elevatorExist[tempstartFloor][i]] + " ���� �ҿ� �ð�: " + totalTime[i]);
        // System.out.println("");
      }
    }

    int minTime = INF, minElevator = -1;
    for (int i = 0; i < ELENUM + STAIRNUM; i++) {
      if (minTime > totalTime[i]) {
        minTime = totalTime[i];
        minElevator = i;
      }
    }

    try {
      temp.add(startFloorNodeName[elevatorExist[tempstartFloor][minElevator]]);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("tempstartFloor" + tempstartFloor);
      System.out.println("minElevator" + minElevator);
      System.out.println("elevatorExist" + elevatorExist[tempstartFloor][minElevator]);
    }

    j = elevatorExist[tempstartFloor][minElevator];
    do {
      j = startFloorPred[j];
      temp.add(startFloorNodeName[j]);
    } while (j != startPlace);
    for (int i = temp.size() - 1; i >= 0; i--)
      path.add(temp.elementAt(i));

    j = elevatorExist[tempendFloor][minElevator];
    do {
      j = endFloorPred[j];
      path.add(endFloorNodeName[j]);
    } while (j != endPlace);

    if (path.elementAt(0).equals(path.elementAt(1)))
      path.remove(0);
    if (path.elementAt(path.size() - 2).equals(path.elementAt(path.size() - 1)))
      path.remove(path.size() - 1);

    RecommendObject recommendobject = new RecommendObject();
    recommendobject.move.add(startFloorNodeName[elevatorExist[tempstartFloor][minElevator]]);
    recommendobject.requiredTime = minTime;
    recommendobject.path = path;
    return recommendobject;
  }

  public static int floorMoveTime(int eleNum, int startFloor, int endFloor) {
    if (eleNum <= 2) // ���������� �̿� ���+�̵��ð�
      return EvInfo.GetMoveTime(eleNum, startFloor, endFloor).avg;
    else { // ��� �̿� ���+�̵��ð�
      if (startFloor * endFloor < 0)
        return (Math.abs(startFloor - endFloor) - 1) * ONEFLOORSTAIRMOVETIME;
      else
        return Math.abs(startFloor - endFloor) * ONEFLOORSTAIRMOVETIME;
    }
  }

  public static int readCSV(int floor, boolean isStartFloor) {
    String csvFile;
    String path = MainClass.class.getResource("").getPath();

    if (floor >= 1)
      csvFile = path + "data/floor/" + floor + "F.csv";
    // csvFile = ".\"+floor+"F.csv"; (�����)
    else {
      floor = -floor;
      csvFile = path + "data/floor/B" + floor + "F.csv";
      // csvFile = ".\"+"B"+floor+"F.csv"; (�����)
    }

    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int row = 0;
    int num = 0;

    try {
      br = new BufferedReader(new FileReader(csvFile));
      line = br.readLine();
      String[] nodeContent = line.split(csvSplitBy);
      num = nodeContent.length;
      if (isStartFloor) {
        for (int col = 0; col < num - 1; col++)
          startFloorNodeName[col] = nodeContent[col + 1];
      } else {
        for (int col = 0; col < num - 1; col++)
          endFloorNodeName[col] = nodeContent[col + 1];
      }

      while ((line = br.readLine()) != null) {
        String[] lenContent = line.split(csvSplitBy);
        if (isStartFloor) {
          for (int col = 0; col < num - 1; col++)
            startFloorLen[row][col] = Integer.parseInt(lenContent[col + 1]);
        } else {
          for (int col = 0; col < num - 1; col++)
            endFloorLen[row][col] = Integer.parseInt(lenContent[col + 1]);
        }
        row++;
      }

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
    return num - 1;
  }

  public MainClass() {
  }
}
