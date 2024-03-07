/**
 * 24년 3월 7일
 * 오후 4시 30분
 * todo수업
 *
 */
package com.ssg.jdbcex.Service;

import com.ssg.jdbcex.dao.TodoDAO;
import com.ssg.jdbcex.domain.TodoVO;
import com.ssg.jdbcex.dto.TodoDTO;
import com.ssg.jdbcex.util.MapperUtil;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TodoService는 비즈니스 계층에서 DTO와 VO를 함께 사용하는 역할을 수행한다.
 * ModelMapper와 TodoDAO를 이용하여 구성되어 있다.
 */
public enum TodoService {

    /**
     * TodoService의 싱글톤 인스턴스
     */
    INSTANCE;

    // TodoDAO 및 ModelMapper 객체 선언
    private TodoDAO todoDAO;
    private ModelMapper modelMapper;

    // TodoService 생성자에서 TodoDAO와 ModelMapper 초기화
    TodoService() {
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.getModelMapper();
    }

    /**
     * register() 메서드는 TodoDTO를 파라미터로 받아서 TodoVO로 변환하여 등록한다.
     *
     * @param todoDTO TodoDTO 객체 (등록할 할일 정보를 담은 데이터 전송 객체)
     * @return 등록된 할일의 결과를 나타내는 정수값 (성공 시 1, 실패 시 0 등)
     * @throws Exception 등록 과정에서 예외가 발생할 경우 전파
     */
    public int register(TodoDTO todoDTO) throws Exception {
        TodoVO vo = modelMapper.map(todoDTO, TodoVO.class); // TodoDTO를 TodoVO로 변환한다.
        System.out.println("todoVo: " + vo);
        int result = todoDAO.insert(vo); // TodoDAO에 변환된 TodoVO를 전달하여 등록한다.
        return result;
    }

    /**
     * list() 메서드는 모든 할일 정보를 조회하여 TodoDTO 리스트로 반환한다.
     *
     * @return 모든 할일 정보를 담은 TodoDTO 리스트
     * @throws Exception 조회 과정에서 예외가 발생할 경우 전파
     */
    public List<TodoDTO> list() throws Exception {
        List<TodoVO> voList = todoDAO.selectAll(); // TodoDAO를 통해 모든 할일 정보를 조회하여 TodoVO 리스트를 얻어온다.

        // Stream을 활용하여 TodoVO 리스트를 TodoDTO 리스트로 변환한다.
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class)) // TodoVO를 TodoDTO로 변환한다.
                .collect(Collectors.toList()); // 변환된 TodoDTO를 리스트로 수집한다.

        // 변환된 TodoDTO 리스트를 반환한다.
        return dtoList;
    }

    /**
     * read() 메서드는 특정 할일 번호(tno)에 해당하는 할일 정보를 조회하여 TodoDTO로 반환한다.
     *
     * @param tno 조회할 할일 번호
     * @return 특정 할일 번호에 해당하는 할일 정보를 담은 TodoDTO 객체
     * @throws Exception 조회 과정에서 예외가 발생할 경우 전파
     */
    public TodoDTO read(Long tno) throws Exception {
        TodoVO vo = todoDAO.selectOne(tno);
        TodoDTO dto = modelMapper.map(vo, TodoDTO.class);
        return dto;
    }

    /**
     * update() 메서드는 TodoDTO를 받아서 해당 정보로 할일을 수정한다.
     *
     * @param dto 수정할 할일 정보를 담은 TodoDTO 객체
     * @throws Exception 수정 과정에서 예외가 발생할 경우 전파
     */
    public void update(TodoDTO dto) throws Exception {
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        todoDAO.updateOne(vo);
    }

    /**
     * delete() 메서드는 특정 할일 번호(tno)에 해당하는 할일을 삭제한다.
     *
     * @param tno 삭제할 할일 번호
     * @return 삭제 결과를 나타내는 정수값 (성공 시 1, 실패 시 0 등)
     * @throws Exception 삭제 과정에서 예외가 발생할 경우 전파
     */
    public int delete(Long tno) throws Exception {
        int result = todoDAO.deleteOne(tno);
        return result;
    }
}
