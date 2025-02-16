package org.example.pojos;

import org.example.utils.JsonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class Item
{
    public static final String json="src/main/resources/testdata/Items.json";
    private String name;
    private String desc;
    private Float price;
    boolean selected ;

    public Item(String name, String desc, Float price)
    {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
    public Item(WebElement item)
    {
        By itemNameBy = By.className("inventory_item_name");
        this.name = item.findElement(itemNameBy).getText();
        By itemDescBy = By.className("inventory_item_desc");
        this.desc = item.findElement(itemDescBy).getText();
        By itemPriceBy = By.className("inventory_item_price");
        this.price = Float.parseFloat(item.findElement(itemPriceBy).getText().replaceFirst("\\$", ""));
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Float getPrice()
    {
        return price;
    }

    public void setPrice(Float price)
    {
        this.price = price;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return selected == item.selected && Objects.equals(name, item.name) && Objects.equals(desc, item.desc) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, desc, price, selected);
    }

    public void savetoJson(){
        JsonUtils.writeObjectToJsonFile(json,this);
    }
    public static Item getObjectFromJson(){
       return JsonUtils.readJsonFromFile(json,Item.class);
    }
}
