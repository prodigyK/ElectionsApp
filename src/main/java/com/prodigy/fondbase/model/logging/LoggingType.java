package com.prodigy.fondbase.model.logging;

import com.prodigy.fondbase.model.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bf_log_type")
public class LoggingType extends AbstractNamedEntity {


    public LoggingType() {
    }

    public LoggingType(Integer id, String name) {
        super(id, name);
    }

}
