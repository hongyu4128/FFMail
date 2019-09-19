package com.yhhy.FFMail.setting.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhhy.FFMail.setting.common.bo.FlagResultMessage;
import com.yhhy.FFMail.setting.common.bo.exceptions.JSONNumberCastException;
import com.yhhy.FFMail.setting.common.bo.exceptions.JSONStringCastException;

public class JsonInterfaceTool {

  /**
   * 返回flag型标志
   * 
   * @param succ
   * @param msg
   * @return
   */
  public static JSONObject isSucceed(Boolean succ, Object msg) {
    JSONObject object = new JSONObject();
    object.put("succeed", succ);
    object.put("data", msg);
    return object;
  }

  /**
   * 返回flag型标志
   * 
   * @param succ
   * @param msg
   * @return
   */
  public static JSONObject isSucceed(Boolean succ, Object msg, String warning) {
    JSONObject object = new JSONObject();
    object.put("succeed", succ);
    object.put("data", msg);
    if (StringUtil.isNotEmpty(warning)) {
      object.put("warning", warning);
    }
    return object;
  }

  /**
   * 处理成功
   * 
   * @param msg: 消息内容
   * @return
   */
  public static JSONObject succeed(Object msg) {
    return isSucceed(true, msg);
  }

  /**
   * 处理失败
   * 
   * @param msg: 消息内容
   * @return
   */
  public static JSONObject fail(Object msg) {
    return isSucceed(false, msg);
  }

  public static JSONObject retFlagResult(FlagResultMessage r) {
    return isSucceed(r.getResult(), r.getMsg());
  }

  /**
   * 返回单条数据
   * 
   * {succeed: true, data{key1: val1, key2: val2}}
   * 
   * @param object
   * @return
   */
  public static JSONObject retOneRecord(Object object) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("succeed", true);
    // jsonObject.put("data", JSONObject.toJSONString(object));
    jsonObject.put("data", object);
    return jsonObject;
  }

  /**
   * 通过对象集合返回数组
   * 
   * @param list
   * @return
   */
  public static JSONObject retEntityListForGrid(List<?> list) {
    JSONObject ret = new JSONObject();
    JSONArray a = new JSONArray();
    list.forEach(n -> a.add(n));
    ret.put("succeed", true);
    ret.put("data", a);
    ret.put("dataCount", a.size());
    return ret;
  }

  /**
   * 通过对象集合返回数组
   * 
   * @param list
   * @param totalDataCount
   * @return
   */
  public static JSONObject retEntityListForGrid(List<?> list, int totalDataCount) {
    JSONObject ret = new JSONObject();
    JSONArray a = new JSONArray();
    list.forEach(n -> a.add(n));
    ret.put("succeed", true);
    ret.put("data", a);
    ret.put("dataCount", totalDataCount);
    return ret;
  }

  /**
   * 通过对象集合返回数组
   * 
   * @param list
   * @param totalDataCount
   * @return
   */
  public static JSONObject retEntityListForTable(List<String> title, List<Map<String, String>> datas,
      int totalDataCount) {
    JSONObject ret = new JSONObject();
    JSONArray titleJson = new JSONArray();
    title.forEach(n -> titleJson.add(n));
    ret.put("succeed", true);
    ret.put("title", titleJson);
    ret.put("dataCount", totalDataCount);

    JSONArray dataJson = new JSONArray();
    datas.forEach(row -> {
      dataJson.add(row);
    });
    ret.put("data", dataJson);

    return ret;
  }

  /**
   * 将接口字段转换成列表(String型)
   * 
   * @param interfaceData
   * @return List<String>
   * @throws JSONStringCastException
   */
  public static List<String> getListString(String interfaceData) throws JSONStringCastException {
    try {
      JSONArray jsonArray = JSONArray.parseArray(interfaceData);
      List<String> ret = new ArrayList<String>();
      jsonArray.forEach(n -> ret.add(String.valueOf(n)));
      return ret;
    } catch (Exception e) {
      throw new JSONStringCastException(interfaceData);
    }
  }

  /**
   * 将接口字段转换成列表(Double型)
   * 
   * @param interfaceData
   * @return List<Double>
   * @throws JSONNumberCastException
   */
  public static List<Double> getListNumber(String interfaceData) throws JSONNumberCastException {
    try {
      JSONArray jsonArray = JSONArray.parseArray(interfaceData);
      List<Double> ret = new ArrayList<Double>();
      jsonArray.forEach(n -> ret.add(Double.parseDouble(String.valueOf(n))));
      return ret;
    } catch (Exception e) {
      throw new JSONNumberCastException(interfaceData);
    }
  }

}
