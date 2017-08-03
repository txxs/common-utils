package com.txxs.common.utils.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:jianghuimin
 * @Date: 2017/8/3
 * @Time:10:28
 */
public class NameValueList<N, V> {
    private List<NameValuePair<N, V>> nvList = new ArrayList();

    public NameValueList() {
    }

    public NameValueList<N, V> add(N name, V value) {
        this.nvList.add(new NameValueList.NameValuePair(name, value));
        return this;
    }

    public List<NameValueList.NameValuePair<N, V>> getList() {
        return this.nvList;
    }

    public static class NameValuePair<N, V> {
        private N name;
        private V value;

        public NameValuePair(N name, V value) {
            this.name = name;
            this.value = value;
        }

        public N getName() {
            return this.name;
        }

        public V getValue() {
            return this.value;
        }
    }
}

