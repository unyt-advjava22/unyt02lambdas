package com.eltonb.fp.app.manually.consumers;

import com.eltonb.fp.app.Utils;
import com.eltonb.fp.model.Student;

public class StudentNotifSender implements StudentProcessor {
    @Override
    public void process(Student s) {
        Utils.sendNotification(s);
    }
}
