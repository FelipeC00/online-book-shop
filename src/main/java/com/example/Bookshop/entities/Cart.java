package com.example.Bookshop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "tb_cart")
@Entity
public class Cart implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "cart")
  private User user;
  
  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CartItem> items = new ArrayList<>();

  public BigDecimal getTotalPrice(){
    BigDecimal totalPrice = new BigDecimal(0);
    if(items!= null){
      for(CartItem item: items){
        totalPrice= totalPrice.add(item.getTotalPrice());
      }
      return totalPrice;
    }else{
      return BigDecimal.ZERO;
    }
  }

  public void addItem(CartItem item){
    if(item !=null){
      items.add(item);
      item.setCart(this);
    }else{
      throw new IllegalArgumentException("The item cannot be null");
    }
   
  }

  public void removeItem(CartItem item){
    if(items.contains(item)){
      items.remove(item);
      item.setCart(null);
    }else{
      throw new IllegalArgumentException("The cart does not contain the item: " + item);
    }
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Cart other = (Cart) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  
}
