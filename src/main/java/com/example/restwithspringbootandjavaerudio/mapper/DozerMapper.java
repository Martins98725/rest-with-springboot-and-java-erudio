package com.example.restwithspringbootandjavaerudio.mapper;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origem, Class<D> destinations){
        return  mapper.map(origem, destinations);
    }
    public static <O, D> List<D> parseListObject(List<O> origem, Class<D> destinations){
        List<D> destinationsObjects = new ArrayList<D>();
        for (O o: origem) {
            destinationsObjects.add(mapper.map(o, destinations));
        }
        return destinationsObjects;
    }

}
