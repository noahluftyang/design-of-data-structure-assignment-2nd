package com.shortcut.server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RouterController {
  public final static int INF = 10000;
  public final static int ELENUM = 3;
  public final static int STAIRNUM = 4;
  public final static int MAX = 50;
  public final static int ONEFLOORSTAIRMOVETIME = 20;
  static String[] startFloorNodeName = new String[MAX];
  static String[] endFloorNodeName = new String[MAX];
  static int[][] startFloorLen = new int[MAX][MAX];
  static int[][] endFloorLen = new int[MAX][MAX];
  static int[][] elevatorExist = { { -1, 0, -1, -1, -1, -1, -1 }, { -1, 0, -1, -1, -1, -1, -1 },
      { -1, 0, -1, -1, -1, -1, 1 }, { 0, 1, 2, -1, -1, -1, 3 }, { 0, 1, 2, -1, -1, -1, 3 }, { 0, 1, 2, -1, -1, -1, 3 },
      { 0, 1, 2, 3, 4, 5, 6 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 },
      { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 }, { 0, 1, 2, 3, 4, 5, -1 },
      { 0, 1, 2, 3, 4, 5, -1 } };

  @CrossOrigin
  @RequestMapping(value = "/", method = RequestMethod.GET)
  Response hello() {
    Response response = new Response();
    response.setMessage("Hello world!");

    return response;
  }

  @CrossOrigin
  @RequestMapping(value = "/path", method = RequestMethod.GET)
  Response pathInfo(@RequestParam String startFloor, @RequestParam String endFloor, @RequestParam String startPlace,
      @RequestParam String endPlace) {

    MainClass mainAlgorithm = new MainClass();
    ResponseData data = mainAlgorithm.getData(Integer.parseInt(startFloor), Integer.parseInt(endFloor),
        Integer.parseInt(startPlace), Integer.parseInt(endPlace));

    Response response = new Response();
    // String[] path = { "727", "BElevator" };
    // ResponseData data = new ResponseData("BElevator", "12min", path);

    response.setData(data);
    response.setMessage("success");

    System.out.println(startFloor);
    System.out.println(endFloor);
    System.out.println(startPlace);
    System.out.println(endPlace);

    return response;
  }
}