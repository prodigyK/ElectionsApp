package com.prodigy.fondbase.model.logging;


import com.prodigy.fondbase.model.AbstractBaseEntity;
import com.prodigy.fondbase.model.Subscriber;
import com.prodigy.fondbase.model.security.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bf_log_main")
public class LoggingMain extends AbstractBaseEntity {

    @CreationTimestamp
    @Column(name = "changed")
    private LocalDateTime timeChange;

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

    @Column(name = "is_new")
    private Boolean isNew = false;

    public LoggingMain() {
    }

    public LocalDateTime getTimeChange() {
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

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
