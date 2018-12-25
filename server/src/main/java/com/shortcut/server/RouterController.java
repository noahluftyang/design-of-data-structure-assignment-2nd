package com.shortcut.server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RouterController {

  @CrossOrigin
  @RequestMapping(value = "/", method = RequestMethod.GET)
  Response hello() {
    Response response = new Response();
    response.setMessage("Hello world!");

    return response;
  }

  @CrossOrigin
  @RequestMapping(value = "/path", method = RequestMethod.GET)
  Response requestMethodName(@RequestParam String startFloor, @RequestParam String endFloor,
      @RequestParam String startPlace, @RequestParam String endPlace) {
    Response response = new Response();
    String[] path = { "727", "BElevator" };
    ResponseData data = new ResponseData("BElevator", "12min", path);

    response.setData(data);
    response.setMessage("success");

    System.out.println(startFloor);
    System.out.println(endFloor);
    System.out.println(startPlace);
    System.out.println(endPlace);

    return response;
  }
}