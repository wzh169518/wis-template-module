package com.wis.template.infrastructure.general.common.bean;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wis.template.infrastructure.general.common.constant.ErrorConstant;
import com.wis.template.infrastructure.general.util.BeanUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author wzengheng
 *
 * @create 2021/05/07
 */
@ApiModel("返回结果")
public class RspBase implements Serializable {

    @ApiModelProperty("200表示成功，其他表示失败")
    private int code = ErrorConstant.OK;

    @ApiModelProperty("失败时的错误信息")
    private String msg;

    @ApiModelProperty("返回的数据对象")
    private Object data;

    @ApiModelProperty("返回的记录总数")
    private Long total;

    /**
     * 快速创建返回成功对象
     *
     * @return
     */
    public static RspBase success() {
        return new RspBase();
    }

    /**
     * 快速创建返回成功对象
     *
     * @param ret 返回内容
     * @return
     */
    public static RspBase success(Object ret) {
        if (ret instanceof IPage) {
            IPage page = (IPage) ret;
            RspBase rsp = new RspBase();
            rsp.setData(page.getRecords());
            rsp.setTotal(page.getTotal());
            return rsp;
        } else {
            return new RspBase().setData(ret);
        }
    }

    /**
     * 快速创建返回失败对象
     *
     * @param code 错误码
     * @param msg  错误描述
     * @return
     */
    public static RspBase fail(int code, String msg) {
        return new RspBase().setCode(code).setMsg(msg);
    }

    /**
     * 快速创建返回失败对象
     *
     * @param code 错误码
     * @param msg  错误描述
     * @param data 提示信息
     * @return
     */
    public static RspBase fail(int code, String msg, Object data) {
        return new RspBase().setCode(code).setMsg(msg).setData(data);
    }

    public RspBase() {
    }

    public RspBase(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RspBase(Object ret) {
        this.data = ret;
    }

    public int getCode() {
        return code;
    }

    public RspBase setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RspBase setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RspBase setData(Object data) {
        if (data instanceof List) {
            List<Object> temp = (List<Object>) data;
            if (null != temp && !temp.isEmpty()) {
                if (temp.get(0) instanceof Map) {
                    data = BeanUtil.lowerCaseList((List<Map<String, Object>>) data);
                }
            }
        }
        this.data = data;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
