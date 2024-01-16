package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.Util.MapperUtil;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.dao.TodoDAO;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {

    INSTANCE;
    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        dao = new TodoDAO();
        modelMapper = MapperUtil.Instance.get();
    }

    //동록
    public void register(TodoDTO todoDTO)throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        //System.out.println("todoVO:" + todoVO);
        log.info("todoVO:" + todoVO);
        dao.insert(todoVO);
    }

    public List<TodoDTO> listAll()throws Exception { //listAll()은 TodoDAO에서 가져온 TodoVO 목록을 TodoDTO로 변환
        List<TodoVO> voList = dao.selectAll();
        log.info("voList.............");

        log.info(voList);

        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))//ModelMapper와 Java Stream의 map() 이용
                .collect(Collectors.toList());
        return dtoList;
    }

    public TodoDTO get(Long tno)throws Exception{
        log.info("tno: " + tno);
        TodoVO todoVO = dao.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
        return todoDTO;
    }

    public void remove(Long tno)throws Exception {
        log.info("tno: " + tno);
        dao.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO)throws Exception{
        log.info("todoDTO: " + todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        dao.updateOne(todoVO);
    }
}
