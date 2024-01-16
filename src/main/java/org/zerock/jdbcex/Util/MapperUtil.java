package org.zerock.jdbcex.Util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.config.Configuration;
import sun.jvm.hotspot.oops.Instance;

public enum MapperUtil {

    Instance;
    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies. STRICT);
    }

    public ModelMapper get(){
        return modelMapper;
    }
}
