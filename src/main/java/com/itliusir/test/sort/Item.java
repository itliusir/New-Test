package com.itliusir.test.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * test item
 *
 * @author liugang
 * @since 2019/3/26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private int id;

    private int count;

    private String type;
}
