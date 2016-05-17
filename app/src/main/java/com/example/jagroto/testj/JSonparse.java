package com.example.jagroto.testj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F on 5/16/2016.
 */
public class JSonparse {
    protected static List<Food>foods(String content){
        try {
            JSONArray array =new JSONArray(content);
            List<Food>foodList=new ArrayList<>();
            for (int i =0; i <array.length(); i++){
                JSONObject jsonObject =array.getJSONObject(i);
                Food food=new Food();
                food.setName(jsonObject.getString("name"));
                food.setPrice(jsonObject.getString("price"));
                food.setDescription(jsonObject.getString("description"));
                foodList.add(food);
            }
            return foodList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
