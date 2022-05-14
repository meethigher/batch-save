package repository;

import javax.validation.constraints.*;
import java.util.List;

/**
 * crud实现
 *
 * @param <T> 实体
 * @param <S> 主键类型
 * @author chenchuancheng github.com/meethigher
 * @since 2022/5/14 09:53
 */
public interface CrudRepository<T, S> {

    /**
     * 添加/更新 所有
     *
     * @param list
     */
    void saveAll(@NotNull List<T> list);

    /**
     * 添加更新 一条
     *
     * @param t
     */
    void save(@NotNull T t);

    /**
     * 删除 一条
     *
     * @param s
     */
    void delete(@NotNull S s);

    /**
     * 批量删除
     *
     * @param list
     */
    void deleteAll(@NotNull List<S> list);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 查询一条
     *
     * @param id
     * @return
     */
    T select(@NotNull S id);
}
