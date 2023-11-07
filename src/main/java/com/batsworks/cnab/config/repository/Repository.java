package com.batsworks.cnab.config.repository;

import com.batsworks.cnab.config.database.DataBaseDriver;
import com.batsworks.cnab.model.CNAB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository<T> extends DataBaseDriver {

    private static Logger log = LoggerFactory.getLogger(Repository.class);

    private static PreparedStatement preparedStatement;
    private static ResultSetMetaData metaData;
    private final Class<T> classParam;

    public Repository(Class<T> classParam) {
        this.classParam = classParam;
    }

    public List<T> findAll() {
        try {
            List<T> list = new ArrayList<>();
            String query = "SELECT * FROM %s".formatted(getEntity(classParam));
            log.info(query);

            preparedStatement = connection().prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                metaData = resultSet.getMetaData();
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    Long id = resultSet.getLong("id");
                    Time localDateTime = resultSet.getTime("dataCadastro");

                    list.add((T) new CNAB(id, nome, null));
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    protected String getEntity(Class<?> aClass) {
        return classParam.getTypeName().replace(aClass.getPackageName().concat("."), "");
    }

    protected void metadaToList() throws Exception {
        Field[] fields = classParam.getDeclaredFields();
        for (var field : fields) {
            System.out.println(field.getName());
        }
    }

}
