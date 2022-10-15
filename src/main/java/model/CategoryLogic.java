package model;

import java.util.ArrayList;
import java.util.List;

import beans_entity.Category;
import beans_entity.Product;
import check.CategoryIdChecker;
import check.CategoryNameChecker;
import check.IChecker;
import dao.CategoryDAO;
import dao.ProductDAO;



public class CategoryLogic {
	
	public List<Category> getDiffCategory(){
		
		CategoryDAO cda=new CategoryDAO();
		ProductDAO pda=new ProductDAO();

		List<Category> cLi=new ArrayList<>();
		List<Product> pLi=new ArrayList<>();		
		cLi=cda.collectCategory();
		pLi=pda.collectProduct();
		
		List<Category> reCa=new ArrayList<>();
		
		boolean fl=false;
		for(Category ca: cLi) {
			fl=false;
			for(Product pr: pLi) {
				if(ca.getCategoryId().equals(pr.getCategoryId())) {
					fl=true;
					break;
				}
			}
			if(!fl) {
				reCa.add(ca);
			}
		}
		return reCa;
		
		
		/*
		ArrayList<String> ca=new ArrayList<>();
		ArrayList<String> pr=new ArrayList<>();
		
		for(Category q: cLi) {
			ca.add(q.getCategoryId());
		}
		for(Product q: pLi) {
			pr.add(q.getCategoryId());
		}
		ca.removeAll(pr);
		return ca;
		*/

	}
	
	public String createCategoryLogic(Category category) {
		String msg="";
		
		IChecker ck=new CategoryIdChecker();
		if(!ck.check(category.getCategoryId())){
			msg=ck.getErrMsg();
		}
		ck=new CategoryNameChecker();
		if(!ck.check(category.getCategoryName())){
			msg+=ck.getErrMsg();
		}
		
		if(msg.length()!=0) {
			return msg;
		}
		CategoryDAO da=new CategoryDAO();
		msg=da.isExistCategory(category);
		
		
		if(msg.length()!=0) {
			return msg;
		}
		
		if(!da.createCategory(category)){
			msg="登録に失敗しました<br>";
		}
		
		return "登録しました";
		
	}
	
	public List<Category> collectCategoryLogic(){
		CategoryDAO da=new CategoryDAO();
		return da.collectCategory();
		
	}
	
	public List<Category> collectSortedCategoryLogic(String sort, String type){
		sort= sort==null? "category_id":sort;
		type= type==null? "asc":type;
		
		CategoryDAO da=new CategoryDAO();
		return da.collectSortedCategory(sort, type);
	}
	
	public String deleteCategoryLogic(Category category) {
		CategoryDAO da=new CategoryDAO();
		if(!da.deleteCategory(category)) {
			return "削除失敗<br>";
		}
		return "削除成功しました<br>";
	}
	
	public String updateCategoryLogic(Category category) {
		String msg="";
		IChecker ck=new CategoryNameChecker();
		
		if(!ck.check(category.getCategoryName())) {
			return msg=ck.getErrMsg();
		}
		
		CategoryDAO da=new CategoryDAO();
		if(!da.updateCategory(category)) {
			return "編集に失敗しました";
		}
		
		return "編集しました<br>";
	}

}
