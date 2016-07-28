package com.example.flytv.demofuuu.huidiao;

import android.app.usage.UsageEvents;

/**
 * Created by Flytv on 2016/6/28.
 * 被监听的类，事件源
 */
public class Student {

    public  String name;
    //监听事件源的接口对象
    private StudentListener studentListener;

    //类中被监听的方法
    public void study() {
        if (studentListener != null) {
            Event e = new Event();
            e.setStudent(this);
            studentListener.doStudy(e);
        }
    }

    public void setName(String name) {
        this.name = name;
        if (studentListener != null) {
            Event e = new Event();
            e.setStudent(this);
            studentListener.doChangeName(e);
        }
    }

    interface StudentListener {
        public void doStudy(Event e);

        public void doChangeName(Event e);
    }

    class Event {
        private Student student;

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }
}
