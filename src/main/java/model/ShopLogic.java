package model;

import java.util.ArrayList;
import java.util.List;

import beans_entity.Product;
import dao.ShopDAO;

public class ShopLogic {
	public List<Product> collectProductByProductIdLogic(String[] str){
		ShopDAO da=new ShopDAO();
		List<Product> reLi=new ArrayList<>();
		return reLi=da.collectProductByProductId(str);
	}

}
