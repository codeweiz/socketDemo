package cn.microboat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息体，需要实现 Serializable，不然报 未序列化 异常
 *
 * @author zhouwei
 */
@Data
@AllArgsConstructor
public class Message implements Serializable {

    private String content;
}
