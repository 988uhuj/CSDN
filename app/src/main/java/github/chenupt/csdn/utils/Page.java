package github.chenupt.csdn.utils;

import org.androidannotations.annotations.EBean;

@EBean
public class Page {
	public int page = 1;
	public int contentMutiPages;
	public boolean contentFirstPage = true;
	
	public void resetPage(){
		page = 1;
	}
	public void setPage(int num){
		page = num;
	}
	public String getCurrentPage(){
		return page + "";
	}
	public void increasePage(){
		page ++;
	}
}
