package com.jin.algorithm.lecture_01;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author wu.jinqing
 * @date 2017年06月28日
 */
public class DFATest {
    public static Map<Character, Node> words = new HashMap<>();

    public static Map<Character, Node> init(Set<String> dataSource)
    {
        if(null == dataSource)
            return null;

        Iterator<String> iterator = dataSource.iterator();
        Map<Character, Node> next;

        while (iterator.hasNext())
        {
            char[] chars = iterator.next().toCharArray();

            next = words;
            int len = chars.length;

            for(int i = 0; i < len; i++)
            {
                char c = chars[i];
                Node v = next.get(c);

                if(null == v)
                {
                    v = new Node();

                    v.setEnd(false);
                    v.setChilds(new HashMap<>());

                    next.put(c, v);
                }

                if(i == len - 1)
                    v.setEnd(true);

                next = v.getChilds();
            }
        }

        return words;
    }
    private static class Node
    {
        private boolean isEnd = false;
        private Map<Character, Node> childs;

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public Map<Character, Node> getChilds() {
            return childs;
        }

        public void setChilds(Map<Character, Node> childs) {
            this.childs = childs;
        }
    }















    public static Map<String, String> sensitiveWordMap ;
    public static void main(String[] args) {
        Set<String> dataSource = new HashSet<>();

        dataSource.add("中国");
        dataSource.add("中国人民的");
        dataSource.add("虹桥");

        System.out.println(JSON.toJSONString(init(dataSource)));

    }

    private static void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }
    public static void main1(String[] args) {
        Set<String> dataSource = new HashSet<>();

        for (int i = 0; i <= 100_0000; i++)
        {
            dataSource.add("中国" + i);
        }
        dataSource.add("中国");
        dataSource.add("中国人");

        DFA dfa = new DFA();

        dfa.setDataSource(dataSource);
        dfa.init();
        dfa.filter("我中是中国人。");
    }
}
