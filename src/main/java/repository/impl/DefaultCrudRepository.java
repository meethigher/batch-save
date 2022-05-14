package repository.impl;

import entity.Student;
import repository.CrudRepository;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 学生Crud的默认实现
 *
 * @author chenchuancheng github.com/meethigher
 * @since 2022/5/14 10:05
 */
public class DefaultCrudRepository implements CrudRepository<Student, String> {

    private Map<String, Student> storeMap = new HashMap<>();

    @Override
    public void saveAll(List<Student> list) {
        list.forEach(x -> {
            storeMap.put(x.getId(), x);
        });
        System.out.printf("保存%s条数据\n", list.size());
    }

    @Override
    public void save(Student student) {
        storeMap.put(student.getId(), student);
        System.out.printf("保存1条数据：%s\n", student);
    }

    @Override
    public void delete(String s) {
        Student remove = storeMap.remove(s);
        System.out.printf("删除1条数据：%s\n", remove);
    }

    @Override
    public void deleteAll(List<String> list) {
        list.forEach(x -> {
            storeMap.remove(x);
        });
        System.out.printf("删除%s条数据\n", list.size());
    }

    @Override
    public List<Student> selectAll() {
        return (List<Student>) storeMap.values();
    }

    @Override
    public Student select(String id) {
        return storeMap.get(id);
    }
}
