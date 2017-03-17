package com.cxstock.biz.print;

import java.util.HashMap;
import java.util.List;

import com.cxstock.pojo.Tbtemplate;

public interface PrintBiz {

	public List<HashMap> getInfo(String sql);

	public String getSingInfo(String table);

	public void saveInfo(Tbtemplate t);

}
