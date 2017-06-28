package com.jin.algorithm.lecture_01;

import java.nio.CharBuffer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wu.jinqing
 * @date 2017年06月28日
 */
public class DFA {
    public static ConcurrentHashMap<Character, Integer> words;
    public static Set<String> dataSource;

    public static Set<String> dataSource()
    {
        return dataSource;
    }

    public static  void setDataSource(Set<String> dataSource)
    {
        DFA.dataSource = dataSource;
    }

    public static ConcurrentHashMap<Character, Integer> init()
    {
        Set<String> datas = dataSource();
        ConcurrentHashMap<Character, Integer> map = new ConcurrentHashMap<>();

        if(datas != null)
        {
            datas.forEach(d -> {
                int len = d.length();
                int i = 0;
                for(char c : d.toCharArray())
                {
                    char k = c;
                    int v = 0;

                    if(i++ == len -1)
                        v = 1;

                    if(map.containsKey(k))
                    {
                        int oldVal = map.get(k);

                        if(v == 1 && oldVal != 1)
                            map.put(k, v);
                    }else
                    {
                        map.put(k, v);
                    }
                }
            });
        }
        words = map;
        return map;
    }

    public static void filter(String s)
    {
        if(s != null)
        {
            int start = 0;
            int end = 0;
            StringBuilder buf = new StringBuilder();

            int idx = 0;
            for(char c : s.toCharArray())
            {
                if(words.containsKey(c))
                {
                    if(start < 0)
                    {
                        start = idx;
                    }
                    end = idx;
                    buf.append(c);
                    Integer k = words.get(c);

                    if(k == 1)
                    {
                        System.out.println(buf.toString());
                    }
                }else
                {
                    start = -1;
                    end = -1;
                    buf = new StringBuilder();
                }
            }
        }
    }
}
