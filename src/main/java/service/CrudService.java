package service;

import entity.Student;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * crud业务层
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2022/5/14 10:44
 */
public interface CrudService {

    /**
     * 批量导入学生信息
     *
     * @param list
     */
    void batchImport(@NotNull List<Student> list);
}
