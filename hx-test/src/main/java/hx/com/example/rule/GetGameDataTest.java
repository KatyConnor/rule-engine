package hx.com.example.rule;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
*@author yangjunxiang  
*@date 2018年4月17日上午11:57:38 
*@version 1.0  
*获取游戏区服数据测试
*-------测试获取的数据与蔡洋开发的后台的数据是否一致，数据来源代练通
*/
public class GetGameDataTest {

	public static void main(String[] args) throws Exception {
//		test1();
		System.out.println(~1);
		//Objects.hashCode(key) ^ Objects.hashCode(value)
	}
	
//	@Test
	public static void test1() throws Exception {
		String bodystr = "{\"gamezoneserver\":[{\"ZoneServerID\":\"100100010000000\",\"ServerName\":\"英雄联盟/电信/艾欧尼亚\"},{\"ZoneServerID\":\"100100010000100\",\"ServerName\":\"英雄联盟/电信/祖安\"}]}";//getGameDataTest();
		System.out.println(bodystr);
		JSONObject object = (JSONObject) JSONObject.parse(bodystr);
		JSONArray jsonObject =  (JSONArray) JSONArray.parse(object.get("gamezoneserver").toString());
//		JSONArray jsonObject = (JSONArray) JSONArray.parse(object);
		for (int j = 0; j < jsonObject.size(); j++) {
			JSONObject obj = jsonObject.getJSONObject(j);
			String ZoneServerID = obj.getString("ZoneServerID");
			System.out.print(ZoneServerID);
			String ServerName = obj.getString("ServerName");
			System.out.println(ServerName);
		}
		System.out.println(JSONObject.parse(bodystr));
	}
//	public static String getGameDataTest(){
//		String url = "http://open.dailiantong.com/API/OpenService.ashx";
//		String AppId = "880a09c63a17";
//		String AppSecret = "706292907a364021ad932c36519d5229";
//		String AppVersion = "1.0";
////		String PayPassword = "qq123456";
////		String UserId = "USR201509282247";
//		String Action = "GameZoneServer";
//		String TimeStamp = String.valueOf(System.currentTimeMillis());
//
//		String Sign = DigestUtils.md5Hex(Action+AppId+TimeStamp+AppVersion+AppSecret);
//
//		return HttpUtil.get(url+"?Action=GameZoneServer&AppId=880a09c63a17&TimeStamp="+TimeStamp+"&Sign="+Sign);
//	}
}
