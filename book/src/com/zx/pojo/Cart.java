package com.zx.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
/*1 这个才用的是把购物车数据存储在session域中
第二种方法 ：把购物车信息 使用Cookie+Redis缓存和数据库，这是最流行的吧
* */
public class Cart {
//    private Integer totalCount;
//    private Integer totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<>();


    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CartItem> itemEntry:items.entrySet())
             {
            totalCount += itemEntry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> itemEntry:items.entrySet()){
            totalPrice = totalPrice.add(itemEntry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    /**
* @Description：[用一句话描述方法] 添加商品项
* @Param
* @return
*/
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if (item==null){
            items.put(cartItem.getId(),cartItem);
        }
        else{
            //设置商品总数和总价
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }


    }

    /**
    * @Description：[用一句话描述方法】  删除商品项
    * @Param
    * @return
    */
    public void deleteItem(int id){
        items.remove(id);

    }

    /**
    * @Description：[用一句话描述方法] 清空购物车
    * @Param
    * @return
    */
    public void clear(){
        items.clear();
    }

    /**
    * @Description：[用一句话描述方法] 修改商品数量
    * @Param
    * @return
    */
    public void updateCount(int id,int count){
        CartItem item = items.get(id);
        if (item != null){
            item.setCount(count);
            System.out.println(item.getCount());
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }


}


