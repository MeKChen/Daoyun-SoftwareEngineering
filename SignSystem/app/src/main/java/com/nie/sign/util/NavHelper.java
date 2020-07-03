package com.nie.sign.util;

import android.content.Context;
import android.util.SparseArray;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavHelper<T> {
    private final SparseArray<Tab<T>> tabs = new SparseArray<Tab<T>>();

    private final Context mContext;
    private final int containerId;
    private final FragmentManager fragmentManager;
    private final OnTabChangeListener<T> mListener;

    private Tab<T> currentTab;

    public NavHelper(Context mContext, int containerId, FragmentManager fragmentManager, OnTabChangeListener<T> mListener) {
        this.mContext = mContext;
        this.containerId = containerId;
        this.fragmentManager = fragmentManager;
        this.mListener = mListener;
    }

    /**
     * 添加tab
     *
     * @param menuId
     * @param tab
     * @return
     */
    public NavHelper<T> add(int menuId, Tab<T> tab) {
        tabs.put(menuId, tab);
        return this;
    }

    /**
     * 获取当前Tab
     *
     * @return
     */
    public Tab<T> getCurrentTab() {
        return currentTab;
    }

    /**
     * 执行点击菜单的操作
     *
     * @param menuId
     * @return
     */
    public boolean performClickMenu(int menuId) {
        Tab<T> tab = tabs.get(menuId);
        if (tab != null) {
            doSelect(tab);
            return true;
        }

        return false;
    }

    /**
     * 进行tab的选择操作
     *
     * @param tab
     */
    private void doSelect(Tab<T> tab) {
        Tab<T> oldTab = null;
        if (currentTab != null) {
            oldTab = currentTab;
            if (oldTab == tab) {
                //如果当前tab点击的tab，不做任何操作或者刷新
                notifyReselect(tab);
                return;
            }
        }
        currentTab = tab;
        doTabChanged(currentTab, oldTab);
    }

    /**
     * Tab切换方法
     *
     * @param newTab
     * @param oldTab
     */
    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (oldTab != null) {
            if (oldTab.fragment != null) {
                //从界面移除，但在Fragment的缓存中
                ft.hide(oldTab.fragment);
            }
        }

        if (newTab != null) {
            if (newTab.fragment == null) {
                //首次新建并缓存
                Fragment fragment = Fragment.instantiate(mContext, newTab.clx.getName(), null);
                newTab.fragment = fragment;
                ft.add(containerId, fragment, newTab.clx.getName());
            } else {
                ft.show(newTab.fragment);
            }
        }
        ft.commit();
        notifySelect(newTab, oldTab);
    }

    /**
     * 选择通知回调
     *
     * @param newTab
     * @param oldTab
     */
    private void notifySelect(Tab<T> newTab, Tab<T> oldTab) {
        if (mListener != null) {
            mListener.onTabChange(newTab, oldTab);
        }

    }

    private void notifyReselect(Tab<T> tab) {
        //TODO 刷新
    }

    public static class Tab<T> {
        public Tab(Class<?> clx, T extra) {
            this.clx = clx;
            this.extra = extra;
        }

        Class<?> clx;
        public T extra;
        //内部缓存对应的Fragment
        private Fragment fragment;
    }

    /**
     * 事件处理回调接口
     *
     * @param <T>
     */
    public interface OnTabChangeListener<T> {
        void onTabChange(Tab<T> newTab, Tab<T> oldTab);
    }
}