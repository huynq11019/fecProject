package com.fec.demo.DTO;

import java.util.List;

import lombok.Data;
@Data
public class ParentOutput<T> {
private int page;
private int totalPage;
private List<T> listResult;
private boolean frist;
private boolean last;
}
