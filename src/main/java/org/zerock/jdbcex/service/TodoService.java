package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.Util.MapperUtil;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.dao.TodoDAO;

@Log4j2
public enum TodoService {

    INSTANCE;
    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.Instance.get();
    }

    public void register(TodoDTO todoDTO)throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        //System.out.println("todoVO:" + todoVO);
        log.info("todoVO:" + todoVO);

        dao.insert(todoVO);
    }

}
