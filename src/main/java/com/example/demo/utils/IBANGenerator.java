package com.example.demo.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Component
public class IBANGenerator {


    public static String generateIBAN() {
        try {
            String RandomNo = (new Date().getTime()) % 100000000000L + "";
            return "NL" + RandomNo.substring(0, 2) + "INHO0" + RandomNo.substring(2, 11);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}