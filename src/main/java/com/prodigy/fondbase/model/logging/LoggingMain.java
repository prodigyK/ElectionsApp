package com.prodigy.fondbase.model.logging;


import com.prodigy.fondbase.model.AbstractBaseEntity;
import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.model.security.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bf_log_main")
public class LoggingMain extends AbstractBaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "changed")
    private Date timeChange = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "log_type_id")
    private LoggingType type;

    @OneToMany(mappedBy = "main", fetch = FetchType.EAGER)
    private List<LoggingChanges> changes = new ArrayList<>();

    public LoggingMain() {
    }

    public Date getTimeChange() {
        return timeChange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public LoggingType getType() {
        return type;
    }

    public void setType(LoggingType type) {
        this.type = type;
    }

    public List<LoggingChanges> getChanges() {
        return changes;
    }

    public void setChanges(List<LoggingChanges> changes) {
        this.changes = changes;
    }
}
