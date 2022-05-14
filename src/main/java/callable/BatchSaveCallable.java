package callable;

import entity.Student;
import repository.CrudRepository;
import repository.impl.DefaultCrudRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * callable
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2022/5/14 14:01
 */
public class BatchSaveCallable implements Callable<Integer> {

    private List<Student> list = new LinkedList<>();

    public BatchSaveCallable(List<Student> list) {
        this.list.addAll(list);
    }

    @Override
    public Integer call() throws Exception {
        Iterator<Student> iterator = list.iterator();
        List<Student> saveList = new ArrayList<>(list.size());
        while (iterator.hasNext()) {
            Student student = iterator.next();
            saveList.add(student);
            iterator.remove();
        }
        CrudRepository crudRepository = new DefaultCrudRepository();
        crudRepository.saveAll(saveList);
        System.out.printf("%s线程执行保存%s条\n", Thread.currentThread().getName(), saveList.size());
        return saveList.size();
    }
}
