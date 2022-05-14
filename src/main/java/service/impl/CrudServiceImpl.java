package service.impl;

import callable.BatchSaveCallable;
import config.ExecutorConfig;
import entity.Student;
import repository.CrudRepository;
import service.CrudService;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * crud业务层实现
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2022/5/14 10:52
 */
public class CrudServiceImpl implements CrudService {


    private final CrudRepository<Student, String> crudRepository;

    /**
     * 一批的数量
     */
    private final Integer batchNumber = 100;

    public CrudServiceImpl(CrudRepository<Student, String> crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public void batchImport(List<Student> list) {
        batchSaveAsync(list);
    }

    /**
     * 同步保存
     *
     * @param list
     */
    private void batchSaveSync(List<Student> list) {
        int slice = slice(list.size(), batchNumber);
        for (int i = 0; i < slice; i++) {
            List<Student> tempList;
            if (batchNumber > list.size()) {
                //前闭后开
                tempList = list.subList(0, list.size());
            } else {
                tempList = list.subList(0, batchNumber);
            }
            crudRepository.saveAll(tempList);
            //因为SubList是指向的地址，如果将该区域清掉，原List也会受到影响，所以就可以继续使用subList从0开始
            tempList.clear();
        }
    }

    /**
     * 异步通过线程池保存
     *
     * @param list
     */
    private void batchSaveAsync(List<Student> list) {
        ExecutorService executorService = new ExecutorConfig().executorService();
        int slice = slice(list.size(), batchNumber);
        for (int i = 0; i < slice; i++) {
            List<Student> tempList;
            if (batchNumber > list.size()) {
                //前闭后开
                tempList = list.subList(0, list.size());
            } else {
                tempList = list.subList(0, batchNumber);
            }
            BatchSaveCallable callable = new BatchSaveCallable(tempList);
            executorService.submit(callable);
            //因为SubList是指向的地址，如果将该区域清掉，原List也会受到影响，所以就可以继续使用subList从0开始
            tempList.clear();
        }
    }


    /**
     * 分组
     * [java分页算法 - SegmentFault 思否](https://segmentfault.com/a/1190000018350964)
     *
     * @param size
     * @param batchNumber
     * @return
     */
    private Integer slice(int size, int batchNumber) {
        return (size - 1) / batchNumber + 1;
    }
}
