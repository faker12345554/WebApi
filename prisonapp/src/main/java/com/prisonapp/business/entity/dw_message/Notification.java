package com.prisonapp.business.entity.dw_message;

    public enum Notification {
        TASK("任务提醒", 1),
        CROSS_THE_BORDER("越界报警", 2),
        CALL("通话消息", 3),
        GO_OUT("外出提醒", 4),
        MESSAGING("传讯提醒",5),
        BATTERY("电量提醒",6);
        // 成员变量
        private String name;
        private int index;
        // 构造方法
        private Notification(String name, int index) {
            this.name = name;
            this.index = index;
        }
        // 普通方法
        public static String getName(int index) {
            for (Notification c : Notification.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }
        // get set 方法
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int index) {
            this.index = index;
        }
    }

