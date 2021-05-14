package com.wis.template.infrastructure.persistent.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 顶级 Service
 *
 * @param <T>
 * @author wzengheng
 * @since 2021-05-07
 */
public interface IService<T> {

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     * @return
     */
    boolean save(T entity);

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveBatch(List<T> entityList) {
        return saveBatch(entityList, 1000);
    }

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize  插入批次数量
     * @return
     */
    boolean saveBatch(List<T> entityList, int batchSize);

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean saveOrUpdateBatch(List<T> entityList) {
        return saveOrUpdateBatch(entityList, 1000);
    }

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize  每次的数量
     * @return
     */
    boolean saveOrUpdateBatch(List<T> entityList, int batchSize);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     * @return
     */
    boolean removeById(Serializable id);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     * @return
     */
    boolean removeByMap(Map<String, Object> columnMap);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     * @return
     */
    boolean removeByIds(List<? extends Serializable> idList);

    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     * @return
     */
    boolean updateById(T entity);

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    default boolean updateBatchById(List<T> entityList) {
        return updateBatchById(entityList, 1000);
    }


    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @param batchSize  更新批次数量
     * @return
     */
    boolean updateBatchById(List<T> entityList, int batchSize);

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     * @return
     */
    boolean saveOrUpdate(T entity);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     * @return
     */
    T getById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     * @return
     */
    List<T> listByIds(List<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     * @return
     */
    List<T> listByMap(Map<String, Object> columnMap);

    /**
     * 查询总记录数
     *
     * @return
     */
    int count();


    /**
     * 查询所有
     *
     * @return
     */
    List<T> list();

    /**
     * 按排序查询所有
     *
     * @param isAsc   是否递增
     * @param orderBy 排序字段
     * @return
     */
    List<T> list(boolean isAsc, String... orderBy);

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @return
     */
    IPage<T> page(IPage<T> page);

    /**
     * 条件翻页查询，按等于查询（根据 columnMap 条件）
     *
     * @param page      翻页对象
     * @param columnMap 条件
     * @return
     */
    IPage<T> page(IPage<T> page, Map<String, Object> columnMap);

    /**
     * 条件翻页查询，按等于模糊查询（根据 columnMap 条件）
     *
     * @param page      翻页对象
     * @param columnMap 条件
     * @return
     */
    IPage<T> pageLike(IPage<T> page, Map<String, Object> columnMap);

    /**
     * 查询所有列表
     *
     * @return
     */
    List<Map<String, Object>> listMaps();

    /**
     * 按排序查询所有
     *
     * @param isAsc   是否递增
     * @param orderBy 排序字段
     * @return
     */
    List<Map<String, Object>> listMaps(boolean isAsc, String... orderBy);

    /**
     * 查询全部记录
     *
     * @return
     */
    default List<Object> listObjs() {
        return listObjs(Function.identity());
    }

    /**
     * 查询全部记录
     *
     * @param mapper 转换函数
     * @param <V>
     * @return
     */
    <V> List<V> listObjs(Function<? super Object, V> mapper);

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     * @return
     */
    IPage<Map<String, Object>> pageMaps(IPage<T> page);


}

