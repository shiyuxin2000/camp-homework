package com.example.chapter3.homework.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("让人忘记原唱的歌手", "524.6w"));
        result.add(new TestData("钢q小霸王在线抓飞行嘉宾", "823.4w"));
        result.add(new TestData("说车的小宇偶遇赵雅芝儿子", "809.2w"));
        result.add(new TestData("投身乡村教育的燃灯者", "333.6w"));
        result.add(new TestData("暑期嘉年华", "285.6w"));
        result.add(new TestData("2020年三伏天有40天", "183.2w"));
        result.add(new TestData("会跟游客合照的老虎", "139.4w"));
        result.add(new TestData("苏州暴雨", "75.6w"));
        result.add(new TestData("6月全国菜价上涨", "55w"));
        result.add(new TestData("猫的第六感有多强", "43w"));
        result.add(new TestData("IU真好看", "22.2w"));
        return result;
    }

}
