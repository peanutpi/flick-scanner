package com.pk.flick.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface SingletonSerializable extends Serializable {

    static final Logger logger = LoggerFactory.getLogger(SingletonSerializable.class);

    void initialize();

    void serialise();

}