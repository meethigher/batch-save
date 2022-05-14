import entity.Student;
import org.junit.Test;
import repository.CrudRepository;
import repository.impl.DefaultCrudRepository;
import service.CrudService;
import service.impl.CrudServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class CrudTest {

    @Test
    public void testBatchSave() {
        List<Student> list=new LinkedList<>();
        for(int i=0;i<998;i++) {
            Student student = new Student();
            student.setId(String.format("编号%d",i));
            student.setName(String.format("名称%s",i));
            list.add(student);
        }
        CrudRepository crudRepository=new DefaultCrudRepository();
        CrudService crudService = new CrudServiceImpl(crudRepository);
        crudService.batchImport(list);

        while(true) {

        }
    }


}
